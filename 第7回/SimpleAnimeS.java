package forStudent;

import javax.swing.*;  //Swingライブラリの提供するJPanel等を使うので
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.* ;  // AWTライブラリの提供するBorderLayout等を使うので
import java.awt.event.*; //ActionListener等を使うので
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

@SuppressWarnings("serial")
public class SimpleAnimeS extends anime.AbstractAnime {
	// ① すべてのメソッドや内部クラスで使えるような変数の宣言
	private String words[][] = {
		{"We", "You", "They", "Boys", "Girls"},
		{"like", "hate", "play"},
		{"baseball", "basketball", "pingpong", "chess"}
	};  // 主語・述語・目的語を覚えるための2次元配列
	private int index ;  // 次に表示する単語のindex（0以上2以下）を覚える変数
	private JButton ctrlBtn ;  // アニメーションをコントロールするボタン
	private LinkedList<String> sentence ; //文章の単語を記憶するリスト
	private Random rand ; //乱数生成器を記憶する
	private JSlider speedSlider ; //アニメの速度を制御するスライダ

	@Override
	public void initAnime() {
		index = 0 ;//最初に生成するのは主語
		sentence = new LinkedList<String>() ; //空のリストを作成
		rand = new Random() ; //乱数生成器を作成
	}

	//部品を生成して配置するメソッド
	@Override
	public void arrangeComponents() {
		setLayout(new BorderLayout( ) ) ; // アプレットのレイアウトを設定する
		JPanel animePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g); // 親クラスのpaintComponentメソッドで描画を準備
				drawAnime(g) ; //文章を描くメソッドを呼び出す
			}
		}; // 画像表示パネルを生成する
		add("Center", animePanel ) ;  // 画像表示パネルをアプレットの中央に配置
		animePanel.setBackground(Color.YELLOW) ;

		JPanel buttonPanel = new JPanel( ) ; // ボタンパネルを生成
		add("South", buttonPanel ) ; // ボタンパネルをアプレットの南側に配置
		ctrlBtn = new JButton("停止") ;  // コントロールボタンを生成
		buttonPanel.add( ctrlBtn ) ; // ボタンパネルにコントロールボタンを配置

		//北側領域のセットアップ
		speedSlider = new JSlider(SwingConstants.HORIZONTAL, 1, 1000, 200) ;
		JPanel speedPanel = new JPanel(); // NORTH 用のパネル
		add("North", speedPanel) ;
		//speedPanel.setLayout(new FlowLayout());
		speedPanel.setBackground(Color.GREEN);
		JLabel speedLabel = new JLabel("速度：");
		speedLabel.setForeground (Color.RED);
		speedPanel.add(speedLabel) ;
		speedPanel.add(new JLabel("高")) ;
		speedPanel.add(speedSlider) ;
		speedPanel.add(new JLabel("低")) ;
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

		//speed slider にリスナーを付ける
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(final ChangeEvent e) {
				setDelay(speedSlider.getValue()); // 動かされたら表示時間を変更
			}
		});
	}


	@Override
	public void updateAnime( ) { //アニメの1ステップを行うメソッド
		if (index == words.length) {//文章が完成したら，クリアする．
			index = 0 ;
			sentence.clear() ;
		}

		//文章の次の語をランダムに生成して，文章に追加．
		int i = rand.nextInt(words[index].length) ;
		sentence.add(words[index][i]) ;

		index = (index + 1) ; //次の語の生成へ
	}


	//文章を描くメソッド
	public void drawAnime(Graphics g) {
		// 文章の単語を1つの文字列にまとめる
		String result = "" ;
		for (String str : sentence)
			result = result + " " + str ;

		Graphics2D g2 = (Graphics2D) g ; //GraphicsをGraphics2Dに変換

		//文章を描画
		double y = getHeight( ) / 2 ; //描画パネルの縦中央の座標を計算
		Font font = new Font( "SansSerif", Font.BOLD, 36) ; // フォントを生成.
		g2.setFont( font ) ; //フォントを設定
		g2.drawString( result, 10, (int) y) ; // 文字列を描く.
	}


	//getter method
	public JButton getCtrlBtn() {
		return ctrlBtn ;
	}
} // SimpleAnimeS.javaの終わり