package com.example.demo.app.trainingmenucreate;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * トレーニングメニュー作成画面 フォームクラス
 * 
 * @author 菅生　2021/5/28
 *
 */
public class TrainingMenuCreateForm implements Serializable{

	private static final long serialVersionUID = -8237935725232960983L;
	
	//ユーザーId
	private String userId;
	
	//TrainingMenu
	@NotEmpty(message = TrainingMenuCreateErrorStatement.TRAININGMENU_NOT_INPUT_MESSAGE)
	private String trainingMenu;
	
	//TrainingTarget
	@NotEmpty(message = TrainingMenuCreateErrorStatement.TRAININGTARGET_NOT_INPUT_MESSAGE)
	private String trainingTarget;
	
	//TrainingSabTarget1
	private String trainingSabTarget1;
	
	//TrainingSabTarget2
	private String trainingSabTarget2;
	
	//TrainingSabTarget3
	private String trainingSabTarget3;

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

}
