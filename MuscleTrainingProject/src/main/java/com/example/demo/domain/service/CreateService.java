package com.example.demo.domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.create.CreateForm;
import com.example.demo.domain.entity.CreateDao;
import com.example.demo.domain.repository.CreateRepository;

/**
 * アカウント作成画面　サービスクラス
 * 
 * @author 菅生瑞騎 2021/2/21
 *
 */
@Service
public class CreateService{
	
	/**　新規登録時、アカウント権限「一般」*/
	private static final String AUTHORITY = "2";
	
	/**　新規登録時、ログイン回数「0回」*/
	private static final String LOGINFAILURECOUNT = "0";
	
	/**　新規登録時、ログイン失敗回数「0回」*/
	private static final String LOGINCOUNT = "0";
	
	/**　新規登録時、アカウントロックフラグ「ロック無し」*/
	private static final String ACCOUNTLOCKFLAG = "0";
	
	/**　新規登録時、削除フラグ「削除無し」*/
	private static final String DELETEFLAG = "0";
	
	/** 現在日付ををYYYY/MM/DDの形で取得 */
	Calendar today = Calendar.getInstance();
    SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy/MM/dd");
    String strToday = yyyymmdd.format(today.getTime());
    
    /**アカウント作成画面Repository */
	private final CreateRepository repository;

	@Autowired
	CreateService(CreateRepository repository){
		this.repository = repository;
	}
	
    /**
     * アカウント情報の追加を行う。
     */
	public void insertUser(CreateDao create,
			CreateForm createForm) throws Exception {
		
		create.setUserId(createForm.getUserId());
		create.setLastName(createForm.getLastName());
		create.setFirstName(createForm.getFirstName());
		create.setLastNameKana(createForm.getLastNameKana());
		create.setFirstNameKana(createForm.getFirstNameKana());
		create.setPassword(createForm.getPassword());
		create.setAuthority(AUTHORITY);
		create.setLoginCount(LOGINCOUNT);
		create.setLoginFailureCount(LOGINFAILURECOUNT);
		create.setAccountLockFlag(ACCOUNTLOCKFLAG);
		create.setInsertDate(strToday);
		create.setInsertUser(createForm.getUserId());
		create.setUpdateUser(createForm.getUserId());
		create.setDeleteFlag(DELETEFLAG);
		
		repository.insertUser(create);
	}
}
