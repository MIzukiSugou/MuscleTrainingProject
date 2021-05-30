package com.example.demo.app.create;

/**
 * エラー文言、正規表現、格納クラス
 * 
 * @author 菅生 2021/2/18
 *
 */
public class CreateErrorStatement {

	/**
	 * [ユーザーＩＤ]未入力時のエラーメッセージ
	 */
	public static final String USERID_NOT_INPUT_MESSAGE = "※ユーザーＩＤの入力は必須になります。";

	/**
	 * [名]未入力時のエラーメッセージ
	 */
	public static final String LASTNAME_NOT_INPUT_MESSAGE = "※名の入力は必須になります。";

	/**
	 * [姓]未入力時のエラーメッセージ
	 */
	public static final String FIRSTNAME_NOT_INPUT_MESSAGE = "※姓の入力は必須になります。";

	/**
	 * [メイ]未入力時のエラーメッセージ
	 */
	public static final String LASTNAMEKANA_NOT_INPUT_MESSAGE = "※メイの入力は必須になります。";

	/**
	 * [セイ]未入力時のエラーメッセージ
	 */
	public static final String FIRSTNAMEKANA_NOT_INPUT_MESSAGE = "※セイの入力は必須になります。";

	/**
	 * [パスワード]未入力時のエラーエラーメッセージ
	 */
	public static final String PASSWORD_NOT_INPUT_MESSAGE = "※パスワードの入力は必須になります。";

	/**
	 * [パスワード（確認用）]未入力時のエラーエラーメッセージ
	 */
	public static final String PASSWORDCONFIRM_NOT_INPUT_MESSAGE = "※パスワード（確認用）の入力は必須になります。";
	
	/**
	 * [ユーザーＩＤ]半角英数字6文字以外を入力時のエラーエラーメッセージ
	 */
	public static final String MISMATCH__USERID_MESSAGE = "※ユーザーＩＤは半角数字6桁で入力してください。";

	/**
	 * [ユーザーＩＤ]半角英数字6文字以外
	 */
	public static final String PATTERN_USERID = "^([a-zA-Z0-9]{6})?$";

	/**
	 * [パスワード]半角英数字8～15文字以外のエラーエラーメッセージ
	 */
	public static final String MISMATCH_PASSWORD_MESSAGE = "※パスワードは半角英数字8~15桁で入力してください。";

	/**
	 * [パスワード]半角英数字6～10文字以外
	 */
	public static final String PATTERN_PASSWORD = "^([a-zA-Z0-9]{8,15})?$";
	
	/**
	 * [パスワード][パスワード（確認用）]不一致時、エラーメッセージ
	 */
	public static final String INCONSISTENCY_PASSWORD_MESSAGE = "※パスワードとパスワード（確認用）が一致しません。";
	
	/**
	 *  [ユーザーＩＤ][パスワード]どちらかに誤りが有る場合のエラーメッセージ
	 */
	public static final String LOGIN_ERROR = "※ユーザーＩＤまたはパスワードの入力に誤りがあります。";
	
	/**
	 * [ユーザーＩＤ]重複登録時のエラーメッセージ
	 */
	public static final String USERID_DUPLICATE_REGISTRATION = "※入力されたユーザーＩＤは既に登録されております。";
	
	/**
	 * 全角カタカナ以外
	 */
	public static final String PATTERN_NAMEKANA = "^[ァ-ンヴー]*$";
	
	/**
	 * [メイ]全角カタカナ以外　エラーメッセージ
	 */
	public static final String LASTNAMEKANA__MESSAGE = "※メイは全角カタカナを入力してください。";
	
	/**
	 * [セイ]全角カタカナ以外　エラーメッセージ
	 */
	public static final String FIRSTNAMEKANA__MESSAGE = "※セイは全角カタカナを入力してください。";
	
	/**
	 * アカウント作成完了メッセージ
	 */
	public static final String CREATECOMPLARE__MESSAGE = "※アカウント作成が完了致しました。";
}
