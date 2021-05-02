package com.example.demo.app.menu;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.View;
import com.example.demo.domain.entity.Login;

/**
 *メニュー画面　コントローラークラス
 * 
 * @author 菅生　2021/2/20
 * 
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	/**
	 * メニュー画面表示処理
	 * 
	 * @param model モデル情報
	 * @return 画面
	 */
	@RequestMapping(value = "/access", method = {RequestMethod.GET, RequestMethod.POST})
	public String getLogin(Model model,
			HttpSession session) {
		
		// ユーザ情報をセッションから取得
		Login loginUser = (Login) session.getAttribute(CommonConst.LOGIN_USER);
		model.addAttribute("loginUser", loginUser.getFirstName() + loginUser.getLastName());
		model.addAttribute("title",View.VIEW_MENU);
		return "/menu";
	}
}
