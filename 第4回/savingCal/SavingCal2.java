package savingCal;

import  javax.swing.* ;  //Swing���C�u�����̒񋟂���JFrame�����g���̂�
import  java.awt.* ;  // AWT���C�u�����̒񋟂��郌�C�A�E�g�����g���̂�
import  java.awt.event.* ; // �{�^���������ꂽ�Ƃ��ɔ�������C�x���g��
                            // ��������K�v������̂�
import myGUI.AbstractGUI ;

@SuppressWarnings("serial")
public  class  SavingCal2 extends AbstractGUI  {
     // �@�����̃��\�b�h�Ŏg�����i�̐錾�D
     private JTextField[] textFields ; //0�ԁF�N����  1�ԁF�N��  2�ԁF�ϗ����z
     private JLabel totalLabel ;
     private JButton[] buttons ; //0�ԁF�N���A  1�ԁF�v�Z

     // �e�L�X�g�t�B�[���h�̖��O���L������z��
     private String[]  tfNames ;

     //�{�^���̖��O���L������z��
     private String[] btnNames ;

     //�t�B�[���h�����������郁�\�b�h
     @Override
     public void initialize() {
    	 String[] tempArray = {"�N����(%)�F", "�N���F", "�ϗ����z(�~)�F"} ;
    	 tfNames = tempArray.clone();
    	 String[] tempArray2 = {"�N���A", "�v�Z"} ;
    	 btnNames = tempArray2.clone();
     }


     //���i�𐶐����Ĕz�u���郁�\�b�h
     public void arrangeComponents() {
         // �p�l���ɕ��i��ǉ����邽�߂ɁC
         // ���ɕ��i�̔z�u�@�i���C�A�E�g�j��ݒ肷��K�v������
         GridLayout  myLayout = new GridLayout(5, 2);  // ���i�̔z�u�@�Ƃ���
                                                       // 5�s2��̊i�q�𐶐�
         setLayout(myLayout);  // �p�l���ւ̕��i�z�u��
                               // myLayout�ɐݒ肷��

         //�e�L�X�g�t�B�[���h�̔z��𐶐�
         textFields = new JTextField[tfNames.length] ;

         //�{�^���̔z��𐶐�
    	 buttons = new JButton[btnNames.length] ;

         // �ŏ���3�s�ɕ��i��z�u����
         for (int i = 0 ; i < tfNames.length ; i++) {
        	 add( new JLabel(tfNames[i], JLabel.RIGHT) ) ;
        	 textFields[i] = new JTextField(15) ;
        	 add(textFields[i]) ;
         }

         //4�s�ڂɕ��i�𐶐����Ĕz�u����
         add( new JLabel("���q���ݍ��v�i�~�j: ", JLabel.RIGHT) ) ;
         totalLabel = new JLabel("") ;
         add(totalLabel) ;
         totalLabel.setOpaque(true) ;    // �w�i�F��������悤�ɂ���
         totalLabel.setBackground(Color.YELLOW);  //�w�i�F�����F�ɃZ�b�g
         totalLabel.setForeground(Color.RED) ;  // �O�i�F��ԂɃZ�b�g

         // 5�s�ڂ̕��i�𐶐����Ĕz�u����
         for (int i = 0 ; i < btnNames.length ; i++) {
        	 buttons[i] = new JButton(btnNames[i]) ;
        	 add( buttons[i] ) ;
         }
     }


     //���i�Ƀ��X�i�[��t���郁�\�b�h
     public void addListeners() {
         // �{�^���Ɏ��샊�X�i�[��t����
         addClearButtonListener() ; //�N���A�{�^���Ƀ��X�i�[�t����
         addCalcButtonListener() ; //�v�Z�{�^���Ƀ��X�i�[��t����
         addCursorListener() ; //�{�^���̏�ɃJ�[�\�����������ɂ��̌`��ς��郊�X�i�[��t����
     }


     //�{�^���̏�ɃJ�[�\�����������ɂ��̌`��ς��郊�X�i�[
     private void addCursorListener() {
    	 MouseListener ml = new MouseAdapter() {
    		 public void mouseEntered(MouseEvent e) {
    			 setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)) ;
    		 }
    		 public void mouseExited(MouseEvent e) {
    			 setCursor(Cursor.getDefaultCursor()) ;
    		 }
    	 };
    	 for (int i = 0 ; i < btnNames.length ; i++)
    		 buttons[i].addMouseListener(ml) ;
     }


     //�u�N���A�v�{�^���Ƀ��X�i�[��t���郁�\�b�h
     private void addClearButtonListener() {
    	 if (buttons.length == 0) return ;
         ClearButtonListener listen = new ClearButtonListener( ) ; // ���샊�X�i�[�𐶐�
         buttons[0].addActionListener(listen) ; //�����������X�i�[���N���A�{�^���ɕt����
     }


     //�u�v�Z�v�{�^���Ƀ��X�i�[��t���郁�\�b�h
     private void addCalcButtonListener() {
    	 if (buttons.length <= 1) return ;
         CalcButtonListener  listen = new CalcButtonListener( ) ; // ���샊�X�i�[�𐶐�
         buttons[1].addActionListener(listen) ; // �����������X�i�[���v�Z�{�^���ɕt����
     }


     // �B�u�N���A�v�{�^���������ꂽ�Ƃ��̏������L�q����N���X
     private  class  ClearButtonListener  implements  ActionListener {
          public void actionPerformed(ActionEvent e) {
        	  for (int i = 0 ; i < textFields.length ; i++)
        		  textFields[i].setText("") ;  //�e�L�X�g�t�B�[���h���N���A
              totalLabel.setText("") ; //���q���ݐϗ����v�z���N���A
          }
     }


     // �B�u�v�Z�v�{�^���������ꂽ�Ƃ��̏������L�q����N���X
     private  class  CalcButtonListener  implements  ActionListener {
         public void actionPerformed(ActionEvent e) {
        	 try {
        		 // �e�L�X�g�E�t�B�[���h�̒l����荞��
        		 double rate = Double.parseDouble(textFields[0].getText( )) ;
        		 int  years = Integer.parseInt(textFields[1].getText( )) ;
        		 int  monthsaving = Integer.parseInt(textFields[2].getText( ));

        		 // ���ɁC���q���ݐϗ����v�z���v�Z���ĕ\��
        		 int     months = years * 12 ;        //�ϗ���
        		 double  monthRate = 1.0 + rate / 1200.0;  //������
        		 double  total = 0;                        //���v���z
        		 for (int  i = 0 ; i < months ; i++) {
        			 total += monthsaving ;
        			 total *= monthRate ;
        		 }

        		 // ���v�����x���Ƃ��ĕ\������
        		 totalLabel.setText(String.valueOf(total)) ;
        	 } catch ( NumberFormatException ex ) {
                 // �����ɗ�Oex�����������Ƃ��̏���������
                 JOptionPane.showMessageDialog(null , "���̓G���[") ;
        	 }
         }
    }


     //accessor���\�b�h
     public JTextField[] getTextFields() {
    	 return textFields ;
     }
     public JTextField getTextField(int i) {
    	 if (i >= 0 && i < textFields.length)
    		 return textFields[i] ;
    	 else return null ;
     }
     public JButton[] getButtons() {
    	 return buttons ;
     }
     public JButton getButton(int i) {
    	 if (i >= 0 && i < buttons.length)
    		 return buttons[i] ;
    	 else return null ;
     }
     public JLabel getTotalLabel() {
    	 return totalLabel ;
     }
     public void setTextField(int i, JTextField tf) {
    	 if (i >= 0 && i < textFields.length)
    		 this.textFields[i] = tf ;
     }
     public void setButton(int i, JButton btn) {
    	 if (i >= 0 && i < buttons.length)
    		 this.buttons[i] = btn ;
     }
     public void setTotalLabel(JLabel label) {
    	 this.totalLabel = label ;
     }
}
