package com.example.demo.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.TrainingRecord;

/**
 * トレーニング記録画面　リポジトリクラス
 * @author 菅生　2021/2/21
 *
 */
@Repository
public class TrainingRecordRepository {

	private final JdbcTemplate jdbcTempate;

	@Autowired
	public TrainingRecordRepository(JdbcTemplate jdbcTempate) {
		this.jdbcTempate = jdbcTempate;
	}
	
	/**
	 * TRAINING_MENU_MANAGEMENTテーブルのログインIDの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public int checkUserIdMenu(String userId) {

		String sql = "SELECT"
				+ "    COUNT(*)"
				+ "FROM"
				+ "    TRAINING_MENU_MANAGEMENT "
				+ "WHERE"
				+ "    USER_ID = ?";

		int count = jdbcTempate.queryForObject(sql, new Object[] { userId}, Integer.class);

		return count;
	}
	
	/**
	 * TRAINING_RECORDテーブルのログインIDの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public int checkUserIdTrainingRecord(String userId) {

		String sql = "SELECT"
				+ "    COUNT(*)"
				+ "FROM"
				+ "    TRAINING_RECORD "
				+ "WHERE"
				+ "    USER_ID = ?";

		int count = jdbcTempate.queryForObject(sql, new Object[] { userId}, Integer.class);

		return count;
	}
	
	/**
	 * TRAINING_RECORDテーブルの、DATE情報を取得します。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public List<String> selectTrainingRecordDate(String userId) {

		String sql = "select"
				+ "    DATE_RECORD "
				+ "from"
				+ "    TRAINING_RECORD "
				+ "WHERE"
				+ "    USER_ID = ?"
				+ "    AND DELETE_FLAG = 0";

		List<Map<String, Object>> resultList = jdbcTempate.queryForList(sql, new Object[] { userId });
		List<String> dateList = new ArrayList<>();
		for (Map<String, Object> result : resultList) {
			String set = (String)result.get("DATE_RECORD");
			dateList.add(set);
		}

		return dateList;
	}
	
	/**
	 * トレーニングメニュー情報を取得します。
	 *
	 * @param userId ユーザID
	 * @return トレーニングメニュー情報
	 */
	public List<String> selectMenu(String userId) {

		String sql = "SELECT"
				+ "    TRAINING_MENU "
				+ "FROM"
				+ "    TRAINING_MENU_MANAGEMENT "
				+ "WHERE"
				+ "    USER_ID = ?"
				+ "    AND DELETE_FLAG = 0";
		
		List<Map<String, Object>> resultList = jdbcTempate.queryForList(sql, new Object[] { userId });
		
		List<String> menuList = new ArrayList<>();
		
		for (Map<String, Object> result : resultList) {
			String set = (String)result.get("TRAINING_MENU");
			menuList.add(set);
		}
		return menuList;
	}
	
	/**
	 * 実施するトレーニングを記録します。
	 *
	 * @param trainingRecordList トレーニング記録情報
	 * @return DB結果
	 */
	public int insertTrainingRecord(List<TrainingRecord> trainingRecordList) {
		
		int	count = 0;
		
		for (TrainingRecord trainingRecord: trainingRecordList) {
			
			String sql = "INSERT "
					+ "INTO TRAINING_RECORD( "
					+ "    USER_ID"
					+ "    , DATE_RECORD"
					+ "    , TRAINING_MENU"
					+ "    , WEIGHT"
					+ "    , SET1"
					+ "    , SET2"
					+ "    , SET3"
					+ "    , SET4"
					+ "    , SET5"
					+ "    , SET6"
					+ "    , TOTAL"
					+ "    , IMPLEMENTATION_FLAG"
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
					+ "    , ? "
					+ "    , ? "
					+ "    , ? "
					+ "    , ? "
					+ "    , NOW()"
					+ "    , ? "
					+ "    , NOW()"
					+ "    , ? "
					+ "    , ? "
					+ ")";
			
			count = jdbcTempate.update(sql,trainingRecord.getUserId(),trainingRecord.getDateRecord(),trainingRecord.getTrainingMenu(),
					trainingRecord.getWeight(),trainingRecord.getSt1(),trainingRecord.getSt2(),
					trainingRecord.getSt3(),trainingRecord.getSt4(),trainingRecord.getSt5(),trainingRecord.getSt6(),trainingRecord.getTotal(),
					trainingRecord.getImplementationFlag(),trainingRecord.getInsertUser(),trainingRecord.getUpdateUser(),trainingRecord.getDeleteFlag());
		}
		
		return count;
	}
	
	/**
	 * トレーニング記録年月日よりTRAINING_RECORDから実施トレーニング情報を取得
	 * @param date [yyyymmdd]形式の年月日文字列
	 * @return DB結果
	 */
	public List<TrainingRecord> selectTrainingRecord(String date) {
		
		String sql = "select"
				+ "    TRAINING_MENU"
				+ "    , WEIGHT"
				+ "    , SET1"
				+ "    , SET2"
				+ "    , SET3"
				+ "    , SET4"
				+ "    , SET5"
				+ "    , SET6"
				+ "    , TOTAL "
				+ "    , Implementation_FLAG "
				+ "from"
				+ "    TRAINING_RECORD "
				+ "WHERE"
				+ "    DATE_RECORD = ?";

		List<Map<String, Object>> resultList = jdbcTempate.queryForList(sql,date);
		List<TrainingRecord> trainingRecordList = new ArrayList<TrainingRecord>();
		for (Map<String, Object> result : resultList) {
			TrainingRecord trainingRecord = new TrainingRecord();
			trainingRecord.setTrainingMenu((String)result.get("TRAINING_MENU"));
			trainingRecord.setWeight((String)result.get("WEIGHT"));
			trainingRecord.setSt1((String)result.get("SET1"));
			trainingRecord.setSt2((String)result.get("SET2"));
			trainingRecord.setSt3((String)result.get("SET3"));
			trainingRecord.setSt4((String)result.get("SET4"));
			trainingRecord.setSt5((String)result.get("SET5"));
			trainingRecord.setSt6((String)result.get("SET6"));
			trainingRecord.setTotal((String)result.get("TOTAL"));
			trainingRecord.setImplementationFlag(((String)result.get("Implementation_FLAG")));
			
			trainingRecordList.add(trainingRecord);
		}
		return trainingRecordList;
	}
	
	/**
	 * 実施するトレーニングを更新します。
	 *
	 * @param trainingRecordList トレーニング記録情報
	 * @return DB結果
	 */
	public int updateTrainingRecord(List<TrainingRecord> trainingRecordList) {
		
		int count = 0;
		for (TrainingRecord trainingRecord: trainingRecordList) {
			
			String sql = "UPDATE TRAINING_RECORD "
					+ "SET"
					+ "    WEIGHT = ? "
					+ "    , SET1 = ? "
					+ "    , SET2 = ? "
					+ "    , SET3 = ? "
					+ "    , SET4 = ? "
					+ "    , SET5 = ? "
					+ "    , SET6 = ? "
					+ "    , TOTAL = ? "
					+ "    , IMPLEMENTATION_FLAG = ? "
					+ "    , UPDATE_DATE = NOW() "
					+ "    , UPDATE_USER = ? "
					+ "WHERE"
					+ "    USER_ID = ? "
					+ "    AND DATE_RECORD = ? "
					+ "    AND TRAINING_MENU = ? ";
			
			count =  jdbcTempate.update(sql,trainingRecord.getWeight(),trainingRecord.getSt1(),trainingRecord.getSt2(),
					trainingRecord.getSt3(),trainingRecord.getSt4(),trainingRecord.getSt5(),trainingRecord.getSt6(),trainingRecord.getTotal(),
					trainingRecord.getImplementationFlag(),trainingRecord.getUpdateUser(),trainingRecord.getUserId(),trainingRecord.getDateRecord(),trainingRecord.getTrainingMenu());
			
		}
		
		return count;
	}
	
}
