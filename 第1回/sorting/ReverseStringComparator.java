package sorting;

import  java.util.Comparator ;

public class ReverseStringComparator
	implements Comparator<String> {
	public int compare(String data, String anotherData) {
		return anotherData.compareTo(data) ;
	} // compareメソッドの終わり
} // ReverseStringComparatorクラスの終わり