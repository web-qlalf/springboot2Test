package mall.service;

import java.util.List;

import mall.dto.MemberDto;

public interface MemberService {

	List<MemberDto> selectMemberList() throws Exception;
	void insertMember(MemberDto memberDto) throws Exception;
}
