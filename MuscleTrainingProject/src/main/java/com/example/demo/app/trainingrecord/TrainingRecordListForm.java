package com.example.demo.app.trainingrecord;

import java.io.Serializable;
import java.util.Map;
/**
 * トレーニング記録画面 フォーム「子」クラス
 * 
 * @author 菅生　2021/4/5
 *
 */
public class TrainingRecordListForm implements Serializable{

	private static final long serialVersionUID = -6927939434355858350L;
	
	//更新時：削除フラグ
	private String deleteFlg;
	
	//トレーニングメニューリスト(プルダウン)
	private Map<String, String> menuMap;
	
	//TrainingMenu
	private String menu;
	
	//TrainingMenu:key
	private String menuKey;
	
	//Weight
	private int weight;
	
	//St1
	private int st1;
	
	//St2
	private int st2;
	
	//St3
	private int st3;	
	
	//St4
	private int st4;	
	
	//St5
	private int st5;
	
	//St6
	private int st6;
	
	//Total
	private int total;
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSt1() {
		return st1;
	}

	public void setSt1(int st1) {
		this.st1 = st1;
	}

	public int getSt2() {
		return st2;
	}

	public void setSt2(int st2) {
		this.st2 = st2;
	}

	public int getSt3() {
		return st3;
	}

	public void setSt3(int st3) {
		this.st3 = st3;
	}

	public int getSt4() {
		return st4;
	}

	public void setSt4(int st4) {
		this.st4 = st4;
	}

	public int getSt5() {
		return st5;
	}

	public void setSt5(int st5) {
		this.st5 = st5;
	}

	public int getSt6() {
		return st6;
	}

	public void setSt6(int st6) {
		this.st6 = st6;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Map<String, String> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String, String> menuMap) {
		this.menuMap = menuMap;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

}
