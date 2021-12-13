package echo ;

import java.util.Scanner;
import java.io.* ; // PrintWriterクラスなどを使うので必要

import network.Client;

public class EchoClient extends Client {
	//子クラスで呼ばれるために，何もしない引数なしコンストラクタを用意しておく．
	public EchoClient() { }

	//引数ありのコンストラクタ
	public EchoClient(String serverName, int portNo) throws IOException {
        super(serverName, portNo) ;
	}

	//printWriterを受け取ってサーバーにメッセージを送るための自作メソッド．
	@SuppressWarnings("resource") //キーボードからの入力用Scannerを閉じたくないので
	public void send(PrintWriter p) throws IOException {
		Scanner keyboard = new Scanner(System.in); // キーボードからの入力用.
		String request ; // サーバーに送られる一文を覚える変数．
		try {
			while ( !getSocket().isClosed() && keyboard.hasNextLine() ) {
				request = keyboard.nextLine( ); //キーボードから次の会話文を読み込む.
				p.println(request); // 読み込んだ会話文をサーバーに送る.
				if ( request.equals("BYE") )  break ;
			}
		} finally {
			disconnect() ;
		}
	}


	//scannerを受け取ってサーバーからメッセージを受信するための自作メソッド．
	public void receive(Scanner scan) throws IOException {
		 String reply ; // サーバーから受信した一文を覚える変数．
		 try {
			 while ( scan.hasNextLine() ) {
				 reply = scan.nextLine( ); //入力ストリームから次の会話文を読み込む.
				 System.out.println(reply); // 読み込んだ会話文を画面に表示する.
			 }
		 } finally {
			disconnect() ;
		}
	}

	//mainメソッド
    public static void main( String [ ] args ) throws IOException {
        if ( args.length != 2 ) {
            System.out.println("Usage: java EchoClient serverName portNo"); // 使い方
            return ;  // 使い方が正しくないときに，プログラムを終了する．
        }
        new EchoClient(args[0], Integer.parseInt(args[1])).startService(); //サーバーに接続.
    } // mainメソッドの終わり
} // EchoClient.java の終わり