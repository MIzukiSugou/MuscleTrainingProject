package com.example.demo.domain.entity;

/**
 * トレーニングメニュー作成画面　エンティティクラス
 * 
 * @author 菅生　2021/05/30
 *
 */
public class TrainingMenuCreateDao {
	
	//ユーザーId
	private String userId;
		
	//TrainingMenu
	private String trainingMenu;
	
	//TrainingTarget
	private String trainingTarget;
	
	//TrainingSabTarget1
	private String trainingSabTarget1;
	
	//TrainingSabTarget2
	private String trainingSabTarget2;
	
	//TrainingSabTarget3
	private String trainingSabTarget3;
	
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

	public String getTrainingMenu() {
		return trainingMenu;
	}

	public void setTrainingMenu(String trainingMenu) {
		this.trainingMenu = trainingMenu;
	}

	public String getTrainingTarget() {
		return trainingTarget;
	}

	public void setTrainingTarget(String trainingTarget) {
		this.trainingTarget = trainingTarget;
	}

	public String getTrainingSabTarget1() {
		return trainingSabTarget1;
	}

	public void setTrainingSabTarget1(String trainingSabTarget1) {
		this.trainingSabTarget1 = trainingSabTarget1;
	}

	public String getTrainingSabTarget2() {
		return trainingSabTarget2;
	}

	public void setTrainingSabTarget2(String trainingSabTarget2) {
		this.trainingSabTarget2 = trainingSabTarget2;
	}

	public String getTrainingSabTarget3() {
		return trainingSabTarget3;
	}

	public void setTrainingSabTarget3(String trainingSabTarget3) {
		this.trainingSabTarget3 = trainingSabTarget3;
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
