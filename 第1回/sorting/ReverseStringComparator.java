package sorting;

import  java.util.Comparator ;

public class ReverseStringComparator
	implements Comparator<String> {
	public int compare(String data, String anotherData) {
		return anotherData.compareTo(data) ;
	} // compare���\�b�h�̏I���
} // ReverseStringComparator�N���X�̏I���