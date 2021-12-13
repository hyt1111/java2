package keywdSearch ;

import  java.io.* ; // Fileクラスを使うので必要
import  java.util.concurrent.* ; // BlockingQueueインターフェースを使うので必要
 
public class  EnumerationTask implements Runnable {  
	protected final static File  DUMMY_FILE = new File("") ; //列挙の終了を示すのに使われる. 
	protected BlockingQueue<File> queue ;  // ブロッキングキューを指す変数. 
	protected File startDirectory ;  // 検索の開始ディレクトリを指す変数. 

	// コンストラクタ（列挙の仕事を１つ作る） 
	public EnumerationTask(BlockingQueue<File> queue, File startDirectory) {  
		this.queue = queue ; // ブロッキングキューを呼び出し側のプログラムからもらう. 
		this.startDirectory = startDirectory ; // 検索の開始ディレクトリを呼び出し側からもらう. 
	}  

	// 検索スレッドの行う仕事（指定されたディレクトリとそのサブディレクトリの下にあるファイルの列挙） 
	public void run( ) {  
		try { 
			enumerate(startDirectory); //自作メソッド（次頁）でファイルを列挙してキューに追加. 
			queue.put(DUMMY_FILE); // キューにダミーを置く（i.e., 列挙の完了の合図を送る）. 
		} catch ( InterruptedException e) {
			Thread.currentThread().interrupt();
		} 
	} 

	// 引数directoryの示すディレクトリの下にあるファイル（サブディレクトリを含む）を 
	// すべて列挙してブロッキングキューに置くためのメソッド. 
	public void enumerate( File directory ) throws InterruptedException { 
		File[ ] files = directory.listFiles( ) ; // 引数directory の示すディレクトリのすぐ下 
	                                       // にあるすべてのファイル（ディレクトリを含む）を返す. 
		if (files == null)   // 引数directory は正しいパス名ではなければ，
			return ;            // 何もしないで列挙を終える． 
		for (int i = 0; i < files.length ; i++) {  
			if ( files[i].isDirectory( ) ) // ディレクトリであれば，その下およびそのサブ 
				enumerate( files[i] ) ; // ディレクトリの下にあるファイルを再帰的に列挙. 
			else                   // ディレクトリではなければ（i.e.，普通のファイルであれば），
				queue.put( files[i] ) ;  // それをブロッキングキューに追加する．
		}
	} // enumerateメソッドの終わり
} // EnumerationTask.java の終わり