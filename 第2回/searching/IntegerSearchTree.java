package genericSearch;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class IntegerSearchTree {
	//テスト用のmainメソッド
	@SuppressWarnings("resource")
	public static void main( String [ ] args ) {
		//キーの型がIntegerで，値の型がObjectである空の2分探索木を作る．
    	TreeMap<Integer,Object> tree =
    		new TreeMap<Integer,Object>(
        		new Comparator<Integer>() {
    				public int compare(Integer a, Integer b) {
    					return b - a ;//大きいほうを左に置きたい
    				}
    			}
    		) ;

    	//1,2,..,nのランダムな順列を生成する
    	int n ;
    	Scanner keyboard = new Scanner(System.in) ;
    	System.out.println("#integers:") ;
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
    		tree.put(array[i], null);
    	}
    	System.out.println("") ;

    	//検索と削除操作をテスト
    	int m = rand.nextInt(n) ;
    	System.out.println("trying to find " + m + " ...");
    	if (tree.containsKey(m)) {
    		System.out.println("found " + m) ; //結果表示
        	System.out.println("") ;
    		System.out.println("trying to delete " + m + " ...");
    		tree.remove(m); //削除
        	System.out.println("trying to find " + m + " again ...");
        	if (tree.containsKey(m))
        		System.out.println("Error: found the deleted key.") ;
        }
    	System.out.println("") ;

    	//木にあるデータのキーを指定順に表示する．
    	Set<Integer> keys = tree.keySet() ;
    	System.out.println("data in sorted order:");
    	Iterator<Integer> iter = keys.iterator() ;
    	while (iter.hasNext()) {
    		System.out.print(iter.next() + " ");
    	}
    }
}