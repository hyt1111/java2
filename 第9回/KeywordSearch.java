package keywdSearch ;

import  java.io.* ; // Fileクラスを使うので必要
import  java.util.concurrent.* ; // BlockingQueueインターフェースを使うので必要
import  java.util.* ; // Scannerクラスを使うので必要

public  class  KeywordSearch {
	private static final int  SIZE = 100;  // ブロッキングキューに置けるファイルの最大個数.

	// 素因数を担当するスレッドの本数.
	private static final int  SEARCHER = Runtime.getRuntime().availableProcessors() - 2 ;

	@SuppressWarnings("resource")
	public static void main(String[ ] args ) {
		//System.out.println("#availableThreads: " +
		//	Runtime.getRuntime().availableProcessors()) ;

		//検索スレッドの本数を表示
		System.out.println("#searchers: " + SEARCHER) ;

		// 指定されたサイズのブロッキングキューを作成する
		final BlockingQueue<File> queue = new ArrayBlockingQueue<File>( SIZE ) ;


		// 列挙スレッドの仕事を生成する.
		EnumerationTask et = new EnumerationTask(queue) ;

		// 合成数の列挙を担当するスレッドを1本作成する．
		Thread enumerator = new Thread( et ) ;

		// 素因数分解担当スレッドをたくさん生成する．
		Thread[ ] searchers = new Thread[SEARCHER]; //検索スレッドを覚える配列を生成.
		for (int i = 0 ; i < SEARCHER ; i++) {
			searchers[i] = new Thread( new SearchTask(queue) ) ; // i番目の検索スレッドを作成する.
		}

		// 素因数分解担当スレッドを起動する．
		for (int i = 0 ; i < SEARCHER ; i++)
			searchers[i].start( ) ; // i番目の検索スレッドを起動する.

		// 合成数列挙を担当するスレッドを起動する．
		enumerator.start( ) ;
	} // mainメソッドの終わり
} // KeywordSearch.javaの終わり