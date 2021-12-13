package genericSearch;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Kはデータのキーの型，Vはデータの値の型
public class BinSearchTree<K,V> {
	private BinSearchTreeNode<K,V> root,curret;
	private Comparator<K> comparator ;

	//引数なしのコンストラクタを禁止．
	@SuppressWarnings("all")
	private BinSearchTree( ) { }

	//引数ありのコンストラクタ．
	public BinSearchTree(Comparator<K> comparator) {
		this.comparator = comparator ;
	}

	//空の木か否かを判定するメソッド
	public boolean isEmpty() {
		return root == null;
	}

	//この木において指定キーを持つデータを検索するメソッド
	public V search(K key) {
		return search(root, key);
	}


	//startを根とする木において指定キーを持つデータを検索するメソッド
	private V search(BinSearchTreeNode<K,V> start, K key) {
		if (start == null) return null ; //木が空の場合

		//見つかった場合
		if (comparator.compare(key, start.getData().getKey()) == 0)
			return start.getData().getValue();

		if (comparator.compare(key, start.getData().getKey()) <0) {
			//左部分木で再帰的に検索
			return start = start.getLeft() ;
		} else {
			//右部分木で再帰的に検索
			return start = start.getRight;
		}
	}


	//この木において指定キーを持つデータを検索するメソッド
	public void insert(Map.Entry<K,V> data) {
		root = insert(root, data);
	}
	


	//startを根とする木に新しいデータを挿入してから，結果の木を返すメソッド
	private BinSearchTreeNode<K,V> insert(BinSearchTreeNode<K,V> start, Map.Entry<K,V> data) {
		//易しい場合：空の木の場合
		if (start == null) {
			return new BinSearchTreeNode<K,V>(data) ;
		}
		
		//難しい場合
		if (comparator.compare(data.getKey(), start.getData().getKey()) < 0) {
			//左の子を根とする部分木に挿入すべきとき
			return new BinSearchTreeNode<K,V>(start.getLeft.data) ;
		} else { //右の子を根とする部分木に挿入すべきとき
			return new BinSearchTreeNode<K,V>(start.getRight.data) ;
		}
		return start ;
	}


	//木から指定のキーを持つデータを削除してから，結果の木を返すメソッド
	public void delete(K key) {
		root = delete(root, key);
	}


	//木から指定のキーを持つデータを削除してから，結果の木を返すメソッド
	private BinSearchTreeNode<K,V> delete(BinSearchTreeNode<K,V> start, K key) {
		//木が空のとき，そのまま返す
		if (start == null) return null ;

		if (comparator.compare(key, start.getData().getKey()) < 0) {
			//左の木から削除すべきとき
			start.setLeft(start.getRight) ;
			return start;
		} else if (comparator.compare(key, start.getData().getKey()) > 0) {
			//右の木から削除すべきとき
			start.setRight(start.getLeft) ;
			return start;
		} else {//このノードを削除すべきとき
			if (start.isLeaf()) {//このノードが葉のとき
				return null ;
			} else if (start.getLeft() == null) { //左の子がいないとき
				return start.getRight() ;
			} else if (start.getRight() == null) {//右の子がいないとき
				return start.getLeft() ;
			} else {//左の子も右の子がいるとき
				//次に大きいキーを持つデータを捜す
				BinSearchTreeNode<K,V> p = start.getRight() ;
				while ( p.getLeft() != null ) p = p.getLeft() ;
				Map.Entry<K,V> nextBigData = p.getData();

				//右の木から次に大きいデータを削除
				start.setRight(start.getLeft);

				//次に大きいデータでこのノードにあるデータを上書きする
				start.setData(nextBigData) ;
				return start ;
			}
		}
	}

	//preorderの順に2分探索木にあるデータをたどるためのメソッド
	public Iterator<Map.Entry<K,V>> preorderIterator() {
		return preorder(root).iterator();
	}

	//preorderIterator内でしか呼び出されない再帰メソッド
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

	//inorderの順に2分探索木にあるデータをたどるためのメソッド
	public Iterator<Map.Entry<K,V>> inorderIterator() {
		return inorder(root).iterator();
	}

	//inorderIterator内でしか呼び出されない再帰メソッド
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

	//postorderの順に2分探索木にあるデータをたどるためのメソッド
	public Iterator<Map.Entry<K,V>> postorderIterator() {
		return postorder(root).iterator();
	}

	//postorderIterator内でしか呼び出されない再帰メソッド
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

	//accessorメソッド
	public BinSearchTreeNode<K,V> getRoot() {
		return root ;
	}
	public void setRoot(BinSearchTreeNode<K,V> root) {
		this.root = root ;
	}
}
