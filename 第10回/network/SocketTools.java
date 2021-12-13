package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class SocketTools {
	private SocketTools() {} ;//�C���X�^���X�̐������֎~�D

	//�\�P�b�g������͗p��Scanner���擾���邽�߂̃��\�b�h
	public static Scanner getScanner(Socket s) throws IOException {
        InputStream in = s.getInputStream( ); // �\�P�b�g������̓X�g���[�����擾.
        Reader reader = new InputStreamReader(in, "UTF-8");// �����R�[�h�΍�
        return new Scanner(reader); // �X�g���[���ɃX�L���i�[�����Ԃ���.
	}

	//�\�P�b�g����o�͗p��PrintWriter���擾���邽�߂̃��\�b�h
	public static PrintWriter getPrintWriter(Socket s) throws IOException {
        OutputStream out = s.getOutputStream(); // �\�P�b�g����o�̓X�g���[�����擾.
        Writer writer = new OutputStreamWriter(out, "UTF-8");
        return new PrintWriter(writer, true);//�X�g���[���Ƀv�����g���C�^�[�����Ԃ���D
	}
}
