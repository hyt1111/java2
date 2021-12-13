package genericSearch;

import java.util.Map;

//Kはデータのキーの型，Vはデータの値の型
public class BinSearchTreeNode<K,V> {
	private Map.Entry<K,V> data ;
	private BinSearchTreeNode<K,V> left, right ;

	//コンストラクタ
	public BinSearchTreeNode(Map.Entry<K,V> data, BinSearchTreeNode<K,V> left,
			BinSearchTreeNode<K,V> right) {
		this.data = data ;
		this.left = left ;
		this.right = right ;
	}

	//葉を作るためのコンストラクタ
	public BinSearchTreeNode(Map.Entry<K,V> data) {
		this(data, null, null) ;
	}

	//葉か否かを判定するメソッド
	public boolean isLeaf() {
		return left == null && right == null ;
	}


	//accessorメソッド
	public Map.Entry<K,V> getData() {
		return data ;
	}
	public BinSearchTreeNode<K,V> getLeft() {
		return left ;
	}
	public BinSearchTreeNode<K,V> getRight() {
		return right ;
	}
	public void setData(Map.Entry<K,V> data) {
		this.data = data ;
	}
	public void setLeft(BinSearchTreeNode<K,V> left) {
		this.left = left ;
	}
	public void setRight(BinSearchTreeNode<K,V> right) {
		this.right = right ;
	}
}
