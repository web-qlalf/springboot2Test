package mall.service;

import java.util.List;
import java.util.Optional;

import mall.domain.MemberDto;
import mall.domain.RefreshToken;
import mall.security.AuthToken;

public interface MemberService {
								
	List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception;
	
	void insertMember(MemberDto memberDto) throws Exception;
	
	MemberDto selectMemberDetail(String userid) throws Exception;

	List<MemberDto> selectMemberListAgGrid() throws Exception;

	
	
}
