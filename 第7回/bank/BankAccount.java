package bank ;

public  class   BankAccount {
	private static volatile long nextNumber ;  // 次に使える口座番号．クラス・フィールド

	//インスタンス・フィールド
	private long  number ;  // 口座番号
	private long  balance ;  // 貯金残高

	// コンストラクタ（新しい口座を開く）
	public BankAccount( ) {
		number = nextNumber ; // 口座番号を（次に使える番号に）設定
		balance = 0 ; // 貯金残高を初期化
		nextNumber++ ; // 次に使える番号を増やす
	}


	// 貯金を預けるメソッド
	public void deposit(long amount) {
		System.out.print("Depositing " + amount) ;  // 預け金額を表示
		long newBalance = balance + amount ; // 新しい残高を計算
		System.out.println(" , new balance is " + newBalance) ;  // 新残高を表示
		balance = newBalance ; // 貯金残高を更新
	}

	// 貯金を引き出すメソッド
	public void withdraw(long amount) {
		if (amount > balance) {
			System.out.println("insufficient balance") ;  // 残高不足を表示
		} else {
			System.out.print("Withdrawing " + amount) ;  // 引き出し額を表示
			long newBalance = balance - amount ; // 新残高を計算
			System.out.println(", new balance is " + newBalance) ;  // 新残高を表示
			balance = newBalance ; // 残高を更新
		}
	}

	// 口座番号を返すメソッド
	public long getNumber( ) {
		return number; // 口座番号を返す
	}

	// 残高を返すメソッド
	public long getBalance( ) {
		return balance ; // 貯金残高を返す
	}

	// 残高を更新するメソッド
	public void setBalance(long newBalance) {
		balance = newBalance ; // 貯金残高を更新する
	}
} // BankAccountクラスの終わり
