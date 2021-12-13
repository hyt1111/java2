package sorting;

import java.util.Comparator;

public class StudentScoreComparator implements Comparator<StudentData> {
	public int compare(StudentData data, StudentData anotherData) {
		return anotherData.getScore() - data.getScore( ) ;
	} // compareメソッドの終わり
}
