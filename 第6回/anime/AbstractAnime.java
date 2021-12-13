package anime;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractAnime extends JPanel implements AnimeIF{
    private Thread animeThread ; //�A�j����S������X���b�h���o����ϐ�
    private int delay = 500 ; //��ʂ̍X�V�Ԋu�i�P�ʁFms�j
    private volatile boolean running = true ;

	public final void start() {
		initAnime( ) ; //�t�B�[���h����������
		arrangeComponents( ) ; //���i�𐶐����Ĕz�u
		addListeners( ) ; //���i�Ƀ��X�i�[��t����
		startAnime() ; //�A�j�����J�n
	}

	//���i�𐶐����Ĕz�u���郁�\�b�h
	public abstract void arrangeComponents( ) ;

	//���i�Ƀ��X�i�[��t���郁�\�b�h
	public abstract void addListeners( ) ;

	// ����loadImages���\�b�h�̒�`�F
	public Image[] loadImages(String fileNames[])
			throws IOException, InterruptedException {
		Image[] imgs = new Image[fileNames.length]; //�S�摜���o����z��̐���
		MediaTracker tracker = new MediaTracker(this);
		for ( int i = 0 ; i < imgs.length ; i++) {   //�摜���t�@�C������ǂݍ���
			File file = new File(fileNames[i]) ;
			imgs[i] = ImageIO.read(file) ;
			tracker.addImage( imgs[i], i) ; // ID�ԍ�i�ŉ摜�����f�B�A�g���b�J�[�ɓo�^
		}
		tracker.waitForAll( ) ; // ���ׂẲ摜�̃��[�h����������܂ő҂�
		return imgs ;
	} // loadImages���\�b�h�̏I���

	@Override
	public void startAnime( ) {
		if (!isRunning()) {
			running = true ;
			animeThread = new Thread() {
				@Override
				public void run() {
					animeLoop() ;
				}
			};
			animeThread.start() ;
		}
	}

	public void animeLoop() {
		try {
			while ( running ) {
				updateAnime() ;
				repaint( ) ; // ��ʂ̍ĕ`���v��
				Thread.sleep( delay ) ;  // �A������摜�Ԃł�0.2�b�ԃ|�[�Y��������
			}
		} catch (InterruptedException e) {
			running = false ;
		}
	}

	@Override
	public void stopAnime() {
		if (isRunning()) {
			running = false ;
			animeThread = null ;
		}
	}

	//�A�j���������Ă��邩�𒲂ׂ郁�\�b�h
	@Override
	public boolean isRunning() {
		return animeThread != null && running == true ;
	}

	//setter and getter methods
	public int getDelay() {
		return delay ;
	}
	public void setDelay(int delay) {
		this.delay = delay ;
	}
	public Thread getAnimeThread() {
		return animeThread ;
	}
	public void setAnimeThread(Thread t) {
		animeThread = t ;
	}
}