package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Create;

/**
 * アカウント作成画面　リポジトリインターフェース
 * 
 * @author 菅生瑞騎
 *
 */
public interface CreateRepository {

	/**
	 * ユーザー情報の追加を行う。
	 * 
	 * @param create
	 * @return
	 */
	int insertUser(Create create);

}
