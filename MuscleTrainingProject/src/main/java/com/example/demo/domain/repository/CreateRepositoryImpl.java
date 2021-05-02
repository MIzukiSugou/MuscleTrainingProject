package com.example.demo.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Create;

/**
 * アカウント作成画面のリポジトリクラスです。
 * 
 * @author 菅生瑞騎 2021/2/21
 *
 */
@Repository
public class CreateRepositoryImpl implements CreateRepository {

	private final JdbcTemplate jdbcTempate;

	@Autowired
	public CreateRepositoryImpl(JdbcTemplate jdbcTempate) {
		this.jdbcTempate = jdbcTempate;
	}

	/**
	 * ユーザー情報の追加を行う。
	 * 
	 * @param create
	 * @return
	 */
	@Override
	public int insertUser(Create create) {
		
		String sql = "INSERT "
				+ "INTO USER_MANAGEMENT( "
				+ "    USER_ID"
				+ "    , LAST_NAME"
				+ "    , FIRST_NAME"
				+ "    , LAST_NAME_KANA"
				+ "    , FIRST_NAME_KANA"
				+ "    , PASSWORD"
				+ "    , AUTHORITY"
				+ "    , LOGIN_COUNT"
				+ "    , LOGIN_FAILURE_COUNT"
				+ "    , LAST_LOGIN_DATE"
				+ "    , ACCOUNT_LOCK_FLAG"
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
				+ "    , NOW() "
				+ "    , ? "
				+ "    , ? "
				+ "    , ? "
				+ "    , NOW() "
				+ "    , ? "
				+ "    , ?"
				+ ")";
		
		int count = jdbcTempate.update(sql,create.getUserId(),create.getLastName(),create.getFirstName(),
				create.getLastNameKana(),create.getFirstNameKana(),create.getPassword(),create.getAuthority(),
				create.getLoginCount(),create.getLoginFailureCount(),create.getAccountLockFlag(),
				create.getInsertDate(),create.getInsertUser(),create.getUpdateUser(),create.getDeleteFlag());
		
		return count;
	}

}
