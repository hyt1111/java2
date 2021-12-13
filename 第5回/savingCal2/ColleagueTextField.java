package savingCal;

import javax.swing.JTextField;
import mediator.Colleague;
import mediator.Mediator;

@SuppressWarnings("serial")
public class ColleagueTextField extends JTextField implements Colleague {
	private Mediator mediator ; //Mediator���L�����邽�߂̃t�B�[���h

	//�R���X�g���N�^
	public ColleagueTextField(int columns) {
		super(columns) ;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator ;
	}

	//Mediator����L���^�������w�������
	public void consultMediator( ) {
		mediator.colleagueChanged(this) ;
	}
}