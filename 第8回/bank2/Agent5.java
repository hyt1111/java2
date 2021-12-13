package bank ;

public  class  Agent5 {      
    private static void testAccount(BankAccount account, long amount, int times, int delay) {
        // �^�X�N�i�a������Ƃ����d���j���P�������C���̃^�X�N��S���X���b�h���P�������� 
        Runnable dTask = new DepositTask(account, amount, times, delay) ; 
        Thread dAgent1 = new Thread(dTask) ;
        Thread dAgent2 = new Thread(dTask) ;

        // �^�X�N�i�������Ƃ��Ƃ����d���j���P�������C���̃^�X�N��S���X���b�h���P�������� 
        Runnable wTask = new WithdrawTask(account, amount, times, delay) ; 
        Thread wAgent1 = new Thread(wTask) ;
        Thread wAgent2 = new Thread(wTask) ;

        // ��قǐ��������Q�̃X���b�h���N������ 
        wAgent1.start( ) ; 
        wAgent2.start( ) ; 
        dAgent1.start( ) ;  
        dAgent2.start( ) ;  
    }
    
    public static void main(String[ ] args ) {
    	testAccount(new BankAccount6(), 100, 1000, 1) ;
    }
}