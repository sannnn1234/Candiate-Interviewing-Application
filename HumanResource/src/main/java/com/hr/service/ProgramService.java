package com.hr.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.model.Groupmaster;
import com.hr.model.Program;
import com.hr.repository.ProgramRepository;




@Service
public class ProgramService {

	@Autowired
	ProgramRepository pr;

	public List<String[]> findAllMenus(long groupCode) {

		List<String[]> menus = pr.findAllAllMenuByGroupCode(groupCode);


		return menus;
	}

	
	
	public List<Program> programDataList() {
		Iterable<Program> programList = (List<Program>) pr.findAll();
		List<Program> programData = new ArrayList<Program>();

		for (Iterator<Program> it = programList.iterator(); it.hasNext();) {
			Program p = it.next();
			if (p.getProgramPrint() == null) {
				for (Iterator<Program> sub = programList.iterator(); sub.hasNext();) {
					Program subP = sub.next(); // menu
					if (p.getProgramCode().equals(subP.getProgramPrint())) {
						p.getProgramLIst().add(subP); // sub
						for (Iterator<Program> ssub = programList.iterator(); ssub.hasNext();) {
							Program ssubP = ssub.next();
							if (subP.getProgramCode().equals(ssubP.getProgramPrint())) {
								subP.getProgramLIst().add(ssubP); // low-sub
							}
						}
					}
				}
				programData.add(p);
			}
		}

		return programData;
	}
	

	public List<Program> findAllProgramMapGroupCode(Long groupCode) {
		Iterable<Program> programList = pr.findAllByugrpCode(groupCode);

		List<Program> programData = new ArrayList<Program>();

		for (Iterator<Program> it = programList.iterator(); it.hasNext();) {
			Program p = it.next();
			if (p.getProgramPrint() == null) {
				for (Iterator<Program> sub = programList.iterator(); sub.hasNext();) {
					Program subP = sub.next(); // menu
					if (p.getProgramCode().equals(subP.getProgramPrint())) {
						p.getProgramLIst().add(subP); // sub
						for (Iterator<Program> ssub = programList.iterator(); ssub.hasNext();) {
							Program ssubP = ssub.next();
							if (subP.getProgramCode().equals(ssubP.getProgramPrint())) {
								subP.getProgramLIst().add(ssubP); // low-sub
							}
						}
					}
				}
				programData.add(p);
			}
		}
		return programData;
		
	}
	
	public List<Program> programMapList(Groupmaster gm) {
		List<Program> programList = pr.findAllByugrpCodeMapAndNotMap(gm.getGroupCode());
		List<Program> programData = new ArrayList<Program>();
		for (Iterator<Program> it = programList.iterator(); it.hasNext();) {
			Program p = it.next();
			if (p.getProgramPrint() == null) {
				for (Iterator<Program> sub = programList.iterator(); sub.hasNext();) {
					Program subP = sub.next(); // menu
					if (p.getProgramCode().equals(subP.getProgramPrint())) {
						p.getProgramLIst().add(subP); // sub
						for (Iterator<Program> ssub = programList.iterator(); ssub.hasNext();) {
							Program ssubP = ssub.next();
							if (subP.getProgramCode().equals(ssubP.getProgramPrint())) {
								subP.getProgramLIst().add(ssubP); // low-sub
							}
						}
					}
				}
				programData.add(p);
			}
		}
		return programData;
	}
}
