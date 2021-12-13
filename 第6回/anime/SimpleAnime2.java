package anime ;

import javax.swing.*;  //Swing���C�u�����̒񋟂���JPanel�����g���̂�
import java.awt.* ;  // AWT���C�u�����̒񋟂���BorderLayout�����g���̂�
import java.awt.event.*; //ActionListener�����g���̂�
import java.io.File;

@SuppressWarnings("serial")
public class SimpleAnime2 extends AbstractAnime {
	// �@ ���ׂẴ��\�b�h������N���X�Ŏg����悤�ȕϐ��̐錾
	private Image imgs[ ] ;  // �S�摜���o���邽�߂̔z��
	private int img_index ;  // ���ɕ\������摜���o����ϐ�
	private JButton ctrlBtn ;  // �A�j���[�V�������R���g���[������{�^��

	@Override
	public void initAnime() {
		String[] fileNames = new String[6] ;
		for (int i = 0 ; i < fileNames.length ; i++) {
			fileNames[i] = "bin/anime/java" + i + ".gif" ;
			if (!(new File(fileNames[i]).exists())) {
				System.out.println("File " + fileNames[i] + ": not found") ;
				System.exit(0) ;
			}
		}
		try {
			imgs = loadImages(fileNames) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//���i�𐶐����Ĕz�u���郁�\�b�h
	@Override
	public void arrangeComponents() {
		setLayout(new BorderLayout( ) ) ; // �A�v���b�g�̃��C�A�E�g��ݒ肷��
		JPanel animePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g); // �e�N���X��paintComponent���\�b�h�ŕ`�������
				drawAnime(g) ; //�}�`��`�����\�b�h���Ăяo��
			}
		}; // �摜�\���p�l���𐶐�����
		add("Center", animePanel ) ;  // �摜�\���p�l�����A�v���b�g�̒����ɔz�u
		JPanel buttonPanel = new JPanel( ) ; // �{�^���p�l���𐶐�
		add("South", buttonPanel ) ; // �{�^���p�l�����A�v���b�g�̓쑤�ɔz�u
		ctrlBtn = new JButton("��~") ;  // �R���g���[���{�^���𐶐�
		buttonPanel.add( ctrlBtn ) ; // �{�^���p�l���ɃR���g���[���{�^����z�u
	}


	//���i�Ƀ��X�i�[��t���郁�\�b�h
	@Override
	public void addListeners() {
		// �{�^���Ɏ��샊�X�i�[��t����
		ctrlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand( ) ; // �����ꂽ�{�^���̖��O�𓾂�
				if ( cmd.equals("��~") ) {  // ��~�{�^���������ꂽ��
					ctrlBtn.setText("�J�n"); // �{�^���̖��O���u�J�n�v�ɐݒ�
					stopAnime( ) ; // �A�j���X���b�h���~
				} else {  // �J�n�{�^���������ꂽ��
					ctrlBtn.setText("��~"); // �{�^���̖��O���u��~�v�ɐݒ�
					startAnime( ) ;  // �A�j���X���b�h���N������
				}
			}
		}) ;
	}


	@Override
	public void updateAnime( ) { //�A�j����1�X�e�b�v���s�����\�b�h
		img_index = (img_index + 1) % imgs.length ;
	}

	//�}�`��`�����\�b�h
	public void drawAnime(Graphics g) {
		Dimension d = getSize( ); //���́i�`��j�p�l���̃T�C�Y�𓾂�

		// �摜�\���p�l����t�Ɏ��̉摜��\������
		g.drawImage( imgs[img_index], 0, 0, d.width, d.height, this );
	}

	//getter method
	public JButton getCtrlBtn() {
		return ctrlBtn ;
	}
} // SimpleAnime2.java�̏I���