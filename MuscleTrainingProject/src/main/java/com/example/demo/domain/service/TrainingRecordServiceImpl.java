package com.example.demo.domain.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.trainingrecord.TrainingRecordForm;
import com.example.demo.app.trainingrecord.TrainingRecordListForm;
import com.example.demo.domain.entity.Login;
import com.example.demo.domain.entity.TrainingRecord;
import com.example.demo.domain.repository.TrainingRecordRepository;

@Service
public class TrainingRecordServiceImpl implements TrainingRecordService {
	
	/** DELETE_FLG ：　0 */
	private final String DELETE_FLG_ZERO = "0";
	
	/**トレーニング記録画面Repository */
	private final TrainingRecordRepository repository;
	
	@Autowired
	TrainingRecordServiceImpl(TrainingRecordRepository repository){
		this.repository = repository;
	}
	
	/**
	 * ログインユーザーの登録トレーニングメニュー情報を取得
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 */
	@Override
	public boolean selectMenu(TrainingRecordForm trainingRecordForm, HttpSession session)  {
		
        // セッションからユーザ情報の取得
        Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
        
        //ユーザーIDの取得
        String userId = loginUser.getUserId();
        
        //ログイン中のユーザーIDでの登録トレーニングメニューの有無を確認
        int count = repository.checkUserIdMenu(userId);
        
        if (count== 0) {
        	//ログインユーザーIDでトレーニングメニューが存在無し
            return true;
            
        }else {
        	//ログインユーザーIDでトレーニングメニューが存在有り
        	//トレーニングメニューの取得
            List<String> menuList = repository.selectMenu(userId);
            
            //TODO: エラー回避　削除予定
            //ListからMapに変換
            Map<String, String> menuMap = new LinkedHashMap<String, String>();
            for (int i = 0; i < menuList.size();i++) {
            	menuMap.put("00" + (1 + i), menuList.get(i));
            }
            
            
            List<TrainingRecordListForm> trainingRecordFormList = new ArrayList<>();
            for (String menu: menuList) {
            	TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
            	trainingRecordListForm.setMenu(menu);
            	trainingRecordFormList.add(trainingRecordListForm);
            }
            trainingRecordForm.setTrainingRecordList(trainingRecordFormList);
            
            return false;
        }
	}
	
	/**
	 * ログインユーザーがトレーニングを行った日付の年月日を取得します。
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 */
	@Override
	public boolean selectTrainingRecordDate(TrainingRecordForm trainingRecordForm, HttpSession session)  {
		
        // セッションからユーザ情報の取得
        Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
        
        //ユーザーIDの取得
        String userId = loginUser.getUserId();
        
		//画面表示年月日情報を取得してトレーニング記録画面フォームにセットします
		setDate(trainingRecordForm);
        
        //ログイン中のユーザーIDでのトレーニング記録の有無を確認
        int count = repository.checkUserIdTrainingRecord(userId);
        
        if (count== 0) {
        	//ログインユーザーIDでトレーニング記録無し
            return true;
            
        }else {
        	//ログインユーザーIDでトレーニング記録有り
        	
        	//日時情報の取得
            List<String> dateList = repository.selectTrainingRecordDate(userId);
            
            //重複項目の削除
            dateList = new ArrayList<String>(new HashSet<>(dateList));
            
            //ListからMapに変換
            Map<String, String> dateMap = new LinkedHashMap<String, String>();
            for (int i = 0; i < dateList.size();i++) {
            	dateMap.put("00" + (1 + i), dateList.get(i));
            }
            
            trainingRecordForm.setDate(dateMap.get("001"));
            trainingRecordForm.setPulldownDate(dateMap);
            
            return false;
        }
	}
	
	/**
	 * 実施するトレーニングを記録（追加）
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	@Override
	public void insertTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) throws Exception {
		
		//ログインユーザーのセッション情報を取得
		Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
		
		//日付情報（当日）を「トレーニング記録画面フォーム」にセット
		setDate(trainingRecordForm);
		
		//日付情報（当日）をYYYYMMDDで取得
		String dateRecord = trainingRecordForm.getYear() + trainingRecordForm.getMonth() + trainingRecordForm.getDay();
		
		//DB登録用情報を格納する「トレーニング記録:entity」リストの作成
		List<TrainingRecord> trainingRecordList = new ArrayList<>();	
		
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordList();
		
		//「トレーニング記録:entity」リストに「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを
		// 移し替え作業かつ、他DB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size(); i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecord trainingRecord = new TrainingRecord();
			trainingRecord.setUserId(loginUser.getUserId());
			trainingRecord.setDateRecord(dateRecord);
			trainingRecord.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
			trainingRecord.setWeight(trainingRecordFormList.get(i).getWeight() + "");
			trainingRecord.setSt1(trainingRecordFormList.get(i).getSt1() + "");
			trainingRecord.setSt2(trainingRecordFormList.get(i).getSt2() + "");
			trainingRecord.setSt3(trainingRecordFormList.get(i).getSt3() + "");
			trainingRecord.setSt4(trainingRecordFormList.get(i).getSt4() + "");
			trainingRecord.setSt5(trainingRecordFormList.get(i).getSt5() + "");
			trainingRecord.setSt6(trainingRecordFormList.get(i).getSt6() + "");
			trainingRecord.setTotal(trainingRecordFormList.get(i).getTotal() + "");
			trainingRecord.setImplementationFlag(trainingRecordFormList.get(i).getImplementationFlag());
			trainingRecord.setInsertUser(loginUser.getUserId());
			trainingRecord.setUpdateUser(loginUser.getUserId());
			trainingRecord.setDeleteFlag(DELETE_FLG_ZERO);
			trainingRecordList.add(trainingRecord);
		}
		//ログインユーザーがトレーニングを行った日付の年月日のプルダウン作成
		selectTrainingRecordDate(trainingRecordForm, session);
		
		//DB記録処理(トレーニング情報)
		repository.insertTrainingRecord(trainingRecordList);
		
		//ログインユーザーがトレーニングを行った日付の年月日のプルダウン作成
		selectTrainingRecordDate(trainingRecordForm, session);
	}
	
	/**
	 * 実施するトレーニングを更新
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	@Override
	public void updateTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) throws Exception {
		
		//ログインユーザーのセッション情報を取得
		Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
		
		//日付情報（当日）を「トレーニング記録画面フォーム」にセット
		setDate(trainingRecordForm);
		
		//プルダウンに選択されている年月日情報を取得
		String date = trainingRecordForm.getDate();
		
		//DB登録用情報を格納する「トレーニング記録:entity」リストの作成
		List<TrainingRecord> trainingRecordList = new ArrayList<>();	
		
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordList();
		
		//「トレーニング記録:entity」リストに「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを
		// 移し替え作業かつ、他DB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size(); i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecord trainingRecord = new TrainingRecord();
			trainingRecord.setUserId(loginUser.getUserId());
			trainingRecord.setDateRecord(date);
			trainingRecord.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
			trainingRecord.setWeight(trainingRecordFormList.get(i).getWeight() + "");
			trainingRecord.setSt1(trainingRecordFormList.get(i).getSt1() + "");
			trainingRecord.setSt2(trainingRecordFormList.get(i).getSt2() + "");
			trainingRecord.setSt3(trainingRecordFormList.get(i).getSt3() + "");
			trainingRecord.setSt4(trainingRecordFormList.get(i).getSt4() + "");
			trainingRecord.setSt5(trainingRecordFormList.get(i).getSt5() + "");
			trainingRecord.setSt6(trainingRecordFormList.get(i).getSt6() + "");
			trainingRecord.setTotal(trainingRecordFormList.get(i).getTotal() + "");
			trainingRecord.setImplementationFlag(trainingRecordFormList.get(i).getImplementationFlag());
			trainingRecord.setUpdateUser(loginUser.getUserId());
			trainingRecordList.add(trainingRecord);
		}
		
		//DB更新処理(トレーニング情報)
		repository.updateTrainingRecord(trainingRecordList);
		
		//ログインユーザーがトレーニングを行った日付の年月日のプルダウン作成
		selectTrainingRecordDate(trainingRecordForm, session);
	}
	
	/**
	 * トレーニング記録画面フォームの選択されているプルダウンの年月日よりDBから実施トレーニング情報を取得
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	@Override
	public void selectTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) {

		//プルダウンに選択されている年月日情報を取得
		String date = trainingRecordForm.getDate();
		
		//年月日よりDBから実施トレーニング情報を取得して、「トレーニング記録:entity」リストに設定
		List<TrainingRecord> trainingRecordList = repository.selectTrainingRecord(date);
		
		//画面表示年月日情報を取得してトレーニング記録画面フォームにセットします
		setDate(trainingRecordForm);
		
		//ログインユーザーがトレーニングを行った日付の年月日のプルダウン作成
		selectTrainingRecordDate(trainingRecordForm, session);
		
		//「トレーニング記録画面フォーム」にセットする「トレーニング記録画面フォーム」のリストを作成
		List<TrainingRecordListForm> trainingRecordListForm = new ArrayList<>();
		
		//DBより取得した「トレーニング記録:entity」リストを「トレーニング記録画面フォーム」のリストにセット
		for (int i = 0; i < trainingRecordList.size(); i++ ) {
			
			TrainingRecordListForm addListForm = new TrainingRecordListForm();
			
			addListForm.setMenu(trainingRecordList.get(i).getTrainingMenu());
			addListForm.setWeight(Integer.parseInt(trainingRecordList.get(i).getWeight()));
			addListForm.setSt1(Integer.parseInt(trainingRecordList.get(i).getSt1()));
			addListForm.setSt2(Integer.parseInt(trainingRecordList.get(i).getSt2()));
			addListForm.setSt3(Integer.parseInt(trainingRecordList.get(i).getSt3()));
			addListForm.setSt4(Integer.parseInt(trainingRecordList.get(i).getSt4()));
			addListForm.setSt5(Integer.parseInt(trainingRecordList.get(i).getSt5()));
			addListForm.setSt6(Integer.parseInt(trainingRecordList.get(i).getSt6()));
			addListForm.setTotal(Integer.parseInt(trainingRecordList.get(i).getTotal()));
			addListForm.setImplementationFlag(trainingRecordList.get(i).getImplementationFlag());
			
			trainingRecordListForm.add(addListForm);
		}
		//「トレーニング記録画面フォーム」のリストを「トレーニング記録画面フォーム」にセット
		trainingRecordForm.setTrainingRecordList(trainingRecordListForm);
		
	}
	
	/**
	 * 「トレーニング記録画面フォーム」に日付情報を格納します。
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	@Override
	public void setDate(TrainingRecordForm trainingRecordForm) {
		
		//現在日付をYYYY/MM/DDの形で取得
		Calendar today = Calendar.getInstance();
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		String strToday = yyyymmdd.format(today.getTime());
		
		trainingRecordForm.setYear(strToday.substring(0, 4));
		trainingRecordForm.setMonth(strToday.substring(4, 6));
		trainingRecordForm.setDay(strToday.substring(6, 8));
	}
	
}
