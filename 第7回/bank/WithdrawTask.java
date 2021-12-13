package bank ;

public  class   WithdrawTask implements  Runnable {  
     private int delay ;  //引き落とし間隔を覚えるインスタンス・フィールド  
     private BankAccount account; // 引き落とし先口座番号を覚えるインスタンス・フィールド 
     private long  amount ;  // 引き落とし金額を覚えるインスタンス・フィールド
     private int  times ;  // 引き落としの回数を覚えるインスタンス・フィールド


    // コンストラクタ（引き落とし先の口座，金額，回数を初期化する） 
     public WithdrawTask(BankAccount account, long amount, int times, int delay) {  
         this.account = account ; 
         this.amount = amount ; 
         this.times = times ;  
         this.delay = delay ;
     }  


    // 引き落としを行うメソッド 
     public void run( ) {
         try {
             for ( int i = 1; i <= times;  i++ ) {  
                 account.withdraw(amount); // 1回引き落とす    
                 Thread.sleep(delay);  // 連続する引き落としでは0.001秒間の間隔を置く 
             }
         } catch (InterruptedException e) {
        	 Thread.currentThread().interrupt();
         }
     }
}