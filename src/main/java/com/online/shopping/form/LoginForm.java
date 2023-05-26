package com.online.shopping.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	
	@Email(message = "please enter correct e-mail!")
	@NotEmpty(message="email is required")
	private String email;

	@NotEmpty(message="password is required")
	private String password;
	
}
