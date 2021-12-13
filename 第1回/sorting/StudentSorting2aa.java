package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class StudentSorting2aa {
	@SuppressWarnings("resource")
	public static void main(String arg[ ]){
		Scanner  sc = new  Scanner(System.in);

		//キーボードから整列したい学生データ数を入力
		System.out.printf("#students: ") ;
		int n =  sc.nextInt( ) ; //nを入力する
		StudentData[ ] array = new StudentData[n]; //配列のメモリを確保
		for (int i = 0 ; i < n ; i++)  // n人の学生データのメモリを確保
			array[i] =  new StudentData( ) ;
		for (int i = 0 ; i < n ; i++)  // n人の学生データを入力する
			array[i].inputStudentData( ) ; //自作メソッドでデータを入力

		//成績のよい順に整列してから表示
		Comparator<StudentData> c = new StudentScoreComparator() ;
		Arrays.sort(array, c) ;
		for (int i = 0 ; i < n ; i++) // 整列結果の配列を表示する
			array[i].printStudentData( ) ;

		//IDの昇順に整列してから表示
		c = new StudentIDComparator() ;
		Arrays.sort(array, c) ;
		for (int i = 0 ; i < n ; i++) // 整列結果の配列を表示する
			array[i].printStudentData( ) ;
	} // mainメソッドの終わり
}
