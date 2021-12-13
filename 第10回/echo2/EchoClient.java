package echo2 ;

import java.util.Scanner;
import java.io.* ; // PrintWriter�N���X�Ȃǂ��g���̂ŕK�v

import network2.Client;

public class EchoClient extends Client {
	//�q�N���X�ŌĂ΂�邽�߂ɁC�������Ȃ������Ȃ��R���X�g���N�^��p�ӂ��Ă����D
	public EchoClient() { }

	//��������̃R���X�g���N�^
	public EchoClient(String serverName, int portNo) throws IOException {
        super(serverName, portNo) ;
	}

	//printWriter���󂯎���ăT�[�o�[�Ƀ��b�Z�[�W�𑗂邽�߂̎��상�\�b�h�D
	@SuppressWarnings("resource") //�L�[�{�[�h����̓��͗pScanner��������Ȃ��̂�
	public void send(PrintWriter p) throws IOException {
		Scanner keyboard = new Scanner(System.in); // �L�[�{�[�h����̓��͗p.
		String request ; // �T�[�o�[�ɑ�����ꕶ���o����ϐ��D
		try {
			while ( keyboard.hasNextLine() ) {
				request = keyboard.nextLine( ); //�L�[�{�[�h���玟�̉�b����ǂݍ���.
				p.println(request); // �ǂݍ��񂾉�b�����T�[�o�[�ɑ���.
				if ( request.equals("BYE") )  break ;
			}
		} finally {
			disconnect() ;
		}
	}


	//scanner���󂯎���ăT�[�o�[���烁�b�Z�[�W����M���邽�߂̎��상�\�b�h�D
	public void receive(Scanner scan) throws IOException {
		 String reply ; // �T�[�o�[�����M�����ꕶ���o����ϐ��D
		 try {
			 while ( scan.hasNextLine() ) {
				 reply = scan.nextLine( ); //���̓X�g���[�����玟�̉�b����ǂݍ���.
				 System.out.println(reply); // �ǂݍ��񂾉�b������ʂɕ\������.
			 }
		 } finally {
			disconnect() ;
		}
	}

	//main���\�b�h
    public static void main( String [ ] args ) throws IOException {
        if ( args.length != 2 ) {
            System.out.println("Usage: java EchoClient serverName portNo"); // �g����
            return ;  // �g�������������Ȃ��Ƃ��ɁC�v���O�������I������D
        }
        new EchoClient(args[0], Integer.parseInt(args[1])).startService(); //�T�[�o�[�ɐڑ�.
    } // main���\�b�h�̏I���
} // EchoClient.java �̏I���