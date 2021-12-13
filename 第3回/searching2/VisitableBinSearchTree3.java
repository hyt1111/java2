package genericSearch;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

//Kはデータのキーの型，Vはデータの値の型
public class VisitableBinSearchTree3<K,V> implements BTAcceptor<K,V> {
	private BinSearchTree<K,V> tree ;
	private BTVisitor<K,V> visitor ;

	//引数なしのコンストラクタを禁止．
	@SuppressWarnings("all")
	private VisitableBinSearchTree3( ) { }

	//引数ありのコンストラクタ
	public VisitableBinSearchTree3(Comparator<K> comparator) {
		tree = new BinSearchTree<K,V>(comparator) ;
	}

	//************** 訪問可能な2分探索木が2分探索木でもあるので，*******
	//************** BinSearchTree<K,V>クラスで提供しているpublicな ****
	//************** メソッドをすべてVisitableBinSearchTree3<K,V>に ****
	//************** 持たせる必要がある．     **************************
	//************** しかし，そのままコピー・ペーストをすると，*********
	//************** コードの重複が酷くなってくるので，各publicな ******
	//************** メソッドを treeに委譲するようにする．  ************
	//+++++ コピー開始 +++++
	//空の木か否かを判定するメソッド
	public boolean isEmpty() {
		return tree.isEmpty() ; //treeに委譲
	}

	//この木において指定キーを持つデータを検索するメソッド
	public V search(K key) {
		return tree.search(key) ;
	}

	//この木において指定キーを持つデータを検索するメソッド
	public void insert(Map.Entry<K,V> data) {
		tree.insert(data) ; //treeに委譲
	}

	//木から指定のキーを持つデータを削除してから，結果の木を返すメソッド
	public void delete(K key) {
		tree.delete(key) ; //treeに委譲
	}

	//preorderの順に2分探索木にあるデータをたどるためのメソッド
	public Iterator<Map.Entry<K,V>> preorderIterator() {
		return tree.preorderIterator() ; //treeに委譲
	}

	//inorderの順に2分探索木にあるデータをたどるためのメソッド
	public Iterator<Map.Entry<K,V>> inorderIterator() {
		return tree.inorderIterator() ; //treeに委譲
	}

	//postorderの順に2分探索木にあるデータをたどるためのメソッド
	public Iterator<Map.Entry<K,V>> postorderIterator() {
		return tree.postorderIterator() ; //treeに委譲
	}

	//accessorメソッド
	public BinSearchTreeNode<K,V> getRoot() {
		return tree.getRoot() ; //treeに委譲
	}
	public void setRoot(BinSearchTreeNode<K,V> root) {
		tree.setRoot(root) ; //treeに委譲
	}
	//+++++ コピー終了 +++++
	//**************************************************************

	//Visitorを受け入れるメソッド
	public void accept(BTVisitor<K,V> visitor) {
		this.visitor = visitor ;
	}

	//木全体を処理するメソッド
	public Object traverse( ) {
		return traverse(tree.getRoot()) ;
	}

	//木の指定ノードstartを根とする部分木を処理するメソッド
	private Object traverse(BinSearchTreeNode<K,V> start) {
		if (start == null)//空の木であるとき
			return visitor.visitNull( );
		else {//空ではない木のとき
			Object left = traverse(start.getLeft()) ;
			Object right = traverse(start.getRight()) ;
			return visitor.visitNode(left, right, start.getData());
		}
	}
}
