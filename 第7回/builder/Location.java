package builder;

public class Location {
	//定数
	public static final String SPACE = " ";
	public static final String COMMA = ",";
	public static final String EMPTY_STRING = "" ;

	//フィールド
	private String street;
	private String city;
	private String region;
	private String postalCode;

	//コンストラクタ
	public Location(String s, String c, String r, String p) {
		if (s != null) setStreet(s) ; else setStreet(EMPTY_STRING) ;
		if (c != null) setCity(c) ; else setCity(EMPTY_STRING) ;
		if (r != null) setRegion(r) ; else setRegion(EMPTY_STRING) ;
		if (p != null) setPostalCode(p) ; else setPostalCode(EMPTY_STRING) ;
	}

	//抽象メソッド
	public String toString() {
		return getStreet() + COMMA + SPACE + getCity() + COMMA + SPACE
				+ getRegion() + COMMA + SPACE + getPostalCode() ;
	}

	//accessor methods
	public String getStreet(){ return street; }
	public String getCity(){ return city; }
	public String getPostalCode(){ return postalCode; }
	public String getRegion(){ return region; }
	public void setStreet(String s){ street = s; }
	public void setCity(String c){ city = c; }
	public void setRegion(String r){ region = r; }
	public void setPostalCode(String p){ postalCode = p; }
}
