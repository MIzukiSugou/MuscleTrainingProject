package com.example.demo.app.create;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	
	/** ユーザーＩＤが既に存在する場合のエラーメッセージ */
	private static final String ERRORMESSAGE = "既に存在するユーザーＩＤです。";
	
	/**　新規登録時、アカウント権限「一般」*/
	private static final String AUTHORITY = "2";
	
	/**　新規登録時、ログイン回数「0回」*/
	private static final String LOGINFAILURECOUNT = "0";
	
	/**　新規登録時、ログイン失敗回数「0回」*/
	private static final String LOGINCOUNT = "0";
	
	/**　新規登録時、アカウントロックフラグ「ロック無し」*/
	private static final String ACCOUNTLOCKFLAG = "0";
	
	/**　新規登録時、削除フラグ「削除無し」*/
	private static final String DELETEFLAG = "0";
	
	/** 現在日付ををYYYY/MM/DDの形で取得 */
	Calendar today = Calendar.getInstance();
    SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy/MM/dd");
    String strToday = yyyymmdd.format(today.getTime());
	
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
		
		model.addAttribute("title",View.VIEW_CREATECONFIRM);
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			return "/create";
		}
		
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
	 * @return　アカウント作成画面またはメニュー画面
	 */
	@RequestMapping(value = "/confirm", params = "execution", method = RequestMethod.POST)
	public String completeExecution(@Validated CreateForm createForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttridutes,
			HttpSession session) {
		
		// 入力値エラーチェック
		if (result.hasErrors()) {
			return "/create";
		}

		Create create = new Create();
		create.setUserId(createForm.getUserId());
		create.setLastName(createForm.getLastName());
		create.setFirstName(createForm.getFirstName());
		create.setLastNameKana(createForm.getLastNameKana());
		create.setFirstNameKana(createForm.getFirstNameKana());
		create.setPassword(createForm.getPassword());
		create.setAuthority(AUTHORITY);
		create.setLoginCount(LOGINCOUNT);
		create.setLoginFailureCount(LOGINFAILURECOUNT);
		create.setAccountLockFlag(ACCOUNTLOCKFLAG);
		create.setInsertDate(strToday);
		create.setInsertUser(createForm.getUserId());
		create.setUpdateUser(createForm.getUserId());
		create.setDeleteFlag(DELETEFLAG);

		try {
			createService.insertUser(create);
			
		}catch (DuplicateKeyException e) {
			model.addAttribute("message", ERRORMESSAGE);
			return "/create";
			
		} catch (Exception e) {
			model.addAttribute("message",  e.getMessage());
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
