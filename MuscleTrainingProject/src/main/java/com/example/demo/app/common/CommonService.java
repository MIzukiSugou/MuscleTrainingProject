package com.example.demo.app.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.LoginDao;

/**
 * 共通処理のServiceクラスです。
 *
 * @author 菅生 2021/4/10
 */
@Service
@Transactional
public class CommonService {
	
    /**
    * セッションタイムアウトのチェックを行います。
    *
    * @param session セッション情報
    * @return true:セッションタイムアウト false:セッション正常
     */
   public boolean checkSessionTimeOut(HttpSession session) {

       if (session == null ||
               session.getAttribute(CommonConst.LOGIN_USER) == null) {
           return true;
       }

       return false;
   }
   
   /**
   * セッションからログインユーザーの情報取得を行います。
   *
   * @param session セッション情報
   * @return loginUser ログインユーザー情報
    */
   public LoginDao getUserFullName(HttpSession session) {
	   
		// ユーザ情報をセッションから取得
		LoginDao loginUser = (LoginDao) session.getAttribute(CommonConst.LOGIN_USER);
		
		return loginUser;
   }
   
	/**
	 * 日付情報を(YYYY-MM-DD)で取得します。
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public String setDate() {
		
		//現在日付をYYYY/MM/DDの形で取得
		Calendar today = Calendar.getInstance();
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		String Today = yyyymmdd.format(today.getTime());
		
		return Today;
	}

}
