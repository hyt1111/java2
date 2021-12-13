package genericSearch;

public interface BTAcceptor<K,V> {
	//BTVisitor<K,V>‚ğó‚¯“ü‚ê‚é‚½‚ß‚Ìƒƒ\ƒbƒh
    public abstract void accept(BTVisitor<K,V> visitor);
}