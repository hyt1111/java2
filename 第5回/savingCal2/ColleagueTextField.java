package savingCal;

import javax.swing.JTextField;
import mediator.Colleague;
import mediator.Mediator;

@SuppressWarnings("serial")
public class ColleagueTextField extends JTextField implements Colleague {
	private Mediator mediator ; //Mediatorを記憶するためのフィールド

	//コンストラクタ
	public ColleagueTextField(int columns) {
		super(columns) ;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator ;
	}

	//Mediatorから有効／無効が指示される
	public void consultMediator( ) {
		mediator.colleagueChanged(this) ;
	}
}