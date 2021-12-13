package genericSearch;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

//K�̓f�[�^�̃L�[�̌^�CV�̓f�[�^�̒l�̌^
public class VisitableBinSearchTree3<K,V> implements BTAcceptor<K,V> {
	private BinSearchTree<K,V> tree ;
	private BTVisitor<K,V> visitor ;

	//�����Ȃ��̃R���X�g���N�^���֎~�D
	@SuppressWarnings("all")
	private VisitableBinSearchTree3( ) { }

	//��������̃R���X�g���N�^
	public VisitableBinSearchTree3(Comparator<K> comparator) {
		tree = new BinSearchTree<K,V>(comparator) ;
	}

	//************** �K��\��2���T���؂�2���T���؂ł�����̂ŁC*******
	//************** BinSearchTree<K,V>�N���X�Œ񋟂��Ă���public�� ****
	//************** ���\�b�h�����ׂ�VisitableBinSearchTree3<K,V>�� ****
	//************** ��������K�v������D     **************************
	//************** �������C���̂܂܃R�s�[�E�y�[�X�g������ƁC*********
	//************** �R�[�h�̏d���������Ȃ��Ă���̂ŁC�epublic�� ******
	//************** ���\�b�h�� tree�ɈϏ�����悤�ɂ���D  ************
	//+++++ �R�s�[�J�n +++++
	//��̖؂��ۂ��𔻒肷�郁�\�b�h
	public boolean isEmpty() {
		return tree.isEmpty() ; //tree�ɈϏ�
	}

	//���̖؂ɂ����Ďw��L�[�����f�[�^���������郁�\�b�h
	public V search(K key) {
		return tree.search(key) ;
	}

	//���̖؂ɂ����Ďw��L�[�����f�[�^���������郁�\�b�h
	public void insert(Map.Entry<K,V> data) {
		tree.insert(data) ; //tree�ɈϏ�
	}

	//�؂���w��̃L�[�����f�[�^���폜���Ă���C���ʂ̖؂�Ԃ����\�b�h
	public void delete(K key) {
		tree.delete(key) ; //tree�ɈϏ�
	}

	//preorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> preorderIterator() {
		return tree.preorderIterator() ; //tree�ɈϏ�
	}

	//inorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> inorderIterator() {
		return tree.inorderIterator() ; //tree�ɈϏ�
	}

	//postorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> postorderIterator() {
		return tree.postorderIterator() ; //tree�ɈϏ�
	}

	//accessor���\�b�h
	public BinSearchTreeNode<K,V> getRoot() {
		return tree.getRoot() ; //tree�ɈϏ�
	}
	public void setRoot(BinSearchTreeNode<K,V> root) {
		tree.setRoot(root) ; //tree�ɈϏ�
	}
	//+++++ �R�s�[�I�� +++++
	//**************************************************************

	//Visitor���󂯓���郁�\�b�h
	public void accept(BTVisitor<K,V> visitor) {
		this.visitor = visitor ;
	}

	//�ؑS�̂��������郁�\�b�h
	public Object traverse( ) {
		return traverse(tree.getRoot()) ;
	}

	//�؂̎w��m�[�hstart�����Ƃ��镔���؂��������郁�\�b�h
	private Object traverse(BinSearchTreeNode<K,V> start) {
		if (start == null)//��̖؂ł���Ƃ�
			return visitor.visitNull( );
		else {//��ł͂Ȃ��؂̂Ƃ�
			Object left = traverse(start.getLeft()) ;
			Object right = traverse(start.getRight()) ;
			return visitor.visitNode(left, right, start.getData());
		}
	}
}
