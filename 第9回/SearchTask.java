package keywdSearch ;

import  java.io.* ; // Fileクラスを使うので必要
import  java.util.concurrent.* ; // BlockingQueueインターフェースを使うので必要
import  java.util.* ; // Scannerクラスを使うので必要
 
public class  SearchTask implements Runnable {   
    protected BlockingQueue<File> queue ;  // ブロッキングキューを指す変数. 
    protected String keyword ;  // 検索キーワードを指す変数. 
  
   // コンストラクタ（検索の仕事を１つ作る） 
    public SearchTask(BlockingQueue<File> queue, String keyword) {  
        this.queue = queue; //ブロッキングキューへの参照を呼び出し側のプログラムからもらう. 
        this.keyword = keyword; // 検索キーワードへの参照を呼び出し側の プログラムからもらう. 
    }  

    public void run( ) { // 検索スレッドの行う仕事（ブロッキングキューからファイルを1つずつ 
                                     // 取ってきて，その中で検索キーワードを含む行をすべて見つける）. 
        try { 
            while ( true ) { // 下記をずっと繰り返し：
                File  file = queue.take( ) ; // キューからファイルを１つ取ってくる. 

                if ( file == EnumerationTask.DUMMY_FILE ) { // 取ってきたのがダミーなら, 
                    queue.put( file ) ; // それが終わりの合図なので，キューに戻す．
                    return ; // 検索が終了した．
               } 
               if (!file.canRead()) continue;
               Scanner in = null ; 
               try { 
                   in = new Scanner(file); // ファイルを開く. 
                   int lineNumber = 0 ; // ファイルの行の番号を覚えるための変数. 
                   while ( in.hasNextLine( ) ) {  // まだ検索していない行がある間，
                       lineNumber++ ;  // 行の番号を１増やし，
                       String line = in.nextLine( ) ; // 検索していない次の行を読み込み，
                       if (line.contains(keyword)) //その行が検索キーワードを含む場合結果表示. 
                    	   //synchronized ( System.out ) {
                    	   		System.out.println(file.getPath( ) + ":" + lineNumber + ":" + line); 
                       			//System.out.println("上の行に続けてこの行を表示したいなら、ロックが必要。") ;
                    	   //}
                   }
               } finally {  
                   if (in != null) in.close( ) ; // 開いたファイルを閉じる. 
               }
          }  // while-文の終わり 
      } catch ( IOException e) { 
    	  e.printStackTrace() ;
      } catch ( InterruptedException e) {
    	  Thread.currentThread().interrupt();
      } 
  } // runメソッドの終わり 
} // SearchTask.java の終わり