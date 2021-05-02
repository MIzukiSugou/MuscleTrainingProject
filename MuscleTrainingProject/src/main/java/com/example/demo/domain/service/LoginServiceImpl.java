package com.example.demo.domain.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.ErrorStatement;
import com.example.demo.app.login.LoginForm;
import com.example.demo.domain.entity.Login;
import com.example.demo.domain.repository.LoginRepositoryImpl;

/**
 * ログイン画面　サービスクラス
 * 
 * @author 菅生　2021/2/18
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	private final LoginRepositoryImpl repository;
	
	@Autowired
	LoginServiceImpl(LoginRepositoryImpl repository){
		this.repository = repository;
	}
	
    /**
     * 入力チェックを行います。
     *
     * @param form ログインフォーム
     * @return true:エラー false:正常
     */
	@Override
	public boolean checkInput(LoginForm form) {
		
		
		 //UserId
        String userId = form.getUserId();
        //PassWord
        String password = form.getPassword();
        
        int count = repository.checkLogin(userId, password);
        
        if (count== 0) {
        	
            // ユーザ情報が存在しない場合
            form.setErrorMessage(ErrorStatement.LOGIN_ERROR);
            // ログイン失敗時のユーザ情報更新クエリを実行
            repository.updateLoginFailure(form.getUserId());
            return true;
            
        }
        // ユーザ情報が存在する場合
		return false;
	}
	
    /**
     * ログイン処理を行います。
     *
     * @param form ログインフォーム
     * @param session セッション情報
     * @return true:失敗 false:成功
     */
	@Override
	public boolean executeLogin(LoginForm form, HttpSession session) {
		
		 // UserId
        String userId = form.getUserId();
        
        // ユーザ情報の取得
        Login loginUser = repository.selectUser(userId);
        
        if (loginUser == null) {
            // ユーザ情報が存在しない場合
            form.setErrorMessage(ErrorStatement.LOGIN_ERROR);
            return true;
        }
        
        // ユーザ情報をセッションに保存
        session.setAttribute(CommonConst.LOGIN_USER, loginUser);
        // ユーザ情報の更新
        repository.updateLoginSuccess(form.getUserId());
		return false;
		
	}
	
}
