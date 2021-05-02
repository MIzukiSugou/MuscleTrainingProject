package com.example.demo.app.login;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.example.demo.app.common.ErrorStatement;
/**
 * ログイン画面 フォームクラス
 * 
 * @author 菅生　2021/2/18
 *
 */
public class LoginForm implements Serializable {
	
	private static final long serialVersionUID = -8238758270284186778L;

	//ユーザーId
	@NotEmpty(message = ErrorStatement.USERID_NOT_INPUT_MESSAGE)
	@Pattern(regexp = ErrorStatement.PATTERN_USERID, message = ErrorStatement.MISMATCH__USERID_MESSAGE)
	private String userId;

	//パスワード
	@NotEmpty(message = ErrorStatement.PASSWORD_NOT_INPUT_MESSAGE)
	@Pattern(regexp = ErrorStatement.PATTERN_PASSWORD, message = ErrorStatement.MISMATCH_PASSWORD_MESSAGE)
	private String password;

	//ログインエラーメッセージ
	private String errorMessage;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}