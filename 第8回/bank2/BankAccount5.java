package bank ;

// Lock�C���^�[�t�F�[�X��ReentrantLock�N���X�������̂Ŏ���1�s���K�v
import java.util.concurrent.locks.* ;

public class BankAccount5 extends BankAccount {
	// ���̃N���X��1�ȏ�̃��\�b�h���X���b�h�Z�[�t�ɂ������Ƃ��C���̃N���X�̃t�B�[���h
	// �Ƃ��āC���̍s�̂悤�� Lock�C���^�[�t�F�[�X�i�����������N���X�j�^�̕ϐ���ǉ�����
	private Lock  balanceChangeLock;
	private  Condition  sufficientBalanceCondition; // �҂������̃t�B�[���h

	// �R���X�g���N�^�i�V�����������J���j
	public BankAccount5( ) {
		// �R���X�g���N�^��ReentrantLock�N���X�̃C���X�^���X�i���b�N�E�I�u�W�F�N�g�j�𐶐�
		balanceChangeLock = new ReentrantLock( ) ;
		sufficientBalanceCondition = balanceChangeLock.newCondition( ); //�����擾
	}


	// �������Ƃ����s�����\�b�h
	@Override
	public void withdraw(long amount) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������ɓ���O�Ɍ���������
		balanceChangeLock.lock( ) ;

		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			try {
				while (amount > getBalance()) {
					sufficientBalanceCondition.await( ); // �҂�
				}
				System.out.print("Withdrawing " + amount) ;  // �����o���z��\��
				long newBalance = getBalance() - amount ; // �V�c�����v�Z
				System.out.println(", new balance is " + newBalance); //�V�c����\��
				setBalance(newBalance) ; // �c�����X�V
				sufficientBalanceCondition.signalAll() ;
			} finally {
				balanceChangeLock.unlock( ) ; // �����O��
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt() ;
		}
	}


	// ������a���郁�\�b�h
	@Override
	public void deposit(long amount) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������ɓ���O�Ɍ���������
		balanceChangeLock.lock( ) ;

		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			super.deposit(amount) ;
			sufficientBalanceCondition.signalAll( ) ; // �҂��Ă���X���b�h�S���ɒʒm
		} finally {
			balanceChangeLock.unlock( ) ; // �����O��
		}
	}


	// �c�����X�V���郁�\�b�h
	@Override
	public void setBalance(long newBalance) {
		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������ɓ���O�Ɍ���������
		balanceChangeLock.lock( ) ;

		// ���̃X���b�h�Ɋ��荞�܂ꂽ���Ȃ������� try�u���b�N�ɓ����
		// finally�u���b�N�� �����O��.
		try {
			super.setBalance(newBalance) ;
			sufficientBalanceCondition.signalAll( ) ; // �҂��Ă���X���b�h�S���ɒʒm
		} finally {
			balanceChangeLock.unlock( ) ; // �����O��
		}
	}
} // BankAccount5�N���X�̏I���