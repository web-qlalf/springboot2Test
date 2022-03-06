package mall.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import mall.domain.MemberDto;
import mall.domain.RefreshToken;
import mall.paging.Criteria;
import mall.service.User;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception;
	
	void insertMember(MemberDto memberDto) throws Exception;
	
	MemberDto selectMemberDetail(String userId) throws Exception;

	int selectMemberTotalCount(Criteria cri) throws Exception;

	List<MemberDto> selectMemberListAgGrid() throws Exception;


}
