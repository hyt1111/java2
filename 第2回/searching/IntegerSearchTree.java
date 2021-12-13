package genericSearch;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class IntegerSearchTree {
	//�e�X�g�p��main���\�b�h
	@SuppressWarnings("resource")
	public static void main( String [ ] args ) {
		//�L�[�̌^��Integer�ŁC�l�̌^��Object�ł�����2���T���؂����D
    	TreeMap<Integer,Object> tree =
    		new TreeMap<Integer,Object>(
        		new Comparator<Integer>() {
    				public int compare(Integer a, Integer b) {
    					return b - a ;//�傫���ق������ɒu������
    				}
    			}
    		) ;

    	//1,2,..,n�̃����_���ȏ���𐶐�����
    	int n ;
    	Scanner keyboard = new Scanner(System.in) ;
    	System.out.println("#integers:") ;
    	n = keyboard.nextInt();
    	int[] array = new int[n] ;
    	for (int i = 0 ; i < n ; i++)
    		array[i] = i + 1;
    	Random rand = new Random() ;
    	for (int i = 0 ; i < n ; i++) {
    		int j = rand.nextInt(n - i) ;
    		int temp = array[n - 1 - i] ;
    		array[n - 1 - i] = array[j] ;
    		array[j] = temp ;
    	}

    	//��L�̖؂�n�̃f�[�^���C�������������_�����ɑ}��
    	for (int i = 0 ; i < n ; i++) {
    		//System.out.print(array[i] + " ");
    		tree.put(array[i], null);
    	}
    	System.out.println("") ;

    	//�����ƍ폜������e�X�g
    	int m = rand.nextInt(n) ;
    	System.out.println("trying to find " + m + " ...");
    	if (tree.containsKey(m)) {
    		System.out.println("found " + m) ; //���ʕ\��
        	System.out.println("") ;
    		System.out.println("trying to delete " + m + " ...");
    		tree.remove(m); //�폜
        	System.out.println("trying to find " + m + " again ...");
        	if (tree.containsKey(m))
        		System.out.println("Error: found the deleted key.") ;
        }
    	System.out.println("") ;

    	//�؂ɂ���f�[�^�̃L�[���w�菇�ɕ\������D
    	Set<Integer> keys = tree.keySet() ;
    	System.out.println("data in sorted order:");
    	Iterator<Integer> iter = keys.iterator() ;
    	while (iter.hasNext()) {
    		System.out.print(iter.next() + " ");
    	}
    }
}