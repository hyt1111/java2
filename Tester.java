package chat2 ;

import java.io.* ; // IOExceptionクラスを使うので必要
import java.net.Socket;
import java.util.concurrent.* ; // BlockingQueueインターフェース等を使うので必要

public class  ChatServer3 extends chat.ChatServer {
	private ConcurrentHashMap<String, String> members ;

	public ChatServer3() {}
    public ChatServer3(int portNo) throws IOException, InterruptedException {
        super(portNo) ;
    }

    public ConcurrentHashMap<String, String> getMembers() {
    	return members ;
    }


    @Override
    public void prepareForService() {
    	super.prepareForService() ;

        // 「メンバー表」を生成してメンバーを登録しておく．
        members = new  ConcurrentHashMap<String, String>( );
        members.put( "Zhi-Zhong Chen", "You tell me" ) ;
        members.put( "Hideki Matsui", "Who knows?" ) ;
    }


    @Override
    public void startService(Socket s) throws IOException, InterruptedException {
        // 受け付けたばかりのクライアントに応対する「応対スレッド」を生成して起動する．
        Connection3 connection = new Connection3(s, getQueue(), getBroadCaster(), members);
        connection.start( );
    }


    public static void main( String[] args ) {
        if ( args.length != 1 ) {
            System.out.println("Usage: java ChatServer3 portNo"); // 使い方を表示する.
            return ;  // 使い方が正しくないときに，プログラムを終了する．
        }
        try {
        	new ChatServer3( Integer.parseInt(args[0])).startService() ;
        } catch (Exception e) {
         	e.printStackTrace() ;
        }
    } // mainメソッドの終わり
} // ChatServer3.java の終わり