package com.example.demo.app.trainingrecord;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
	
	//トレーニング記録　フォーム「子」クラス
	private List<TrainingRecordListForm> trainingRecordList;
	
	//年
	private String year;
	
	//月
	private String month;
	
	//日
	private String day;
	
	//年月日
	private String date;
	
	//TrainingMenu
	private String menu;
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<TrainingRecordListForm> getTrainingRecordList() {
		return trainingRecordList;
	}

	public void setTrainingRecordList(List<TrainingRecordListForm> trainingRecordList) {
		this.trainingRecordList = trainingRecordList;
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

}
