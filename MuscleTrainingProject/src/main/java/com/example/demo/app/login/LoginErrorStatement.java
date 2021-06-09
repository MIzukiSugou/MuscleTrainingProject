package com.example.demo.app.login;

/**
 * ログイン画面　エラー文言、正規表現、格納クラス
 * 
 * @author 菅生 2021/2/18
 *
 */
public class LoginErrorStatement {

	/**
	 * [USERID]未入力時のエラーメッセージ
	 */
	public static final String USERID_NOT_INPUT_MESSAGE = "USERIDの入力は必須になります。";

	/**
	 * [PASSWORD]未入力時のエラーエラーメッセージ
	 */
	public static final String PASSWORD_NOT_INPUT_MESSAGE = "PASSWORDの入力は必須になります。";

	/**
	 * [USERID]半角英数字6文字以外を入力時のエラーエラーメッセージ
	 */
	public static final String MISMATCH__USERID_MESSAGE = "USERIDは半角数字6桁で入力してください。";

	/**
	 * [USERID]半角英数字6文字以外
	 */
	public static final String PATTERN_USERID = "^([a-zA-Z0-9]{6})?$";

	/**
	 * [PASSWORD]半角英数字8～15文字以外　エラーメッセージ
	 */
	public static final String MISMATCH_PASSWORD_MESSAGE = "PASSWORDは半角英数字8~15桁で入力してください。";

	/**
	 * [PASSWORD]半角英数字6～10文字以外
	 */
	public static final String PATTERN_PASSWORD = "^([a-zA-Z0-9]{8,15})?$";
	
	/**
	 * [USERID][PASSWORD]どちらかに誤りが有る場合のエラーメッセージ
	 */
	public static final String LOGIN_ERROR = "USERIDまたはPASSWORDの入力に誤りがあります。";
	
	/**
	 * 全角カタカナ以外
	 */
	public static final String PATTERN_NAMEKANA = "^[ァ-ンヴー]*$";
	
}
