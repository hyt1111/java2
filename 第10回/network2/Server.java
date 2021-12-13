package network2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {
	private ServerSocket ss ;

	//�q�N���X�ňÖقɂ܂��͖����ɌĂ΂�邽�߂ɁC
	//�������Ȃ������Ȃ��R���X�g���N�^��p�ӂ��Ă����D
	public Server() { }

	//��������̃R���X�g���N�^
	public Server(int portNo) throws IOException {
		ss = new ServerSocket(portNo); // �T�[�o�[�\�P�b�g�𐶐�
    }

	//�T�[�o�[���~�߂郁�\�b�h
	public void shutdown() throws IOException {
		if (ss != null && !ss.isClosed()) ss.close();
	}

	//�N���C�A���g�Ƃ̒ʐM���~�߂郁�\�b�h
	public static void disconnect(Socket s) throws IOException {
		if (s != null && !s.isClosed()) s.close();
	}

	public void startService() throws IOException, InterruptedException {
		prepareForService();
		while ( !ss.isClosed() ) { // �������[�v
			try {
				Socket s = ss.accept( ); // �N���C�A���g����̐ڑ��v�����Ď��E�󗝂���
				startService(new ScanPrintSocket(s)) ; //���̃N���C�A���g�ƒʐM���鎩�상�\�b�h�D
			} catch (Exception e) { e.printStackTrace(); }
		} // �������[�v�̏I���
	}

	//�N���C�A���g�S�̂ɃT�[�r�X��񋟂���O�̏������s�����\�b�h�D
	//�������Ȃ��Ă������ꍇ������̂ŁC������default����ɂ��Ă����D
	public void prepareForService() {}

	//�\�P�b�g�𗘗p���ăN���C�A���g�ɃT�[�r�X��񋟂��郁�\�b�h�D�q�N���X�Œ�`�D
	public abstract void startService(ScanPrintSocket s)
			throws IOException, InterruptedException ;
}
