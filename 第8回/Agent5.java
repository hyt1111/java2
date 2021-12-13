package bank ;

public  class  Agent5 {      
    private static void testAccount(BankAccount account, long amount, int times, int delay) {
        // タスク（預け入れという仕事）を１つ生成し，そのタスクを担うスレッドを１つ生成する 
        Runnable dTask = new DepositTask(account, amount, times, delay) ; 
        Thread dAgent1 = new Thread(dTask) ;
        Thread dAgent2 = new Thread(dTask) ;

        // タスク（引き落としという仕事）を１つ生成し，そのタスクを担うスレッドを１つ生成する 
        Runnable wTask = new WithdrawTask(account, amount, times, delay) ; 
        Thread wAgent1 = new Thread(wTask) ;
        Thread wAgent2 = new Thread(wTask) ;

        // 先ほど生成した２つのスレッドを起動する 
        wAgent1.start( ) ; 
        wAgent2.start( ) ; 
        dAgent1.start( ) ;  
        dAgent2.start( ) ;  
    }
    
    public static void main(String[ ] args ) {
    	testAccount(new BankAccount6(), 100, 1000, 1) ;
    }
}