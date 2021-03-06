package br.com.san.ls.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.san.ls.entity.UserLogin;
import br.com.san.ls.validation.FieldsMatch;

@FieldsMatch.Fields({
	@FieldsMatch(field = "email", fieldVerify = "verifyEmail", message = "Os emails não correspondem!"),
	@FieldsMatch(field = "password", fieldVerify = "verifyPassword", message = "As senhas não correspondem!") })
public class UserLoginDTO {

	private Integer id;
	
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Email
	private String verifyEmail;
	@NotBlank
	@Length(min = 8, message = "Deve conter no mínimo 8 caracteres")
	private String password;
	@NotBlank
	@Length(min = 8, message = "Deve conter no mínimo 8 caracteres")
	private String verifyPassword;

	private String codeLogin;

	private boolean verified;

	public UserLoginDTO() {

	}

	public UserLoginDTO(String email, String verifyEmail, String password, String verifyPassword, String codeLogin,
			boolean verified) {
		super();
		this.email = email;
		this.verifyEmail = verifyEmail;
		this.password = password;
		this.verifyPassword = verifyPassword;
		this.codeLogin = codeLogin;
		this.verified = verified;
	}
	
	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodeLogin() {
		return codeLogin;
	}

	public void setCodeLogin(String codeLogin) {
		this.codeLogin = codeLogin;
	}

	public String getVerifyEmail() {
		return verifyEmail;
	}

	public void setVerifyEmail(String verifyEmail) {
		this.verifyEmail = verifyEmail;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public UserLogin toUserLogin() {
		UserLogin userLogin = new UserLogin();
		userLogin.setEmail(this.email);
		userLogin.setPassword(this.password);
		return userLogin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLoginDTO other = (UserLoginDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

}
