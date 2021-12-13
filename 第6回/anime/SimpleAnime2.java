package anime ;

import javax.swing.*;  //Swingライブラリの提供するJPanel等を使うので
import java.awt.* ;  // AWTライブラリの提供するBorderLayout等を使うので
import java.awt.event.*; //ActionListener等を使うので
import java.io.File;

@SuppressWarnings("serial")
public class SimpleAnime2 extends AbstractAnime {
	// ① すべてのメソッドや内部クラスで使えるような変数の宣言
	private Image imgs[ ] ;  // 全画像を覚えるための配列
	private int img_index ;  // 次に表示する画像を覚える変数
	private JButton ctrlBtn ;  // アニメーションをコントロールするボタン

	@Override
	public void initAnime() {
		String[] fileNames = new String[6] ;
		for (int i = 0 ; i < fileNames.length ; i++) {
			fileNames[i] = "bin/anime/java" + i + ".gif" ;
			if (!(new File(fileNames[i]).exists())) {
				System.out.println("File " + fileNames[i] + ": not found") ;
				System.exit(0) ;
			}
		}
		try {
			imgs = loadImages(fileNames) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//部品を生成して配置するメソッド
	@Override
	public void arrangeComponents() {
		setLayout(new BorderLayout( ) ) ; // アプレットのレイアウトを設定する
		JPanel animePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g); // 親クラスのpaintComponentメソッドで描画を準備
				drawAnime(g) ; //図形を描くメソッドを呼び出す
			}
		}; // 画像表示パネルを生成する
		add("Center", animePanel ) ;  // 画像表示パネルをアプレットの中央に配置
		JPanel buttonPanel = new JPanel( ) ; // ボタンパネルを生成
		add("South", buttonPanel ) ; // ボタンパネルをアプレットの南側に配置
		ctrlBtn = new JButton("停止") ;  // コントロールボタンを生成
		buttonPanel.add( ctrlBtn ) ; // ボタンパネルにコントロールボタンを配置
	}


	//部品にリスナーを付けるメソッド
	@Override
	public void addListeners() {
		// ボタンに自作リスナーを付ける
		ctrlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand( ) ; // 押されたボタンの名前を得る
				if ( cmd.equals("停止") ) {  // 停止ボタンが押されたら
					ctrlBtn.setText("開始"); // ボタンの名前を「開始」に設定
					stopAnime( ) ; // アニメスレッドを停止
				} else {  // 開始ボタンが押されたら
					ctrlBtn.setText("停止"); // ボタンの名前を「停止」に設定
					startAnime( ) ;  // アニメスレッドを起動する
				}
			}
		}) ;
	}


	@Override
	public void updateAnime( ) { //アニメの1ステップを行うメソッド
		img_index = (img_index + 1) % imgs.length ;
	}

	//図形を描くメソッド
	public void drawAnime(Graphics g) {
		Dimension d = getSize( ); //この（描画）パネルのサイズを得る

		// 画像表示パネル一杯に次の画像を表示する
		g.drawImage( imgs[img_index], 0, 0, d.width, d.height, this );
	}

	//getter method
	public JButton getCtrlBtn() {
		return ctrlBtn ;
	}
} // SimpleAnime2.javaの終わり