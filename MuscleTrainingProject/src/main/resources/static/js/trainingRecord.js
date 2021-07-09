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
	
	//totalの自動計算
	var stName = "st"
	var total = "total_";
	
	//trainingRecordListのサイズ分繰り返し実行
	for (var index = 0; index < document.getElementById("trainingRecordListSize").value; index++) {
		for (var stIndex = 1; stIndex <= 6; stIndex++){
			var idName = stName + stIndex + "_" + index;
			document.getElementById(idName).onchange = function(){
  				totalKeisan(stName,total);
			};
		}
	}
	
	// select要素を取得
	var select_menu = document.querySelector("select[name=menuKey]");
	
	// ページ読み込み時の処理
	window.onload = function(){
		recordBtn();
		selectChange();
		totalKeisan(stName,total);
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

	
	//totalの自動計算
	function totalKeisan(stName,total) {
		//trainingRecordListのサイズ分繰り返し実行
		for (var index = 0; index < document.getElementById("trainingRecordListSize").value; index++) {
			countTotal = 0;
			for (var stIndex = 1; stIndex <= 6; stIndex++){
				var idName = stName + stIndex + "_" + index;
				countTotal += Number(document.getElementById(idName).value);
			}
			document.getElementById(total + index).value = countTotal;	
		}
	}
	
});

