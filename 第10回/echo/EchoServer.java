package echo ;

import java.net.* ; // ServerSocket�N���X��Socket�N���X�Ȃǂ��g���̂ŕK�v
import java.io.* ; // InputStream�N���X��OutputStream�N���X�Ȃǂ��g���̂ŕK�v
import java.util.* ; // Scanner�N���X���g���̂ŕK�v

import network.Server;
import network.SocketTools;

public class  EchoServer extends Server {
	//�q�N���X�ňÖقɂ܂��͖����ɌĂ΂�邽�߂ɁC
	//�������Ȃ������Ȃ��R���X�g���N�^��p�ӂ��Ă����D
	public EchoServer() { }

	//��������̃R���X�g���N�^
	public EchoServer(int portNo) throws IOException, InterruptedException {
		super(portNo) ;
	}

	//�\�P�b�g���󂯎���ăN���C�A���g�ƒʐM���s�����߂̎��상�\�b�h�D
	@Override
	public void startService(Socket s) throws IOException {
		try (Scanner scan = SocketTools.getScanner(s);
				PrintWriter p = SocketTools.getPrintWriter(s)) {
			p.println("Welcome!!!"); // �ŏ��̈��A����\��
			while ( scan.hasNextLine( ) ) {    // �N���C�A���g�����b�����Ă����.
				String line = scan.nextLine( ); // �N���C�A���g����̎��̉�b����ǂݍ���
				p.println("Server: " + line); // �N���C�A���g����̉�b���ɃG�R�[�����ĕԂ�.
				if (line.trim().equals("BYE")) break;
			}
		} finally {
			disconnect(s) ;
		}
	}

	//main���\�b�h
	public static void main( String[] args ) {
		if ( args.length != 1 ) {
			System.out.println("Usage: java EchoServer portNo"); // �g����
			return ;  // �g�������������Ȃ��Ƃ��ɁC�v���O�������I������D
		}
		try{
			new EchoServer(Integer.parseInt(args[0])).startService() ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}  // main���\�b�h�̏I���
}  // EchoServer.java �̏I���
