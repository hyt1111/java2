package keywdSearch ;

import  java.io.* ; // File�N���X���g���̂ŕK�v
import  java.util.concurrent.* ; // BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import  java.util.* ; // Scanner�N���X���g���̂ŕK�v

public  class  KeywordSearch {
	private static final int  SIZE = 100;  // �u���b�L���O�L���[�ɒu����t�@�C���̍ő��.

	// �f������S������X���b�h�̖{��.
	private static final int  SEARCHER = Runtime.getRuntime().availableProcessors() - 2 ;

	@SuppressWarnings("resource")
	public static void main(String[ ] args ) {
		//System.out.println("#availableThreads: " +
		//	Runtime.getRuntime().availableProcessors()) ;

		//�����X���b�h�̖{����\��
		System.out.println("#searchers: " + SEARCHER) ;

		// �w�肳�ꂽ�T�C�Y�̃u���b�L���O�L���[���쐬����
		final BlockingQueue<File> queue = new ArrayBlockingQueue<File>( SIZE ) ;


		// �񋓃X���b�h�̎d���𐶐�����.
		EnumerationTask et = new EnumerationTask(queue) ;

		// �������̗񋓂�S������X���b�h��1�{�쐬����D
		Thread enumerator = new Thread( et ) ;

		// �f��������S���X���b�h���������񐶐�����D
		Thread[ ] searchers = new Thread[SEARCHER]; //�����X���b�h���o����z��𐶐�.
		for (int i = 0 ; i < SEARCHER ; i++) {
			searchers[i] = new Thread( new SearchTask(queue) ) ; // i�Ԗڂ̌����X���b�h���쐬����.
		}

		// �f��������S���X���b�h���N������D
		for (int i = 0 ; i < SEARCHER ; i++)
			searchers[i].start( ) ; // i�Ԗڂ̌����X���b�h���N������.

		// �������񋓂�S������X���b�h���N������D
		enumerator.start( ) ;
	} // main���\�b�h�̏I���
} // KeywordSearch.java�̏I���