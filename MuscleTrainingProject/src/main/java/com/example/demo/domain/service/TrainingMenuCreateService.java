package com.example.demo.domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.trainingmenucreate.TrainingMenuCreateForm;
import com.example.demo.domain.entity.TrainingMenuCreate;
import com.example.demo.domain.repository.TrainingMenuCreateRepository;

/**
 * トレーニングメニュー作成画面　サービスクラス
 * 
 * @author 菅生瑞騎 2021/5/28
 *
 */
@Service
public class TrainingMenuCreateService {
	
	/**　新規登録時、削除フラグ「削除無し」*/
	private static final String DELETEFLAG = "0";
	
	/** 現在日付ををYYYY/MM/DDの形で取得 */
	Calendar today = Calendar.getInstance();
    SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy/MM/dd");
    String strToday = yyyymmdd.format(today.getTime());
    
	
	private final TrainingMenuCreateRepository repository;

	@Autowired
	TrainingMenuCreateService(TrainingMenuCreateRepository repository){
		this.repository = repository;
	}
	
	/**
	 * トレーニングメニュー情報の追加を行う。
	 * 
	 * @param trainingMenuCreate トレーニングメニュー情報リポジトリクラス
     */
	public void insertTrainingMenu(TrainingMenuCreate trainingMenuCreate,
			TrainingMenuCreateForm createForm) throws Exception {
		
		trainingMenuCreate.setUserId(createForm.getUserId());
		trainingMenuCreate.setTrainingMenu(createForm.getTrainingMenu());
		trainingMenuCreate.setTrainingTarget(createForm.getTrainingTarget());
		trainingMenuCreate.setTrainingSabTarget1(createForm.getTrainingSabTarget1());
		trainingMenuCreate.setTrainingSabTarget2(createForm.getTrainingSabTarget2());
		trainingMenuCreate.setTrainingSabTarget3(createForm.getTrainingSabTarget3());
		trainingMenuCreate.setInsertDate(strToday);
		trainingMenuCreate.setInsertUser(createForm.getUserId());
		trainingMenuCreate.setUpdateUser(createForm.getUserId());
		trainingMenuCreate.setDeleteFlag(DELETEFLAG);
		
		repository.insertTrainingMenu(trainingMenuCreate);
	}
}
