package mall.service;

import java.util.List;


import mall.dto.MemberDto;
import mall.paging.Criteria;

public interface MemberService {

	List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception;
	
	void insertMember(MemberDto memberDto) throws Exception;
	
	MemberDto selectMemberDetail(String userid) throws Exception;
}
