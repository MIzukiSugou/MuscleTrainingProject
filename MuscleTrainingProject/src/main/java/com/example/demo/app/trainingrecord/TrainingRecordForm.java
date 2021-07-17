package com.example.demo.app.trainingrecord;

import java.io.Serializable;
import java.util.List;
/**
 * トレーニング記録画面 フォーム「親」クラス
 * 
 * @author 菅生　2021/3/9
 *
 */
public class TrainingRecordForm implements Serializable{

	private static final long serialVersionUID = -6927939434355858350L;
	
	//ログインユーザーID
	private String userId;
	
	//DB削除予定のトレーニングメニュー一覧
	private List<String> deleteMenusEditing;
	
	//トレーニング記録　フォーム「子」クラス(追加用)
	private List<TrainingRecordListForm> trainingRecordListAdd;
	
	//トレーニング記録　フォーム「子」クラス(編集用)
	private List<TrainingRecordListForm> trainingRecordListEditing;
	
	//年月日(yyyy-MM-dd)
	private String dateView;
	
	//年月日(yyyymmdd)
	private String date;
	
	//TrainingMenu:value
	private String menu;
	
	//TrainingMenu:key
	private String menuKey;
	
	//記録ボタン制御
	private String recordBtnControl;
	
	//trainingRecordListAdd表示最終位置(index)
	private int trainingRecordFormAddListEndIndex;
	
	//trainingRecordListEditing表示最終位置(index)
	private int trainingRecordFormEditingListEndIndex;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

//	public String getMenuKey() {
//		return menuKey;
//	}
//
//	public void setMenuKey(String menuKey) {
//		this.menuKey = menuKey;
//	}

	public String getRecordBtnControl() {
		return recordBtnControl;
	}

	public void setRecordBtnControl(String recordBtnControl) {
		this.recordBtnControl = recordBtnControl;
	}

	public List<TrainingRecordListForm> getTrainingRecordListAdd() {
		return trainingRecordListAdd;
	}

	public void setTrainingRecordListAdd(List<TrainingRecordListForm> trainingRecordListAdd) {
		this.trainingRecordListAdd = trainingRecordListAdd;
	}

	public List<TrainingRecordListForm> getTrainingRecordListEditing() {
		return trainingRecordListEditing;
	}

	public void setTrainingRecordListEditing(List<TrainingRecordListForm> trainingRecordListEditing) {
		this.trainingRecordListEditing = trainingRecordListEditing;
	}

	public String getDateView() {
		return dateView;
	}

	public void setDateView(String dateView) {
		this.dateView = dateView;
	}

	public List<String> getDeleteMenusEditing() {
		return deleteMenusEditing;
	}

	public void setDeleteMenusEditing(List<String> deleteMenusEditing) {
		this.deleteMenusEditing = deleteMenusEditing;
	}

	public int getTrainingRecordFormAddListEndIndex() {
		return trainingRecordFormAddListEndIndex;
	}

	public void setTrainingRecordFormAddListEndIndex(int trainingRecordFormAddListEndIndex) {
		this.trainingRecordFormAddListEndIndex = trainingRecordFormAddListEndIndex;
	}

	public int getTrainingRecordFormEditingListEndIndex() {
		return trainingRecordFormEditingListEndIndex;
	}

	public void setTrainingRecordFormEditingListEndIndex(int trainingRecordFormEditingListEndIndex) {
		this.trainingRecordFormEditingListEndIndex = trainingRecordFormEditingListEndIndex;
	}


}
