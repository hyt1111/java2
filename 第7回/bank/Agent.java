package bank ;

public  class  Agent {
	protected static void testAccount(BankAccount account, long amount, int times, int delay) {
		// タスク（預け入れという仕事）を１つ生成し，そのタスクを担うスレッドを１つ生成する
		Runnable dTask = new DepositTask(account, amount, times, delay) ;
		Thread dAgent = new Thread(dTask) ;

		// タスク（引き落としという仕事）を１つ生成し，そのタスクを担うスレッドを１つ生成する
		Runnable wTask = new WithdrawTask(account, amount, times, delay) ;
		Thread wAgent = new Thread(wTask) ;

		// 先ほど生成した２つのスレッドを起動する
		dAgent.start( ) ;
		wAgent.start( ) ;
	}

	public static void main(String[ ] args ) {
		testAccount(new BankAccount(), 100, 1000, 1) ;
	}
}