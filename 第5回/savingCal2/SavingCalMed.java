package savingCal;

import  javax.swing.* ;  //Swing���C�u�����̒񋟂���JFrame�����g���̂�
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import  java.awt.* ;  // AWT���C�u�����̒񋟂��郌�C�A�E�g�����g���̂�
import  java.awt.event.* ; // �{�^���������ꂽ�Ƃ��ɔ�������C�x���g��
                            // ��������K�v������̂�
import myGUI.AbstractGUI ;
import mediator.Mediator;
import mediator.Colleague;

@SuppressWarnings("serial")
public class SavingCalMed extends AbstractGUI  {
     // �@�����̃��\�b�h�Ŏg�����i�̐錾�D
     private ColleagueTextField[] textFields ;
     private ColleagueButton[] buttons ;
     private JLabel totalLabel ;

     // �e�L�X�g�t�B�[���h�̖��O���L������z��
     private String[]  tfNames ;

     //�{�^���̖��O���L������z��
     private String[] btnNames ;

     //mediator�̐錾
     private Mediator mediator ;

     //�t�B�[���h�����������郁�\�b�h
     @Override
     public void initialize() {
    	 String[] tempArray = {"�N����(%)�F", "�N���F", "�ϗ����z(�~)�F"} ;
    	 tfNames = tempArray.clone();
    	 String[] tempArray2 = {"�N���A", "�v�Z"} ;
    	 btnNames = tempArray2.clone();

    	 //Mediator�𐶐�
    	 mediator = new Mediator( ) {
    		 public void colleagueChanged(Colleague colleague) {
    			 //���݂̏�Ԃ𒲂ׂĂ���C�ݒ��ς���
    			 analyzeState(colleague) ;
    		 }
    	 };
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
         textFields = new ColleagueTextField[tfNames.length] ;

         //�{�^���̔z��𐶐�
    	 buttons = new ColleagueButton[btnNames.length] ;

         // �ŏ���3�s�ɕ��i��z�u����
         for (int i = 0 ; i < tfNames.length ; i++) {
        	 add( new JLabel(tfNames[i], JLabel.RIGHT) ) ;
        	 textFields[i] = new ColleagueTextField(15) ;
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
        	 buttons[i] = new ColleagueButton(btnNames[i]) ;
        	 add( buttons[i] ) ;
         }

         //mediator�𐶐����āCcolleagues�ɐݒ�
         setMediator() ;
     }


     //mediator��colleagues�ɐݒ�
     public void setMediator() {
    	 //Colleague��Mediator��ݒ肷��
    	 for (int i = 0 ; i < buttons.length ; i++)
    		 buttons[i].setMediator(mediator) ;
    	 for (int i = 0 ; i < buttons.length ; i++)
    		 textFields[i].setMediator(mediator) ;

    	 //�����邩�̏����ݒ�D����̓N���A�{�^���������ꂽ�Ƃ��̏�ԂƓ����D
    	 buttons[0].consultMediator() ;
     }


     //���i�̏�Ԃ𕪐͂��āC�����邩�ǂ����𔻒肷��
     public void analyzeState(Colleague colleague) {
		if (colleague == buttons[0]) {
			for (int i = 0 ; i <textFields.length ; i++)
				textFields[i].setEnabled(true);
			buttons[1].setEnabled(false);
		} else if (colleague == buttons[1]) {
			for (int i = 0 ; i < textFields.length ; i++)
				textFields[i].setEnabled(false);
			buttons[1].setEnabled(false);
		} else {
			int i = 0 ;
			int numberOfFields = textFields.length ;
			for ( ; i < numberOfFields ; i++)
				if (colleague == textFields[i] ) break ;
			if ( i == numberOfFields ) return ;
			for ( ; i < numberOfFields ; i++)
				if (textFields[i].getText().isEmpty() ) break ;
			if ( i == numberOfFields ) {
				buttons[1].setEnabled(true);
			} else {
				buttons[1].setEnabled(false);
			}
		}
     }


     //���i�Ƀ��X�i�[��t���郁�\�b�h
     public void addListeners() {
    	 //�e�L�X�g�t�B�[���h�Ɏ��샊�X�i�[��t����
    	 addTextFieldListener() ;

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

     //�e�L�X�g�t�B�[���h�Ƀ��X�i�[��t���郁�\�b�h
     private void addTextFieldListener() {
 		DocumentListener al = new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				mediator.colleagueChanged(textFields[0]);
			}
			public void removeUpdate(DocumentEvent e) {
				mediator.colleagueChanged(textFields[0]);
			}
			public void insertUpdate(DocumentEvent e) {
				mediator.colleagueChanged(textFields[0]);
			}
		} ;
		for (int i = 0 ; i < textFields.length ; i++) {
			textFields[i].getDocument().addDocumentListener(al) ;
		}
     }


     //�u�N���A�v�{�^���Ƀ��X�i�[��t���郁�\�b�h
     private void addClearButtonListener() {
    	 if (buttons.length == 0) return ;
         buttons[0].addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 //�N���A�{�^���������ꂽ��mediator�ɕ�
                 buttons[0].consultMediator();

                 for (int i = 0 ; i < textFields.length ; i++)
                	 textFields[i].setText("") ;  //�e�L�X�g�t�B�[���h���N���A
                 totalLabel.setText("") ; //���q���ݐϗ����v�z���N���A
        	 }
         }) ; //�����������X�i�[���N���A�{�^���ɕt����
     }


     //�u�v�Z�v�{�^���Ƀ��X�i�[��t���郁�\�b�h
     private void addCalcButtonListener() {
    	 if (buttons.length <= 1) return ;
         buttons[1].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 try {
            		 //�v�Z�{�^���������ꂽ��mediator�ɕ�
                     buttons[1].consultMediator();

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
            	 } catch (NumberFormatException ex) {
            		 JOptionPane.showMessageDialog(null , "���̓G���[") ;
            	 }
             }
         }) ; // �����������X�i�[���v�Z�{�^���ɕt����
     }


     //accessor���\�b�h
     public ColleagueTextField[] getTextFields() {
    	 return textFields ;
     }
     public ColleagueTextField getTextField(int i) {
    	 if (i >= 0 && i < textFields.length)
    		 return textFields[i] ;
    	 else return null ;
     }
     public ColleagueButton[] getButtons() {
    	 return buttons ;
     }
     public ColleagueButton getButton(int i) {
    	 if (i >= 0 && i < buttons.length)
    		 return buttons[i] ;
    	 else return null ;
     }
     public JLabel getTotalLabel() {
    	 return totalLabel ;
     }
     public void setTextField(int i, ColleagueTextField tf) {
    	 if (i >= 0 && i < textFields.length)
    		 this.textFields[i] = tf ;
     }
     public void setButton(int i, ColleagueButton btn) {
    	 if (i >= 0 && i < buttons.length)
    		 this.buttons[i] = btn ;
     }
     public void setTotalLabel(JLabel label) {
    	 this.totalLabel = label ;
     }
}
