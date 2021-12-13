package chat2 ;

import java.io.* ; // IOException�N���X���g���̂ŕK�v
import java.net.Socket;
import java.util.concurrent.* ; // BlockingQueue�C���^�[�t�F�[�X�����g���̂ŕK�v

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

        // �u�����o�[�\�v�𐶐����ă����o�[��o�^���Ă����D
        members = new  ConcurrentHashMap<String, String>( );
        members.put( "Zhi-Zhong Chen", "You tell me" ) ;
        members.put( "Hideki Matsui", "Who knows?" ) ;
    }


    @Override
    public void startService(Socket s) throws IOException, InterruptedException {
        // �󂯕t�����΂���̃N���C�A���g�ɉ��΂���u���΃X���b�h�v�𐶐����ċN������D
        Connection3 connection = new Connection3(s, getQueue(), getBroadCaster(), members);
        connection.start( );
    }


    public static void main( String[] args ) {
        if ( args.length != 1 ) {
            System.out.println("Usage: java ChatServer3 portNo"); // �g������\������.
            return ;  // �g�������������Ȃ��Ƃ��ɁC�v���O�������I������D
        }
        try {
        	new ChatServer3( Integer.parseInt(args[0])).startService() ;
        } catch (Exception e) {
         	e.printStackTrace() ;
        }
    } // main���\�b�h�̏I���
} // ChatServer3.java �̏I���