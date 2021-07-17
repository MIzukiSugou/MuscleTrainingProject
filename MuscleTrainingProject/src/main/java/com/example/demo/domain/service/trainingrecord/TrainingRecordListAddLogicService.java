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
	 * @param trainingRecordForm ：トレーニング記録画面フォーム
	 * @param session：セッション情報
	 * 
	 * @return menuMap：DBから取得したユーザーのトレーニングメニュー情報をHashMapで取得
	 */
	public Map<String, String> selectMenu(TrainingRecordForm trainingRecordForm)  {
		
		//DBからトレーニングメニューの取得
		List<String> menuList = repository.selectMenu(trainingRecordForm.getUserId(), trainingRecordForm.getDate());
          
		menuList = menuSettings(trainingRecordForm, endIndex(trainingRecordForm), menuList);
		
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
	 * trainingRecordFormListAddのメニュープルダウンの編集処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param endIndex 入力箇所最終indexを返却
	 * @param menuList プルダウン表示用リスト
	 * @return menuList 既に選択（表示）済みのメニューは削除して返却
	 */
	public List<String> menuSettings(TrainingRecordForm trainingRecordForm,
			int endIndex,
			List<String> menuList
			) {
		//trainingRecordFormListで選択済みのプルダウン内容の削除
		for (int i = 0; i < endIndex; i++) {
			String daleteMenu = trainingRecordForm.getTrainingRecordListAdd().get(i).getMenu();
//			String daleteMenu = trainingRecordForm.getTrainingRecordListAdd().get(endIndex).getMenuMap().get(trainingRecordForm.getMenuKey());
			for (int removeIndex = 0; removeIndex < menuList.size(); removeIndex++) {
				if (daleteMenu.equals(menuList.get(removeIndex))) {
					menuList.remove(removeIndex);
				}
			}
		}
		return menuList;
	}
	
	/**
	 * trainingRecordFormListAddの初期化処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return trainingRecordForm トレーニング記録画面フォーム
	 */
	public TrainingRecordForm stateInitializationtate(TrainingRecordForm trainingRecordForm
			) {
		List<TrainingRecordListForm> trainingRecordFormListAdd = new ArrayList<>();
		TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
		trainingRecordFormListAdd.add(trainingRecordListForm);
        trainingRecordForm.setTrainingRecordListAdd(trainingRecordFormListAdd);
        
        
        trainingRecordForm.setTrainingRecordListAdd(menuEditing(trainingRecordForm,
						selectMenu(trainingRecordForm),endIndex(trainingRecordForm),
						CommonConst.INITIAL));
        
        return trainingRecordForm;
	}
	
	/**
	 * trainingRecordFormListAddの追加処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @return trainingRecordForm トレーニング記録画面フォーム
	 */
	public TrainingRecordForm stateAdd(TrainingRecordForm trainingRecordForm
			) {
		List<TrainingRecordListForm> trainingRecordFormListAdd = trainingRecordForm.getTrainingRecordListAdd();
        TrainingRecordListForm trainingRecordListForm = new TrainingRecordListForm();
        trainingRecordFormListAdd.add(trainingRecordListForm);
        trainingRecordForm.setTrainingRecordListAdd(trainingRecordFormListAdd);
        
        int endIndex = endIndex(trainingRecordForm);
        
        //menuMapのvalue(トレーニングメニュー)を格納
        trainingRecordForm.getTrainingRecordListAdd().get(endIndex-1).setMenu(
        		trainingRecordForm.getMenu());
        
        trainingRecordForm.setTrainingRecordListAdd(menuEditing(trainingRecordForm,
				selectMenu(trainingRecordForm),endIndex(trainingRecordForm),
				CommonConst.ADD));
        
		return trainingRecordForm;
	}
	
	/**
	 * trainingRecordFormListAddの削除処理
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param trainingMenuIndex　削除時index その他は999999999 
	 * @return trainingRecordForm トレーニング記録画面フォーム
	 */
	public TrainingRecordForm stateDelete(TrainingRecordForm trainingRecordForm,
			int trainingMenuIndex) {
		trainingRecordForm.getTrainingRecordListAdd().remove(trainingMenuIndex);
		
		trainingRecordForm.setTrainingRecordListAdd(menuEditing(trainingRecordForm,
				selectMenu(trainingRecordForm),endIndex(trainingRecordForm),
				CommonConst.DALETE));
        
        return trainingRecordForm;
	}
	
	/**
	 * 追加記録用（trainingRecordListAdd）にメニュープルダウン（menuMap）を格納
	 * 追加記録用（trainingRecordListAdd）のmenu,keyに値を格納
	 * 
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * @param menuMap プルダウン表示用HashMap
	 * @param endIndex trainingRecordFormListAddの入力箇所最終index
	 * @param state　ボタン分岐
	 * 
	 * @return trainingRecordListAdd 追加記録用のリストを返却
	 */
	public List<TrainingRecordListForm> menuEditing(TrainingRecordForm trainingRecordForm
			,Map<String, String> menuMap, int endIndex, String state) {
		
		List<TrainingRecordListForm> trainingRecordListAdd = trainingRecordForm.getTrainingRecordListAdd();
		
		for (int i = 0; i < endIndex; i++) {
			for (int keyIndex = 0; keyIndex < menuMap.size(); keyIndex++) {
				if (trainingRecordListAdd.get(i).getMenu().equals(menuMap.get("00" + (keyIndex + 1)))) {
					trainingRecordListAdd.get(i).setMenuKey("00" + (keyIndex + 1));
				}
			}
		}
		//メニュープルダウンを格納
		trainingRecordListAdd.get(endIndex).setMenuMap(menuMap);
		
		return trainingRecordListAdd;
	}
	
	/**
	 * trainingRecordFormListAddの入力箇所最終indexを返却
	 * @param trainingRecordForm：トレーニング記録画面フォーム
	 * @return endIndex：trainingRecordFormListAddの入力箇所最終index
	 */
	public int endIndex(TrainingRecordForm trainingRecordForm
			) {
		int endIndex = trainingRecordForm.getTrainingRecordListAdd().size()-1;
		trainingRecordForm.setTrainingRecordFormAddListEndIndex(trainingRecordForm.getTrainingRecordListAdd().size()-1);
		return endIndex;
		
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
