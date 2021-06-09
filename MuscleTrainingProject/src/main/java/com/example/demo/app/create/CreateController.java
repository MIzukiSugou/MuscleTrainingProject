package com.example.demo.app.create;

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

import com.example.demo.app.common.View;
import com.example.demo.domain.entity.Create;
import com.example.demo.domain.service.CreateService;

/**
 * アカウント作成画面　コントローラークラス
 * 
 * @author 菅生　2021/2/21
 * 
 */
@Controller
@RequestMapping(value = "/create")
public class CreateController {
	
    /**アカウント作成画面Service */
	private final CreateService createService;

	@Autowired
	public CreateController(CreateService createService) {
		this.createService = createService;
	}
	
	/**
	 * アカウント作成画面表示処理
	 * 
	 * @param model モデル情報
	 * @return 画面
	 */
	@RequestMapping(value = "/access", method = {RequestMethod.GET, RequestMethod.POST})
	public String getLogin(Model model,CreateForm createForm) {
		
		model.addAttribute("title",View.VIEW_CREATE);
		model.addAttribute("createForm", createForm);
		return "/create";
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
	public String confirmExecution(@Validated CreateForm createForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttridutes,
			HttpSession session) {
		
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			model.addAttribute("title",View.VIEW_CREATE);
			return "/create";
			
		}
		
		model.addAttribute("title",View.VIEW_CREATECONFIRM);
		return "/createconfirm";
		
	}
	
	/**
	 * 作成ボタン押下時の処理
	 * 
	 * @param createForm アカウント作成フォーム
	 * @param result　バリデーションエラー情報
	 * @param model　モデル情報
	 * @param redirectAttridutes	
	 * @param session
	 * @param create アカウント作成エンティティ
	 * @return　アカウント作成画面またはメニュー画面
	 */
	@RequestMapping(value = "/confirm", params = "execution", method = RequestMethod.POST)
	public String completeExecution(@Validated CreateForm createForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttridutes,
			HttpSession session,
			Create create) {
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			model.addAttribute("title",View.VIEW_CREATE);
			return "/create";
		}

		try {
			createService.insertUser(create,createForm);
			
		}catch (DuplicateKeyException e) {
			createForm.setMessage(CreateErrorStatement.USERID_DUPLICATE_REGISTRATION);
			model.addAttribute("title",View.VIEW_CREATE);
			return "/create";
			
		} catch (Exception e) {
			createForm.setMessage(e.getMessage());
			model.addAttribute("title",View.VIEW_CREATE);
			return "/create";
		}
		
		model.addAttribute("message", CreateErrorStatement.CREATECOMPLARE__MESSAGE);
		
		return "forward:/login/access";
	}
	
	/**
	 * 戻るボタン押下時の処理
	 * @return　ログイン画面
	 */
	@RequestMapping(value = "/execution", params = "back", method = RequestMethod.POST)
	public String createBack() {

		return "forward:/login/access";
	}
	
	/**
	 * 戻るボタン押下時の処理
	 * @return アカウント作成画面
	 */
	@RequestMapping(value = "/confirm", params = "back", method = RequestMethod.POST)
	public String confirmBack() {

		return "forward:/create/access";
	}
}
