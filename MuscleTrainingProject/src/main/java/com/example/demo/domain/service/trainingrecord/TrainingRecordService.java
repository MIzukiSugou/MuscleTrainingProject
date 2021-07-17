package com.example.demo.domain.service.trainingrecord;

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
/**
 * トレーニング記録画面　サービスクラス
 * @author sugou
 *
 */
@Service
public class TrainingRecordService {
	
	/**トレーニング記録画面Repository */
	private final TrainingRecordRepository repository;
	
	/**トレーニング記録画面Service */
	private final TrainingRecordListAddLogicService trainingRecordListAddLogicService;
	
	/**トレーニング記録画面Service */
	private final TrainingRecordListEditingLogicService trainingRecordListEditingLogicService;
	
	/**共通処理Service */
	private final CommonService commonService;
	
	@Autowired
	TrainingRecordService(TrainingRecordRepository repository,
			TrainingRecordListAddLogicService trainingRecordListAddLogicService,
			TrainingRecordListEditingLogicService trainingRecordListEditingLogicService,
			CommonService commonService){
		this.repository = repository;
		this.trainingRecordListAddLogicService = trainingRecordListAddLogicService;
		this.trainingRecordListEditingLogicService = trainingRecordListEditingLogicService;
		this.commonService = commonService;
	}
	
	/**
	 * 「トレーニング記録画面」の共通処理を記述
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public void common(TrainingRecordForm trainingRecordForm, HttpSession session,Model model) {
		
	    //日付情報が設定されていない場合のみ実施
	    if (trainingRecordForm.getDateView() == null) {
		    //日付情報(YYYY-MM-DD)をtrainingRecordFormに格納
			trainingRecordForm.setDateView(commonService.setDate());
			
	    }
		//日付情報(YYYYMMDD)をtrainingRecordFormに格納
		trainingRecordForm.setDate(trainingRecordForm.getDateView().replace("-", ""));
		
		// ユーザ情報をセッションから取得
        LoginDao loginUser = commonService.getUserFullName(session);
        String userFullName = loginUser.getFirstName() + loginUser.getLastName();
        
        //ユーザーIDの取得
        trainingRecordForm.setUserId(loginUser.getUserId());
        
        model.addAttribute("loginUser", userFullName);
        model.addAttribute("title", View.VIEW_TRAININGRECORD);
		model.addAttribute("trainingRecordForm", trainingRecordForm);

	}
	
	/**
	 * ログインユーザーの登録トレーニングメニューの存在有無を確認
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public void selectMenuAndTrainingRecordCheck(TrainingRecordForm trainingRecordForm)  {
		
		// ログインユーザーIDトレーニングメニュー有り
		if (!(repository.checkUserIdMenu(trainingRecordForm.getUserId(), trainingRecordForm.getDate()))) {
			trainingRecordListAddLogicService.stateInitializationtate(trainingRecordForm);
		// ログインユーザーIDトレーニングメニュー無し
		} else {
			trainingRecordListEditingLogicService.initialization(trainingRecordForm);
		}
		// ログインユーザーIDトレーニング記録有り
		if (!(repository.checkUserIdTrainingRecord(trainingRecordForm.getUserId(), trainingRecordForm.getDate()))) {
			selectTrainingRecord(trainingRecordForm);
		// ログインユーザーIDトレーニング記録無し			
		} else {
			trainingRecordListEditingLogicService.initialization(trainingRecordForm);
		}
	}
	
	/**
	 * 追加記録フォームのボタン押下時の共通処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param state　右記の状態　ADD,INITIAL,ERROR,DALETE
	 * @param trainingMenuIndex　削除時index その他は999999999(不要)
	 */
	public void buttonTrainingRecordFormListAdd(TrainingRecordForm trainingRecordForm,
			String state,
			int trainingMenuIndex
			) {
		
		//初期表示時の処理を記述
		if (state.equals(CommonConst.INITIAL)) {
			trainingRecordListAddLogicService.stateInitializationtate(trainingRecordForm);
			
		//追加ボタン押下時の処理を記述	
		} else if (state.equals(CommonConst.ADD)) {
			trainingRecordListAddLogicService.stateAdd(trainingRecordForm);
			
		// 削除ボタン押下時の処理を記述
		}else if (state.equals(CommonConst.DALETE)) {
			trainingRecordListAddLogicService.stateDelete(trainingRecordForm, trainingMenuIndex);
			
		}
	}
	
	/**
	 * DBへの実施するトレーニングを記録
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public String insertTrainingRecord(TrainingRecordForm trainingRecordForm,
			Model model) throws Exception {
		
		List<TrainingRecordDao> trainingRecordDaoList = trainingRecordListAddLogicService.getInsertListTrainingRecordDao(trainingRecordForm);
		
		//DB記録処理(トレーニング情報)、結果取得
		String[] conditionMenu = repository.insertTrainingRecord(trainingRecordDaoList);
			
		//更新失敗の場合
		if (!conditionMenu[0].equals(CommonConst.INSERT_SUCCESS)) {
			
			//重複エラーの場合
			if (conditionMenu[0].equals(CommonConst.INSERT_DUPLICATION_FAILURE)) {
				model.addAttribute("message", conditionMenu[1] + CommonConst.TODAY_REGISTERED);
				
			} else if (conditionMenu[0].equals(CommonConst.INSERT_FAILURE)) {
				model.addAttribute("message",  CommonConst.UPDATE_ERROR);
			}
			
			buttonTrainingRecordFormListAdd(trainingRecordForm, CommonConst.ERROR ,CommonConst.OTHER_INDEX);
			return "/trainingrecord_boot";
		
		//更新成功の場合
		} else {
			model.addAttribute("message", CommonConst.UPDATE_COMPLATE);
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

		List<TrainingRecordListForm> trainingRecordListEditing = trainingRecordListEditingLogicService.getSelectTrainingRecordListForm(trainingRecordForm);
		
		//「トレーニング記録画面フォーム」のリストを「トレーニング記録画面フォーム」にセット
		trainingRecordForm.setTrainingRecordListEditing(trainingRecordListEditing);
		trainingRecordForm.setTrainingRecordFormEditingListEndIndex(trainingRecordListEditing.size());
		
	}
	
	/**
	 * 実施済みのトレーニングを更新
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param model モデル情報
	 */
	public String updateTrainingRecord(TrainingRecordForm trainingRecordForm,
			Model model) throws Exception {
		
		//DB登録用情報を格納する「トレーニング記録:Dao」リストの作成
		List<TrainingRecordDao> trainingRecordDaoList = trainingRecordListEditingLogicService.getUpdateListTrainingRecordDao(trainingRecordForm);
		
		//DB更新処理(トレーニング情報)
		String message = repository.updateTrainingRecord(trainingRecordDaoList);
		
		//更新用の情報を再表示
		selectTrainingRecord(trainingRecordForm);
		model.addAttribute("message",  message);
		
		trainingRecordForm.setTrainingRecordListAdd(
				trainingRecordListAddLogicService.menuEditing(trainingRecordForm,
						trainingRecordListAddLogicService.selectMenu(trainingRecordForm),
						trainingRecordListAddLogicService.endIndex(trainingRecordForm),
						CommonConst.UPDATE));
		;
		
		return "/trainingrecord_boot";
	}
	
}
