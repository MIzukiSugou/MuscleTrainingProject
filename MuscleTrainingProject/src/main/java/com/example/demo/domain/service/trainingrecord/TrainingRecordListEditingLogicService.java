package com.example.demo.domain.service.trainingrecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.CommonConst;
import com.example.demo.app.trainingrecord.TrainingRecordForm;
import com.example.demo.app.trainingrecord.TrainingRecordListForm;
import com.example.demo.domain.entity.TrainingRecordDao;
import com.example.demo.domain.repository.TrainingRecordRepository;

/**
 * トレーニング記録画面
 * トレーニング更新記録情報の編集ロジッククラス
 * @author sugou
 *
 */
@Service
public class TrainingRecordListEditingLogicService {
	
	/**トレーニング記録画面Repository */
	private final TrainingRecordRepository repository;
	
	@Autowired
	TrainingRecordListEditingLogicService(TrainingRecordRepository repository){
		this.repository = repository;
	}
	
	/**
	 * 更新トレーニングリストの初期化処理
	 * 更新完了時、検索結果無の場合
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 */
	public void initialization(TrainingRecordForm trainingRecordForm) {
		
		List<TrainingRecordListForm> trainingRecordListEditing = new ArrayList<>();
		trainingRecordForm.setTrainingRecordListEditing(trainingRecordListEditing);
	}
	
	/**
	 * トレーニング記録画面フォームの選択されているプルダウンの年月日よりDBから実施トレーニング情報を取得
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * 
	 */
	public List<TrainingRecordListForm> getSelectTrainingRecordListForm(TrainingRecordForm trainingRecordForm
			) {
		
		//年月日よりDBから実施トレーニング情報を取得して、「トレーニング記録:entity」リストに設定
		List<TrainingRecordDao> trainingRecordDaoList = repository.selectTrainingRecord(trainingRecordForm.getDate(), trainingRecordForm.getUserId());
		
		//「トレーニング記録画面フォーム」にセットする「トレーニング記録画面フォーム」のリストを作成
		List<TrainingRecordListForm> trainingRecordListEditing = new ArrayList<>();
		
		//DBより取得した「トレーニング記録:entity」リストを「トレーニング記録画面フォーム」のリストにセット
		for (int i = 0; i < trainingRecordDaoList.size(); i++ ) {
			
			TrainingRecordListForm addListForm = new TrainingRecordListForm();
			addListForm.setMenu(trainingRecordDaoList.get(i).getTrainingMenu());
			addListForm.setWeight(Integer.parseInt(trainingRecordDaoList.get(i).getWeight()));
			addListForm.setSt1(Integer.parseInt(trainingRecordDaoList.get(i).getSt1()));
			addListForm.setSt2(Integer.parseInt(trainingRecordDaoList.get(i).getSt2()));
			addListForm.setSt3(Integer.parseInt(trainingRecordDaoList.get(i).getSt3()));
			addListForm.setSt4(Integer.parseInt(trainingRecordDaoList.get(i).getSt4()));
			addListForm.setSt5(Integer.parseInt(trainingRecordDaoList.get(i).getSt5()));
			addListForm.setSt6(Integer.parseInt(trainingRecordDaoList.get(i).getSt6()));
			addListForm.setTotal(Integer.parseInt(trainingRecordDaoList.get(i).getTotal()));
			
			trainingRecordListEditing.add(addListForm);
		}
		
		return trainingRecordListEditing;
		
	}
	/**
	 * //DB登録用情報を格納する「トレーニング記録:Dao」リストの作成
	 * @param trainingRecordForm トレーニング記録画面フォーム
	 * 
	 */
	public List<TrainingRecordDao> getUpdateListTrainingRecordDao(TrainingRecordForm trainingRecordForm
			) {
		//「トレーニング記録画面フォーム」に格納されている、「トレーニング記録画面フォーム」のリストを取得
		List<TrainingRecordListForm> trainingRecordFormList = trainingRecordForm.getTrainingRecordListEditing();
		//DB登録用情報を格納する「トレーニング記録:Dao」リストの作成
		List<TrainingRecordDao> trainingRecordDaoList = new ArrayList<>();	
		
		//「トレーニング記録:Dao」リストにDB登録情報の設定
		for (int i = 0; i < trainingRecordFormList.size(); i++) {
			//トレーニング記録情報１件分登録用に「トレーニング記録:entity」インスタンス作成
			TrainingRecordDao trainingRecordDao = new TrainingRecordDao();
					
			if (trainingRecordFormList.get(i).getDeleteFlg() == null ||
				trainingRecordFormList.get(i).getDeleteFlg() == CommonConst.DELETE_FLG_ZERO) {
				trainingRecordDao.setDeleteFlg(CommonConst.DELETE_FLG_ZERO);
			}else {
				trainingRecordDao.setDeleteFlg(trainingRecordFormList.get(i).getDeleteFlg());
			}
			trainingRecordDao.setUserId(trainingRecordForm.getUserId());
			trainingRecordDao.setDateRecord(trainingRecordForm.getDate());
			trainingRecordDao.setTrainingMenu(trainingRecordFormList.get(i).getMenu());
			trainingRecordDao.setWeight(trainingRecordFormList.get(i).getWeight() + "");
			trainingRecordDao.setSt1(trainingRecordFormList.get(i).getSt1() + "");
			trainingRecordDao.setSt2(trainingRecordFormList.get(i).getSt2() + "");
			trainingRecordDao.setSt3(trainingRecordFormList.get(i).getSt3() + "");
			trainingRecordDao.setSt4(trainingRecordFormList.get(i).getSt4() + "");
			trainingRecordDao.setSt5(trainingRecordFormList.get(i).getSt5() + "");
			trainingRecordDao.setSt6(trainingRecordFormList.get(i).getSt6() + "");
			trainingRecordDao.setTotal(trainingRecordFormList.get(i).getTotal() + "");
			trainingRecordDao.setUpdateUser(trainingRecordForm.getUserId());
			trainingRecordDaoList.add(trainingRecordDao);
		}
		
		return trainingRecordDaoList;
		
	}
	
}
