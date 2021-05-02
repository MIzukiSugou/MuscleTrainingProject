package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Login;

/**
 * ログイン画面　リポジトリインターフェース
 * @author 菅生　2021/2/21
 *
 */
public interface LoginRepository {
	
	/**
	 * ログインID/パスワードの存在をチェックします。
	 *
	 * @param userId   ユーザID
	 * @param password パスワード
	 * @return レコード数
	 */
	int checkLogin(String userId, String password);

	/**
	 * ユーザ情報を取得します。
	 *
	 * @param userId ユーザID
	 * @return ユーザ情報
	 */
	Login selectUser(String userId);
	
    /**
     * ログイン情報を更新します。(失敗時)
     *
     * @param userId ユーザID
     */
    public void updateLoginFailure(String userId);

    /**
     * ログイン情報を更新します。(成功時)
     *
     * @param userId ユーザID
     */
    public void updateLoginSuccess(String userId);

}
