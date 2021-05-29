package com.example.demo.app.menu;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.common.CommonService;
import com.example.demo.app.common.View;

/**
 *メニュー画面　コントローラークラス
 * 
 * @author 菅生　2021/2/20
 * 
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	/**共通処理Service */
    private CommonService commonService;
	
	@Autowired
	public MenuController(CommonService commonService) {
		this.commonService = commonService;
	}
	
	/**
	 * メニュー画面表示処理
	 * 
	 * @param model モデル情報
	 * @return 画面
	 */
	@RequestMapping(value = "/access", method = {RequestMethod.GET, RequestMethod.POST})
	public String getLogin(Model model,
			HttpSession session) {
		
        // セッションタイムアウトチェック
        if (commonService.checkSessionTimeOut(session)) {
            return "redirect:/" + CommonConst.TIME_OUT_REQUEST_PARAMETER;
        }
		
		// ユーザ情報をセッションから取得
		String userFullName = commonService.getUserFullName(session);
		
		model.addAttribute("loginUser", userFullName);
		model.addAttribute("title",View.VIEW_MENU);
		return "/menu";
	}
}
