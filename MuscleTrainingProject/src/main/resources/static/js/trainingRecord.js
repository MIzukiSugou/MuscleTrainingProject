/**
 * トレーニング記録画面
 */

window.addEventListener('DOMContentLoaded', function() {
	
	//プルダウンが"トレーニングメニューを選択してください"のkey
	const menuMapInitial = "000";
	//ボタン制御フラグ：活性
	const BTNCONTROLACTIVITY = "1";
	//ボタン制御フラグ：非活性
	const BTNCONTROLINACTIVE = "0";
	//trainingRecordListAddSizeのStのid名
	var addStName = "add_st";
	//trainingRecordListAddSizeのTotalのid名
	var addTotal = "add_total_";
	//trainingRecordListEditingのStのid名
	var editingStName = "editing_st";
	//trainingRecordListEditingのTotalのid名
	var editingTotal = "editing_total_";
	//trainingRecordListEditingの削除のid名
	var editingDeleteFlg = "editing_deleteFlg_";
	//trainingRecordListEditingのWeightのid名
	var weight = "weight_";
	
	//trainingRecordListのサイズ分繰り返し実行
	for (var index = 0; index < document.getElementById("trainingRecordListAddSize").value; index++) {
		for (var stIndex = 1; stIndex <= 6; stIndex++){
			document.getElementById(addStName + stIndex + "_" + index).onchange = function(){
  				addSum(addStName,addTotal);
			};
		}
	}
	
	//trainingRecordListのサイズ分繰り返し実行
	for (var index = 0; index < document.getElementById("trainingRecordListEditingSize").value; index++) {
		for (var stIndex = 1; stIndex <= 6; stIndex++){
			document.getElementById(editingStName + stIndex + "_" + index).onchange = function(){
  				editingSum(editingStName,editingTotal,editingDeleteFlg,weight);
			};
			document.getElementById(editingDeleteFlg + index).onchange = function(){
  				editingSum(editingStName,editingTotal,editingDeleteFlg,weight);
			};
		}
	}	
	// select要素を取得
	var select_menu = document.querySelector("select[name=menuKey]");
	
	// ページ読み込み時の処理
	window.onload = function(){
		recordBtn();
		selectChange();
		addSum(addStName,addTotal);
		editingSum(editingStName,editingTotal,editingDeleteFlg,weight);
	}
	
	//プルダウン変更時の処理
	select_menu.addEventListener('change', function() {
		selectChange();
	});
	
	//プルダウン状態での入力項目の表示：非表示
	function selectChange() {
		
		if (select_menu.value != menuMapInitial) {
			//プルダウンが"トレーニングメニューを選択してください"以外の場合、disabled属性を削除
			document.getElementById("menuInputButton").removeAttribute("disabled");
			
			console.log(select_menu.value);
	
		} else {
			//プルダウンが"トレーニングメニューを選択してください"の場合、disabled属性を設定
			document.getElementById("menuInputButton").setAttribute("disabled", true);
			
		}
	}
	
	//記録ボタンの活性・非活性制御
	function recordBtn(){
	
		if (document.getElementById("recordBtnControl").value === BTNCONTROLACTIVITY){
			// disabled属性を削除
			document.getElementById("recordBtn").removeAttribute("disabled");
			
		}else if (document.getElementById("recordBtnControl").value === BTNCONTROLINACTIVE) {
			// disabled属性を設定
			document.getElementById("recordBtn").setAttribute("disabled", true);
		}
	}

	
	//追加項目の入力フォームの制御
	function addSum(stId,totalId) {
		//trainingRecordListのサイズ分繰り返し実行
		for (var index = 0; index < document.getElementById("trainingRecordListAddSize").value; index++) {
			sum = 0;
			for (var stIndex = 1; stIndex <= 6; stIndex++){
				sum += Number(document.getElementById(stId + stIndex + "_" + index).value);
			}
			document.getElementById(totalId + index).value = sum;	
		}
	}
	
	//更新項目の入力フォームの制御
	function editingSum(stId,totalId,deleteId,weightId) {
		//trainingRecordListのサイズ分繰り返し実行
		for (var index = 0; index < document.getElementById("trainingRecordListEditingSize").value; index++) {
			sum = 0;
			for (var stIndex = 1; stIndex <= 6; stIndex++){
				
				sum += Number(document.getElementById(stId + stIndex + "_" + index).value);
				
				if (document.getElementById(deleteId + index).checked){
					// disabled属性を設定
					document.getElementById(stId + stIndex + "_" + index).setAttribute("disabled", true);
					document.getElementById(totalId + index).setAttribute("disabled", true);
					document.getElementById(weightId + index).setAttribute("disabled", true);
			
				}else {
					// disabled属性を削除
					document.getElementById(stId + stIndex + "_" + index).removeAttribute("disabled");
					document.getElementById(totalId + index).removeAttribute("disabled");
					document.getElementById(weightId + index).removeAttribute("disabled");
				}
			}
			document.getElementById(totalId + index).value = sum;	
		}
	}	
	
	
});

