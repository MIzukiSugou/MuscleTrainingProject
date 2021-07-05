/**
 * トレーニング記録画面
 */

const menuMapInitial = "トレーニングメニューを選択してください";

window.addEventListener('DOMContentLoaded', function() {
	
	// select要素を取得
	var select_menu = document.querySelector("select[name=menu]");

	// 入力項目要素を取得
	let menuInputButton = document.getElementById('menuInputButton');

	// 現在の display プロパティの値を保持
	const displayInputButton = menuInputButton.style.display;
	
	
	// ページ読み込み時の処理
	window.onload = function(){
		selectChange();
	}
	
	//プルダウン変更時の処理
	select_menu.addEventListener('change', function() {
		selectChange();
	});
	
	//プルダウン状態での入力項目の表示：非表示
	function selectChange() {
				if (select_menu.value != menuMapInitial) {
			//プルダウンが"トレーニングメニューを選択してください"以外の場合、入力フォーム表示
			menuInputButton.style.display = displayInputButton;
			
			console.log(select_menu.value);
	
		} else {
			//プルダウンが"トレーニングメニューを選択してください"の場合、入力フォーム非表示
			menuInputButton.style.display = 'none';
			
			console.log(select_menu.value);
			
		}
	}
});

