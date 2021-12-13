package forStudent;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import genericSearch.BinSearchTreeNode ;

//K�̓f�[�^�̃L�[�̌^�CV�̓f�[�^�̒l�̌^
public class BinSearchTree<K,V> {
	private BinSearchTreeNode<K,V> root ;
	private Comparator<K> comparator ;

	//�����Ȃ��̃R���X�g���N�^���֎~�D
	@SuppressWarnings("all")
	private BinSearchTree( ) { }

	//��������̃R���X�g���N�^�D
	public BinSearchTree(Comparator<K> comparator) {
		this.comparator = comparator ;
	}

	//��̖؂��ۂ��𔻒肷�郁�\�b�h
	public boolean isEmpty() {
		return root == null;
	}

	//���̖؂ɂ����Ďw��L�[�����f�[�^���������郁�\�b�h
	public V search(K key) {
		//return search(root, key); //�ċA���g���o�[�W�����D

		//���L�͍ċA���g��Ȃ��o�[�W����
		BinSearchTreeNode<K,V> currentNode = root ; //�����猟�����X�^�[�g
		while (currentNode != null) {
			if (comparator.compare(key, currentNode.getData().getKey()) == 0)
				return currentNode.getData().getValue() ;//����������
			else if (comparator.compare(key, currentNode.getData().getKey()) < 0)
				currentNode = currentNode.getLeft() ;//�������؂ŒT��
			else
				currentNode = currentNode.getRight() ; //�E�����؂ŒT��
		}
		return null ; //������Ȃ��Ƃ�
	}


	//start�����Ƃ���؂ɂ����Ďw��L�[�����f�[�^����������ċA���\�b�h
	private V search(BinSearchTreeNode<K,V> start, K key) {
		if (start == null) return null ; //�؂���̏ꍇ

		//���������ꍇ
		if (comparator.compare(key, start.getData().getKey()) == 0)
			return start.getData().getValue();

		if (comparator.compare(key, start.getData().getKey()) <0) {
			//�������؂ōċA�I�Ɍ���
			return search(start.getLeft(), key) ;
		} else {
			//�E�����؂ōċA�I�Ɍ���
			return search(start.getRight(), key) ;
		}
	}


	//���̖؂ɂ����Ďw��L�[�����f�[�^���������郁�\�b�h
	public void insert(Map.Entry<K,V> data) {
		//root = insert(root, data); //�ċA���g���o�[�W�����D

		//���L�͍ċA���g��Ȃ��o�[�W����
		BinSearchTreeNode<K,V> newNode = new BinSearchTreeNode<K,V>(data) ;//�V�m�[�h�쐬
		BinSearchTreeNode<K,V> currentNode = root ; //������}���ꏊ�̌������X�^�[�g
		if (root == null) { //�؂���̂Ƃ�
			root = newNode ;
			return ;
		}

		//null�ɂ��ǂ蒅���܂Ŗ؂������Ă���
		BinSearchTreeNode<K,V> previousNode ;
		do {
			previousNode = currentNode ; //�؂�����O�Ɍ��݂̃m�[�h���L���D
			if (comparator.compare(data.getKey(), currentNode.getData().getKey()) < 0)
				currentNode = currentNode.getLeft() ;//���Ɉ���~���
			else
				currentNode = currentNode.getRight() ; //�E�Ɉ���~���
		} while (currentNode != null) ;

		//null�܂ŗ�����}��
		if (comparator.compare(data.getKey(), previousNode.getData().getKey()) < 0)
			previousNode.setLeft(newNode) ;
		else
			previousNode.setRight(newNode) ;
	}


	//start�����Ƃ���؂ɐV�����f�[�^��}�����Ă���C���ʂ̖؂�Ԃ��ċA���\�b�h
	private BinSearchTreeNode<K,V> insert(BinSearchTreeNode<K,V> start, Map.Entry<K,V> data) {
		//�Ղ����ꍇ�F��̖؂̏ꍇ
		if (start == null) {
			return new BinSearchTreeNode<K,V>(data) ;
		}

		//����ꍇ
		if (comparator.compare(data.getKey(), start.getData().getKey()) < 0) {
			//���̎q�����Ƃ��镔���؂ɑ}�����ׂ��Ƃ�
			start.setLeft( insert(start.getLeft(), data) ) ;
		} else { //�E�̎q�����Ƃ��镔���؂ɑ}�����ׂ��Ƃ�
			start.setRight( insert(start.getRight(), data) ) ;
		}
		return start ;
	}


	//�؂���w��̃L�[�����f�[�^���폜���Ă���C���ʂ̖؂�Ԃ����\�b�h
	public void delete(K key) {
		//root = delete(root, key); //�ċA���g���o�[�W�����D

		//���L�͍ċA���g��Ȃ��o�[�W����
		BinSearchTreeNode<K,V> currentNode = root ; //�����猟�����X�^�[�g
		BinSearchTreeNode<K,V> previousNode = null ;
		while (currentNode != null) {
			if (comparator.compare(key, currentNode.getData().getKey()) == 0)
				break ;//����������
			else if (comparator.compare(key, currentNode.getData().getKey()) < 0) {
				previousNode = currentNode ; //�؂�����O�Ɍ��݂̃m�[�h���L���D
				currentNode = currentNode.getLeft() ;//�������؂ŒT��
			} else {
				previousNode = currentNode ; //�؂�����O�Ɍ��݂̃m�[�h���L���D
				currentNode = currentNode.getRight() ; //�E�����؂ŒT��
			}
		}

		if (currentNode == null) return ; //������Ȃ������Ƃ��������Ȃ�

		if (currentNode.isLeaf()) { //�t���폜����Ƃ�
			if (currentNode == root) root = null ;//�����폜����Ƃ�

			//���ȊO�̗t���폜����ꍇ
			if (previousNode.getLeft() == currentNode)
				previousNode.setLeft(null) ;
			else
				previousNode.setRight(null) ;
			return ;
		}

		if (currentNode.getLeft() == null) { //���̎q��null�̃m�[�h���폜����Ƃ�
			if (previousNode == null) root = currentNode.getRight() ;
			else if (previousNode.getLeft() == currentNode)
				previousNode.setLeft(currentNode.getRight()) ;
			else
				previousNode.setRight(currentNode.getRight()) ;
			return ;
		}

		if (currentNode.getRight() == null) { //�E�̎q��null�̃m�[�h���폜����Ƃ�
			if (previousNode == null) root = currentNode.getLeft() ;
			else if (previousNode.getLeft() == currentNode)
				previousNode.setLeft(currentNode.getLeft()) ;
			else
				previousNode.setRight(currentNode.getLeft()) ;
			return ;
		}

		//���̎q���E�̎q��null�ł͂Ȃ��m�[�h���폜����Ƃ�.
		//�܂��C�폜����f�[�^�̎��ɑ傫���f�[�^��T��
		BinSearchTreeNode<K,V> nextBigNode = currentNode.getRight() ;//�E�Ɉ���~���D
		previousNode = currentNode ;
		while (nextBigNode.getLeft() != null) {//���̎q��null�ł͂Ȃ��ԁC���ɍ~���D
			previousNode = nextBigNode ;
			nextBigNode = nextBigNode.getLeft() ;
		}

		//���ɁC�폜����f�[�^�����̎��ɑ傫���f�[�^�ŏ㏑������D
		currentNode.setData(nextBigNode.getData()) ;

		//�Ō�ɁC�폜����f�[�^�̎��ɑ傫���f�[�^���폜����D
		if (previousNode.getLeft() == nextBigNode)
			previousNode.setLeft(nextBigNode.getRight()) ;
		else
			previousNode.setRight(nextBigNode.getRight()) ;
	}


	//�؂���w��̃L�[�����f�[�^���폜���Ă���C���ʂ̖؂�Ԃ��ċA���\�b�h
	private BinSearchTreeNode<K,V> delete(BinSearchTreeNode<K,V> start, K key) {
		//�؂���̂Ƃ��C���̂܂ܕԂ�
		if (start == null) return null ;

		if (comparator.compare(key, start.getData().getKey()) < 0) {
			//���̖؂���폜���ׂ��Ƃ�
			start.setLeft( delete(start.getLeft(), key)) ;
			return start;
		} else if (comparator.compare(key, start.getData().getKey()) > 0) {
			//�E�̖؂���폜���ׂ��Ƃ�
			start.setRight( delete(start.getRight(), key)) ;
			return start;
		} else {//���̃m�[�h���폜���ׂ��Ƃ�
			if (start.isLeaf()) {//���̃m�[�h���t�̂Ƃ�
				return null ;
			} else if (start.getLeft() == null) { //���̎q�����Ȃ��Ƃ�
				return start.getRight() ;
			} else if (start.getRight() == null) {//�E�̎q�����Ȃ��Ƃ�
				return start.getLeft() ;
			} else {//���̎q���E�̎q������Ƃ�
				//���ɑ傫���L�[�����f�[�^��{��
				BinSearchTreeNode<K,V> p = start.getRight() ;
				while ( p.getLeft() != null ) p = p.getLeft() ;
				Map.Entry<K,V> nextBigData = p.getData();

				//�E�̖؂��玟�ɑ傫���f�[�^���폜
				start.setRight( delete(start.getRight(), nextBigData.getKey()) );

				//���ɑ傫���f�[�^�ł��̃m�[�h�ɂ���f�[�^���㏑������
				start.setData(nextBigData) ;
				return start ;
			}
		}
	}

	//preorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> preorderIterator() {
		return preorder(root).iterator();
	}

	//preorderIterator���ł����Ăяo����Ȃ��ċA���\�b�h
	private List<Map.Entry<K,V>> preorder(BinSearchTreeNode<K,V> start) {
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>() ;
		if (start == null) return list ;
		else {
			list.add(start.getData()) ;
			list.addAll(preorder(start.getLeft())) ;
			list.addAll(preorder(start.getRight())) ;
			return list ;
		}
	}

	//inorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> inorderIterator() {
		return inorder(root).iterator();
	}

	//inorderIterator���ł����Ăяo����Ȃ��ċA���\�b�h
	private List<Map.Entry<K,V>> inorder(BinSearchTreeNode<K,V> start) {
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>() ;
		if (start == null) return list ;
		else {
			list.addAll(inorder(start.getLeft())) ;
			list.add(start.getData()) ;
			list.addAll(inorder(start.getRight())) ;
			return list ;
		}
	}

	//postorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> postorderIterator() {
		return postorder(root).iterator();
	}

	//postorderIterator���ł����Ăяo����Ȃ��ċA���\�b�h
	private List<Map.Entry<K,V>> postorder(BinSearchTreeNode<K,V> start) {
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>() ;
		if (start == null) return list ;
		else {
			list.addAll(postorder(start.getLeft())) ;
			list.addAll(postorder(start.getRight())) ;
			list.add(start.getData()) ;
			return list ;
		}
	}

	//accessor���\�b�h
	public BinSearchTreeNode<K,V> getRoot() {
		return root ;
	}
	public void setRoot(BinSearchTreeNode<K,V> root) {
		this.root = root ;
	}
	public Comparator<K> getComparator() {
		return comparator ;
	}
}