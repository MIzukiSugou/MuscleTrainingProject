package com.example.demo.domain.entity;

import java.io.Serializable;

/**
 * トレーニング記録画面　エンティティクラス
 * 
 * @author 菅生　2021/4/10
 *
 */
public class TrainingRecordDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//更新時：削除フラグ
	private String deleteFlg;
	
	//ユーザーID
	private String userId;
	
	//トレーニング実施日
	private String dateRecord;
	
	//トレーニングメニュー
	private String trainingMenu;
	
	//加重
	private String weight;
	
	//st1
	private String st1;
	
	//st2
	private String st2;
	
	//st3
	private String st3;	
	
	//st4
	private String st4;	
	
	//st5
	private String st5;
	
	//st6
	private String st6;
	
	//TOTAL
	private String total;
	
	//作成日時
	private String insertDate;
	
	//作成ユーザー
	private String insertUser;
	
	//更新ユーザー
	private String updateUser;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(String dateRecord) {
		this.dateRecord = dateRecord;
	}

	public String getTrainingMenu() {
		return trainingMenu;
	}

	public void setTrainingMenu(String trainingMenu) {
		this.trainingMenu = trainingMenu;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getSt1() {
		return st1;
	}

	public void setSt1(String st1) {
		this.st1 = st1;
	}

	public String getSt2() {
		return st2;
	}

	public void setSt2(String st2) {
		this.st2 = st2;
	}

	public String getSt3() {
		return st3;
	}

	public void setSt3(String st3) {
		this.st3 = st3;
	}

	public String getSt4() {
		return st4;
	}

	public void setSt4(String st4) {
		this.st4 = st4;
	}

	public String getSt5() {
		return st5;
	}

	public void setSt5(String st5) {
		this.st5 = st5;
	}

	public String getSt6() {
		return st6;
	}

	public void setSt6(String st6) {
		this.st6 = st6;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

}
