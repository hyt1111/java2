package savingCal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mediator.MyMediator ;

@SuppressWarnings("serial")
public class SavingCalMediator extends SavingCal3 {
	//���i�̏�Ԃ��W���Ǘ�����Mediator
	private MyMediator mediator ;

	@Override
	public void initialize() {
		super.initialize();

		//mediator�𐶐�����
		mediator = new MyMediator() {
			public void colleagueChanged(JComponent component) {
				//���݂̏�Ԃ𒲂ׂĂ���C�ݒ��ς���
				analyzeState(component) ;
			}
		} ;
	}

	@Override
	public void arrangeComponents() {
		super.arrangeComponents();

		//�����邩�̏����ݒ�D����̓N���A�{�^���������ꂽ�Ƃ��̏�ԂƓ����D
		mediator.colleagueChanged(getButton(0));
	}


	//���i�̏�Ԃ𕪐͂��āC�����邩�ǂ����𔻒肷��
	public void analyzeState(JComponent component) {
		if (component == getButton(0)) {
			for (int i = 0 ; i < getTextFields().length ; i++)
				getTextField(i).setEnabled(true);
			getButton(1).setEnabled(false);
		} else if (component == getButton(1)) {
			for (int i = 0 ; i < getTextFields().length ; i++)
				getTextField(i).setEnabled(false);
			getButton(1).setEnabled(false);
		} else {
			int i = 0 ;
			int numberOfFields = getTextFields().length ;
			for ( ; i < numberOfFields ; i++)
				if (component == getTextField(i) ) break ;
			if ( i == numberOfFields ) return ;
			for ( ; i < numberOfFields ; i++)
				if (getTextField(i).getText().isEmpty() ) break ;
			if ( i == numberOfFields ) {
				getButton(1).setEnabled(true);
			} else {
				getButton(1).setEnabled(false);
			}
		}
	}


	//���i�Ƀ��X�i�[��ǉ�
	@Override
	public void addListeners() {
		super.addListeners();

		//�e�L�X�g�t�B�[���h�Ɏ��샊�X�i�[��t����
		addTextFieldListener() ;

		//�{�^���ɂ����P���X�i�[��t����
		for (int i = 0 ; i < getButtons().length ; i++)
			getButton(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Mediator�ɏ�Ԃ̕ω���`����
					mediator.colleagueChanged((JComponent)e.getSource());
				}
			}) ;
	}


	//�e�L�X�g�t�B�[���h�ɢ�ω��F�����X�i�[�v��t���郁�\�b�h
	private void addTextFieldListener() {
		DocumentListener al = new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				mediator.colleagueChanged(getTextField(0));
			}
			public void removeUpdate(DocumentEvent e) {
				mediator.colleagueChanged(getTextField(0));
			}
			public void insertUpdate(DocumentEvent e) {
				mediator.colleagueChanged(getTextField(0));
			}
		} ;
		for (int i = 0 ; i < getTextFields().length ; i++) {
			getTextField(i).getDocument().addDocumentListener(al) ;
		}
	}

	//accessor���\�b�h
	public MyMediator getMediator() {
		return mediator ;
	}
	public void setMediator(MyMediator mediator) {
		this.mediator = mediator ;
	}
}
