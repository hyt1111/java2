package forStudent;

import javax.swing.*;  //Swing���C�u�����̒񋟂���JPanel�����g���̂�
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.* ;  // AWT���C�u�����̒񋟂���BorderLayout�����g���̂�
import java.awt.event.*; //ActionListener�����g���̂�
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

@SuppressWarnings("serial")
public class SimpleAnimeS extends anime.AbstractAnime {
	// �@ ���ׂẴ��\�b�h������N���X�Ŏg����悤�ȕϐ��̐錾
	private String words[][] = {
		{"We", "You", "They", "Boys", "Girls"},
		{"like", "hate", "play"},
		{"baseball", "basketball", "pingpong", "chess"}
	};  // ���E�q��E�ړI����o���邽�߂�2�����z��
	private int index ;  // ���ɕ\������P���index�i0�ȏ�2�ȉ��j���o����ϐ�
	private JButton ctrlBtn ;  // �A�j���[�V�������R���g���[������{�^��
	private LinkedList<String> sentence ; //���͂̒P����L�����郊�X�g
	private Random rand ; //������������L������
	private JSlider speedSlider ; //�A�j���̑��x�𐧌䂷��X���C�_

	@Override
	public void initAnime() {
		index = 0 ;//�ŏ��ɐ�������͎̂��
		sentence = new LinkedList<String>() ; //��̃��X�g���쐬
		rand = new Random() ; //������������쐬
	}

	//���i�𐶐����Ĕz�u���郁�\�b�h
	@Override
	public void arrangeComponents() {
		setLayout(new BorderLayout( ) ) ; // �A�v���b�g�̃��C�A�E�g��ݒ肷��
		JPanel animePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g); // �e�N���X��paintComponent���\�b�h�ŕ`�������
				drawAnime(g) ; //���͂�`�����\�b�h���Ăяo��
			}
		}; // �摜�\���p�l���𐶐�����
		add("Center", animePanel ) ;  // �摜�\���p�l�����A�v���b�g�̒����ɔz�u
		animePanel.setBackground(Color.YELLOW) ;

		JPanel buttonPanel = new JPanel( ) ; // �{�^���p�l���𐶐�
		add("South", buttonPanel ) ; // �{�^���p�l�����A�v���b�g�̓쑤�ɔz�u
		ctrlBtn = new JButton("��~") ;  // �R���g���[���{�^���𐶐�
		buttonPanel.add( ctrlBtn ) ; // �{�^���p�l���ɃR���g���[���{�^����z�u

		//�k���̈�̃Z�b�g�A�b�v
		speedSlider = new JSlider(SwingConstants.HORIZONTAL, 1, 1000, 200) ;
		JPanel speedPanel = new JPanel(); // NORTH �p�̃p�l��
		add("North", speedPanel) ;
		//speedPanel.setLayout(new FlowLayout());
		speedPanel.setBackground(Color.GREEN);
		JLabel speedLabel = new JLabel("���x�F");
		speedLabel.setForeground (Color.RED);
		speedPanel.add(speedLabel) ;
		speedPanel.add(new JLabel("��")) ;
		speedPanel.add(speedSlider) ;
		speedPanel.add(new JLabel("��")) ;
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

		//speed slider �Ƀ��X�i�[��t����
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(final ChangeEvent e) {
				setDelay(speedSlider.getValue()); // �������ꂽ��\�����Ԃ�ύX
			}
		});
	}


	@Override
	public void updateAnime( ) { //�A�j����1�X�e�b�v���s�����\�b�h
		if (index == words.length) {//���͂�����������C�N���A����D
			index = 0 ;
			sentence.clear() ;
		}

		//���͂̎��̌�������_���ɐ������āC���͂ɒǉ��D
		int i = rand.nextInt(words[index].length) ;
		sentence.add(words[index][i]) ;

		index = (index + 1) ; //���̌�̐�����
	}


	//���͂�`�����\�b�h
	public void drawAnime(Graphics g) {
		// ���͂̒P���1�̕�����ɂ܂Ƃ߂�
		String result = "" ;
		for (String str : sentence)
			result = result + " " + str ;

		Graphics2D g2 = (Graphics2D) g ; //Graphics��Graphics2D�ɕϊ�

		//���͂�`��
		double y = getHeight( ) / 2 ; //�`��p�l���̏c�����̍��W���v�Z
		Font font = new Font( "SansSerif", Font.BOLD, 36) ; // �t�H���g�𐶐�.
		g2.setFont( font ) ; //�t�H���g��ݒ�
		g2.drawString( result, 10, (int) y) ; // �������`��.
	}


	//getter method
	public JButton getCtrlBtn() {
		return ctrlBtn ;
	}
} // SimpleAnimeS.java�̏I���