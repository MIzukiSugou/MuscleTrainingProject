package com.example.demo.app.common;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Login;

/**
 * ���ʏ�����Service�N���X�ł��B
 *
 * @author ���� 2021/4/10
 */
@Service
@Transactional
public class CommonService {
	
    /**
    * �Z�b�V�����^�C���A�E�g�̃`�F�b�N���s���܂��B
    *
    * @param session �Z�b�V�������
    * @return true:�Z�b�V�����^�C���A�E�g false:�Z�b�V��������
     */
   public boolean checkSessionTimeOut(HttpSession session) {

       if (session == null ||
               session.getAttribute(CommonConst.LOGIN_USER) == null) {
           return true;
       }

       return false;
   }
   
   /**
   * �Z�b�V�������烍�O�C�����[�U�[�̏��擾���s���܂��B
   *
   * @param session �Z�b�V�������
   * @return loginUser ���O�C�����[�U�[���
    */
   public Login getUserFullName(HttpSession session) {
	   
		// ���[�U�����Z�b�V��������擾
		Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
		
		return loginUser;
   }

}
