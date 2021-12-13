package savingCal;

import javax.swing.JButton;
import mediator.Colleague;
import mediator.Mediator;

@SuppressWarnings("serial")
public class ColleagueButton extends JButton implements Colleague {
	private Mediator mediator ;//Mediator���L�����邽�߂̃t�B�[���h

	public ColleagueButton(String caption) {
		super(caption) ;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator ;
	}

	//Mediator����L���^�������w�������
	public void consultMediator( ) {
		mediator.colleagueChanged(this) ;
	}
}
