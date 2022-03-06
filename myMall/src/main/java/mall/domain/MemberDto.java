package mall.domain;



import lombok.Data;
import mall.paging.CommonDTO;
import mall.security.Role;

@Data
public class MemberDto  extends CommonDTO{
	private int usridx;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String tel;
	private String createdtime;
	private String gender;
	private String userauth;
	private String result;
	private Role role;


}
