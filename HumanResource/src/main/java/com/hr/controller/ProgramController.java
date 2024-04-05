package com.hr.controller;


import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hr.dto.UserDto;
import com.hr.model.Groupmaster;
import com.hr.model.Program;
import com.hr.model.ProgramGroup;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.GroupmasterRepository;
import com.hr.repository.ProgramGroupRepository;
import com.hr.repository.ProgramRepository;
import com.hr.service.ProgramService;


@CrossOrigin("${port}")
@RestController()
public class ProgramController {

	
	@Autowired
	ProgramRepository pr;
	
	@Autowired
	GroupmasterRepository ugr;
	
	
	@Autowired
	ProgramGroupRepository pgr;
	
	@Autowired
	ProgramService ps;
	
	@Autowired
	EmployeeRepository er;
	
	
	//get the list of menus
	@GetMapping("/program-list")
	public ResponseEntity<Object> programList() {
		return ResponseEntity.of(Optional.of(ps.programDataList()));
	}
	
	//mapping of program master and group master
	@PostMapping("/program-map-list")
	public ResponseEntity<Object> programEdit(@RequestBody Groupmaster gp) {
		return ResponseEntity.of(Optional.of(ps.programMapList(gp)));
	}


	//get all the list of menus based on roles
	@GetMapping("/get-menus")
	public ResponseEntity<Object> getProgramMenus(Principal p) {	
		UserDto e = er.findEmployeegrpCodeByEmpId(Long.parseLong(p.getName())).get(0);
		List<Program> pl = ps.findAllProgramMapGroupCode(e.getGroupCode());
		return ResponseEntity.of(Optional.of(pl));
	}


	// Edit Program Mapping page
	@PostMapping("/program-group-link-edit")
	public ResponseEntity<Object> projectEditMap(@RequestParam("groupCode") long groupCode,
			@RequestBody List<Program> pl) {
		pgr.deleteByGroupCodeNative(groupCode);
		for (Iterator<Program> it = pl.iterator(); it.hasNext();) {
			Program p = it.next();
			ProgramGroup pg = new ProgramGroup();
			pg.setGroupCode(groupCode);
			pg.setProgramCode(p.getProgramCode());
			pgr.save(pg);
		}
		return ResponseEntity.ok().build();
	}

	// Adding program menu page
	@PostMapping("/program-menu-add")
	public String projectEditMap(HttpServletRequest request, @ModelAttribute() Program m) {
		HttpSession session = request.getSession();
		if (m.getProgramName() == null || m.getProgramDescription() == null) {
			return "redirect:/program-master";
		}
		Integer[] parr = pr.findLastPrgcodeAndPrgOrder(m.getProgramPrint()).get(0);
		m.setProgramCode((parr[0] + 1));
		m.setProgramOrder((parr[1]) + 1);
		m.setCreatedBy((String) session.getAttribute("fullName"));
		pr.save(m);
		return "redirect:/program-master";
	}

	// Access Program master page
	@GetMapping("/program-map-master")
	public ModelAndView programMapMater() {
		ModelAndView modelAndView = new ModelAndView("program-map-master");
		modelAndView.addObject("userGroupList", ugr.findAllMappedGroup());
		return modelAndView;
	}
}
