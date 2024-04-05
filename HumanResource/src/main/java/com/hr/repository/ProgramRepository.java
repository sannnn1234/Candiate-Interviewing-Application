package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hr.model.Program;


public interface ProgramRepository extends CrudRepository<Program, Integer> {

	@Query(value = "select program_code , program_description  from program_master where program_name = '#' ", nativeQuery = true)
	public List<String[]> findAllProgramIncludeSub();

	@Query(value = "select program_code , program_description  from program_master where program_print  is null ", nativeQuery = true)
	public List<String[]> findAllProgram();

	@Query(value = "select program_code , program_description  ,  program_print  FROM program_master", nativeQuery = true)
	public List<String[]> findAllProgramLink();

	@Query(value = "select program_code , program_description  , program_name  , program_order  from program_master where  program_print  =:programPrint", nativeQuery = true)
	public List<String[]> findAllProgramLinkByPrgPrnt(int programPrint);

	@Query(value = "select program_code , program_order from program_master where program_print = :programPrint order by program_code desc limit  1", nativeQuery = true)
	public List<Integer[]> findLastPrgcodeAndPrgOrder(int programPrint);
	
	@Query(value = " select p from Program p where  programCode in (select programCode from ProgramGroup where groupCode = :groupCode) order by programCode " )
	public List<Program> findAllByugrpCode(long groupCode);
	
	
	
	@Query(value = "select pm.programCode, pm.programDescription , pm.programName  , pm.programPrint , pm.programOrder, (select  CASE WHEN COUNT(*) > 0 THEN true ELSE false END  from ProgramGroup pg where pg.programCode = pm.programCode and pg.groupCode = :groupCode) from Program pm  " )
	public List<Program> findAllByugrpCodeMapAndNotMap(long groupCode);
	
	
//	fetching top menus on ui
	@Query(value = "select program_code , program_description  from program_master where  program_print  is null and program_code  in (select program_code  from program_groupmapping where group_code = :groupCode  order by program_code)", nativeQuery = true)
	public List<String[]> findAllMenuByGroupCode(long groupCode);
//	fetching top sub menus on ui
	@Query(value = "select program_code , program_description  , program_name , program_print  from program_master where  program_print  is not null and program_code  in"
			+ "(select program_code  from program_groupmapping  where group_code = :groupCode) order by program_print", nativeQuery = true)
	public List<String[]> findAllSubMenuByGroupCode(long groupCode);
	
//	fetching top all menus and sub menu on ui
	@Query(value = "select  program_code , program_description  , program_name  ,program_print  from program_master where  program_code  in (select program_code  from program_groupmapping pg  where group_code =:groupCode) order by program_print ", nativeQuery = true)
	public List<String[]> findAllAllMenuByGroupCode(long groupCode);
}
