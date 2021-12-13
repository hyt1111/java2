package forStudent;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import genericSearch.BinSearchTreeNode ;

//Kはデータのキーの型，Vはデータの値の型
public class BinSearchTree<K,V> {
	private BinSearchTreeNode<K,V> root ;
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
		//return search(root, key); //再帰を使うバージョン．

		//下記は再帰を使わないバージョン
		BinSearchTreeNode<K,V> currentNode = root ; //根から検索をスタート
		while (currentNode != null) {
			if (comparator.compare(key, currentNode.getData().getKey()) == 0)
				return currentNode.getData().getValue() ;//見つかった時
			else if (comparator.compare(key, currentNode.getData().getKey()) < 0)
				currentNode = currentNode.getLeft() ;//左部分木で探す
			else
				currentNode = currentNode.getRight() ; //右部分木で探す
		}
		return null ; //見つからないとき
	}


	//startを根とする木において指定キーを持つデータを検索する再帰メソッド
	private V search(BinSearchTreeNode<K,V> start, K key) {
		if (start == null) return null ; //木が空の場合

		//見つかった場合
		if (comparator.compare(key, start.getData().getKey()) == 0)
			return start.getData().getValue();

		if (comparator.compare(key, start.getData().getKey()) <0) {
			//左部分木で再帰的に検索
			return search(start.getLeft(), key) ;
		} else {
			//右部分木で再帰的に検索
			return search(start.getRight(), key) ;
		}
	}


	//この木において指定キーを持つデータを検索するメソッド
	public void insert(Map.Entry<K,V> data) {
		//root = insert(root, data); //再帰を使うバージョン．

		//下記は再帰を使わないバージョン
		BinSearchTreeNode<K,V> newNode = new BinSearchTreeNode<K,V>(data) ;//新ノード作成
		BinSearchTreeNode<K,V> currentNode = root ; //根から挿入場所の検索をスタート
		if (root == null) { //木が空のとき
			root = newNode ;
			return ;
		}

		//nullにたどり着くまで木を下っていく
		BinSearchTreeNode<K,V> previousNode ;
		do {
			previousNode = currentNode ; //木を下る前に現在のノードを記憶．
			if (comparator.compare(data.getKey(), currentNode.getData().getKey()) < 0)
				currentNode = currentNode.getLeft() ;//左に一歩降りる
			else
				currentNode = currentNode.getRight() ; //右に一歩降りる
		} while (currentNode != null) ;

		//nullまで来たら挿入
		if (comparator.compare(data.getKey(), previousNode.getData().getKey()) < 0)
			previousNode.setLeft(newNode) ;
		else
			previousNode.setRight(newNode) ;
	}


	//startを根とする木に新しいデータを挿入してから，結果の木を返す再帰メソッド
	private BinSearchTreeNode<K,V> insert(BinSearchTreeNode<K,V> start, Map.Entry<K,V> data) {
		//易しい場合：空の木の場合
		if (start == null) {
			return new BinSearchTreeNode<K,V>(data) ;
		}

		//難しい場合
		if (comparator.compare(data.getKey(), start.getData().getKey()) < 0) {
			//左の子を根とする部分木に挿入すべきとき
			start.setLeft( insert(start.getLeft(), data) ) ;
		} else { //右の子を根とする部分木に挿入すべきとき
			start.setRight( insert(start.getRight(), data) ) ;
		}
		return start ;
	}


	//木から指定のキーを持つデータを削除してから，結果の木を返すメソッド
	public void delete(K key) {
		//root = delete(root, key); //再帰を使うバージョン．

		//下記は再帰を使わないバージョン
		BinSearchTreeNode<K,V> currentNode = root ; //根から検索をスタート
		BinSearchTreeNode<K,V> previousNode = null ;
		while (currentNode != null) {
			if (comparator.compare(key, currentNode.getData().getKey()) == 0)
				break ;//見つかった時
			else if (comparator.compare(key, currentNode.getData().getKey()) < 0) {
				previousNode = currentNode ; //木を下る前に現在のノードを記憶．
				currentNode = currentNode.getLeft() ;//左部分木で探す
			} else {
				previousNode = currentNode ; //木を下る前に現在のノードを記憶．
				currentNode = currentNode.getRight() ; //右部分木で探す
			}
		}

		if (currentNode == null) return ; //見つからなかったとき何もしない

		if (currentNode.isLeaf()) { //葉を削除するとき
			if (currentNode == root) root = null ;//根を削除するとき

			//根以外の葉を削除する場合
			if (previousNode.getLeft() == currentNode)
				previousNode.setLeft(null) ;
			else
				previousNode.setRight(null) ;
			return ;
		}

		if (currentNode.getLeft() == null) { //左の子がnullのノードを削除するとき
			if (previousNode == null) root = currentNode.getRight() ;
			else if (previousNode.getLeft() == currentNode)
				previousNode.setLeft(currentNode.getRight()) ;
			else
				previousNode.setRight(currentNode.getRight()) ;
			return ;
		}

		if (currentNode.getRight() == null) { //右の子がnullのノードを削除するとき
			if (previousNode == null) root = currentNode.getLeft() ;
			else if (previousNode.getLeft() == currentNode)
				previousNode.setLeft(currentNode.getLeft()) ;
			else
				previousNode.setRight(currentNode.getLeft()) ;
			return ;
		}

		//左の子も右の子もnullではないノードを削除するとき.
		//まず，削除するデータの次に大きいデータを探す
		BinSearchTreeNode<K,V> nextBigNode = currentNode.getRight() ;//右に一歩降りる．
		previousNode = currentNode ;
		while (nextBigNode.getLeft() != null) {//左の子がnullではない間，左に降りる．
			previousNode = nextBigNode ;
			nextBigNode = nextBigNode.getLeft() ;
		}

		//次に，削除するデータをその次に大きいデータで上書きする．
		currentNode.setData(nextBigNode.getData()) ;

		//最後に，削除するデータの次に大きいデータを削除する．
		if (previousNode.getLeft() == nextBigNode)
			previousNode.setLeft(nextBigNode.getRight()) ;
		else
			previousNode.setRight(nextBigNode.getRight()) ;
	}


	//木から指定のキーを持つデータを削除してから，結果の木を返す再帰メソッド
	private BinSearchTreeNode<K,V> delete(BinSearchTreeNode<K,V> start, K key) {
		//木が空のとき，そのまま返す
		if (start == null) return null ;

		if (comparator.compare(key, start.getData().getKey()) < 0) {
			//左の木から削除すべきとき
			start.setLeft( delete(start.getLeft(), key)) ;
			return start;
		} else if (comparator.compare(key, start.getData().getKey()) > 0) {
			//右の木から削除すべきとき
			start.setRight( delete(start.getRight(), key)) ;
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
				start.setRight( delete(start.getRight(), nextBigData.getKey()) );

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
	public Comparator<K> getComparator() {
		return comparator ;
	}
}