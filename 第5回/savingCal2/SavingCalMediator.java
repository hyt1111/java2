package savingCal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mediator.MyMediator ;

@SuppressWarnings("serial")
public class SavingCalMediator extends SavingCal3 {
	//部品の状態を集中管理するMediator
	private MyMediator mediator ;

	@Override
	public void initialize() {
		super.initialize();

		//mediatorを生成する
		mediator = new MyMediator() {
			public void colleagueChanged(JComponent component) {
				//現在の状態を調べてから，設定を変える
				analyzeState(component) ;
			}
		} ;
	}

	@Override
	public void arrangeComponents() {
		super.arrangeComponents();

		//押せるかの初期設定．これはクリアボタンが押されたときの状態と同じ．
		mediator.colleagueChanged(getButton(0));
	}


	//部品の状態を分析して，押せるかどうかを判定する
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


	//部品にリスナーを追加
	@Override
	public void addListeners() {
		super.addListeners();

		//テキストフィールドに自作リスナーを付ける
		addTextFieldListener() ;

		//ボタンにもう１つリスナーを付ける
		for (int i = 0 ; i < getButtons().length ; i++)
			getButton(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Mediatorに状態の変化を伝える
					mediator.colleagueChanged((JComponent)e.getSource());
				}
			}) ;
	}


	//テキストフィールドに｢変化認識リスナー」を付けるメソッド
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

	//accessorメソッド
	public MyMediator getMediator() {
		return mediator ;
	}
	public void setMediator(MyMediator mediator) {
		this.mediator = mediator ;
	}
}
