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
	
	// 「記録」ボタン制御フラグ
	private int recordsFlg;
	
	// 「更新」ボタン制御フラグ
	private int updateFlg;
	
	// 「追加」ボタン制御フラグ
	private int addFlg;
	
	//トレーニング記録　フォーム「子」クラス
	private List<TrainingRecordListForm> trainingRecordList;
	
	//トレーニングメニューリスト
	private Map<String, String> menuMap;
	
	//トレーニング実施チェックボックス制御
	private Map<String, String> implementedCheck = initImplementedCheck();
	
	//トレーニング実施制御
	private String implemented;
	
//	//No
//	private int number;
//	
//	//種目
//	private String menu;
//	
//	//加重
//	private int weight;
//	
//	//1ST
//	private int st1;
//	
//	//2ST
//	private int st2;
//	
//	//3ST
//	private int st3;	
//	
//	//4ST
//	private int st4;	
//	
//	//5ST
//	private int st5;
//	
//	//6ST
//	private int st6;
//	
//	//TOTAL
//	private int total;
	
	//年
	private String year;
	
	//月
	private String month;
	
	//日
	private String day;
	
	//年月日
	private String date;
	
	//年月日「プルダウン」
	private Map<String, String> pulldownDate;
	
	
	public Map<String, String> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String, String> menuMap) {
		this.menuMap = menuMap;
	}

	
//	public int getNumber() {
//		return number;
//	}
//
//	public void setNumber(int number) {
//		this.number = number;
//	}
//
//	public String getMenu() {
//		return menu;
//	}
//
//	public void setMenu(String menu) {
//		this.menu = menu;
//	}
//
//	public int getWeight() {
//		return weight;
//	}
//
//	public void setWeight(int weight) {
//		this.weight = weight;
//	}
//
//	public int getSt1() {
//		return st1;
//	}
//
//	public void setSt1(int st1) {
//		this.st1 = st1;
//	}
//
//	public int getSt2() {
//		return st2;
//	}
//
//	public void setSt2(int st2) {
//		this.st2 = st2;
//	}
//
//	public int getSt3() {
//		return st3;
//	}
//
//	public void setSt3(int st3) {
//		this.st3 = st3;
//	}
//
//	public int getSt4() {
//		return st4;
//	}
//
//	public void setSt4(int st4) {
//		this.st4 = st4;
//	}
//
//	public int getSt5() {
//		return st5;
//	}
//
//	public void setSt5(int st5) {
//		this.st5 = st5;
//	}
//
//	public int getSt6() {
//		return st6;
//	}
//
//	public void setSt6(int st6) {
//		this.st6 = st6;
//	}
//
//	public int getTotal() {
//		return total;
//	}
//
//	public void setTotal(int total) {
//		this.total = total;
//	}

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
	
	public Map<String, String> getPulldownDate() {
		return pulldownDate;
	}

	public void setPulldownDate(Map<String, String> pulldownDate) {
		this.pulldownDate = pulldownDate;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getRecordsFlg() {
		return recordsFlg;
	}

	public void setRecordsFlg(int recordsFlg) {
		this.recordsFlg = recordsFlg;
	}

	public int getUpdateFlg() {
		return updateFlg;
	}

	public void setUpdateFlg(int updateFlg) {	
		this.updateFlg = updateFlg;
	}

	public int getAddFlg() {	
		return addFlg;
	}

	public void setAddFlg(int addeFlg) {
		this.addFlg = addeFlg;
	}
	
    private Map<String, String> initImplementedCheck() {
    	implementedCheck = new LinkedHashMap<>(); 
		implementedCheck.put("001","実施");
		implementedCheck.put("002","未実施");
		setImplemented("001");
		return implementedCheck;
	}

	public Map<String, String> getImplementedCheck() {
		return implementedCheck;
	}

	public void setImplementedCheck(Map<String, String> implementedCheck) {
		this.implementedCheck = implementedCheck;
	}

	public String getImplemented() {
		return implemented;
	}

	public void setImplemented(String implemented) {
		this.implemented = implemented;
	}

	public List<TrainingRecordListForm> getTrainingRecordList() {
		return trainingRecordList;
	}

	public void setTrainingRecordList(List<TrainingRecordListForm> trainingRecordList) {
		this.trainingRecordList = trainingRecordList;
	}

}
