package sorting;

import java.util.Arrays;
import java.util.Scanner;

public class StudentSorting2a {
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
		StudentScoreComparator c = new StudentScoreComparator() ;
		//********* ���L�̇@�ƇA�̂ǂ��炩��I�� ********
		//�@���쐮��֐����Ăяo���ĕ�����z��array�𐮗�
		//StringSorting.mySort(array, c) ;

		//�AJava��sort���Ăяo���ĕ�����z��array�𐮗�
		Arrays.sort(array, c) ;
        //***********************************************

		for (int i = 0 ; i < n ; i++) // ���񌋉ʂ̔z���\������
			array[i].printStudentData( ) ;

		//ID�̏����ɐ��񂵂Ă���\��
		StudentIDComparator c2 = new StudentIDComparator() ;
		//********* ���L�̇@�ƇA�̂ǂ��炩��I�� ********
		//�@���쐮��֐����Ăяo���ĕ�����z��array�𐮗�
		//StringSorting.mySort(array, c2) ;

		//�AJava��sort���Ăяo���ĕ�����z��array�𐮗�
		Arrays.sort(array, c2) ;
        //***********************************************

		for (int i = 0 ; i < n ; i++) // ���񌋉ʂ̔z���\������
			array[i].printStudentData( ) ;
	} // main���\�b�h�̏I���
}
