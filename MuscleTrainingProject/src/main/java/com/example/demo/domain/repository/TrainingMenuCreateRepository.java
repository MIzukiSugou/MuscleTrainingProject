package com.example.demo.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.TrainingMenuCreate;

/**
 * トレーニングメニュー作成画面 リポジトリクラス
 * 
 * @author 菅生瑞騎 2021/05/30
 *
 */
@Repository
public class TrainingMenuCreateRepository {

	private final JdbcTemplate jdbcTempate;

	@Autowired
	public TrainingMenuCreateRepository(JdbcTemplate jdbcTempate) {
		this.jdbcTempate = jdbcTempate;
	}

	/**
	 * トレーニングメニュー情報の追加を行う。
	 * 
	 * @param trainingMenuCreate トレーニングメニュー情報リポジトリクラス
	 * @return
	 */
	public int insertTrainingMenu(TrainingMenuCreate trainingMenuCreate) {
		
		String sql = "INSERT "
				+ "INTO TRAINING_MENU_MANAGEMENT( "
				+ "    USER_ID"
				+ "    , TRAINING_MENU"
				+ "    , TRAINING_TARGET"
				+ "    , TRAINING_SABTARGET1"
				+ "    , TRAINING_SABTARGET2"
				+ "    , TRAINING_SABTARGET3"
				+ "    , INSERT_DATE"
				+ "    , INSERT_USER"
				+ "    , UPDATE_DATE"
				+ "    , UPDATE_USER"
				+ "    , DELETE_FLAG"
				+ ") "
				+ "VALUES ( "
				+ "    ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , NOW() "
				+ "    , ? "
				+ "    , ?"
				+ ")";
		
		int count = jdbcTempate.update(sql,trainingMenuCreate.getUserId(),trainingMenuCreate.getTrainingMenu(),
				trainingMenuCreate.getTrainingTarget(),trainingMenuCreate.getTrainingSabTarget1(),trainingMenuCreate.getTrainingSabTarget2(),
				trainingMenuCreate.getTrainingSabTarget3(),trainingMenuCreate.getInsertDate(),
				trainingMenuCreate.getInsertUser(),trainingMenuCreate.getUpdateUser(),trainingMenuCreate.getDeleteFlag());
		
		return count;
	}

}
