package myGUI;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractGUI extends JPanel {
	//GUIを開始するメソッド．finalにしてある．
	public final void start() {
		initialize( ) ; //フィールド等を初期化
		arrangeComponents( ) ; //部品を生成して配置
		addListeners( ) ; //部品にリスナーを付ける
	}

	//フィールド等を初期化するメソッド.
	//必要に応じて，子クラスでoverride.
	public void initialize( ) { }; //defaultは空.

	//部品を生成して配置するメソッド
	public abstract void arrangeComponents( ) ;

	//部品にリスナーを付けるメソッド
	public abstract void addListeners( ) ;
}