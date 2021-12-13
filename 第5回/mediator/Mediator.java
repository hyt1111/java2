package mediator;

public interface Mediator {
	//管理下の部品の状態を管理するためのメソッド
	public abstract void colleagueChanged(Colleague colleague) ;
}