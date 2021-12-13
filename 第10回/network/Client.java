package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Client {
	//�ʐM�p�̃\�P�b�g���o����ϐ��D
	//�q�N���X�Œ��ڎg�p�ł���悤�ɁCprotected�ɂ��Ă����D
	private Socket s ;

	//�q�N���X�ŌĂ΂�邽�߂ɁC�������Ȃ������Ȃ��R���X�g���N�^��p�ӂ��Ă����D
	public Client() { }

	//��������̃R���X�g���N�^
	public Client(String serverName, int portNo) throws IOException {
		s = new Socket(serverName, portNo); //�T�[�o�[�ɐڑ�.
	}

	//�N���C�A���g���~�߂郁�\�b�h
	public void disconnect() throws IOException {
		if (s != null && !s.isClosed()) s.close();
	}

	//�N���C�A���g�S�̂ɃT�[�r�X��񋟂���O�̏������s�����\�b�h�D
	//�����ł́C�I���̎d����\�����邱�Ƃɂ��Ă����D
	public void prepareForService() {
		// �w������\������.
		System.out.println("To quit, close the frame or type \"BYE\"!");
	}

	//����M��S������X���b�h�����ꂼ�ꐶ�����ċN�����郁�\�b�h�D
	public void startService() throws IOException {
		prepareForService() ; //���������鎩�상�\�b�h�D

		//��M�p�X���b�h�𐶐����ċN��
		new Thread(new Runnable() {
			public void run( ) { 	   // �d���̓��e
				try (Scanner scan = SocketTools.getScanner(s)) {
					receive(scan) ;
				} catch ( Exception e ) {
					e.printStackTrace() ;
				}
			} // run���\�b�h�̏I���
		}).start(); //��M��S������X���b�h�𐶐����ċN��.

		//���M�p�X���b�h�𐶐����ċN��
		new Thread(new Runnable(){
			public void run( ) { 	     // �d���̓��e
				try (PrintWriter p = SocketTools.getPrintWriter(s)) {
					send(p) ;
				} catch(IOException ex) {
					ex.printStackTrace() ;
				}
			} // run���\�b�h�̏I���
		}).start(); //���M��S������X���b�h�𐶐����ċN��.
	}


	//PrintWriter���󂯎���ăT�[�o�[�Ƀ��b�Z�[�W�𑗂邽�߂̃��\�b�h�D�q�N���X�Œ�`�D
	public abstract void send(PrintWriter p) throws IOException ;


	//scanner���󂯎���ăT�[�o�[���烁�b�Z�[�W����M���邽�߂̃��\�b�h�D�q�N���X�Œ�`�D
	public abstract void receive(Scanner scan) throws IOException ;

	//accessor���\�b�h
	public Socket getSocket() {
		return s ;
	}
	public void setSocket(Socket s) {
		this.s = s ;
	}
}
