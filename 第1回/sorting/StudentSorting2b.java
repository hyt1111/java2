package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
//import java.util.stream.IntStream;

public class StudentSorting2b {
	@SuppressWarnings("resource")
	public static void main(String arg[ ]){
		Scanner  sc = new  Scanner(System.in);

		//�L�[�{�[�h���琮�񂵂����w���f�[�^�������
		System.out.printf("#students: ") ;
		int n =  sc.nextInt( ) ; //n����͂���
		StudentData[ ] array = new StudentData[n]; //�z��̃��������m��
		for (int i = 0 ; i < n ; i++)  // n�l�̊w���f�[�^�̃��������m��
			array[i] =  new StudentData( ) ;
		for (int i = 0 ; i < n ; i++)  // n�l�̊w���f�[�^����͂���
			array[i].inputStudentData( ) ; //���상�\�b�h�Ńf�[�^�����

		//���т̂悢���ɐ���
		Arrays.sort(array, new Comparator<StudentData>(){
			public int compare(StudentData data, StudentData anotherData) {
				return anotherData.getScore() - data.getScore( ) ;
			} // compare���\�b�h�̏I���
		}) ;
/*		//���̓����_�����g�����o�[�W����
 * 		Arrays.sort(array, (data, anotherData) -> {
								return anotherData.getScore() - data.getScore( ) ;
							}
					) ; */

		for (int i = 0 ; i < n ; i++) // ���񌋉ʂ̔z���\������
			array[i].printStudentData( ) ;
/*		//���̓����_�����g�����o�[�W����
 *		IntStream.range(0,n).forEach(i -> array[i].printStudentData()) ; */

		//ID�̏����ɐ��񂵂Ă���\��
		Arrays.sort(array, new Comparator<StudentData>(){
			public int compare(StudentData data, StudentData anotherData) {
				return data.getID() - anotherData.getID( ) ;
			} // compare���\�b�h�̏I���
		}) ;
/*		//���̓����_�����g�����o�[�W����
 *		Arrays.sort(array, (data, anotherData) -> {
										return data.getID() - anotherData.getID( ) ;
								  }
					) ;*/

		for (int i = 0 ; i < n ; i++) // ���񌋉ʂ̔z���\������
			array[i].printStudentData( ) ;
		/*		//���̓����_�����g�����o�[�W����
		 *		IntStream.range(0,n).forEach(i -> array[i].printStudentData()) ; */
	} // main���\�b�h�̏I���
}
