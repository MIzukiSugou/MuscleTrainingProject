package com.example.demo.domain.entity;

import java.io.Serializable;
/**
 * ログイン画面　エンティティ	クラス
 * 
 * @author 菅生　2021/2/18
 *
 */
public class LoginDao implements Serializable {
	
	private static final long serialVersionUID = -1465116841237835004L;

	//UserId
	private String userId;
	
	//PassWord
	private String password;
	
	//姓
	private String firstName;
	
	//名
	private String lastName;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
