package sorting;

import java.util.Comparator;

public class StudentIDComparator implements Comparator<StudentData>  {
	public int compare(StudentData data, StudentData anotherData) {
		return data.getID() - anotherData.getID( ) ;
	} // compareメソッドの終わり
}
