package genericSearch;

public interface BTAcceptor<K,V> {
	//BTVisitor<K,V>を受け入れるためのメソッド
    public abstract void accept(BTVisitor<K,V> visitor);
}