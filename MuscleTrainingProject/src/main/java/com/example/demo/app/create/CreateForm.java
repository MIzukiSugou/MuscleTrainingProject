package com.example.demo.app.create;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.util.StringUtils;

import com.example.demo.app.common.ErrorStatement;

/**
 * アカウント作成画面 フォームクラス
 * 
 * @author 菅生　2021/2/21
 *
 */
public class CreateForm implements Serializable{

	private static final long serialVersionUID = -8237935725232960983L;
	
	//ユーザーId
	@Pattern(regexp = ErrorStatement.PATTERN_USERID, message = ErrorStatement.MISMATCH__USERID_MESSAGE)
	@NotEmpty(message = ErrorStatement.USERID_NOT_INPUT_MESSAGE)
	private String userId;
	
	//名
	@NotEmpty(message = ErrorStatement.LASTNAME_NOT_INPUT_MESSAGE)
	private String lastName;
	
	//姓
	@NotEmpty(message = ErrorStatement.FIRSTNAME_NOT_INPUT_MESSAGE)
	private String firstName;
	
	//メイ
	@Pattern(regexp = ErrorStatement.PATTERN_NAMEKANA, message = ErrorStatement.LASTNAMEKANA__MESSAGE)
	@NotEmpty(message = ErrorStatement.LASTNAMEKANA_NOT_INPUT_MESSAGE)
	private String lastNameKana;
	
	//セイ
	@Pattern(regexp = ErrorStatement.PATTERN_NAMEKANA, message = ErrorStatement.FIRSTNAMEKANA__MESSAGE)
	@NotEmpty(message = ErrorStatement.FIRSTNAMEKANA_NOT_INPUT_MESSAGE)
	private String firstNameKana;
	
	//パスワード
	@Pattern(regexp = ErrorStatement.PATTERN_PASSWORD, message = ErrorStatement.MISMATCH_PASSWORD_MESSAGE)
	@NotEmpty(message = ErrorStatement.PASSWORD_NOT_INPUT_MESSAGE)
	private String password;
	
	//パスワード（確認用）
	@Pattern(regexp = ErrorStatement.PATTERN_PASSWORD, message = ErrorStatement.MISMATCH_PASSWORD_MESSAGE)
	@NotEmpty(message = ErrorStatement.PASSWORDCONFIRM_NOT_INPUT_MESSAGE)
	private String passwordConfirm;
	
	//パスワードとパスワード（確認用）の一致確認
	@AssertTrue(message = ErrorStatement.INCONSISTENCY_PASSWORD_MESSAGE)
	public boolean isPasswordValid() {
		if (StringUtils.isEmpty(password) && StringUtils.isEmpty(passwordConfirm)) {
			return true;
		} else {
			if (this.password.equals(this.passwordConfirm)) {
				return true;
			}
			return false;
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNameKana() {
		return lastNameKana;
	}

	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
}
