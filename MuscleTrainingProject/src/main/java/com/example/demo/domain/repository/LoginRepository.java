package com.example.demo.domain.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Login;

/**
 * ログイン画面　リポジトリクラス
 * @author 菅生　2021/2/21
 *
 */
@Repository
public class LoginRepository {

	private final JdbcTemplate jdbcTempate;

	@Autowired
	public LoginRepository(JdbcTemplate jdbcTempate) {
		this.jdbcTempate = jdbcTempate;
	}

	/**
	 * ログインID/パスワードの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @param password パスワード
	 * @return レコード数
	 */
	public int checkLogin(String userId, String password) {

		String sql = "SELECT"
				+ "    COUNT(*) "
				+ "FROM"
				+ "    USER_MANAGEMENT "
				+ "WHERE"
				+ "    USER_ID = ? "
				+ "    AND PASSWORD = ? "
				+ "    AND ACCOUNT_LOCK_FLAG = 0 "
				+ "    AND DELETE_FLAG = 0";

		int count = jdbcTempate.queryForObject(sql, new Object[] { userId, password }, Integer.class);

		return count;
	}

	/**
	 * ユーザ情報を取得します。
	 *
	 * @param userId ユーザID
	 * @return ユーザ情報
	 */
	public Login selectUser(String userId) {

		String sql = "SELECT"
				+ "    USER_ID"
				+ "    , LAST_NAME"
				+ "    , FIRST_NAME"
				+ "    , AUTHORITY "
				+ "FROM"
				+ "    USER_MANAGEMENT "
				+ "WHERE"
				+ "    USER_ID = ? "
				+ "    AND DELETE_FLAG = 0";

		Map<String, Object> result = jdbcTempate.queryForMap(sql, new Object[] { userId });

		Login login = new Login();

		login.setUserId((String) result.get("USER_ID"));
		login.setFirstName((String) result.get("FIRST_NAME"));
		login.setLastName((String) result.get("LAST_NAME"));

		return login;

	}

	/**
	 * ログイン情報を更新します。(失敗時)
	 *
	 * @param userId ユーザID
	 */
	public void updateLoginFailure(String userId) {

		String sql = "UPDATE USER_MANAGEMENT "
				+ "SET"
				+ "    LOGIN_FAILURE_COUNT = CASE "
				+ "        WHEN LOGIN_FAILURE_COUNT IS NULL "
				+ "            THEN 1 "
				+ "        ELSE (LOGIN_FAILURE_COUNT + 1) "
				+ "        END"
				+ "    , ACCOUNT_LOCK_FLAG = CASE "
				+ "        WHEN ( "
				+ "            LOGIN_FAILURE_COUNT IS NOT NULL "
				+ "            AND (LOGIN_FAILURE_COUNT + 1) >= 10"
				+ "        ) "
				+ "            THEN '1' "
				+ "        ELSE '0' "
				+ "        END"
				+ "    , UPDATE_DATE = NOW()"
				+ "    , UPDATE_USER = ? "
				+ "WHERE"
				+ "    USER_ID = ? "
				+ "    AND DELETE_FLAG = 0";
		
		jdbcTempate.update(sql,userId,userId);

	}

	/**
	 * ログイン情報を更新します。(成功時)
	 *
	 * @param userId ユーザID
	 */
	public void updateLoginSuccess(String userId) {
		
		String sql = "UPDATE USER_MANAGEMENT "
				+ "SET"
				+ "    LAST_LOGIN_DATE = NOW()"
				+ "    , LOGIN_COUNT = ( "
				+ "        CASE "
				+ "            WHEN LOGIN_COUNT IS NULL "
				+ "                THEN 1 "
				+ "            ELSE LOGIN_COUNT + 1 "
				+ "            END"
				+ "    ) "
				+ "    , LOGIN_FAILURE_COUNT = 0"
				+ "    , UPDATE_DATE = NOW()"
				+ "    , UPDATE_USER = ? "
				+ "WHERE"
				+ "    USER_ID = ? "
				+ "    AND DELETE_FLAG = 0";
		
		jdbcTempate.update(sql,userId,userId);
	}

}
