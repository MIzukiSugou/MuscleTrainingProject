package com.example.demo.domain.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Create;

/**
 * アカウント登録画面　サービスインターフェース
 * 
 * @author 菅生瑞騎
 *
 */
@Service
public interface CreateService {

	/**
	 * アカウント情報の追加を行う。
	 * 
	 * @param Create
	 * @return
	 */
	void insertUser(Create create) throws Exception;

}
