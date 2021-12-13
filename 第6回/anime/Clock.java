package anime ;

import javax.swing.*;  //Swing���C�u�����̒񋟂���JPanel�����g���̂�
import java.awt.event.*; //ActionListener�����g���̂�
import java.util.Calendar;

@SuppressWarnings("serial")
public class Clock extends AbstractAnime {
	// �@ ���ׂẴ��\�b�h������N���X�Ŏg����悤�ȕϐ��̐錾
	private boolean isAm = true ; //���ݎ����͌ߑO���ߌォ���o����ϐ�
	private String time ;//���ݎ������o����ϐ�
	private JTextField tf ;//���ݎ�����\������e�L�X�g�t�B�[���h
	private JButton bt ;//�u��~�v�܂��́u�J�n�v�{�^��

	private void getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.AM_PM) == Calendar.AM)
			isAm = true ;
		else
			isAm = false ;
		time = String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR),
			cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND) ) ;
	}


	//���i�𐶐����Ĕz�u���郁�\�b�h
	@Override
	public void arrangeComponents() {
		//���C�A�E�g��ݒ肵�ĕ��i�𐶐��E�z�u����
		//JPanel panel = new JPanel() ;
		//setLayout(new BorderLayout()) ;
		//add("Center", panel) ;
		JLabel lb = new JLabel("���ݎ���") ;
		add(lb) ;
		getCurrentTime() ;
		tf = new JTextField("�ߑO " + time, 8) ;
		add(tf) ;
		tf.setEditable(false) ;
		bt = new JButton("��~") ;
		add(bt) ;
	}


	//���i�Ƀ��X�i�[��t���郁�\�b�h
	@Override
	public void addListeners() {
		//�{�^���ɖ����̃��X�i�[��t����
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand( ) ; // �����ꂽ�{�^���̖��O�𓾂�
				if ( cmd.equals("��~") ) {  // ��~�{�^���������ꂽ��
					stopAnime( ) ; // �A�j���X���b�h���~
					bt.setText("�J�n"); // �{�^���̖��O���u�J�n�v�ɐݒ�
				} else {  // �J�n�{�^���������ꂽ��
					startAnime( ) ;  // �A�j���X���b�h���N������
					bt.setText("��~"); // �{�^���̖��O���u��~�v�ɐݒ�
				}
			}
		}) ;
	}


	//�A�j�����J�n���郁�\�b�h
	@Override
	public void startAnime() {
		//�A�N�V���������I�ɋN�����^�C�}�E�X���b�h�𐶐����ċN��
		getCurrentTime() ;
		setDelay(1000) ;
		super.startAnime() ;
	}


	//���ݎ������X�V���郁�\�b�h
	@Override
	public void updateAnime() {
		//���ݎ����i������j���猻�݂̎��E���E�b�����߂�
		String[] array = time.split(":") ;
		int h = Integer.parseInt(array[0]) ;
		int m = Integer.parseInt(array[1]) ;
		int s = Integer.parseInt(array[2]) ;

		//���ݎ�����1�b�𑫂��āA�K�v�ɉ����Č��̌J��オ�菈��
		//����� �ߑO�E�ߌ�̐؂�ւ����s��
		s++ ;
		if (s >= 60) {
			s = 0 ;
			m++ ;
			if (m >= 60) {
				m = 0 ;
				h++ ;
				if (h >= 12) {
					h = 0 ;
					isAm = !isAm ;
				}
			}
		}

		//�V�������݂̎��E���E�b�𕶎���i���ꂼ��2���j�ɂ܂Ƃ߂�
		time = String.format("%02d:%02d:%02d", h, m, s) ;

		//�V�����������e�L�X�g�t�B�[���h�ɐݒ�
		if (isAm)
			tf.setText("�ߑO " + time) ;
		else
			tf.setText("�ߌ� " + time) ;
	}


	@Override
	public void initAnime() { }
} // Clock.java�̏I���