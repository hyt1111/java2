package bank ;

// LockインターフェースとReentrantLockクラスをつかうので次の1行が必要
import java.util.concurrent.locks.* ;

public class BankAccount5 extends BankAccount {
	// このクラスの1つ以上のメソッドをスレッドセーフにしたいとき，このクラスのフィールド
	// として，次の行のように Lockインターフェース（を実装したクラス）型の変数を追加する
	private Lock  balanceChangeLock;
	private  Condition  sufficientBalanceCondition; // 待ち条件のフィールド

	// コンストラクタ（新しい口座を開く）
	public BankAccount5( ) {
		// コンストラクタでReentrantLockクラスのインスタンス（ロック・オブジェクト）を生成
		balanceChangeLock = new ReentrantLock( ) ;
		sufficientBalanceCondition = balanceChangeLock.newCondition( ); //条件取得
	}


	// 引き落としを行うメソッド
	@Override
	public void withdraw(long amount) {
		// 他のスレッドに割り込まれたくない部分に入る前に鍵をかける
		balanceChangeLock.lock( ) ;

		// 他のスレッドに割り込まれたくない部分を tryブロックに入れる
		// finallyブロックで 鍵を外す.
		try {
			try {
				while (amount > getBalance()) {
					sufficientBalanceCondition.await( ); // 待つ
				}
				System.out.print("Withdrawing " + amount) ;  // 引き出し額を表示
				long newBalance = getBalance() - amount ; // 新残高を計算
				System.out.println(", new balance is " + newBalance); //新残高を表示
				setBalance(newBalance) ; // 残高を更新
				sufficientBalanceCondition.signalAll() ;
			} finally {
				balanceChangeLock.unlock( ) ; // 鍵を外す
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt() ;
		}
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
			sufficientBalanceCondition.signalAll( ) ; // 待っているスレッド全員に通知
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
			sufficientBalanceCondition.signalAll( ) ; // 待っているスレッド全員に通知
		} finally {
			balanceChangeLock.unlock( ) ; // 鍵を外す
		}
	}
} // BankAccount5クラスの終わり