package com.example.demo.domain.service;

import javax.servlet.http.HttpSession;

import com.example.demo.app.trainingrecord.TrainingRecordForm;

public interface TrainingRecordService {
	
	/**
	 * トレーニングメニュー情報を取得します。
	 *
	 */
	public boolean selectMenu(TrainingRecordForm trainingRecordForm, HttpSession session) ;
	
	/**
	 * Formに日付情報を格納します。
	 */
	public void setDate(TrainingRecordForm trainingRecordForm) ;
	
    /**
	 * 実施するトレーニングを記録します。
     */
	public void insertTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) throws Exception;
	
	/**
	 * 日付情報を取得します。
	 * @param trainingRecordForm トレーニング記録フォーム
	 * @param session セッション情報
	 */
	public boolean selectTrainingRecordDate(TrainingRecordForm trainingRecordForm, HttpSession session) ;
	
	/**
	 * トレーニング記録年月日よりTRAINING_RECORDから実施トレーニング情報を取得、フォーム設定処理
	 * @param trainingRecordForm フォーム情報
	 * @param session セッション情報
	 */
	public void selectTrainingRecord(TrainingRecordForm trainingRecordForm,
			HttpSession session);
	
	/**
	 * 実施するトレーニングを更新
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public void updateTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) throws Exception ;
	
}
