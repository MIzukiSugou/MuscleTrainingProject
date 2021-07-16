package com.example.demo.domain.service.trainingrecord;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.trainingrecord.TrainingRecordForm;
import com.example.demo.app.trainingrecord.TrainingRecordListForm;
import com.example.demo.domain.entity.TrainingRecordDao;
import com.example.demo.domain.repository.TrainingRecordRepository;

/**
 * トレーニング記録画面
 * トレーニング追加記録情報の編集ロジッククラス
 * @author sugou
 *
 */
@Service
public class TrainingRecordListAddLogicService {
	
	/**トレーニング記録画面Repository */
	private final TrainingRecordRepository repository;
	
	@Autowired
	TrainingRecordListAddLogicService(TrainingRecordRepository repository){
		this.repository = repository;
	}
	
	 /** trainingRecordFormListAddのプルダウン表示用
	 * トレーニングメニュー情報を取得
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param session セッション情報
	 */
	public Map<String, String> selectMenu(TrainingRecordForm trainingRecordForm)  {
		
		//DBからトレーニングメニューの取得
		List<String> menuList = repository.selectMenu(trainingRecordForm.getUserId(), trainingRecordForm.getDate());
          
		//Map型に変換
		Map<String, String> menuMap = new LinkedHashMap<String, String>();
		for (int i = -1; i < menuList.size(); i++) {
			if (i >= 0) {
				menuMap.put("00" + (1 + i), menuList.get(i));
			} else {
				menuMap.put("00" + (1 + i), CommonConst.MENUMAP_INITIAL);
			}
		}
		return menuMap;
   }
	
	/**
	 * trainingRecordFormListAddの初期化処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public TrainingRecordForm stateInitializationtate(TrainingRecordForm trainingRecordForm
			) {
		List<TrainingRecordListForm> trainingRecordFormListAdd = new ArrayList<>();
		TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
		trainingRecordFormListAdd.add(trainingRecordListForm);
        trainingRecordForm.setTrainingRecordListAdd(trainingRecordFormListAdd);
        
        int endIndex = endIndex(trainingRecordForm);
        
        //DBからログイン中のトレーニングメニューを取得
        Map<String, String> menuMap = selectMenu(trainingRecordForm);
        
        menuSettings(trainingRecordForm, endIndex, menuMap);
        
        return trainingRecordForm;
	}
	
	/**
	 * trainingRecordFormListAddの追加処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public TrainingRecordForm stateAdd(TrainingRecordForm trainingRecordForm
			) {
		List<TrainingRecordListForm> trainingRecordFormListAdd = trainingRecordForm.getTrainingRecordListAdd();
        TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
        trainingRecordFormListAdd.add(trainingRecordListForm);
        trainingRecordForm.setTrainingRecordListAdd(trainingRecordFormListAdd);
        
        int endIndex = endIndex(trainingRecordForm);
        
        //DBからログイン中のトレーニングメニューを取得
        Map<String, String> menuMap = selectMenu(trainingRecordForm);
        //menuMapのvalue(トレーニングメニュー)を格納
		trainingRecordForm.getTrainingRecordListAdd().get(endIndex-1).setMenu(menuMap.get(trainingRecordForm.getMenuKey()));
		//menuMapのkeyを格納
		trainingRecordForm.getTrainingRecordListAdd().get(endIndex-1).setMenuKey(trainingRecordForm.getMenuKey());
		
		menuSettings(trainingRecordForm, endIndex, menuMap);
		
		return trainingRecordForm;
	}
	
	/**
	 * trainingRecordFormListAddの削除処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param trainingMenuIndex　削除時index その他は999999999 
	 */
	public TrainingRecordForm stateDelete(TrainingRecordForm trainingRecordForm,
			int trainingMenuIndex
			) {
		trainingRecordForm.getTrainingRecordListAdd().remove(trainingMenuIndex);
		
		int endIndex = endIndex(trainingRecordForm);
		
		//DBからログイン中のトレーニングメニューを取得
        Map<String, String> menuMap = selectMenu(trainingRecordForm);
        
        menuSettings(trainingRecordForm, endIndex, menuMap);
        
        return trainingRecordForm;
	}
	
	/**
	 * trainingRecordFormListAddの入力箇所最終indexを返却
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public int endIndex(TrainingRecordForm trainingRecordForm
			) {
		int endIndex = trainingRecordForm.getTrainingRecordListAdd().size()-1;
		trainingRecordForm.setTrainingRecordFormAddListEndIndex(trainingRecordForm.getTrainingRecordListAdd().size()-1);
		return endIndex;
		
	}
	
	/**
	 * trainingRecordFormListAddのメニュープルダウンの編集処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param endIndex 入力箇所最終indexを返却
	 * @param menuMap プルダウン表示用
	 * 
	 */
	public void menuSettings(TrainingRecordForm trainingRecordForm,
			int endIndex,
			Map<String, String> menuMap
			) {
		//trainingRecordFormListで選択済みのプルダウン内容の削除
		for (int i = 0; i < endIndex;i++) {
			menuMap.remove(trainingRecordForm.getTrainingRecordListAdd().get(i).getMenuKey());
		}
		//trainingRecordFormの最終位置にメニューのプルダウンを格納
		trainingRecordForm.getTrainingRecordListAdd().get(endIndex).setMenuMap(menuMap);
		
	}
	
	/**
	 * trainingRecordFormListAddの記録ボタン制御処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * 
	 */
	public void recordBtnControl(TrainingRecordForm trainingRecordForm
			) {
		if (trainingRecordForm.getTrainingRecordListAdd().size() > 1) {
			//表示
			trainingRecordForm.setRecordBtnControl(CommonConst.BTNCONTROLACTIVITY);
		} else {
			//非表示
			trainingRecordForm.setRecordBtnControl(CommonConst.BTNCONTROLINACTIVE);
		}
		
	}
	
	/**
	 * //DB登録用情報を格納する「トレーニング記録:Dao」リストの作成
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * 
	 */
	public List<TrainingRecordDao> getInsertListTrainingRecordDao(TrainingRecordForm trainingRecordForm
			) {
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordListAdd();
		//DB登録用情報を格納する「トレーニング記録:Dao」リストの作成
		List<TrainingRecordDao> trainingRecordDaoList = new ArrayList<>();	
		
		//「トレーニング記録:Dao」リストにDB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size() -1; i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecordDao trainingRecord = new TrainingRecordDao();
			trainingRecord.setUserId(trainingRecordForm.getUserId());
			trainingRecord.setDateRecord(trainingRecordForm.getDate());
			trainingRecord.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
			trainingRecord.setWeight(trainingRecordFormList.get(i).getWeight() + "");
			trainingRecord.setSt1(trainingRecordFormList.get(i).getSt1() + "");
			trainingRecord.setSt2(trainingRecordFormList.get(i).getSt2() + "");
			trainingRecord.setSt3(trainingRecordFormList.get(i).getSt3() + "");
			trainingRecord.setSt4(trainingRecordFormList.get(i).getSt4() + "");
			trainingRecord.setSt5(trainingRecordFormList.get(i).getSt5() + "");
			trainingRecord.setSt6(trainingRecordFormList.get(i).getSt6() + "");
			trainingRecord.setTotal(trainingRecordFormList.get(i).getTotal() + "");
			trainingRecord.setInsertUser(trainingRecordForm.getUserId());
			trainingRecord.setUpdateUser(trainingRecordForm.getUserId());
			trainingRecord.setDeleteFlg(CommonConst.DELETE_FLG_ZERO);
			trainingRecordDaoList.add(trainingRecord);
		}
		
		return trainingRecordDaoList;
		
	}
	
}
