package com.example.demo.app.common;

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

}
