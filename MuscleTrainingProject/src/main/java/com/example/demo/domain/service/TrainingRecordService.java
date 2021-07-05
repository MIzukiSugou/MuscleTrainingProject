package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.CommonService;
import com.example.demo.app.common.View;
import com.example.demo.app.trainingrecord.TrainingRecordForm;
import com.example.demo.app.trainingrecord.TrainingRecordListForm;
import com.example.demo.domain.entity.Login;
import com.example.demo.domain.entity.TrainingRecord;
import com.example.demo.domain.repository.TrainingRecordRepository;

@Service
public class TrainingRecordService {
	
	/** メニュープルダウン初期文言*/
	private final String MENUMAP_INITIAL = "トレーニングメニューを選択してください";
	
	/** DB登録完了メッセージ */
	private final String RECORD_COMPLETION = "トレーニング記録が完了致しました。";
	
	/** DB登録時(重複)エラーメッセージ */
	private final String TODAY_REGISTERED = "既に本日記録済みです。更新を行ってください。";
	
	/** DB登録時エラーメッセージ */
	private final String INSERT_ERROR = "DB更新時にエラーが発生しました。最初からやり直してください。";
	
	/** menuMap　sessionキー*/
	private final String MENUMAP = "MENUMAP";
	
	/** DELETE_FLG ：　0 */
	private final String DELETE_FLG_ZERO = "0";
	
	/**トレーニング記録画面Repository */
	private final TrainingRecordRepository repository;
	
	@Autowired
	TrainingRecordService(TrainingRecordRepository repository){
		this.repository = repository;
	}
	
	/**
	 * 「トレーニング記録画面」の共通処理を記述
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public void common(TrainingRecordForm trainingRecordForm, HttpSession session,Model model) {
		
		/**共通処理Service */
	    CommonService commonService = new CommonService();
	    
	    //日付情報をtrainingRecordFormに格納
		Map<String, String> days = commonService.setDate();
		trainingRecordForm.setYear(days.get(CommonConst.YEAR));
		trainingRecordForm.setMonth(days.get(CommonConst.MONTH));
		trainingRecordForm.setDay(days.get(CommonConst.DAY));
		
		// ユーザ情報をセッションから取得
        Login loginUser = commonService.getUserFullName(session);
        String userFullName = loginUser.getFirstName() + loginUser.getLastName();
        
        //ユーザーIDの取得
        String userId = loginUser.getUserId();
        trainingRecordForm.setUserId(userId);
        
        model.addAttribute("loginUser", userFullName);
        model.addAttribute("title", View.VIEW_TRAININGRECORD);
		model.addAttribute("trainingRecordForm", trainingRecordForm);

	}
	
	/**
	 * ログインユーザーの登録トレーニングメニュー情報を取得
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 */
	public boolean selectMenu(TrainingRecordForm trainingRecordForm, HttpSession session)  {
		
        //ユーザーIDの取得
        String userId = trainingRecordForm.getUserId();
        
        //ログイン中のユーザーIDでの登録トレーニングメニューの有無を確認
        int count = repository.checkUserIdMenu(userId);
        
        //ログインユーザーIDでトレーニングメニューが存在無し
        if (count== 0) {
            return true;
        
        //ログインユーザーIDでトレーニングメニューが存在有り       
        }else {
        	//トレーニングメニューの取得
            List<String> menuList = repository.selectMenu(userId);
            
            //ListからMapに変換
            Map<String, String> menuMap = new LinkedHashMap<String, String>();
            for (int i = -1; i < menuList.size();i++) {
            	if (i >= 0) {
            		menuMap.put("00" + (1 + i), menuList.get(i));
            	} else {
            		menuMap.put("00" + (1 + i), MENUMAP_INITIAL);
            	}
            }
            
            //メニュープルダウン情報をsessionに格納
            session.setAttribute(MENUMAP, menuMap);
            
            return false;
        }
	}
	
	/**
	 * 初期表示またはボタン押下時の共通処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報	 * 
	 * @param flg　削除：0 追加：1 
	 */
	public void buttonTrainingRecordFormList(TrainingRecordForm trainingRecordForm,
			HttpSession session,
			int trainingMenuIndex) {
		
		List<TrainingRecordListForm> trainingRecordFormList;
		
		//初期表示時の処理を記述
		if (trainingMenuIndex == CommonConst.INITIAL) {
			
			//初期表示の際の１件分(空)の入力情報を格納
	        trainingRecordFormList = new ArrayList<>();
			
	        TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
	        trainingRecordFormList.add(trainingRecordListForm);
	        trainingRecordForm.setTrainingRecordList(trainingRecordFormList);
		
	    //追加ボタン押下時の処理を記述
		} else if (trainingMenuIndex == CommonConst.ADD) {
			
			trainingRecordFormList = trainingRecordForm.getTrainingRecordList();
			
	        TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
	        trainingRecordFormList.add(trainingRecordListForm);
	        trainingRecordForm.setTrainingRecordList(trainingRecordFormList);
		
		//削除ボタン押下時の処理を記述
		} else if (trainingMenuIndex != CommonConst.ADD && trainingMenuIndex != CommonConst.INITIAL && trainingMenuIndex != CommonConst.ERROR) {
			trainingRecordForm.getTrainingRecordList().remove(trainingMenuIndex);
		}
		
		
		//trainingRecordFormList最終位置
		int trainingRecordFormListEndIndex = trainingRecordForm.getTrainingRecordList().size()-1;
		
		//sessionからメニューのプルダウンを取得
		Map<String, String> menuMap = (Map<String, String>) session.getAttribute(MENUMAP);
		
		//trainingRecordFormの最終位置にメニューのプルダウンを格納
		trainingRecordForm.getTrainingRecordList().get(trainingRecordFormListEndIndex).setMenuMap(menuMap);
        
		//プルダウンで選択したトレーニングメニューをtrainingRecordFormListに格納
		if (trainingMenuIndex == CommonConst.ADD && trainingRecordFormListEndIndex >= 1) {
			trainingRecordForm.getTrainingRecordList().get(trainingRecordFormListEndIndex-1).setMenu(trainingRecordForm.getMenu());
		}
		
	}
	
	/**
	 * 実施するトレーニングを記録（追加）
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public String insertTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session, Model model) throws Exception {
		
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordList();
		
		//ユーザーIDの取得
        String userId = trainingRecordForm.getUserId();
		
		//日付情報（当日）をYYYYMMDDで取得
		String dateRecord = trainingRecordForm.getYear() + trainingRecordForm.getMonth() + trainingRecordForm.getDay();
		
		//DB登録用情報を格納する「トレーニング記録:entity」リストの作成
		List<TrainingRecord> trainingRecordList = new ArrayList<>();	
		
		//「トレーニング記録:entity」リストにDB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size() -1; i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecord trainingRecord = new TrainingRecord();
			trainingRecord.setUserId(userId);
			trainingRecord.setDateRecord(dateRecord);
			trainingRecord.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
			trainingRecord.setWeight(trainingRecordFormList.get(i).getWeight() + "");
			trainingRecord.setSt1(trainingRecordFormList.get(i).getSt1() + "");
			trainingRecord.setSt2(trainingRecordFormList.get(i).getSt2() + "");
			trainingRecord.setSt4(trainingRecordFormList.get(i).getSt4() + "");
			trainingRecord.setSt5(trainingRecordFormList.get(i).getSt5() + "");
			trainingRecord.setSt6(trainingRecordFormList.get(i).getSt6() + "");
			trainingRecord.setTotal(trainingRecordFormList.get(i).getTotal() + "");
			trainingRecord.setInsertUser(userId);
			trainingRecord.setUpdateUser(userId);
			trainingRecord.setDeleteFlag(DELETE_FLG_ZERO);
			trainingRecordList.add(trainingRecord);
		}
		
		try {
			//DB記録処理(トレーニング情報)
			repository.insertTrainingRecord(trainingRecordList);
		
		//既に同日にトレーニングのDBへの追加が行われていた場合の処理	
		}catch (DuplicateKeyException e) {
			//trainingRecordFormを設定
			buttonTrainingRecordFormList(trainingRecordForm, session, CommonConst.ERROR);
			model.addAttribute("message", TODAY_REGISTERED);
			return "/trainingrecord_boot";
		
		//その他DB追加時のエラー時の処理	
		} catch (Exception e) {
			//trainingRecordFormを設定
			buttonTrainingRecordFormList(trainingRecordForm, session, CommonConst.ERROR);
			model.addAttribute("message",  INSERT_ERROR);
			return "/trainingrecord_boot";
		}

		model.addAttribute("message", RECORD_COMPLETION);
		
		//trainingRecordFormを初期化
		buttonTrainingRecordFormList(trainingRecordForm, session, CommonConst.INITIAL);
		
		return "/trainingrecord_boot";
		
	}
	
//	/**
//	 * ログインユーザーがトレーニングを行った日付の年月日を取得します。
//	 * @param trainingRecordForm トレーニング記録画面フォーム
//	 * @param session セッション情報
//	 */
//	public boolean selectTrainingRecordDate(TrainingRecordForm trainingRecordForm, HttpSession session)  {
//		
//        // セッションからユーザ情報の取得
//        Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
//        
//        //ユーザーIDの取得
//        String userId = loginUser.getUserId();
//        
//		//画面表示年月日情報を取得してトレーニング記録画面フォームにセットします
//		setDate(trainingRecordForm);
//        
//        //ログイン中のユーザーIDでのトレーニング記録の有無を確認
//        int count = repository.checkUserIdTrainingRecord(userId);
//        
//        if (count== 0) {
//        	//ログインユーザーIDでトレーニング記録無し
//            return true;
//            
//        }else {
//        	//ログインユーザーIDでトレーニング記録有り
//        	
//        	//日時情報の取得
//            List<String> dateList = repository.selectTrainingRecordDate(userId);
//            
//            //重複項目の削除
//            dateList = new ArrayList<String>(new HashSet<>(dateList));
//            
//            //ListからMapに変換
//            Map<String, String> dateMap = new LinkedHashMap<String, String>();
//            for (int i = 0; i < dateList.size();i++) {
//            	dateMap.put("00" + (1 + i), dateList.get(i));
//            }
//            
//            trainingRecordForm.setDate(dateMap.get("001"));
//            trainingRecordForm.setPulldownDate(dateMap);
//            
//            return false;
//        }
//	}
	
//	/**
//	 * 実施するトレーニングを更新
//	 * @param session セッション情報
//	 * @param trainingRecordForm トレーニング記録画面フォーム
//	 */
//	public void updateTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) throws Exception {
//		
//		//ログインユーザーのセッション情報を取得
//		Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
//		
//		//日付情報（当日）を「トレーニング記録画面フォーム」にセット
//		setDate(trainingRecordForm);
//		
//		//プルダウンに選択されている年月日情報を取得
//		String date = trainingRecordForm.getDate();
//		
//		//DB登録用情報を格納する「トレーニング記録:entity」リストの作成
//		List<TrainingRecord> trainingRecordList = new ArrayList<>();	
//		
//		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
//		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordList();
//		
//		//「トレーニング記録:entity」リストに「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを
//		// 移し替え作業かつ、他DB登録情報の設定
//		for (int i = 0; i < trainingRecordFormList.size(); i++) {
//			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
//			TrainingRecord trainingRecord = new TrainingRecord();
//			trainingRecord.setUserId(loginUser.getUserId());
//			trainingRecord.setDateRecord(date);
//			trainingRecord.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
//			trainingRecord.setWeight(trainingRecordFormList.get(i).getWeight() + "");
//			trainingRecord.setSt1(trainingRecordFormList.get(i).getSt1() + "");
//			trainingRecord.setSt2(trainingRecordFormList.get(i).getSt2() + "");
//			trainingRecord.setSt3(trainingRecordFormList.get(i).getSt3() + "");
//			trainingRecord.setSt4(trainingRecordFormList.get(i).getSt4() + "");
//			trainingRecord.setSt5(trainingRecordFormList.get(i).getSt5() + "");
//			trainingRecord.setSt6(trainingRecordFormList.get(i).getSt6() + "");
//			trainingRecord.setTotal(trainingRecordFormList.get(i).getTotal() + "");
//			trainingRecord.setUpdateUser(loginUser.getUserId());
//			trainingRecordList.add(trainingRecord);
//		}
//		
//		//DB更新処理(トレーニング情報)
//		repository.updateTrainingRecord(trainingRecordList);
//		
//		//ログインユーザーがトレーニングを行った日付の年月日のプルダウン作成
//		selectTrainingRecordDate(trainingRecordForm, session);
//	}
//	
//	/**
//	 * トレーニング記録画面フォームの選択されているプルダウンの年月日よりDBから実施トレーニング情報を取得
//	 * @param session セッション情報
//	 * @param trainingRecordForm トレーニング記録画面フォーム
//	 */
//	public void selectTrainingRecord(TrainingRecordForm trainingRecordForm, HttpSession session) {
//
//		//プルダウンに選択されている年月日情報を取得
//		String date = trainingRecordForm.getDate();
//		
//		//年月日よりDBから実施トレーニング情報を取得して、「トレーニング記録:entity」リストに設定
//		List<TrainingRecord> trainingRecordList = repository.selectTrainingRecord(date);
//		
//		//画面表示年月日情報を取得してトレーニング記録画面フォームにセットします
//		setDate(trainingRecordForm);
//		
//		//ログインユーザーがトレーニングを行った日付の年月日のプルダウン作成
//		selectTrainingRecordDate(trainingRecordForm, session);
//		
//		//「トレーニング記録画面フォーム」にセットする「トレーニング記録画面フォーム」のリストを作成
//		List<TrainingRecordListForm> trainingRecordListForm = new ArrayList<>();
//		
//		//DBより取得した「トレーニング記録:entity」リストを「トレーニング記録画面フォーム」のリストにセット
//		for (int i = 0; i < trainingRecordList.size(); i++ ) {
//			
//			TrainingRecordListForm addListForm = new TrainingRecordListForm();
//			
//			addListForm.setMenu(trainingRecordList.get(i).getTrainingMenu());
//			addListForm.setWeight(Integer.parseInt(trainingRecordList.get(i).getWeight()));
//			addListForm.setSt1(Integer.parseInt(trainingRecordList.get(i).getSt1()));
//			addListForm.setSt2(Integer.parseInt(trainingRecordList.get(i).getSt2()));
//			addListForm.setSt3(Integer.parseInt(trainingRecordList.get(i).getSt3()));
//			addListForm.setSt4(Integer.parseInt(trainingRecordList.get(i).getSt4()));
//			addListForm.setSt5(Integer.parseInt(trainingRecordList.get(i).getSt5()));
//			addListForm.setSt6(Integer.parseInt(trainingRecordList.get(i).getSt6()));
//			addListForm.setTotal(Integer.parseInt(trainingRecordList.get(i).getTotal()));
//			
//			trainingRecordListForm.add(addListForm);
//		}
//		//「トレーニング記録画面フォーム」のリストを「トレーニング記録画面フォーム」にセット
//		trainingRecordForm.setTrainingRecordList(trainingRecordListForm);
//		
//	}
	
}
