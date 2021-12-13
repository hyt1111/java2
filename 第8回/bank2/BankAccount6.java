package bank ;

public  class   BankAccount6 extends  BankAccount {
	// 引き落としを行うメソッド
	@Override
	public synchronized void withdraw(long amount) {
		// 他のスレッドに割り込まれたくない部分を tryブロックに入れる
		// finallyブロックで 鍵を外す.
		try {
			while (amount > getBalance()) {
				wait( ); // 待つ
			}
			System.out.print("Withdrawing " + amount) ;  // 引き出し額を表示
			long newBalance = getBalance() - amount ; // 新残高を計算
			System.out.println(", new balance is " + newBalance); //新残高を表示
			setBalance(newBalance) ; // 残高を更新
			notifyAll() ;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt() ;
		}
	}


	// 貯金を預けるメソッド
	@Override
	public synchronized void deposit(long amount) {
		super.deposit(amount) ;
		notifyAll( ) ; // 待っているスレッド全員に通知
	}


	// 残高を更新するメソッド
	public synchronized void setBalance(long newBalance) {
		super.setBalance(newBalance) ; // 貯金残高を更新する
		notifyAll() ;
	}
} // BankAccountクラスの終わり
