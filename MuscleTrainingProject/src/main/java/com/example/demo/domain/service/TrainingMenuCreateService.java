package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Create;
import com.example.demo.domain.repository.CreateRepository;

/**
 * アカウント作成画面　サービスクラス
 * 
 * @author 菅生瑞騎 2021/2/21
 *
 */
@Service
public class TrainingMenuCreateService {
	

	private final CreateRepository repository;

	@Autowired
	TrainingMenuCreateService(CreateRepository repository){
		this.repository = repository;
	}
	
    /**
     * アカウント情報の追加を行う。
     */
	public void insertUser(Create create) throws Exception {
		repository.insertUser(create);
	}
}
