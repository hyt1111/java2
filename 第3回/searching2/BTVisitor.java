package genericSearch;

import java.util.Map;

public interface BTVisitor<K,V> {
	//nullを処理して値を返すためのメソッド
    public abstract Object visitNull( );

	//あるノードにあるデータdata，そのノードの左の部分木を処理した結果leftValue，
    //およびそのノードの右の部分木を処理した結果rightValueから，そのノードを
    //処理した結果を返すためのメソッド
    public abstract Object visitNode(Object leftValue, Object rightValue, Map.Entry<K,V> data) ;
}
