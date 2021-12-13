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
	private SocketTools() {} ;//インスタンスの生成を禁止．

	//ソケットから入力用のScannerを取得するためのメソッド
	public static Scanner getScanner(Socket s) throws IOException {
        InputStream in = s.getInputStream( ); // ソケットから入力ストリームを取得.
        Reader reader = new InputStreamReader(in, "UTF-8");// 漢字コード対策
        return new Scanner(reader); // ストリームにスキャナーをかぶせる.
	}

	//ソケットから出力用のPrintWriterを取得するためのメソッド
	public static PrintWriter getPrintWriter(Socket s) throws IOException {
        OutputStream out = s.getOutputStream(); // ソケットから出力ストリームを取得.
        Writer writer = new OutputStreamWriter(out, "UTF-8");
        return new PrintWriter(writer, true);//ストリームにプリントライターをかぶせる．
	}
}
