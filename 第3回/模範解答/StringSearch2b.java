package forStudent;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import genericSearch.BTVisitor ;
import genericSearch.VisitableBinSearchTree2 ;

public class StringSearch2b {
	//�e�X�g�p��main���\�b�h
	@SuppressWarnings("resource")
    public static void main( String [ ] args ) {
    	//�f�[�^�^��String�^�ł�����2���T���؂����D
    	VisitableBinSearchTree2<Integer,String> tree =
    		new VisitableBinSearchTree2<Integer,String>(
        		new Comparator<Integer>() {
    				public int compare(Integer a, Integer b) {
    					return a - b ;
    				}
    			}
    		) ;

    	//1,2,..,n�̃����_���ȏ���𐶐�����
    	int n ;
    	Scanner keyboard = new Scanner(System.in) ;
    	System.out.println("#students:") ;
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
    		tree.insert(new AbstractMap.SimpleEntry<Integer,String>(array[i], i + "-th string"));
    	}
    	System.out.println("") ;

    	//�؂̍��������߂�
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Integer visitNull( ) {
    			return 0 ;
    		}
    		public Integer visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			return Math.max((Integer)left, (Integer)right) + 1 ;
    		}
    	});
    	System.out.println("height: " + (Integer)tree.traverse());
    	System.out.println("") ;

    	//************* �ǉ�������(begin) *****************
    	//�؂�unbalance�x�����߂�
    	tree.accept(new BTVisitor<Integer,String>() {
    		//�߂�l��int[]���܂���2�̐������܂Ƃ߂�N���X�ɂł��Ă���Ȃ�C2�_�D
    		public int[] visitNull( ) {
    			return new int[] {0,0} ; //2�_
    		}
    		public int[] visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			int[] leftArray = (int[])left ;
    			int[] rightArray = (int[])right ;
    			int[] tempArray = new int[2] ;

    			//�f�[�^���������߂�D1�_
    			tempArray[0] = leftArray[0] + rightArray[0] + 1 ;

    			//���E�̕����؂̎q�����̍��D2�_
    			tempArray[1] = Math.abs(leftArray[0] - rightArray[0]) ;

    			//unbalance�x�����߂�D2�_
    			tempArray[1] = Math.max(Math.max(leftArray[1], rightArray[1]), tempArray[1]) ;

    			return tempArray ;//���ʂ�Ԃ�
    		}
    	});
    	System.out.println("unbalance degree: " + ((int[])tree.traverse())[1]);//1�_
    	System.out.println("") ;
       	//************* �ǉ�������(end) *****************

    	//postorder���ɖ؂����ǂ�D
    	System.out.println("visiting the tree in post order");
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Void visitNull( ) {
    			return null ;
    		}
    		public Void visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			System.out.println(data.getKey() + ":" + data.getValue());
    			return null ;
    		}
    	});
    	tree.traverse();
    	System.out.println("") ;

    	//�����ƍ폜������e�X�g
    	int m = rand.nextInt(n) ;
    	System.out.println("trying to find " + m + " ...");
    	String result = tree.search(m) ;
    	if (result != null) {
    		System.out.println("found " + m + ":" + result) ; //���ʕ\��
        	System.out.println("") ;
    		System.out.println("trying to delete " + m + " ...");
    		tree.delete(m); //�폜
        	result = tree.search(m) ;
        	if (result != null)
        		System.out.println("Error: found the deleted data.") ;
        }
    	System.out.println("") ;

    	//�؂ɂ���f�[�^���L�[�̏��������ɕ\������ʂ̕��@�D
    	Iterator<Map.Entry<Integer,String>> iter = tree.inorderIterator() ;
    	System.out.println("data in sorted order:");
    	while (iter.hasNext()) {
    		Map.Entry<Integer,String> d = iter.next();
    		System.out.println(d.getKey() + "�F" + d.getValue());
    	}
    }
}