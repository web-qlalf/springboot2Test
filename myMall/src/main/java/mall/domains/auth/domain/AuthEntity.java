package mall.domains.auth.domain;

import lombok.Builder;
import lombok.Data;
import mall.domains.users.domain.UsersEntity;

@Data
public class AuthEntity {
	
	private int idx;
	private String usr_email;
	private String accesstoken;
	private String refreshToken;
	private UsersEntity usersEntity;

  @Builder
  public AuthEntity(String refreshToken, UsersEntity usersEntity) {
    this.refreshToken = refreshToken;
    this.usersEntity = usersEntity;
  }
  public void refreshUpdate(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
