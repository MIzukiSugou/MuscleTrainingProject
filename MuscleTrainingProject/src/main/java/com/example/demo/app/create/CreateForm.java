package com.example.demo.app.create;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.util.StringUtils;

/**
 * アカウント作成画面 フォームクラス
 * 
 * @author 菅生　2021/2/21
 *
 */
public class CreateForm implements Serializable{

	private static final long serialVersionUID = -8237935725232960983L;
	
	//メッセージ
	private String message;
	
	//CREATE（false） CONFIRM(true) 制御フラグ
	private boolean control;
	
	//ユーザーId
	@Pattern(regexp = CreateErrorStatement.PATTERN_USERID, message = CreateErrorStatement.MISMATCH__USERID_MESSAGE)
	@NotEmpty(message = CreateErrorStatement.USERID_NOT_INPUT_MESSAGE)
	private String userId;
	
	//名
	@NotEmpty(message = CreateErrorStatement.LASTNAME_NOT_INPUT_MESSAGE)
	private String lastName;
	
	//姓
	@NotEmpty(message = CreateErrorStatement.FIRSTNAME_NOT_INPUT_MESSAGE)
	private String firstName;
	
	//メイ
	@Pattern(regexp = CreateErrorStatement.PATTERN_NAMEKANA, message = CreateErrorStatement.LASTNAMEKANA__MESSAGE)
	@NotEmpty(message = CreateErrorStatement.LASTNAMEKANA_NOT_INPUT_MESSAGE)
	private String lastNameKana;
	
	//セイ
	@Pattern(regexp = CreateErrorStatement.PATTERN_NAMEKANA, message = CreateErrorStatement.FIRSTNAMEKANA__MESSAGE)
	@NotEmpty(message = CreateErrorStatement.FIRSTNAMEKANA_NOT_INPUT_MESSAGE)
	private String firstNameKana;
	
	//PASSWORD
	@Pattern(regexp = CreateErrorStatement.PATTERN_PASSWORD, message = CreateErrorStatement.MISMATCH_PASSWORD_MESSAGE)
	@NotEmpty(message = CreateErrorStatement.PASSWORD_NOT_INPUT_MESSAGE)
	private String password;
	
	//PASSWORD（確認用）
	@Pattern(regexp = CreateErrorStatement.PATTERN_PASSWORD, message = CreateErrorStatement.MISMATCH_PASSWORD_MESSAGE)
	@NotEmpty(message = CreateErrorStatement.PASSWORDCONFIRM_NOT_INPUT_MESSAGE)
	private String passwordConfirm;
	
	//PASSWORDとPASSWORD（確認用）の一致確認
	@AssertTrue(message = CreateErrorStatement.INCONSISTENCY_PASSWORD_MESSAGE)
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
	
	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
