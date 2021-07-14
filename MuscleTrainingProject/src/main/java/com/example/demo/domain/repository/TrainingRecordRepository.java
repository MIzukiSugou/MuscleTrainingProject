package com.example.demo.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.demo.app.common.CommonConst;
import com.example.demo.domain.entity.TrainingRecordDao;

/**
 * トレーニング記録画面　リポジトリクラス
 * @author 菅生　2021/2/21
 *
 */
@Transactional(rollbackFor = Exception.class)
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
	public int checkUserIdMenu(String userId, String dateRecord) {

		String sql = "SELECT "
				+ "    COUNT(*) "
				+ "FROM"
				+ "    TRAINING_MENU_MANAGEMENT "
				+ "WHERE"
				+ "    USER_ID = ? "
				+ "    AND DELETE_FLAG = 0"
				+ "    AND NOT TRAINING_MENU IN ( "
				+ "        SELECT distinct"
				+ "            TRAINING_MENU "
				+ "        FROM"
				+ "            TRAINING_RECORD "
				+ "        WHERE"
				+ "            TRAINING_RECORD.INSERT_DATE = ? "
				+ "            AND USER_ID = ?"
				+ "            AND DELETE_FLAG = 0"
				+ "    )";

		int count = jdbcTempate.queryForObject(sql, new Object[] { userId, dateRecord, userId}, Integer.class);

		return count;
	}
	
	/**
	 * TRAINING_RECORDテーブルのログインIDの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @return レコード数
	 */
	public int checkUserIdTrainingRecord(String userId, String dateRecord) {

		String sql = "SELECT"
				+ "    COUNT(*)"
				+ "FROM"
				+ "    TRAINING_RECORD "
				+ "WHERE"
				+ "    USER_ID = ?"
				+ "    AND INSERT_DATE = ?"
				+ "    AND DELETE_FLAG = 0";

		int count = jdbcTempate.queryForObject(sql, new Object[] { userId,dateRecord}, Integer.class);

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
	public List<String> selectMenu(String userId, String dateRecord) {

		String sql = "SELECT "
				+ "    TRAINING_MENU "
				+ "FROM"
				+ "    TRAINING_MENU_MANAGEMENT "
				+ "WHERE"
				+ "    USER_ID = ? "
				+ "    AND DELETE_FLAG = 0"
				+ "    AND NOT TRAINING_MENU IN ( "
				+ "        SELECT distinct"
				+ "            TRAINING_MENU "
				+ "        FROM"
				+ "            TRAINING_RECORD "
				+ "        WHERE"
				+ "            TRAINING_RECORD.INSERT_DATE = ? "
				+ "            AND USER_ID = ?"
				+ "            AND DELETE_FLAG = 0"
				+ "    )";
		
		List<Map<String, Object>> resultList = jdbcTempate.queryForList(sql, new Object[] { userId, dateRecord, userId });
		
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
	 * @param trainingRecordDaoList トレーニング記録情報
	 * @return DB結果(重複の場合メニュー名)
	 */
	public String[] insertTrainingRecord(List<TrainingRecordDao> trainingRecordDaoList) {
		
		String[] conditionMenu = new String[2];
		
		conditionMenu[0] = CommonConst.INSERT_SUCCESS;
		conditionMenu[1] = "";
		
		for (TrainingRecordDao trainingRecordDao: trainingRecordDaoList) {
			
			//該当トレーニングメニューで削除フラフ済みの物があるかチェックを行う
			String selectSql = "select"
					+ "    COUNT(*)"
					+ "from"
					+ "    TRAINING_RECORD "
					+ "WHERE"
					+ "    INSERT_DATE = ?"
					+ "	   AND USER_ID = ?"
					+ "	   AND TRAINING_MENU = ?"
					+ "	   AND DELETE_FLAG = 1";
			
			int count = jdbcTempate.queryForObject(selectSql, new Object[] { trainingRecordDao.getDateRecord(),trainingRecordDao.getInsertUser(),trainingRecordDao.getTrainingMenu()}, Integer.class);
			
			//有り
			if (count > 0) {
				String sql = "UPDATE TRAINING_RECORD "
						+ "SET"
						+ "    WEIGHT_KG = ? "
						+ "    , SET1_COUNT = ? "
						+ "    , SET2_COUNT = ? "
						+ "    , SET3_COUNT = ? "
						+ "    , SET4_COUNT = ? "
						+ "    , SET5_COUNT = ? "
						+ "    , SET6_COUNT = ? "
						+ "    , TOTAL_COUNT = ? "
						+ "    , UPDATE_DATE = NOW() "
						+ "    , UPDATE_USER = ? "
						+ "    , DELETE_FLAG = ? "
						+ "WHERE"
						+ "    USER_ID = ? "
						+ "    AND DATE_RECORD = ? "
						+ "    AND TRAINING_MENU = ? ";
				
				try {
					jdbcTempate.update(sql,trainingRecordDao.getWeight(),trainingRecordDao.getSt1(),trainingRecordDao.getSt2(),
							trainingRecordDao.getSt3(),trainingRecordDao.getSt4(),trainingRecordDao.getSt5(),trainingRecordDao.getSt6(),trainingRecordDao.getTotal(),
							trainingRecordDao.getUpdateUser(),"0",trainingRecordDao.getUserId(),trainingRecordDao.getDateRecord(),trainingRecordDao.getTrainingMenu());
					//message = CommonConst.UPDATE_COMPLATE;
					
				//エラーの場合ロールバック
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					//message = CommonConst.UPDATE_ERROR;
				}
				
			//無し
			} else {
				String insertSql = "INSERT "
						+ "INTO TRAINING_RECORD( "
						+ "    USER_ID"
						+ "    , DATE_RECORD"
						+ "    , TRAINING_MENU"
						+ "    , WEIGHT_KG"
						+ "    , SET1_COUNT"
						+ "    , SET2_COUNT"
						+ "    , SET3_COUNT"
						+ "    , SET4_COUNT"
						+ "    , SET5_COUNT"
						+ "    , SET6_COUNT"
						+ "    , TOTAL_COUNT"
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
						+ "    , ?"
						+ "    , ? "
						+ "    , NOW()"
						+ "    , ? "
						+ "    , ? "
						+ ")";
				
				try {
					jdbcTempate.update(insertSql,trainingRecordDao.getUserId(),trainingRecordDao.getDateRecord(),trainingRecordDao.getTrainingMenu(),
							trainingRecordDao.getWeight(),trainingRecordDao.getSt1(),trainingRecordDao.getSt2(),
							trainingRecordDao.getSt3(),trainingRecordDao.getSt4(),trainingRecordDao.getSt5(),trainingRecordDao.getSt6(),trainingRecordDao.getTotal(),
							trainingRecordDao.getDateRecord(),trainingRecordDao.getInsertUser(),trainingRecordDao.getUpdateUser(),trainingRecordDao.getDeleteFlg());
				
				//既に同日に同じトレーニングのDBへの追加が行われていた場合の処理	
				}catch (DuplicateKeyException e) {
					//エラーの場合ロールバック
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					//同日実施されていたメニュー名を取得
					conditionMenu[0] = CommonConst.INSERT_DUPLICATION_FAILURE;
					conditionMenu[1] = trainingRecordDao.getTrainingMenu();
					
				//その他DB追加時のエラー時の処理	
				} catch (Exception e) {
					//エラーの場合ロールバック
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					
					conditionMenu[0] = CommonConst.INSERT_FAILURE;
				}
				
			}
		}
			
		
		return conditionMenu;
	}
	
	/**
	 * トレーニング記録年月日よりTRAINING_RECORDから実施トレーニング情報を取得
	 * @param date [yyyymmdd]形式の年月日文字列
	 * @return DB結果
	 */
	public List<TrainingRecordDao> selectTrainingRecord(String date, String userId) {
		
		String sql = "select"
				+ "    TRAINING_MENU"
				+ "    , WEIGHT_KG"
				+ "    , SET1_COUNT"
				+ "    , SET2_COUNT"
				+ "    , SET3_COUNT"
				+ "    , SET4_COUNT"
				+ "    , SET5_COUNT"
				+ "    , SET6_COUNT"
				+ "    , TOTAL_COUNT "
				+ "from"
				+ "    TRAINING_RECORD "
				+ "WHERE"
				+ "    INSERT_DATE = ?"
				+ "	   AND USER_ID = ?"
				+ "	   AND DELETE_FLAG = 0";
		
		List<TrainingRecordDao> trainingRecordDaoList = new ArrayList<TrainingRecordDao>();
		try {
			List<Map<String, Object>> resultList = jdbcTempate.queryForList(sql,date,userId);
			
			for (Map<String, Object> result : resultList) {
				TrainingRecordDao trainingRecordDao = new TrainingRecordDao();
				trainingRecordDao.setTrainingMenu((String)result.get("TRAINING_MENU"));
				trainingRecordDao.setWeight((String)result.get("WEIGHT_KG"));
				trainingRecordDao.setSt1((String)result.get("SET1_COUNT"));
				trainingRecordDao.setSt2((String)result.get("SET2_COUNT"));
				trainingRecordDao.setSt3((String)result.get("SET3_COUNT"));
				trainingRecordDao.setSt4((String)result.get("SET4_COUNT"));
				trainingRecordDao.setSt5((String)result.get("SET5_COUNT"));
				trainingRecordDao.setSt6((String)result.get("SET6_COUNT"));
				trainingRecordDao.setTotal((String)result.get("TOTAL_COUNT"));
				
				trainingRecordDaoList.add(trainingRecordDao);
			}	
		} catch (Exception e) {
			
			return trainingRecordDaoList;
		}
		
		return trainingRecordDaoList;
	}
	
	/**
	 * 実施するトレーニングを更新します。
	 *
	 * @param trainingRecordDaoList トレーニング記録情報
	 * @return DB結果
	 */
	public String updateTrainingRecord(List<TrainingRecordDao> trainingRecordDaoList) {
		
		String message = "";
		
		for (TrainingRecordDao trainingRecordDao: trainingRecordDaoList) {
			//削除
			if (trainingRecordDao.getDeleteFlg().equals("1")) {
				String sql = "UPDATE TRAINING_RECORD "
						+ "SET"
						+ "    DELETE_FLAG = ? "
						+ "    , UPDATE_DATE = NOW() "
						+ "    , UPDATE_USER = ? "
						+ "WHERE"
						+ "    USER_ID = ? "
						+ "    AND DATE_RECORD = ? "
						+ "    AND TRAINING_MENU = ? ";
				
				try {
					jdbcTempate.update(sql,trainingRecordDao.getDeleteFlg(),trainingRecordDao.getUpdateUser(),
							trainingRecordDao.getUserId(),trainingRecordDao.getDateRecord(),trainingRecordDao.getTrainingMenu());
					message = CommonConst.UPDATE_COMPLATE;
					
				//エラーの場合ロールバック
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					message = CommonConst.UPDATE_ERROR;
				}
				
			//更新
			}else if (trainingRecordDao.getDeleteFlg().equals("0")) {
				String sql = "UPDATE TRAINING_RECORD "
						+ "SET"
						+ "    WEIGHT_KG = ? "
						+ "    , SET1_COUNT = ? "
						+ "    , SET2_COUNT = ? "
						+ "    , SET3_COUNT = ? "
						+ "    , SET4_COUNT = ? "
						+ "    , SET5_COUNT = ? "
						+ "    , SET6_COUNT = ? "
						+ "    , TOTAL_COUNT = ? "
						+ "    , UPDATE_DATE = NOW() "
						+ "    , UPDATE_USER = ? "
						+ "WHERE"
						+ "    USER_ID = ? "
						+ "    AND DATE_RECORD = ? "
						+ "    AND TRAINING_MENU = ? ";
				
				try {
					jdbcTempate.update(sql,trainingRecordDao.getWeight(),trainingRecordDao.getSt1(),trainingRecordDao.getSt2(),
							trainingRecordDao.getSt3(),trainingRecordDao.getSt4(),trainingRecordDao.getSt5(),trainingRecordDao.getSt6(),trainingRecordDao.getTotal(),
							trainingRecordDao.getUpdateUser(),trainingRecordDao.getUserId(),trainingRecordDao.getDateRecord(),trainingRecordDao.getTrainingMenu());
					message = CommonConst.UPDATE_COMPLATE;
					
				//エラーの場合ロールバック
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					message = CommonConst.UPDATE_ERROR;
				}
			}
		}
		
		return message;
	}
	
}
