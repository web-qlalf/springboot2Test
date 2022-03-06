package mall.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter

public class Member {

    @JsonIgnore
    private String id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String role;
    
    
	private String tel;
	private String createdtime;
	private String gender;
	private String userauth;

}