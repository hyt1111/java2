package echo2 ;

import java.io.* ; // InputStreamクラスやOutputStreamクラスなどを使うので必要
import java.util.* ; // Scannerクラスを使うので必要

import network2.Server;
import network2.ScanPrintSocket;

public class EchoServer extends Server {
	//子クラスで暗黙にまたは明白に呼ばれるために，
	//何もしない引数なしコンストラクタを用意しておく．
	public EchoServer() { }

	//引数ありのコンストラクタ
	public EchoServer(int portNo) throws IOException, InterruptedException {
		super(portNo) ;
	}

	//ソケットを受け取ってクライアントと通信を行うための自作メソッド．
	public void startService(ScanPrintSocket s) throws IOException {
		try (Scanner scan = s.getScanner();
				PrintWriter p = s.getPrintWriter()) {
			p.println("Welcome!!!"); // 最初の挨拶文を表示
			while ( scan.hasNextLine( ) ) {    // クライアントから会話が来ている間.
				String line = scan.nextLine( ); // クライアントからの次の会話文を読み込む
				System.out.println(line) ;
				p.println("Server: " + line); // クライアントからの会話文にエコーをつけて返す.
				if (line.trim().equals("BYE")) break;
			}
		} finally {
			disconnect(s) ;
		}
	}

	//mainメソッド
	public static void main( String[] args ) {
		if ( args.length != 1 ) {
			System.out.println("Usage: java EchoServer portNo"); // 使い方
			return ;  // 使い方が正しくないときに，プログラムを終了する．
		}
		try{
			new EchoServer(Integer.parseInt(args[0])).startService() ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}  // mainメソッドの終わり
}  // EchoServer.java の終わり
