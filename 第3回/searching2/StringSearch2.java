package genericSearch;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StringSearch2 {
	//テスト用のmainメソッド
	@SuppressWarnings("resource")
    public static void main( String [ ] args ) {
    	//データ型がString型である空の2分探索木を作る．
    	VisitableBinSearchTree2<Integer,String> tree =
    		new VisitableBinSearchTree2<Integer,String>(
        		new Comparator<Integer>() {
    				public int compare(Integer a, Integer b) {
    					return a - b ;
    				}
    			}
    		) ;

    	//1,2,..,nのランダムな順列を生成する
    	int n ;
    	Scanner keyboard = new Scanner(System.in) ;
    	System.out.println("#students:") ;
    	n = keyboard.nextInt();
    	int[] array = new int[n] ;
    	for (int i = 0 ; i < n ; i++)
    		array[i] = i + 1;
    	Random rand = new Random() ;
    	for (int i = 0 ; i < n ; i++) {
    		int j = rand.nextInt(n - i) ;
    		int temp = array[n - 1 - i] ;
    		array[n - 1 - i] = array[j] ;
    		array[j] = temp ;
    	}

    	//上記の木にn個のデータを，生成したランダム順に挿入
    	for (int i = 0 ; i < n ; i++) {
    		//System.out.print(array[i] + " ");
    		tree.insert(new AbstractMap.SimpleEntry<Integer,String>(array[i], i + "-th string"));
    	}
    	System.out.println("") ;

    	//木の高さを求める
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Integer visitNull( ) {
    			return 0 ;
    		}
    		public Integer visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			return Math.max((Integer)left, (Integer)right) + 1 ;
    		}
    	});
    	System.out.println("height: " + (Integer)tree.traverse());
    	System.out.println("") ;

    	//postorder順に木をたどる．
    	System.out.println("visiting the tree in post order");
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Void visitNull( ) {
    			return null ;
    		}
    		public Void visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			System.out.println(data.getKey() + ":" + data.getValue());
    			return null ;
    		}
    	});
    	tree.traverse();
    	System.out.println("") ;

    	//検索と削除操作をテスト
    	int m = rand.nextInt(n) ;
    	System.out.println("trying to find " + m + " ...");
    	String result = tree.search(m) ;
    	if (result != null) {
    		System.out.println("found " + m + ":" + result) ; //結果表示
        	System.out.println("") ;
    		System.out.println("trying to delete " + m + " ...");
    		tree.delete(m); //削除
        	result = tree.search(m) ;
        	if (result != null)
        		System.out.println("Error: found the deleted data.") ;
        }
    	System.out.println("") ;

    	//木にあるデータをキーの小さい順に表示する別の方法．
    	Iterator<Map.Entry<Integer,String>> iter = tree.inorderIterator() ;
    	System.out.println("data in sorted order:");
    	while (iter.hasNext()) {
    		Map.Entry<Integer,String> d = iter.next();
    		System.out.println(d.getKey() + "：" + d.getValue());
    	}
    }
}