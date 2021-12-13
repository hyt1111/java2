package bank ;

// LockインターフェースとReentrantLockクラスをつかうので次の1行が必要
import  java.util.concurrent.locks.* ;

public  class   BankAccount2 extends BankAccount {
	// このクラスの1つ以上のメソッドをスレッドセーフにしたいとき，このクラスのフィールド
	// として，次の行のように Lockインターフェース（を実装したクラス）型の変数を追加する
	private Lock  balanceChangeLock;

	// コンストラクタ（新しい口座を開く）
	public BankAccount2( ) {
		// コンストラクタでReentrantLockクラスのインスタンス（ロック・オブジェクト）を生成
		balanceChangeLock = new ReentrantLock( ) ;
	}


	// 貯金を預けるメソッド
	@Override
	public void deposit(long amount) {
		// 他のスレッドに割り込まれたくない部分に入る前に鍵をかける
		balanceChangeLock.lock( ) ;

		// 他のスレッドに割り込まれたくない部分を tryブロックに入れる
		// finallyブロックで 鍵を外す.
		try {
			super.deposit(amount) ;
		} finally {
			balanceChangeLock.unlock( ) ; // 鍵を外す
		}
	}


	// 引き落としを行うメソッド
	@Override
	public void withdraw(long amount) {
		// 他のスレッドに割り込まれたくない部分に入る前に鍵をかける
		balanceChangeLock.lock( ) ;

		// 他のスレッドに割り込まれたくない部分を tryブロックに入れる
		// finallyブロックで 鍵を外す.
		try {
			super.withdraw(amount) ;
		} finally {
			balanceChangeLock.unlock( ) ; // 鍵を外す
		}
	}

	// 残高を更新するメソッド
	@Override
	public void setBalance(long newBalance) {
		// 他のスレッドに割り込まれたくない部分に入る前に鍵をかける
		balanceChangeLock.lock( ) ;

		// 他のスレッドに割り込まれたくない部分を tryブロックに入れる
		// finallyブロックで 鍵を外す.
		try {
			super.setBalance(newBalance) ;
		} finally {
			balanceChangeLock.unlock( ) ; // 鍵を外す
		}
	}
} // BankAccount2クラスの終わり