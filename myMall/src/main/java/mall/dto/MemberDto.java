package mall.dto;



import java.util.Date;

import lombok.Data;

@Data
public class MemberDto {
	private int usridx;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String tel;
	private String createdtime;
	private String gender;
	private String userauth;
}
