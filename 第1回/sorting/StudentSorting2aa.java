package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class StudentSorting2aa {
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

		//���т̂悢���ɐ��񂵂Ă���\��
		Comparator<StudentData> c = new StudentScoreComparator() ;
		Arrays.sort(array, c) ;
		for (int i = 0 ; i < n ; i++) // ���񌋉ʂ̔z���\������
			array[i].printStudentData( ) ;

		//ID�̏����ɐ��񂵂Ă���\��
		c = new StudentIDComparator() ;
		Arrays.sort(array, c) ;
		for (int i = 0 ; i < n ; i++) // ���񌋉ʂ̔z���\������
			array[i].printStudentData( ) ;
	} // main���\�b�h�̏I���
}
