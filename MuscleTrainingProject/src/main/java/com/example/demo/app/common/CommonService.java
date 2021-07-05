package com.example.demo.app.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Login;

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
   public Login getUserFullName(HttpSession session) {
	   
		// ユーザ情報をセッションから取得
		Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
		
		return loginUser;
   }
   
	/**
	 * 日付情報を取得します。
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public Map<String, String> setDate() {
		
		//現在日付をYYYY/MM/DDの形で取得
		Calendar today = Calendar.getInstance();
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		String strToday = yyyymmdd.format(today.getTime());
		
		Map<String, String> days = new HashMap<>();
		
		days.put(CommonConst.YEAR, strToday.substring(0, 4));
		days.put(CommonConst.MONTH, strToday.substring(4, 6));
		days.put(CommonConst.DAY, strToday.substring(6, 8));
		
		return days;
	}

}
