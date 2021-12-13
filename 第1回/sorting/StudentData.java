package sorting;

import java.util.Scanner ;

//学生のデータを表すクラス（Ｃ言語の構造体みたいなもの）
public class StudentData {
    private int id, score ;
    private String name, address ;

    // 引数なしコンストラクタ
    public StudentData( ) {
    } //引数なしコンストラクタの終わり


    // 引数ありコンストラクタ
    public StudentData(int id, String name, String address, int score) {
          this.id = id;
          this.name = name;
          this.address = address;
          this.score = score;
    } //引数ありコンストラクタの終わり

    public int getID( ) { // ゲッターメソッド
        return id ;
    }

    public int getScore( ) { // ゲッターメソッド
        return score ;
    }

    public String getName( ) { // ゲッターメソッド
        return name ;
    }

    public String getAddress( ) { // ゲッターメソッド
        return address ;
    }

    public void setID(int id) { // セッターメソッド
    	//本当はidの正当性を調べて，正当のときのみ下記の代入を行う
        this.id = id ;
    }

    public void setScore(int score) { // セッターメソッド
    	if (score < 0 || score > 100)
    		throw new IllegalArgumentException() ;
        this.score = score ;
    }

    public void setName(String name) { // セッターメソッド
    	if (name == null)
    		throw new IllegalArgumentException() ;
        this.name = name ;
    }

    public void setAddress(String address) { // セッターメソッド
        this.address = address ;
    }

    @SuppressWarnings("resource")
    public void inputStudentData( ) { // 学生データを入力するメソッド
        Scanner  sc = new Scanner(System.in);

        System.out.printf("id: ") ;
        id = Integer.parseInt( sc.nextLine( ) ) ;
        System.out.printf("name: ") ;
        name = sc.nextLine( ) ;
        System.out.printf("address: ") ;
        address = sc.nextLine( ) ;
        System.out.printf("score: ") ;
        score = Integer.parseInt( sc.nextLine( ) ) ;
    } // 学生データを入力するメソッドの終わり

    public void printStudentData( ) { // 学生データを表示するメソッド
        System.out.println("id: " + id) ;
        System.out.println("name: " + name) ;
        System.out.println("address: " + address) ;
        System.out.println("score: " + score) ;
    } // 学生データを入力するメソッドの終わり
} // StudentDataクラスの終わり

