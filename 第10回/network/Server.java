package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {
	private ServerSocket ss ;

	//子クラスで暗黙にまたは明白に呼ばれるために，
	//何もしない引数なしコンストラクタを用意しておく．
	public Server() { }

	//引数ありのコンストラクタ
	public Server(int portNo) throws IOException {
		ss = new ServerSocket(portNo); // サーバーソケットを生成
    }

	//サーバーを止めるメソッド
	public void shutdown() throws IOException {
		if (ss != null && !ss.isClosed()) ss.close();
	}

	//クライアントとの通信を止めるメソッド
	public static void disconnect(Socket s) throws IOException {
		if (s != null && !s.isClosed()) s.close();
	}

	public void startService() {
		prepareForService();
		while ( !ss.isClosed() ) { // 無限ループ
			try {
				Socket s = ss.accept( ); // クライアントからの接続要求を監視・受理する
				startService(s) ; //このクライアントと通信する自作メソッド．
			} catch (Exception e) {
				e.printStackTrace(); //例外が起きても，whileループが継続
			}
		} // 無限ループの終わり
	}

	//クライアント全体にサービスを提供する前の処理を行うメソッド．
	//何もしなくてもいい場合もあるので，ここでdefaultを空にしておく．
	public void prepareForService() {}

	//ソケットを利用してクライアントにサービスを提供するメソッド．子クラスで定義．
	public abstract void startService(Socket s) throws IOException, InterruptedException ;
}
