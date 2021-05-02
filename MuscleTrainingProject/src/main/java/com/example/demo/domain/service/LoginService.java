package com.example.demo.domain.service;

import javax.servlet.http.HttpSession;

import com.example.demo.app.login.LoginForm;

/**
 * ログイン画面　サービスインターフェース
 * 
 * @author 菅生　2021/2/18
 *
 */
public interface LoginService {
	
    /**
     * 入力チェックを行います。
     *
     * @param form ログインフォーム
     * @return true:エラー false:正常
     */
	public boolean checkInput(LoginForm form);
	
    /**
     * ログイン処理を行います。
     *
     * @param form ログインフォーム
     * @param session セッション情報
     * @return true:失敗 false:成功
     */
	public boolean executeLogin(LoginForm form, HttpSession session)  ;
	
}
