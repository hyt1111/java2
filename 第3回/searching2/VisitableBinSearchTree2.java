package genericSearch;

import java.util.Comparator;

//Kはデータのキーの型，Vはデータの値の型
public class VisitableBinSearchTree2<K,V> extends BinSearchTree<K,V>
		implements BTAcceptor<K,V> {
	private BTVisitor<K,V> visitor ;

	//引数ありのコンストラクタ
	public VisitableBinSearchTree2(Comparator<K> comparator) {
		super(comparator) ;
	}

	//Visitorを引き受けるメソッド
	public void accept(BTVisitor<K,V> visitor) {
		this.visitor = visitor ;
	}


	//木全体を処理するメソッド
	public Object traverse( ) {
		return traverse(getRoot()) ;
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
