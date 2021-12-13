package sorting;

import java.util.Comparator;
import java.util.Random;

public class MySortAlgorithm {
	//自作汎用挿入ソート
	public static <T> void insertionSort(T[ ] array, Comparator<T> cmp) {
		int size = array.length ;
		for (int i = 1 ; i < size ; i++) {
			T temp = array[i] ;
			int j ;
			for (j = i - 1 ; j >= 0 ; j--) {
				if ( cmp.compare(array[j], array[j - 1]) < 0 )
					array[j] = array[j - 1];
				else
					break;
			}
			array[j] = temp ;
		}
	}

	//自作クリックソート．
	public static <T> void quickSort(T[] a, Comparator<T> cmp) {
		randomShuffle(a); //配列aを自作メソッドでランダムにシャッフル．
		quickSort(a, 0, a.length - 1, cmp);//自作メソッドでソート．
	}

	//機能：配列をランダムにシャッフルする．
	private static <T> void randomShuffle(Object[] a) {
		Random rand = new Random() ;
		for (int i = 0; i < a.length; i++) {
			int r = i + rand.nextInt(a.length - i);
			exchange(a, i, r) ;
		}
	}

	//機能：a[left..right] をクリックソートする．
	private static <T> void quickSort(T[] a, int left, int right, Comparator<T> cmp) {
		if (right <= left) return;
		int j = partition(a, left, right, cmp); //自作メソッドで配列を分割．
		quickSort(a, left, j-1, cmp); //左の部分を再帰的にクリックソート．
		quickSort(a, j+1, right, cmp);//右の部分を再帰的にクリックソート．
    }

	//便宜上に用意した自作メソッド．
	//機能：a[i] と a[j] を交換
	private static void exchange(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

    //機能：a[left..j-1] <= a[j] <= a[j+1..right]となるように，
	//      a[left..right]を並べ替える．
	//戻り値：j.
	private static <T> int partition(T[] a, int left, int right, Comparator<T> cmp) {
		int i = left;
		int j = right + 1;
		T pivot = a[left];
		while (true) {
			// 配列の左側でpivotより小さくない要素を探す
			while (cmp.compare(a[++i], pivot) < 0) {
				if (i == right) break;
			}

			// 配列の右側でpivotより大きくない要素を探す
			while (cmp.compare(pivot, a[--j]) < 0) {
				//if (j == left) break; // この行は冗長
			}

			// iとjが交差したかをチェック
			if (i >= j) break;

			exchange(a, i, j);//見つけた2要素を交換
        }

        // 軸要素とa[j]を入れ替える
        exchange(a, left, j);

        // a[left .. j-1] <= a[j] <= a[j+1 .. right]が成り立つ
        return j;
    }
}