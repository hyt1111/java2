package genericSearch;

public interface BTAcceptor<K,V> {
	//BTVisitor<K,V>���󂯓���邽�߂̃��\�b�h
    public abstract void accept(BTVisitor<K,V> visitor);
}