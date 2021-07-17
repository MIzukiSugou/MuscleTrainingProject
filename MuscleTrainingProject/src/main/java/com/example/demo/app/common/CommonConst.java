package com.example.demo.app.common;

/**
 * sessionキーの定数クラス
 * 
 * @author 菅生 2021/2/18
 * 
 */
public class CommonConst {

	/** ログインユーザのユーザ情報取得キー */
	public static final String LOGIN_USER = "loginUser";

	/** トレーニング記録フォーム情報取得キー */
	public static final String TRAININGRECORDFROM = "trainingrecordFrom";
	
	/** トレーニング記録フォーム情報取得キー */
	public static final String TRAININGMENU = "trainingMenu";

	/** トレーニング記録年月日情報取得キー */
	public static final String TRAININGDATE = "trainingDate";
	
	/** トレーニング記録フォームリスト情報取得キー */
	public static final String TRAININGRECORDFROMLIST = "trainingrecordfromList";
	
    /** セッションタイムアウト時のリクエストパラメータ文字列 */
    public static final String TIME_OUT_REQUEST_PARAMETER = "?logoutParam=session_time_out";
    
    /********************************************************************************************************
     * 【トレーニング記録画面】
     * ****/
    
    /**
	 * トレーニング記録リスト　処理フラグ：（追加） */
    public static final String ADD = "ADD";
    
	/**
	 * トレーニング記録リスト　処理フラグ：（初期化） */
    public static final String INITIAL = "INITIAL";
    
	/** 
	 * トレーニング記録リスト　処理フラグ：（削除） */
    public static final String DALETE = "DALETE";
    
    /** 
	 * トレーニング記録リスト　処理フラグ：（更新） */
    public static final String UPDATE = "UPDATE";
    
	/** 
	 * DB更新用　削除フラグ ：　(無)*/
    public static final String DELETE_FLG_ZERO = "0";
	
	/** 
	 * DB更新用　削除フラグ ：　(有) */
    public static final String DELETE_FLG_ONE = "1";
	
	/** 
	 * ボタン制御フラグ：（活性） */
    public static final String BTNCONTROLACTIVITY = "1";
	
	/** 
	 * ボタン制御フラグ：（非活性） */
    public static final String BTNCONTROLINACTIVE = "0";
    
	/** 
	 * エラーフラグ */
    public static final String ERROR = "ERROR";
    
	/** 
	 * トレーニング記録リスト index： (その他) */
    public static final int OTHER_INDEX = 999999999;
    
	/**
	 *　DB insert return (追加成功)*/
    public static final String INSERT_SUCCESS = "追加成功";
    
	/** 
	 * DB insert return (追加失敗) */
    public static final String INSERT_FAILURE = "追加失敗";
    
	/** 
	 * DB insert return (追加重複失敗) */
    public static final String INSERT_DUPLICATION_FAILURE = "追加重複失敗";
    
	/**
	 * DB UPDATE メッセージ： (失敗)　*/
    public static final String UPDATE_ERROR = "更新中にエラーが発生しました。";
	
	/**
	 * DB UPDATE メッセージ： (完了)　*/
    public static final String UPDATE_COMPLATE = "更新が完了しました。";
    
	/**
	 * DB insert メッセージ： (重複)　*/
    public static final String TODAY_REGISTERED = "は本日記録済みです。更新を行ってください。";
    
	/**
	 * メニュープルダウン：初期文言　*/
    public static final String MENUMAP_INITIAL = "トレーニングメニューを選択してください";
    
    /************************************************************************************************************/
}
