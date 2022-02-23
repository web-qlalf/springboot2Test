package mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mall.dto.MemberDto;
import mall.paging.Criteria;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception;
	
	void insertMember(MemberDto memberDto) throws Exception;
	
	MemberDto selectMemberDetail(String userId) throws Exception;

	int selectMemberTotalCount(Criteria cri) throws Exception;

	List<MemberDto> selectMemberListAgGrid() throws Exception;
}
