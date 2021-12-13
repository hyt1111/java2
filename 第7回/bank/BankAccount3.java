package bank ;

public  class   BankAccount3 extends  BankAccount {
	// 貯金を預けるメソッド（同期メソッド）
	@Override
	public synchronized  void deposit(long amount) {
		super.deposit(amount) ;
	}

	// 貯金を引き出すメソッド
	@Override
	public  synchronized  void withdraw(long amount) {
		super.withdraw(amount) ;
	}

	// 残高を更新するメソッド
	public synchronized void setBalance(long newBalance) {
		super.setBalance(newBalance) ; // 貯金残高を更新する
	}
} // BankAccountクラスの終わり
