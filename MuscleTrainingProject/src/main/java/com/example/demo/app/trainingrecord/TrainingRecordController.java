package com.example.demo.app.trainingrecord;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.CommonService;
import com.example.demo.domain.service.trainingrecord.TrainingRecordService;

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
		
        //共通処理実行
        trainingRecordService.common(trainingRecordForm, session, model);
        trainingRecordService.selectMenuAndTrainingRecordCheck(trainingRecordForm);
        trainingRecordService.buttonTrainingRecordFormListAdd(trainingRecordForm, CommonConst.INITIAL, CommonConst.OTHER_INDEX);
		
		return "/trainingrecord_boot";
	}
	
	/**
	 * 設定ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/confirm", params = "setting", method = RequestMethod.POST)
	public String setting(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
        trainingRecordService.common(trainingRecordForm, session, model);
		trainingRecordService.selectMenuAndTrainingRecordCheck(trainingRecordForm);
		
		return "/trainingrecord_boot";
	}
	
	/**
	 * 追加ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/confirm", params = "add", method = RequestMethod.POST)
	public String add(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
        //共通処理実行
        trainingRecordService.common(trainingRecordForm, session, model);
        
        trainingRecordService.buttonTrainingRecordFormListAdd(trainingRecordForm, CommonConst.ADD, CommonConst.OTHER_INDEX);
		
		
		return "/trainingrecord_boot";
	}
	
	/**
	 * 削除ボタン押下時（追加項目）の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 */
	@RequestMapping(value = "/confirm", params = "deleteAdd", method = RequestMethod.POST)
	public String deleteAdd(@RequestParam("deleteAdd") int trainingMenuIndex,
			Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
        
        //共通処理実行
        trainingRecordService.common(trainingRecordForm, session, model);
		
        //ボタン押下時の共通処理(追加項目)：(削除)
        trainingRecordService.buttonTrainingRecordFormListAdd(trainingRecordForm, CommonConst.DALETE, trainingMenuIndex);
		
		
		return "/trainingrecord_boot";
	}
	
	/**
	 * 記録ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/confirm", params = "records", method = RequestMethod.POST)
	public String ｒecord(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) throws Exception {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
        //共通処理実行
        trainingRecordService.common(trainingRecordForm, session, model);
        
         //トレーニング記録画面フォームリストの情報をDBに追加を行う
        return trainingRecordService.insertTrainingRecord(trainingRecordForm,model);
        
	}
	
	/**
	 * 更新ボタン押下時の処理
	 * 
	 * @param model モデル情報
	 * @param session セッション情報
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return トレーニング記録画面
	 * @throws Exception 
	 */
	@RequestMapping(value = "/confirm", params = "update", method = RequestMethod.POST)
	public String update(Model model,
			HttpSession session,
			TrainingRecordForm trainingRecordForm
			) throws Exception {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
        
        //共通処理実行
        trainingRecordService.common(trainingRecordForm, session, model);
        
        //トレーニング記録画面フォームリストの情報をDB更新を行う
        return trainingRecordService.updateTrainingRecord(trainingRecordForm,model);
	}
	
}
