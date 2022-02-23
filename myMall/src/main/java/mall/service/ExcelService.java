package mall.service;

import java.util.List;

import mall.dto.MemberDto;

public interface ExcelService {
	List<MemberDto> selectMemberListAgGrid() throws Exception;
}
