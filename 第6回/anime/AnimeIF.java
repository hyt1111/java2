package anime;

public interface AnimeIF {
	void initAnime( ) ; //初期化用のメソッド
    void startAnime( ) ; //アニメを（再）起動するメソッド
    void stopAnime( ) ; //アニメを止めるメソッド
    void updateAnime( ) ; //アニメの1ステップを行うメソッド
    boolean isRunning( ) ; //アニメが動いているかを判定するメソッド
}
