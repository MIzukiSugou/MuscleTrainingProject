package com.example.demo.app.create;

/**
 * エラー文言、正規表現、格納クラス
 * 
 * @author 菅生 2021/2/18
 *
 */
public class CreateErrorStatement {

	/**
	 * [USERID]未入力時のエラーメッセージ
	 */
	public static final String USERID_NOT_INPUT_MESSAGE = "USERIDの入力は必須になります。";

	/**
	 * [FIRSTNAME]未入力時のエラーメッセージ
	 */
	public static final String FIRSTNAME_NOT_INPUT_MESSAGE = "FIRSTNAMEの入力は必須になります。";
	
	/**
	 * [LASTNAME]未入力時のエラーメッセージ
	 */
	public static final String LASTNAME_NOT_INPUT_MESSAGE = "LASTNAMEの入力は必須になります。";

	/**
	 * [FIRSTNAMEKANA]未入力時のエラーメッセージ
	 */
	public static final String FIRSTNAMEKANA_NOT_INPUT_MESSAGE = "FIRSTNAMEKANAの入力は必須になります。";

	/**
	 * [LASTNAMEKANA]未入力時のエラーメッセージ
	 */
	public static final String LASTNAMEKANA_NOT_INPUT_MESSAGE = "LASTNAMEKANAの入力は必須になります。";

	/**
	 * [PASSWORD]未入力時のエラーエラーメッセージ
	 */
	public static final String PASSWORD_NOT_INPUT_MESSAGE = "PASSWORDの入力は必須になります。";

	/**
	 * [PASSWORDCONFIRM]未入力時のエラーエラーメッセージ
	 */
	public static final String PASSWORDCONFIRM_NOT_INPUT_MESSAGE = "PASSWORDCONFIRMの入力は必須になります。";
	
	/**
	 * [USERID]半角英数字6文字以外を入力時のエラーエラーメッセージ
	 */
	public static final String MISMATCH__USERID_MESSAGE = "USERIDは半角数字6桁で入力してください。";

	/**
	 * [USERID]半角英数字6文字以外
	 */
	public static final String PATTERN_USERID = "^([a-zA-Z0-9]{6})?$";

	/**
	 * [PASSWORD]半角英数字8～15文字以外のエラーエラーメッセージ
	 */
	public static final String MISMATCH_PASSWORD_MESSAGE = "PASSWORDは半角英数字8~15桁で入力してください。";

	/**
	 * [PASSWORD]半角英数字6～10文字以外
	 */
	public static final String PATTERN_PASSWORD = "^([a-zA-Z0-9]{8,15})?$";
	
	/**
	 * [PASSWORD][PASSWORDCONFIRM]不一致時、エラーメッセージ
	 */
	public static final String INCONSISTENCY_PASSWORD_MESSAGE = "PASSWORDとPASSWORDCONFIRMが一致しません。";
	
	/**
	 * [USERID]重複登録時のエラーメッセージ
	 */
	public static final String USERID_DUPLICATE_REGISTRATION = "入力されたUSERIDは既に登録されております。";
	
	/**
	 * 全角カタカナ以外
	 */
	public static final String PATTERN_NAMEKANA = "^[ァ-ンヴー]*$";
	
	/**
	 * [FIRSTNAMEKANA]全角カタカナ以外　エラーメッセージ
	 */
	public static final String FIRSTNAMEKANA__MESSAGE = "FIRSTNAMEKANAは全角カタカナを入力してください。";
	
	/**
	 * [LASTNAMEKANA]全角カタカナ以外　エラーメッセージ
	 */
	public static final String LASTNAMEKANA__MESSAGE = "※LASTNAMEKANAは全角カタカナを入力してください。";
	
	/**
	 * アカウント作成完了メッセージ
	 */
	public static final String CREATECOMPLARE__MESSAGE = "アカウント作成が完了致しました。";
}
