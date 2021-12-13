package anime;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractAnime extends JPanel implements AnimeIF{
    private Thread animeThread ; //アニメを担当するスレッドを覚える変数
    private int delay = 500 ; //画面の更新間隔（単位：ms）
    private volatile boolean running = true ;

	public final void start() {
		initAnime( ) ; //フィールド等を初期化
		arrangeComponents( ) ; //部品を生成して配置
		addListeners( ) ; //部品にリスナーを付ける
		startAnime() ; //アニメを開始
	}

	//部品を生成して配置するメソッド
	public abstract void arrangeComponents( ) ;

	//部品にリスナーを付けるメソッド
	public abstract void addListeners( ) ;

	// 自作loadImagesメソッドの定義：
	public Image[] loadImages(String fileNames[])
			throws IOException, InterruptedException {
		Image[] imgs = new Image[fileNames.length]; //全画像を覚える配列の生成
		MediaTracker tracker = new MediaTracker(this);
		for ( int i = 0 ; i < imgs.length ; i++) {   //画像をファイルから読み込む
			File file = new File(fileNames[i]) ;
			imgs[i] = ImageIO.read(file) ;
			tracker.addImage( imgs[i], i) ; // ID番号iで画像をメディアトラッカーに登録
		}
		tracker.waitForAll( ) ; // すべての画像のロードが完了するまで待つ
		return imgs ;
	} // loadImagesメソッドの終わり

	@Override
	public void startAnime( ) {
		if (!isRunning()) {
			running = true ;
			animeThread = new Thread() {
				@Override
				public void run() {
					animeLoop() ;
				}
			};
			animeThread.start() ;
		}
	}

	public void animeLoop() {
		try {
			while ( running ) {
				updateAnime() ;
				repaint( ) ; // 画面の再描画を要求
				Thread.sleep( delay ) ;  // 連続する画像間では0.2秒間ポーズをかける
			}
		} catch (InterruptedException e) {
			running = false ;
		}
	}

	@Override
	public void stopAnime() {
		if (isRunning()) {
			running = false ;
			animeThread = null ;
		}
	}

	//アニメが動いているかを調べるメソッド
	@Override
	public boolean isRunning() {
		return animeThread != null && running == true ;
	}

	//setter and getter methods
	public int getDelay() {
		return delay ;
	}
	public void setDelay(int delay) {
		this.delay = delay ;
	}
	public Thread getAnimeThread() {
		return animeThread ;
	}
	public void setAnimeThread(Thread t) {
		animeThread = t ;
	}
}