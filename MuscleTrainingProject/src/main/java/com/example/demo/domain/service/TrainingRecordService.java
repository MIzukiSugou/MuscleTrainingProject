package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.CommonService;
import com.example.demo.app.common.View;
import com.example.demo.app.trainingrecord.TrainingRecordForm;
import com.example.demo.app.trainingrecord.TrainingRecordListForm;
import com.example.demo.domain.entity.LoginDao;
import com.example.demo.domain.entity.TrainingRecordDao;
import com.example.demo.domain.repository.TrainingRecordRepository;

@Service
public class TrainingRecordService {
	
	/** メニュープルダウン初期文言*/
	private final String MENUMAP_INITIAL = "トレーニングメニューを選択してください";
	
	/** DB登録完了メッセージ */
	private final String RECORD_COMPLETION = "トレーニング記録が完了致しました。";
	
	/** DB登録時(重複)エラーメッセージ */
	private final String TODAY_REGISTERED = "は本日記録済みです。更新を行ってください。";
	
	/** DB登録時エラーメッセージ */
	private final String INSERT_ERROR = "DB更新時にエラーが発生しました。最初からやり直してください。";
	
	/** DELETE_FLG ：　(削除無)*/
	private final String DELETE_FLG_ZERO = "0";
	
	/** DELETE_FLG ：　(削除有) */
	private final String DELETE_FLG_ONE = "1";
	
	/** ボタン制御フラグ：活性 */
	private final String BTNCONTROLACTIVITY = "1";
	
	/** ボタン制御フラグ：非活性 */
	private final String BTNCONTROLINACTIVE = "0";
	
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
	    
	    //日付情報が設定されていない場合のみ実施
	    if (trainingRecordForm.getDateView() == null) {
		    //日付情報(yyyy-MM-dd)をtrainingRecordFormに格納
			String dateView = commonService.setDate();
			trainingRecordForm.setDateView(dateView);
			
	    }
		//日付情報(yyyymmdd)をtrainingRecordFormに格納
		String date = trainingRecordForm.getDateView().replace("-", "");
		trainingRecordForm.setDate(date);
		
//		trainingRecordForm.setYear(days.get(CommonConst.YEAR));
//		trainingRecordForm.setMonth(days.get(CommonConst.MONTH));
//		trainingRecordForm.setDay(days.get(CommonConst.DAY));
		
		// ユーザ情報をセッションから取得
        LoginDao loginUser = commonService.getUserFullName(session);
        String userFullName = loginUser.getFirstName() + loginUser.getLastName();
        
        //ユーザーIDの取得
        String userId = loginUser.getUserId();
        trainingRecordForm.setUserId(userId);
        
        model.addAttribute("loginUser", userFullName);
        model.addAttribute("title", View.VIEW_TRAININGRECORD);
		model.addAttribute("trainingRecordForm", trainingRecordForm);

	}
	
	/**
	 * ログインユーザーの登録トレーニングメニューの存在有無を確認
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public boolean selectMenuCheck(TrainingRecordForm trainingRecordForm)  {
		
        //ユーザーIDの取得
        String userId = trainingRecordForm.getUserId();
        
		//日付情報（当日）をYYYYMMDDで取得
		String dateRecord = trainingRecordForm.getDate();
        
        //ログイン中のユーザーIDでの登録トレーニングメニューの有無を確認
        int count = repository.checkUserIdMenu(userId, dateRecord);
        
        //ログインユーザーIDでトレーニングメニューが存在無し
        if (count== 0) {
            return true;
        
        //ログインユーザーIDでトレーニングメニューが存在有り       
        }else {
            return false;
        }
	}
	
	/**
	 * ログインユーザーの実施トレーニングメニュー存在有無を確認
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 */
	public boolean selectTrainingRecordCheck(TrainingRecordForm trainingRecordForm)  {
		
      //ユーザーIDの取得
      String userId = trainingRecordForm.getUserId();
      
	//日付情報（当日）をYYYYMMDDで取得
      String dateRecord = trainingRecordForm.getDate();
		
      //ログイン中のユーザーIDでのトレーニング記録の有無を確認
      int count = repository.checkUserIdTrainingRecord(userId, dateRecord);
      
      //ログインユーザーIDでトレーニング記録無し
      if (count== 0) {
          return true;
      
      //ログインユーザーIDでトレーニング記録有り
      }else {
    	  return false;
      }
	}
	
	 /** ログインユーザーの登録トレーニングメニュー情報を取得
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 */
	public Map<String, String> selectMenu(TrainingRecordForm trainingRecordForm)  {
		
       //ユーザーIDの取得
       String userId = trainingRecordForm.getUserId();
       
       //日付情報（当日）をYYYYMMDDで取得
       String dateRecord = trainingRecordForm.getDate();
       
       //トレーニングメニューの取得
       List<String> menuList = repository.selectMenu(userId, dateRecord);
           
		// ListからMapに変換
		Map<String, String> menuMap = new LinkedHashMap<String, String>();
		for (int i = -1; i < menuList.size(); i++) {
			if (i >= 0) {
				menuMap.put("00" + (1 + i), menuList.get(i));
			} else {
				menuMap.put("00" + (1 + i), MENUMAP_INITIAL);
			}
		}
		return menuMap;
    }
	
	/**
	 * 初期表示またはボタン押下時の共通処理(追加項目)
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param state　右記の状態　ADD,INITIAL,ERROR,DALETE
	 * @param trainingMenuIndex　削除時index その他は999999999 
	 */
	public void buttonTrainingRecordFormListAdd(TrainingRecordForm trainingRecordForm,
			String state,
			int trainingMenuIndex
			) {
		
		//DBからログイン中のトレーニングメニューを取得
		Map<String, String> menuMap = selectMenu(trainingRecordForm);
		
		List<TrainingRecordListForm> trainingRecordFormList;
		
		//初期表示時の処理を記述
		if (state.equals(CommonConst.INITIAL)) {
			
			//初期表示の際の１件分(空)の入力情報を格納
	        trainingRecordFormList = new ArrayList<>();
			
	        TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
	        trainingRecordFormList.add(trainingRecordListForm);
	        trainingRecordForm.setTrainingRecordListAdd(trainingRecordFormList);
		
	    //追加ボタン押下時の処理を記述
		} else if (state.equals(CommonConst.ADD)) {
			
			trainingRecordFormList = trainingRecordForm.getTrainingRecordListAdd();
			
	        TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
	        trainingRecordFormList.add(trainingRecordListForm);
	        trainingRecordForm.setTrainingRecordListAdd(trainingRecordFormList);
		
		// 削除ボタン押下時の処理を記述
		} else if (state.equals(CommonConst.DALETE)) {
			trainingRecordForm.getTrainingRecordListAdd().remove(trainingMenuIndex);
		}
		
		//trainingRecordFormList表示最終位置(index)
		int trainingRecordFormAddListEndIndex = trainingRecordForm.getTrainingRecordListAdd().size()-1;
		trainingRecordForm.setTrainingRecordFormAddListEndIndex(trainingRecordFormAddListEndIndex);
		
		//追加、削除の場合の処理
		if (state.equals(CommonConst.ADD) || state.equals(CommonConst.DALETE) || state.equals(CommonConst.ERROR)) {
			
			//追加の場合
			if (state.equals(CommonConst.ADD)) {
				//menuMapのvalue(トレーニングメニュー)を格納
				trainingRecordForm.getTrainingRecordListAdd().get(trainingRecordFormAddListEndIndex-1).setMenu(menuMap.get(trainingRecordForm.getMenuKey()));
				//menuMapのkeyを格納
				trainingRecordForm.getTrainingRecordListAdd().get(trainingRecordFormAddListEndIndex-1).setMenuKey(trainingRecordForm.getMenuKey());
			}
			
			//trainingRecordFormListで選択済みのプルダウン内容の削除
			for (int i = 0; i < trainingRecordFormAddListEndIndex;i++) {
				menuMap.remove(trainingRecordForm.getTrainingRecordListAdd().get(i).getMenuKey());
			}
		
		}
		
		//trainingRecordFormの最終位置にメニューのプルダウンを格納
		trainingRecordForm.getTrainingRecordListAdd().get(trainingRecordFormAddListEndIndex).setMenuMap(menuMap);
		
		//記録ボタンの制御:"1"/活性　"0"/非活性
		if (trainingRecordForm.getTrainingRecordListAdd().size() > 1) {
			//表示
			trainingRecordForm.setRecordBtnControl(BTNCONTROLACTIVITY);
		} else {
			//非表示
			trainingRecordForm.setRecordBtnControl(BTNCONTROLINACTIVE);
		}
		
	}
	
	/**
	 * 実施するトレーニングを記録（追加）
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public String insertTrainingRecord(TrainingRecordForm trainingRecordForm,
			Model model) throws Exception {
		
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordListAdd();
		
		//ユーザーIDの取得
        String userId = trainingRecordForm.getUserId();
		
        //日付情報（当日）をYYYYMMDDで取得
        String dateRecord = trainingRecordForm.getDate();
        
		//DB登録用情報を格納する「トレーニング記録:entity」リストの作成
		List<TrainingRecordDao> trainingRecordList = new ArrayList<>();	
		
		//「トレーニング記録:entity」リストにDB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size() -1; i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecordDao trainingRecord = new TrainingRecordDao();
			trainingRecord.setUserId(userId);
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
			trainingRecord.setInsertUser(userId);
			trainingRecord.setUpdateUser(userId);
			trainingRecord.setDeleteFlg(DELETE_FLG_ZERO);
			trainingRecordList.add(trainingRecord);
		}
		
		//DB記録処理(トレーニング情報)、結果取得
		String[] conditionMenu = repository.insertTrainingRecord(trainingRecordList);
			
		//更新失敗の場合
		if (!conditionMenu[0].equals(CommonConst.INSERT_SUCCESS)) {
			
			//重複エラーの場合
			if (conditionMenu[0].equals(CommonConst.INSERT_DUPLICATION_FAILURE)) {
				model.addAttribute("message", conditionMenu[1] + TODAY_REGISTERED);
				
			} else if (conditionMenu[0].equals(CommonConst.INSERT_FAILURE)) {
				model.addAttribute("message",  INSERT_ERROR);
			}
			
			buttonTrainingRecordFormListAdd(trainingRecordForm, CommonConst.ERROR ,CommonConst.OTHER_INDEX);
			return "/trainingrecord_boot";
		
		//更新成功の場合
		} else {
			model.addAttribute("message", RECORD_COMPLETION);
			//trainingRecordFormを初期化
			buttonTrainingRecordFormListAdd(trainingRecordForm, CommonConst.INITIAL, CommonConst.OTHER_INDEX);
			//更新用の情報を再表示
			selectTrainingRecord(trainingRecordForm);
			
			return "/trainingrecord_boot";
		}
	}
	
	/**
	 * トレーニング記録画面フォームの選択されているプルダウンの年月日よりDBから実施トレーニング情報を取得
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public void selectTrainingRecord(TrainingRecordForm trainingRecordForm) {

		//ユーザーIDの取得
        String userId = trainingRecordForm.getUserId();
		
        //日付情報（当日）をYYYYMMDDで取得
        String dateRecord = trainingRecordForm.getDate();
		
		//年月日よりDBから実施トレーニング情報を取得して、「トレーニング記録:entity」リストに設定
		List<TrainingRecordDao> trainingRecordList = repository.selectTrainingRecord(dateRecord, userId);
		
		
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
			
			trainingRecordListForm.add(addListForm);
		}
		//「トレーニング記録画面フォーム」のリストを「トレーニング記録画面フォーム」にセット
		trainingRecordForm.setTrainingRecordListEditing(trainingRecordListForm);
		trainingRecordForm.setTrainingRecordFormEditingListEndIndex(trainingRecordListForm.size());
		
	}
	
	/**
	 * 実施するトレーニングを更新
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param model モデル情報
	 */
	public String updateTrainingRecord(TrainingRecordForm trainingRecordForm,
			Model model) throws Exception {
		
		//ユーザーIDの取得
        String userId = trainingRecordForm.getUserId();
		
        //日付情報（当日）をYYYYMMDDで取得
        String dateRecord = trainingRecordForm.getDate();
		
        //DB登録用情報を格納する「トレーニング記録:entity」リストの作成
      	List<TrainingRecordDao> trainingRecordDaoList = new ArrayList<>();	
		
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordListEditing();
		
		//「トレーニング記録:entity」リストに「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを
		// 移し替え作業かつ、他DB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size(); i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecordDao trainingRecordDao = new TrainingRecordDao();
			
			if (trainingRecordFormList.get(i).getDeleteFlg() == null ||
					trainingRecordFormList.get(i).getDeleteFlg() == DELETE_FLG_ZERO) {
				trainingRecordDao.setDeleteFlg(DELETE_FLG_ZERO);
			}else {
				trainingRecordDao.setDeleteFlg(trainingRecordFormList.get(i).getDeleteFlg());
			}
			trainingRecordDao.setUserId(userId);
			trainingRecordDao.setDateRecord(dateRecord);
			trainingRecordDao.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
			trainingRecordDao.setWeight(trainingRecordFormList.get(i).getWeight() + "");
			trainingRecordDao.setSt1(trainingRecordFormList.get(i).getSt1() + "");
			trainingRecordDao.setSt2(trainingRecordFormList.get(i).getSt2() + "");
			trainingRecordDao.setSt3(trainingRecordFormList.get(i).getSt3() + "");
			trainingRecordDao.setSt4(trainingRecordFormList.get(i).getSt4() + "");
			trainingRecordDao.setSt5(trainingRecordFormList.get(i).getSt5() + "");
			trainingRecordDao.setSt6(trainingRecordFormList.get(i).getSt6() + "");
			trainingRecordDao.setTotal(trainingRecordFormList.get(i).getTotal() + "");
			trainingRecordDao.setUpdateUser(userId);
			trainingRecordDaoList.add(trainingRecordDao);
		}
		
		//DB更新処理(トレーニング情報)
		String message = repository.updateTrainingRecord(trainingRecordDaoList);
		
		//更新用の情報を再表示
		selectTrainingRecord(trainingRecordForm);
		model.addAttribute("message",  message);
		model.addAttribute("trainingRecordForm", trainingRecordForm);
		
		return "/trainingrecord_boot";
	}
	
	/**
	 * 更新トレーニングリストの初期化処理
	 * 更新完了時、検索結果無の場合
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param model モデル情報
	 */
	public void initialization(TrainingRecordForm trainingRecordForm,Model model) {
		
		//更新用リスト「ｔrainingRecordListEditing」の中身を削除
		for (int i = 0; i < trainingRecordForm.getTrainingRecordListEditing().size(); i++) {
			trainingRecordForm.getTrainingRecordListEditing().remove(i);
		}
		
	}
	
//	

//	/**
//	 * ログインユーザーがトレーニングを行った日付の年月日を取得します。
//	 * @param trainingRecordForm トレーニング記録画面フォーム
//	 * @param session セッション情報
//	 */
//	public boolean selectTrainingRecordDate(TrainingRecordForm trainingRecordForm)  {
//		
//		//ユーザーIDの取得
//       String userId = trainingRecordForm.getUserId();
//		
//		//日付情報（当日）をYYYYMMDDで取得
//		String dateRecord = trainingRecordForm.getYear() + trainingRecordForm.getMonth() + trainingRecordForm.getDay();
//		
//       //ログイン中のユーザーIDでのトレーニング記録の有無を確認
//       int count = repository.checkUserIdTrainingRecord(userId);
//       
//       if (count== 0) {
//       	//ログインユーザーIDでトレーニング記録無し
//           return true;
//           
//       }else {
//       	//ログインユーザーIDでトレーニング記録有り
//       	
//       	//日時情報の取得
//           List<String> dateList = repository.selectTrainingRecordDate(userId);
//           
//           //重複項目の削除
//           dateList = new ArrayList<String>(new HashSet<>(dateList));
//           
//           //ListからMapに変換
//           Map<String, String> dateMap = new LinkedHashMap<String, String>();
//           for (int i = 0; i < dateList.size();i++) {
//           	dateMap.put("00" + (1 + i), dateList.get(i));
//           }
//           
//           trainingRecordForm.setDate(dateMap.get("001"));
//           trainingRecordForm.setPulldownDate(dateMap);
//           
//           return false;
//       }
//	}
}
