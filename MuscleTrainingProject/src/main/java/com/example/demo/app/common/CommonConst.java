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
    
    /************************************************************************************************************/
	/** 追加時フラグ */
    public static final String ADD = "ADD";
    
	/** 初期フラグ */
    public static final String INITIAL = "INITIAL";
    
	/** エラーフラグ */
    public static final String ERROR = "ERROR";
    
	/** 追加フラグ */
    public static final String DALETE = "DALETE";
    
	/** indexその他 */
    public static final int OTHER_INDEX = 999999999;
    
	/** 年 */
    public static final String YEAR = "year";
    
	/** 月 */
    public static final String MONTH = "month";
    
	/** 日 */
    public static final String DAY = "day";
    
	/** DB insert return 追加成功 */
    public static final String INSERT_SUCCESS = "追加成功";
    
	/** DB insert return 追加失敗 */
    public static final String INSERT_FAILURE = "追加失敗";
    
	/** DB insert return 追加重複失敗 */
    public static final String INSERT_DUPLICATION_FAILURE = "追加重複失敗";
    
	/**更新時：エラーメッセージ*/
    public static final String UPDATE_ERROR = "更新中にエラーが発生しました。";
	
	/**更新時：完了メッセージ*/
    public static final String UPDATE_COMPLATE = "更新が完了しました。";
    
    /************************************************************************************************************/
}
