package com.example.demo.app.trainingmenucreate;

/**
 * トレーニングメニュー作成画面　エラー文言、正規表現、格納クラス
 * 
 * @author 菅生 2021/2/18
 *
 */
public class TrainingMenuCreateErrorStatement {

	/**
	 * [TrainingMenu]未入力時のエラーメッセージ
	 */
	public static final String TRAININGMENU_NOT_INPUT_MESSAGE = "※TrainingMenuの入力は必須になります。";
	
	/**
	 * [TrainingTarget]プルダウン未選択時のエラーメッセージ
	 */
	public static final String TRAININGTARGET_NOT_INPUT_MESSAGE = "※TrainingTargetの選択は必須になります。";
	
	/**
	 * [TrainingMenu]重複登録時のエラーメッセージ
	 */
	public static final String TRAININGTARGET_DUPLICATE_REGISTRATION = "※入力されたTrainingMenuは既に登録されております。";

	/**
	 * [TrainingMenu]作成完了メッセージ
	 */
	public static final String TRAININGMENU_CREATECOMPLARE__MESSAGE = "※TrainingMenu作成が完了致しました。";
}
