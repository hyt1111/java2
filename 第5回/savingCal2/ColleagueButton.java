package savingCal;

import javax.swing.JButton;
import mediator.Colleague;
import mediator.Mediator;

@SuppressWarnings("serial")
public class ColleagueButton extends JButton implements Colleague {
	private Mediator mediator ;//Mediatorを記憶するためのフィールド

	public ColleagueButton(String caption) {
		super(caption) ;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator ;
	}

	//Mediatorから有効／無効が指示される
	public void consultMediator( ) {
		mediator.colleagueChanged(this) ;
	}
}
