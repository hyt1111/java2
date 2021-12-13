package network2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Client {
	//通信用のソケットを覚える変数．
	//子クラスで直接使用できるように，protectedにしておく．
	private ScanPrintSocket s ;

	//子クラスで呼ばれるために，何もしない引数なしコンストラクタを用意しておく．
	public Client() { }

	//引数ありのコンストラクタ
	public Client(String serverName, int portNo) throws IOException {
		s = new ScanPrintSocket(new Socket(serverName, portNo)); //サーバーに接続.
	}

	//クライアントを止めるメソッド
	public void disconnect() throws IOException {
		if (s != null && !s.isClosed()) s.close();
	}

	//クライアント全体にサービスを提供する前の準備を行うメソッド．
	//ここでは，終了の仕方を表示することにしておく．
	public void prepareForService() {
		// 指示文を表示する.
		System.out.println("If you want to quit, please type \"BYE\"!");
	}

	//送受信を担当するスレッドをそれぞれ生成して起動するメソッド．
	public void startService() throws IOException {
		prepareForService() ; //準備をする自作メソッド．

		//受信用スレッドを生成して起動
		new Thread(new Runnable() {
			public void run( ) { 	   // 仕事の内容
				try (Scanner scan = s.getScanner()) {
					receive(scan) ;
				} catch ( IOException e ) {
					e.printStackTrace() ;
				}
			} // runメソッドの終わり
		}).start(); //受信を担当するスレッドを生成して起動.

		//送信用スレッドを生成して起動
		new Thread(new Runnable(){
			public void run( ) { 	     // 仕事の内容
				try (PrintWriter p = s.getPrintWriter()) {
					send(p) ;
				} catch(IOException ex) {
					ex.printStackTrace() ;
				}
			} // runメソッドの終わり
		}).start(); //送信を担当するスレッドを生成して起動.
	}


	//PrintWriterを受け取ってサーバーにメッセージを送るためのメソッド．子クラスで定義．
	public abstract void send(PrintWriter p) throws IOException ;


	//scannerを受け取ってサーバーからメッセージを受信するためのメソッド．子クラスで定義．
	public abstract void receive(Scanner scan) throws IOException ;

	//accessorメソッド
	public Socket getSocket() {
		return s ;
	}
	public void setSocket(ScanPrintSocket s) {
		this.s = s ;
	}
}

