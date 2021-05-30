package com.example.demo.app.menucreate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.CommonService;
import com.example.demo.app.common.View;
import com.example.demo.domain.service.TrainingMenuCreateService;

/**
 * トレーニングメニュー作成画面　コントローラークラス
 * 
 * @author 菅生　2021/5/28
 * 
 */
@Controller
@RequestMapping(value = "/trainingMenucreate")
public class TrainingMenuCreateController {
	
	/** 現在日付ををYYYY/MM/DDの形で取得 */
	Calendar today = Calendar.getInstance();
    SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy/MM/dd");
    String strToday = yyyymmdd.format(today.getTime());
    
    /**トレーニングメニュー作成画面Service */
	private final TrainingMenuCreateService trainingMenucreateService;
	
	/**共通処理Service */
    private CommonService commonService;

	@Autowired
	public TrainingMenuCreateController(TrainingMenuCreateService trainingMenucreateService,
			CommonService commonService) {
		this.trainingMenucreateService = trainingMenucreateService;
		this.commonService = commonService;
	}
	
	/**
	 * トレーニングメニュー作成作成画面表示処理
	 * 
	 * @param model モデル情報
	 * @return 画面
	 */
	@RequestMapping(value = "/access", method = {RequestMethod.GET, RequestMethod.POST})
	public String getLogin(Model model,TrainingMenuCreateForm trainingMenuCreateForm,
			HttpSession session) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		// ユーザ情報をセッションから取得
		String userFullName = commonService.getUserFullName(session);
		
		model.addAttribute("loginUser", userFullName);
		model.addAttribute("title",View.VIEW_TRAININGMENU_CREATE);
		model.addAttribute("createForm", trainingMenuCreateForm);
		return "/trainingmenucreate.html";
	}
	
	/**
	 * 確認ボタン押下時の処理
	 * 
	 * @param createForm アカウント作成フォーム
	 * @param result　バリデーションエラー情報
	 * @param model　モデル情報
	 * @param redirectAttridutes
	 * @param session
	 * @return　アカウント作成画面またはメニュー画面
	 */
	@RequestMapping(value = "/execution", params = "execution", method = RequestMethod.POST)
	public String confirmExecution(@Validated TrainingMenuCreateForm createForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttridutes,
			HttpSession session) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		// ユーザ情報をセッションから取得
		String userFullName = commonService.getUserFullName(session);
		

		model.addAttribute("loginUser", userFullName);
		model.addAttribute("title",View.VIEW_CREATECONFIRM);
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			return "/create";
		}
		
		return "/createconfirm";
		
	}
	
//	/**
//	 * 作成ボタン押下時の処理
//	 * 
//	 * @param createForm アカウント作成フォーム
//	 * @param result　バリデーションエラー情報
//	 * @param model　モデル情報
//	 * @param redirectAttridutes	
//	 * @param session
//	 * @return　アカウント作成画面またはメニュー画面
//	 */
//	@RequestMapping(value = "/confirm", params = "execution", method = RequestMethod.POST)
//	public String completeExecution(@Validated TrainingMenuCreateForm createForm,
//			BindingResult result,
//			Model model,
//			RedirectAttributes redirectAttridutes,
//			HttpSession session) {
//		
//		// 入力値エラーチェック
//		if (result.hasErrors()) {
//			return "/create";
//		}
//
//		Create create = new Create();
//		create.setUserId(createForm.getUserId());
//		create.setLastName(createForm.getLastName());
//		create.setFirstName(createForm.getFirstName());
//		create.setLastNameKana(createForm.getLastNameKana());
//		create.setFirstNameKana(createForm.getFirstNameKana());
//		create.setPassword(createForm.getPassword());
//		create.setAuthority(AUTHORITY);
//		create.setLoginCount(LOGINCOUNT);
//		create.setLoginFailureCount(LOGINFAILURECOUNT);
//		create.setAccountLockFlag(ACCOUNTLOCKFLAG);
//		create.setInsertDate(strToday);
//		create.setInsertUser(createForm.getUserId());
//		create.setUpdateUser(createForm.getUserId());
//		create.setDeleteFlag(DELETEFLAG);
//
//		try {
//			createService.insertUser(create);
//			
//		}catch (DuplicateKeyException e) {
//			model.addAttribute("message", TrainingMenuCreateErrorStatement.TRAININGTARGET_DUPLICATE_REGISTRATION);
//			return "/create";
//			
//		} catch (Exception e) {
//			model.addAttribute("message",  e.getMessage());
//			return "/create";
//		}
//		
//		model.addAttribute("message", TrainingMenuCreateErrorStatement.TRAININGMENU_CREATECOMPLARE__MESSAGE);
//		
//		return "forward:/login/access";
//	}
	
	/**
	 * 戻るボタン押下時の処理
	 * @return　ログイン画面
	 */
	@RequestMapping(value = "/execution", params = "back", method = RequestMethod.POST)
	public String createBack() {

		return "forward:/menu/access";
	}
	
	/**
	 * 戻るボタン押下時の処理
	 * @return トレーニングメニュー作成画面
	 */
	@RequestMapping(value = "/trainingMenucreate", params = "back", method = RequestMethod.POST)
	public String confirmBack() {

		return "forward:/trainingMenucreate/access";
	}
}
