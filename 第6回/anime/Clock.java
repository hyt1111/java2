package anime ;

import javax.swing.*;  //Swingライブラリの提供するJPanel等を使うので
import java.awt.event.*; //ActionListener等を使うので
import java.util.Calendar;

@SuppressWarnings("serial")
public class Clock extends AbstractAnime {
	// ① すべてのメソッドや内部クラスで使えるような変数の宣言
	private boolean isAm = true ; //現在時刻は午前か午後かを覚える変数
	private String time ;//現在時刻を覚える変数
	private JTextField tf ;//現在時刻を表示するテキストフィールド
	private JButton bt ;//「停止」または「開始」ボタン

	private void getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.AM_PM) == Calendar.AM)
			isAm = true ;
		else
			isAm = false ;
		time = String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR),
			cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND) ) ;
	}


	//部品を生成して配置するメソッド
	@Override
	public void arrangeComponents() {
		//レイアウトを設定して部品を生成・配置する
		//JPanel panel = new JPanel() ;
		//setLayout(new BorderLayout()) ;
		//add("Center", panel) ;
		JLabel lb = new JLabel("現在時刻") ;
		add(lb) ;
		getCurrentTime() ;
		tf = new JTextField("午前 " + time, 8) ;
		add(tf) ;
		tf.setEditable(false) ;
		bt = new JButton("停止") ;
		add(bt) ;
	}


	//部品にリスナーを付けるメソッド
	@Override
	public void addListeners() {
		//ボタンに無名のリスナーを付ける
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand( ) ; // 押されたボタンの名前を得る
				if ( cmd.equals("停止") ) {  // 停止ボタンが押されたら
					stopAnime( ) ; // アニメスレッドを停止
					bt.setText("開始"); // ボタンの名前を「開始」に設定
				} else {  // 開始ボタンが押されたら
					startAnime( ) ;  // アニメスレッドを起動する
					bt.setText("停止"); // ボタンの名前を「停止」に設定
				}
			}
		}) ;
	}


	//アニメを開始するメソッド
	@Override
	public void startAnime() {
		//アクションを定期的に起こすタイマ・スレッドを生成して起動
		getCurrentTime() ;
		setDelay(1000) ;
		super.startAnime() ;
	}


	//現在時刻を更新するメソッド
	@Override
	public void updateAnime() {
		//現在時刻（文字列）から現在の時・分・秒を求める
		String[] array = time.split(":") ;
		int h = Integer.parseInt(array[0]) ;
		int m = Integer.parseInt(array[1]) ;
		int s = Integer.parseInt(array[2]) ;

		//現在時刻に1秒を足して、必要に応じて桁の繰り上がり処理
		//および 午前・午後の切り替えを行う
		s++ ;
		if (s >= 60) {
			s = 0 ;
			m++ ;
			if (m >= 60) {
				m = 0 ;
				h++ ;
				if (h >= 12) {
					h = 0 ;
					isAm = !isAm ;
				}
			}
		}

		//新しい現在の時・分・秒を文字列（それぞれ2桁）にまとめる
		time = String.format("%02d:%02d:%02d", h, m, s) ;

		//新しい時刻をテキストフィールドに設定
		if (isAm)
			tf.setText("午前 " + time) ;
		else
			tf.setText("午後 " + time) ;
	}


	@Override
	public void initAnime() { }
} // Clock.javaの終わり