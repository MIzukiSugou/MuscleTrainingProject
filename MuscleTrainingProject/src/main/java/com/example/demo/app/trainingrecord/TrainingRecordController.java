package com.example.demo.app.trainingrecord;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.CommonService;
import com.example.demo.app.common.View;
import com.example.demo.domain.service.TrainingRecordService;

/**
 * トレーニング記録画面:Controllerクラス
 * 
 * ※日々のトレーニング内容を記録、編集を行う画面
 * 
 * @author 菅生 2021/3/2
 * 
 */
@Controller
@RequestMapping(value = "/calendarrecord")
public class TrainingRecordController {
	
	/** DB登録完了メッセージ */
	private final String RECORD_COMPLETION = "トレーニング記録が完了致しました。";
	
	/** DB登録時エラーメッセージ */
	private final String TODAY_REGISTERED = "既に本日記録済みです。更新を行ってください。";
	
	/**トレーニング記録画面Service */
	private final TrainingRecordService trainingRecordService;
	
	/**共通処理Service */
    private CommonService commonService;

	@Autowired
	public TrainingRecordController(TrainingRecordService trainingRecordService,
			CommonService commonService) {
		this.trainingRecordService = trainingRecordService;
		this.commonService = commonService;
	}

	@ModelAttribute
	public TrainingRecordForm setUpForm() {
		TrainingRecordForm trainingRecordForm = new TrainingRecordForm();
		return trainingRecordForm;
	}
	
	@ModelAttribute
	public TrainingRecordListForm setUpＬｉｓｔForm() {
		TrainingRecordListForm trainingRecordFormList = new TrainingRecordListForm();
		return trainingRecordFormList;
	}

	/**
	 * トレーニング記録画面表示処理
	 * 
	 * @param model モデル情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/access", method = { RequestMethod.GET, RequestMethod.POST })
	public String getLogin(Model model,
			TrainingRecordForm trainingRecordForm,
			HttpSession session) {
		
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		// ログインユーザーID登録トレーニングメニューの存在チェック
		if (!(trainingRecordService.selectMenu(trainingRecordForm,session))) {

			// ログインユーザーID登録トレーニングメニュー無し
			trainingRecordService.selectMenu(trainingRecordForm,session);
			
		}
		
		// ログインユーザーIDトレーニング記録存在チェック
		if (!(trainingRecordService.selectTrainingRecordDate(trainingRecordForm,session))) {
			// ログインユーザーIDトレーニング記録有り
			trainingRecordService.selectTrainingRecordDate(trainingRecordForm,session);
			
		} else {
			// ログインユーザーIDトレーニング記録無し
			//TODO　後日実装予定　菅生

		}
		
		model.addAttribute("title", View.VIEW_CALENDARRECORD);

		model.addAttribute("trainingRecordForm", trainingRecordForm);
		
		return "/trainingrecord";
	}

	/**
	 * 記録ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/confirm", params = "records", method = RequestMethod.POST)
	public String ｒecord(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		try {
			//トレーニング記録画面フォームリストの情報をDBに追加を行う
			trainingRecordService.insertTrainingRecord(trainingRecordForm,session);
			
		//既に同日にトレーニングのDBへの追加が行われていた場合の処理	
		}catch (DuplicateKeyException e) {
			model.addAttribute("message", TODAY_REGISTERED);
			model.addAttribute("title", View.VIEW_CALENDARRECORD);
			model.addAttribute("trainingRecordForm", trainingRecordForm);
			return "/trainingrecord";
		
		//その他DB追加時のエラー時の処理	
		} catch (Exception e) {
			model.addAttribute("message",  e.getMessage());
			model.addAttribute("title", View.VIEW_CALENDARRECORD);
			model.addAttribute("trainingRecordForm", trainingRecordForm);
			return "/trainingrecord";
		}
		
		model.addAttribute("message", RECORD_COMPLETION);
		model.addAttribute("title", View.VIEW_CALENDARRECORD);
		model.addAttribute("trainingRecordForm", trainingRecordForm);
		
		return "/trainingrecord";
	}
	
	/**
	 * 更新ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/confirm", params = "update", method = RequestMethod.POST)
	public String update(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		try {
			//トレーニング記録画面フォームリストの情報をDBに更新を行う
			trainingRecordService.updateTrainingRecord(trainingRecordForm,session);
			
		//その他DB追加時のエラー時の処理	
		} catch (Exception e) {
			model.addAttribute("message",  e.getMessage());
			model.addAttribute("title", View.VIEW_CALENDARRECORD);
			model.addAttribute("trainingRecordForm", trainingRecordForm);
			return "/trainingrecord";
		}
		
		model.addAttribute("message", RECORD_COMPLETION);
		model.addAttribute("title", View.VIEW_CALENDARRECORD);
		model.addAttribute("trainingRecordForm", trainingRecordForm);
		
		return "/trainingrecord";
	}
	
	/**
	 *確認ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param trainingRecordFormList トレーニング記録画面フォームリスト
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/confirm", params = "confirmation", method = RequestMethod.POST)
	public String confirmation(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm,
			TrainingRecordListForm trainingRecordFormList) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }

        //トレーニング記録画面フォームのプルダウンで指定している年月日情報から、該当するトレーニング記録を取得する
        trainingRecordService.selectTrainingRecord(trainingRecordForm,session);
        
		model.addAttribute("title", View.VIEW_CALENDARRECORD);
		
		model.addAttribute("trainingRecordForm", trainingRecordForm);
		
		return "/trainingrecord";
	}
}
