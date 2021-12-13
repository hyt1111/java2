package echo ;

import java.io.* ; // IOExceptionクラスを使うので必要
import java.net.Socket;

import network.Server;

public class EchoServer2 extends Server {
	//引数ありのコンストラクタ
	 public EchoServer2(int portNo) throws IOException, InterruptedException {
		 super(portNo);
	}

	@Override
	public void startService(Socket s) {
		//スレッドを生成して起動し，clientとの通信を行う
		new Thread(new Runnable() {
			public void run() {
				try {
					new EchoServer().startService(s) ;
				} catch (IOException ex) {
					ex.printStackTrace() ;
				}
			}
		}).start(); //生成した仕事を担当するスレッドを生成して起動.
	}

	//mainメソッド
	public static void main( String[] args ) {
		if ( args.length != 1 ) {
			System.out.println("Usage: java EchoServer2 portNo"); // 使い方
			return ;  // 使い方が正しくないときに，プログラムを終了する．
		}
		try{
			new EchoServer2(Integer.parseInt(args[0])).startService() ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}  // mainメソッドの終わり
} // EchoServer2.java の終わり