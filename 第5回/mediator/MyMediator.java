package mediator;

import javax.swing.JComponent;

public interface MyMediator {
	//管理下の部品の状態を管理するためのメソッド
	public abstract void colleagueChanged(JComponent colleague) ;
}