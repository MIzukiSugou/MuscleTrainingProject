package com.example.demo.app.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.common.View;
import com.example.demo.domain.service.LoginServiceImpl;

/**
 * ログイン画面　コントローラークラス
 * 
 * @author 菅生　2021/2/18
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private final LoginServiceImpl loginServiceImpl;

	@Autowired
	public LoginController(LoginServiceImpl loginServiceImpl) {
		this.loginServiceImpl = loginServiceImpl;
	}

	@ModelAttribute
	public LoginForm setUpForm() {
		LoginForm form = new LoginForm();
		return form;
	}

	/**
	 * ログイン画面表示処理
	 * 
	 * @param model
	 * @return 画面
	 */
	@RequestMapping(value = "/access", method = {RequestMethod.GET, RequestMethod.POST})
	public String getLogin(Model model) {
		
		model.addAttribute("title",View.VIEW_LOGIN);
		
		return "/login";
	}

	/**
	 * ログインボタン押下時の処理
	 * 
	 * @param form　ログインフォーム
	 * @param result バリデーションエラー情報
	 * @param model　モデル情報
	 * @param redirectAttridutes
	 * @param session　セッション情報
	 * @return　ログイン画面またはメニュー画面
	 */
	@RequestMapping(value = "/execution", method = RequestMethod.POST)
	public String login(@Validated LoginForm form, BindingResult result, Model model,
			RedirectAttributes redirectAttridutes, HttpSession session) {
		
		
		model.addAttribute("title",View.VIEW_LOGIN);
		
		// 入力チェック
		if (result.hasErrors()) {
			// 入力エラーの場合
			return "/login";
		}

		// 社員番号存在チェック
		if (loginServiceImpl.checkInput(form)) {

			// 社員番号・パスワード不一致
			model.addAttribute("errorMessage", form.getErrorMessage());
			return "/login";
		}

		// ログイン処理
		if (loginServiceImpl.executeLogin(form, session)) {

			// 取得エラーの場合
			return "/login";
		}

		return "forward:/menu/access";
	}

}
