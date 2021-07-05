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
   
	/**
	 * ���t�����擾���܂��B
	 * @param trainingRecordForm �g���[�j���O�L�^��ʃt�H�[��
	 */
	public Map<String, String> setDate() {
		
		//���ݓ��t��YYYY/MM/DD�̌`�Ŏ擾
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
