package mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.MemberDto;
import mall.mapper.MemberMapper;

@Service
public class ExcelServiceImpl implements ExcelService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<MemberDto> selectMemberListAgGrid() throws Exception {

		return memberMapper.selectMemberListAgGrid();
	}

}
