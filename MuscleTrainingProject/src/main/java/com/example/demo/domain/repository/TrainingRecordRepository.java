package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.entity.TrainingRecord;

/**
 * トレーニング記録画面　リポジトリインターフェース
 * @author 菅生　2021/3/17
 *
 */
public interface TrainingRecordRepository {
	
	/**
	 * TRAINING_MENU_MANAGEMENTテーブルのログインIDの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public int checkUserIdMenu(String userId);
	
	/**
	 * TRAINING_RECORDテーブルのログインIDの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public int checkUserIdTrainingRecord(String userId) ;
	
	/**
	 * トレーニングメニュー情報を取得します。
	 *
	 * @param userId ユーザID
	 * @return トレーニングメニュー情報
	 */
	List<String> selectMenu(String userId);
	
	/**
	 * TRAINING_RECORDテーブルのログインIDの存在をチェックし、DATE情報を取得します。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public List<String> selectTrainingRecordDate(String userId) ;
	
	/**
	 * 実施するトレーニングを記録します。
	 *
	 * @param trainingRecordList トレーニング記録情報
	 * @return DB結果
	 */
	int insertTrainingRecord(List<TrainingRecord> trainingRecordList);
	
	/**
	 * トレーニング記録年月日よりTRAINING_RECORDから実施トレーニング情報を取得
	 * @param date yyyymmdd形式の年月日文字列
	 * @return DB結果
	 */
	List<TrainingRecord> selectTrainingRecord(String date);
	
	/**
	 * 実施するトレーニングを更新します。
	 *
	 * @param trainingRecordList トレーニング記録情報
	 * @return DB結果
	 */
	public int updateTrainingRecord(List<TrainingRecord> trainingRecordList) ;
		
}
