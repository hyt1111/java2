package bank ;

public  class   DepositTask implements  Runnable {  
     private int delay ;  //預け入れ間隔を覚えるインスタンス・フィールド  
     private BankAccount account; // 預け入れ先口座番号を覚えるインスタンス・フィールド 
     private long  amount ;  // 預け入れ金額を覚えるインスタンス・フィールド
     private int  times ;  // 預け入れの回数を覚えるインスタンス・フィールド


    // コンストラクタ（預け入れ先の口座，金額，回数を初期化する） 
     public DepositTask(BankAccount account, long amount, int times, int delay) {  
         this.account = account ; 
         this.amount = amount ; 
         this.times = times ;  
         this.delay = delay ;
     }  


    // 預け入れを行うメソッド 
     public void run( ) {
         try {
             for ( int i = 1; i <= times;  i++ ) {  
                 account.deposit(amount); // 1回引き落とす    
                 Thread.sleep(delay);  // 連続する預け入れでは0.001秒間の間隔を置く 
             }
         } catch (InterruptedException e) {
        	 Thread.currentThread().interrupt();
         }
     }
}