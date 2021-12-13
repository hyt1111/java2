package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("resource")
public class StringSorting {
	public static void main(String[] args) {
		//キーボードからの入力のためにスキャナーを生成
		Scanner scan = new Scanner(System.in) ;

		//キーボードから整列したい文字列の個数を入力
		System.out.printf("#strings: ") ;
		int size = scan.nextInt();

		//size個の文字列を格納するためのメモリを確保
		String[] array = new String[size] ;

		//size個の文字列をキーボードから入力
		for (int i = 0 ; i < size ; i++) {
			System.out.printf("array[%d] = ", i) ;
			array[i] = scan.next();
		}

		//********* 下記の①と②のどちらかを選ぶ ********
		//①自作整列関数を呼び出して文字列配列arrayを整列
		//mySort(array, new ReverseStringComparator()) ;

		//②Javaのsortを呼び出して文字列配列arrayを整列
		Arrays.sort(array, new ReverseStringComparator()) ;
        //***********************************************

		//整列結果を表示
		System.out.println("Sorted array:") ;
		for (int i = 0 ; i < array.length ; i++) {
			System.out.printf("%s ", array[i]) ;
		}
		System.out.println();
	}


	//汎用挿入ソート用の自作クラス・メソッド
	public static <T> void mySort(T[ ] array, Comparator<T> cmp) {
		int size = array.length ;
		for (int i = 1 ; i < size ; i++) {
			T temp = array[i] ;
			int j = i - 1 ;
			for (; j >= 0 ; j--) {
				if ( cmp.compare(array[j], temp) > 0 )
					array[j + 1] = array[j] ;
				else
					break;
			}
			array[j + 1] = temp ;
		}
	}
}