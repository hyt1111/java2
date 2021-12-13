package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("resource")
public class StringSorting {
	public static void main(String[] args) {
		//�L�[�{�[�h����̓��͂̂��߂ɃX�L���i�[�𐶐�
		Scanner scan = new Scanner(System.in) ;

		//�L�[�{�[�h���琮�񂵂���������̌������
		System.out.printf("#strings: ") ;
		int size = scan.nextInt();

		//size�̕�������i�[���邽�߂̃��������m��
		String[] array = new String[size] ;

		//size�̕�������L�[�{�[�h�������
		for (int i = 0 ; i < size ; i++) {
			System.out.printf("array[%d] = ", i) ;
			array[i] = scan.next();
		}

		//********* ���L�̇@�ƇA�̂ǂ��炩��I�� ********
		//�@���쐮��֐����Ăяo���ĕ�����z��array�𐮗�
		//mySort(array, new ReverseStringComparator()) ;

		//�AJava��sort���Ăяo���ĕ�����z��array�𐮗�
		Arrays.sort(array, new ReverseStringComparator()) ;
        //***********************************************

		//���񌋉ʂ�\��
		System.out.println("Sorted array:") ;
		for (int i = 0 ; i < array.length ; i++) {
			System.out.printf("%s ", array[i]) ;
		}
		System.out.println();
	}


	//�ėp�}���\�[�g�p�̎���N���X�E���\�b�h
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