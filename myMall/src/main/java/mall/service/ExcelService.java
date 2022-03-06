package mall.service;

import java.util.List;

import mall.domain.MemberDto;

public interface ExcelService {
	List<MemberDto> selectMemberListAgGrid() throws Exception;
}
