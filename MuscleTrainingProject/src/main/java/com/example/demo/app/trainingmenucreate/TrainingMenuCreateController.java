package com.example.demo.app.trainingmenucreate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import com.example.demo.domain.entity.Login;
import com.example.demo.domain.entity.TrainingMenuCreate;
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
	public String getTrainingMenuCreate(Model model,TrainingMenuCreateForm trainingMenuCreateForm,
			HttpSession session) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		// ユーザ情報をセッションから取得
        Login loginUser = commonService.getUserFullName(session);
        //ユーザーフルネーム取得
        String userFullName = loginUser.getFirstName() + loginUser.getLastName();
		
		model.addAttribute("loginUser", userFullName);
		model.addAttribute("title",View.VIEW_TRAININGMENU_CREATE);
		model.addAttribute("trainingMenuCreateForm", trainingMenuCreateForm);
		return "/trainingmenucreate";
	}
	
	/**
	 * 確認ボタン押下時の処理
	 * 
	 * @param trainingMenuCreateForm トレーニングメニュー作成フォーム
	 * @param result　バリデーションエラー情報
	 * @param model　モデル情報
	 * @param redirectAttridutes
	 * @param session
	 * @return　トレーニングメニュー作成画面またはトレーニングメニュー作成確認画面
	 */
	@RequestMapping(value = "/execution", params = "execution", method = RequestMethod.POST)
	public String confirmExecution(@Validated TrainingMenuCreateForm trainingMenuCreateForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttridutes,
			HttpSession session) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		// ユーザ情報をセッションから取得
        Login loginUser = commonService.getUserFullName(session);
        //ユーザーフルネーム取得
        String userFullName = loginUser.getFirstName() + loginUser.getLastName();
		
		model.addAttribute("loginUser", userFullName);
		model.addAttribute("title",View.VIEW_TRAININGMENU_CREATECONFIRM);
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			return "/trainingmenucreate";
		}
		
		return "/trainingmenucreateconfirm";
		
	}
	
	/**
	 * 作成ボタン押下時の処理
	 * 
	 * @param trainingMenuCreateForm トレーニングメニュー作成フォーム
	 * @param result　バリデーションエラー情報
	 * @param model　モデル情報
	 * @param redirectAttridutes	
	 * @param session
	 * @param trainingMenuCreate トレーニングメニュー情報リポジトリ
	 * @return　トレーニングメニュー作成画面
	 */
	@RequestMapping(value = "/confirm", params = "execution", method = RequestMethod.POST)
	public String completeExecution(@Validated TrainingMenuCreateForm trainingMenuCreateForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttridutes,
			HttpSession session,
			TrainingMenuCreate trainingMenuCreate) {
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			return "/trainingmenucreate";
		}
		
		// ユーザ情報をセッションから取得
        Login loginUser = commonService.getUserFullName(session);
        //ユーザーフルネーム取得
        String userFullName = loginUser.getFirstName() + loginUser.getLastName();
        //ユーザーID取得
        String userId = loginUser.getUserId();
        
        trainingMenuCreateForm.setUserId(userId);
		
		model.addAttribute("loginUser", userFullName);
		model.addAttribute("title",View.VIEW_TRAININGMENU_CREATE);
		
		try {
			trainingMenucreateService.insertTrainingMenu(trainingMenuCreate,trainingMenuCreateForm);
			
		}catch (DuplicateKeyException e) {
			model.addAttribute("message", TrainingMenuCreateErrorStatement.TRAININGTARGET_DUPLICATE_REGISTRATION);
			return "/trainingmenucreate";
			
		} catch (Exception e) {
			model.addAttribute("message",  e.getMessage());
			return "/trainingmenucreate";
		}
		
		model.addAttribute("message", TrainingMenuCreateErrorStatement.TRAININGMENU_CREATECOMPLARE__MESSAGE);
		
		return "forward:/trainingMenucreate/access";
	}
	
	/**
	 * 戻るボタン押下時の処理
	 * @return　トレーニング記録画面
	 */
	@RequestMapping(value = "/execution", params = "back", method = RequestMethod.POST)
	public String createBack() {

		return "forward:/calendarrecord/access";
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
