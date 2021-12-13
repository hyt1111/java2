package bank ;

public  class   BankAccount6 extends  BankAccount {
	// �������Ƃ����s�����\�b�h
	@Override
	public synchronized void withdraw(long amount) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			while (amount > getBalance()) {
				wait( ); // �҂�
			}
			System.out.print("Withdrawing " + amount) ;  // �����o���z��\��
			long newBalance = getBalance() - amount ; // �V�c�����v�Z
			System.out.println(", new balance is " + newBalance); //�V�c����\��
			setBalance(newBalance) ; // �c�����X�V
			notifyAll() ;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt() ;
		}
	}


	// ������a���郁�\�b�h
	@Override
	public synchronized void deposit(long amount) {
		super.deposit(amount) ;
		notifyAll( ) ; // �҂��Ă���X���b�h�S���ɒʒm
	}


	// �c�����X�V���郁�\�b�h
	public synchronized void setBalance(long newBalance) {
		super.setBalance(newBalance) ; // �����c�����X�V����
		notifyAll() ;
	}
} // BankAccount�N���X�̏I���
