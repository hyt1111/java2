package echo2 ;

import java.io.* ; // IOException�N���X���g���̂ŕK�v

import network2.Server;
import network2.ScanPrintSocket;

public class EchoServer2 extends Server {
	//��������̃R���X�g���N�^
    public EchoServer2(int portNo) throws IOException, InterruptedException {
        super(portNo);
    }

    public void startService(ScanPrintSocket s) {
		//�X���b�h�𐶐����ċN�����Cclient�Ƃ̒ʐM���s��
        new Thread(new Runnable() {
        	public void run() {
        		try {
        		    new EchoServer().startService(s) ;
        		} catch (IOException ex) {
        			ex.printStackTrace() ;
        		}
        	}
        }).start(); //���������d����S������X���b�h�𐶐����ċN��.
    }

	//main���\�b�h
    public static void main( String[] args ) {
        if ( args.length != 1 ) {
            System.out.println("Usage: java EchoServer2 portNo"); // �g����
            return ;  // �g�������������Ȃ��Ƃ��ɁC�v���O�������I������D
        }
        try{
        	new EchoServer2(Integer.parseInt(args[0])).startService() ;
        } catch (Exception e) {
        	e.printStackTrace() ;
        }
    }  // main���\�b�h�̏I���
} // EchoServer2.java �̏I���