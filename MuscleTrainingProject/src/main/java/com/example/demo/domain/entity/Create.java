package com.example.demo.domain.entity;

/**
 * アカウント作成画面　エンティティクラス
 * 
 * @author 菅生　2021/2/21
 *
 */
public class Create {
	
	//ユーザーId
	private String userId;
		
	//名
	private String lastName;
		
	//姓
	private String firstName;
		
	//メイ
	private String lastNameKana;
		
	//セイ
	private String firstNameKana;
		
	//PASSWORD
	private String password;
	
	//権限
	private String authority;
	
	//ログイン回数
	private String loginCount;
	
	//ログイン失敗回数
	private String loginFailureCount;
	
	//アカウントロックフラグ
	private String accountLockFlag;
	
	//作成日時
	private String insertDate;
	
	//作成ユーザー
	private String insertUser;
	
	//更新ユーザー
	private String updateUser;
	
	//削除フラグ
	private String deleteFlag;

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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(String loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public String getAccountLockFlag() {
		return accountLockFlag;
	}

	public void setAccountLockFlag(String accountLockFlag) {
		this.accountLockFlag = accountLockFlag;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
