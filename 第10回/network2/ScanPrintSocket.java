package network2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ScanPrintSocket extends Socket {
	protected Socket s ;//包まれるSocketを記憶

	//包まれるSocketをもらってくるためのコンストラクタ
	public ScanPrintSocket(Socket s) {
		this.s = s ;
	}

	//ソケットから入力用のScannerを取得するためのメソッド
	public Scanner getScanner() throws IOException {
        InputStream in = s.getInputStream( ); // ソケットから入力ストリームを取得.
        Reader reader = new InputStreamReader(in, "UTF-8");// 漢字コード対策
        return new Scanner(reader); // ストリームにスキャナーをかぶせる.
	}

	//ソケットから出力用のPrintWriterを取得するためのメソッド
	public PrintWriter getPrintWriter() throws IOException {
        OutputStream out = s.getOutputStream(); // ソケットから出力ストリームを取得.
        Writer writer = new OutputStreamWriter(out, "UTF-8");
        return new PrintWriter(writer, true);//ストリームにプリントライターをかぶせる．
	}

	//Socketクラスのメソッドを必要な分Override
	@Override
	 public void close() throws IOException {
		 s.close();
	 }
	@Override
	 public SocketChannel getChannel() {
		 return s.getChannel() ;
	 }
	@Override
	 public InetAddress getInetAddress() {
		 return s.getInetAddress();
	 }
	@Override
	 public boolean getKeepAlive() throws SocketException {
		 return s.getKeepAlive();
	 }
	@Override
	 public InetAddress getLocalAddress() {
		 return s.getLocalAddress();
	 }
	@Override
	 public int getLocalPort() {
		 return s.getLocalPort();
	 }
	@Override
	 public SocketAddress getLocalSocketAddress() {
		 return s.getLocalSocketAddress() ;
	 }
	@Override
	 public boolean getOOBInline() throws SocketException {
		 return s.getOOBInline() ;
	 }
	@Override
	 public int getPort() {
		 return s.getPort();
	 }
	@Override
	 public int getReceiveBufferSize() throws SocketException {
		 return s.getReceiveBufferSize();
	 }
	@Override
	 public SocketAddress getRemoteSocketAddress() {
		 return s.getRemoteSocketAddress();
	 }
	@Override
	 public boolean getReuseAddress() throws SocketException {
		 return s.getReuseAddress();
	 }
	@Override
	 public int getSendBufferSize() throws SocketException {
		 return s.getSendBufferSize();
	 }
	@Override
	 public int getSoLinger() throws SocketException {
		 return s.getSoLinger();
	 }
	@Override
	 public int getSoTimeout() throws SocketException {
		 return s.getSoTimeout() ;
	 }
	@Override
	 public boolean	getTcpNoDelay() throws SocketException {
		 return s.getTcpNoDelay();
	 }
	@Override
	 public int getTrafficClass() throws SocketException {
		 return s.getTrafficClass() ;
	 }
	@Override
	 public boolean isBound() {
		 return s.isBound();
	 }
	@Override
	 public boolean isClosed() {
		 return s.isClosed() ;
	 }
	@Override
	 public boolean isConnected() {
		 return s.isConnected();
	 }
	@Override
	 public boolean isInputShutdown() {
		 return s.isInputShutdown();
	 }
	@Override
	 public boolean isOutputShutdown() {
		 return s.isOutputShutdown();
	 }
	@Override
	 public void sendUrgentData(int data) throws IOException {
		 s.sendUrgentData(data);
	 }
	@Override
	 public void setKeepAlive(boolean on) throws SocketException {
		 s.setKeepAlive(on);
	 }
	@Override
	 public void setOOBInline(boolean on) throws SocketException {
		 s.setOOBInline(on);
	 }
	@Override
	 public void setPerformancePreferences(int connectionTime,
			 int latency, int bandwidth) {
		 s.setPerformancePreferences(connectionTime, latency, bandwidth);
	 }
	@Override
	 public void setReceiveBufferSize(int size) throws SocketException {
		 s.setReceiveBufferSize(size);
	 }
	@Override
	 public void setReuseAddress(boolean on) throws SocketException {
		 s.setReuseAddress(on);
	 }
	@Override
	 public void setSendBufferSize(int size) throws SocketException {
		 s.setSendBufferSize(size);
	 }
	@Override
	 public void setSoLinger(boolean on, int linger) throws SocketException {
		 s.setSoLinger(on, linger);
	 }
	@Override
	 public void setSoTimeout(int timeout) throws SocketException {
		 s.setSoTimeout(timeout);
	 }
	@Override
	 public void setTcpNoDelay(boolean on) throws SocketException {
		 s.setTcpNoDelay(on);
	 }
	@Override
	 public void setTrafficClass(int tc) throws SocketException {
		 s.setTrafficClass(tc);
	 }
	@Override
	 public void shutdownInput() throws IOException {
		 s.shutdownInput();
	 }
	@Override
	 public void shutdownOutput() throws IOException {
		 s.shutdownOutput();
	 }
}
