package mall.domains.users.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mall.domain.MemberDto;
import mall.security.Role;

@Data
public class UsersEntity {


	private int usr_idx;
	private String usr_email;
	private String usr_name;
	private String usr_pw;
	private String usr_nick;
	private String usr_tel;
	private String usr_auth;
	private String usr_status;
	private String created_at;
	private String updated_at;
	

  @Builder
  public UsersEntity(String userId, String pw) {
    this.usr_email = userId;
    this.usr_pw = pw;
  }

}
