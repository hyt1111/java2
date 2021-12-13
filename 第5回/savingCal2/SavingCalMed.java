package savingCal;

import  javax.swing.* ;  //Swingライブラリの提供するJFrame等を使うので
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import  java.awt.* ;  // AWTライブラリの提供するレイアウト等を使うので
import  java.awt.event.* ; // ボタンが押されたときに発生するイベントを
                            // 処理する必要があるので
import myGUI.AbstractGUI ;
import mediator.Mediator;
import mediator.Colleague;

@SuppressWarnings("serial")
public class SavingCalMed extends AbstractGUI  {
     // ①複数のメソッドで使う部品の宣言．
     private ColleagueTextField[] textFields ;
     private ColleagueButton[] buttons ;
     private JLabel totalLabel ;

     // テキストフィールドの名前を記憶する配列
     private String[]  tfNames ;

     //ボタンの名前を記憶する配列
     private String[] btnNames ;

     //mediatorの宣言
     private Mediator mediator ;

     //フィールドを初期化するメソッド
     @Override
     public void initialize() {
    	 String[] tempArray = {"年利率(%)：", "年数：", "積立月額(円)："} ;
    	 tfNames = tempArray.clone();
    	 String[] tempArray2 = {"クリア", "計算"} ;
    	 btnNames = tempArray2.clone();

    	 //Mediatorを生成
    	 mediator = new Mediator( ) {
    		 public void colleagueChanged(Colleague colleague) {
    			 //現在の状態を調べてから，設定を変える
    			 analyzeState(colleague) ;
    		 }
    	 };
     }

     //部品を生成して配置するメソッド
     public void arrangeComponents() {
         // パネルに部品を追加するために，
         // 次に部品の配置法（レイアウト）を設定する必要がある
         GridLayout  myLayout = new GridLayout(5, 2);  // 部品の配置法として
                                                       // 5行2列の格子を生成
         setLayout(myLayout);  // パネルへの部品配置を
                               // myLayoutに設定する

         //テキストフィールドの配列を生成
         textFields = new ColleagueTextField[tfNames.length] ;

         //ボタンの配列を生成
    	 buttons = new ColleagueButton[btnNames.length] ;

         // 最初の3行に部品を配置する
         for (int i = 0 ; i < tfNames.length ; i++) {
        	 add( new JLabel(tfNames[i], JLabel.RIGHT) ) ;
        	 textFields[i] = new ColleagueTextField(15) ;
        	 add(textFields[i]) ;
         }

         //4行目に部品を生成して配置する
         add( new JLabel("利子込み合計（円）: ", JLabel.RIGHT) ) ;
         totalLabel = new JLabel("") ;
         add(totalLabel) ;
         totalLabel.setOpaque(true) ;    // 背景色が見えるようにする
         totalLabel.setBackground(Color.YELLOW);  //背景色を黄色にセット
         totalLabel.setForeground(Color.RED) ;  // 前景色を赤にセット

         // 5行目の部品を生成して配置する
         for (int i = 0 ; i < btnNames.length ; i++) {
        	 buttons[i] = new ColleagueButton(btnNames[i]) ;
        	 add( buttons[i] ) ;
         }

         //mediatorを生成して，colleaguesに設定
         setMediator() ;
     }


     //mediatorをcolleaguesに設定
     public void setMediator() {
    	 //ColleagueにMediatorを設定する
    	 for (int i = 0 ; i < buttons.length ; i++)
    		 buttons[i].setMediator(mediator) ;
    	 for (int i = 0 ; i < buttons.length ; i++)
    		 textFields[i].setMediator(mediator) ;

    	 //押せるかの初期設定．これはクリアボタンが押されたときの状態と同じ．
    	 buttons[0].consultMediator() ;
     }


     //部品の状態を分析して，押せるかどうかを判定する
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


     //部品にリスナーを付けるメソッド
     public void addListeners() {
    	 //テキストフィールドに自作リスナーを付ける
    	 addTextFieldListener() ;

         // ボタンに自作リスナーを付ける
         addClearButtonListener() ; //クリアボタンにリスナー付ける
         addCalcButtonListener() ; //計算ボタンにリスナーを付ける
         addCursorListener() ; //ボタンの上にカーソルが来た時にその形を変えるリスナーを付ける
     }


     //ボタンの上にカーソルが来た時にその形を変えるリスナー
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

     //テキストフィールドにリスナーを付けるメソッド
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


     //「クリア」ボタンにリスナーを付けるメソッド
     private void addClearButtonListener() {
    	 if (buttons.length == 0) return ;
         buttons[0].addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 //クリアボタンが押されたらmediatorに報告
                 buttons[0].consultMediator();

                 for (int i = 0 ; i < textFields.length ; i++)
                	 textFields[i].setText("") ;  //テキストフィールドをクリア
                 totalLabel.setText("") ; //利子込み積立合計額をクリア
        	 }
         }) ; //生成したリスナーをクリアボタンに付ける
     }


     //「計算」ボタンにリスナーを付けるメソッド
     private void addCalcButtonListener() {
    	 if (buttons.length <= 1) return ;
         buttons[1].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 try {
            		 //計算ボタンが押されたらmediatorに報告
                     buttons[1].consultMediator();

            		 // テキスト・フィールドの値を取り込む
            		 double rate = Double.parseDouble(textFields[0].getText( )) ;
            		 int  years = Integer.parseInt(textFields[1].getText( )) ;
            		 int  monthsaving = Integer.parseInt(textFields[2].getText( ));

            		 // 次に，利子込み積立合計額を計算して表示
            		 int     months = years * 12 ;        //積立回数
            		 double  monthRate = 1.0 + rate / 1200.0;  //月利率
            		 double  total = 0;                        //合計金額
            		 for (int  i = 0 ; i < months ; i++) {
            			 total += monthsaving ;
            			 total *= monthRate ;
            		 }

            		 // 合計をラベルとして表示する
            		 totalLabel.setText(String.valueOf(total)) ;
            	 } catch (NumberFormatException ex) {
            		 JOptionPane.showMessageDialog(null , "入力エラー") ;
            	 }
             }
         }) ; // 生成したリスナーを計算ボタンに付ける
     }


     //accessorメソッド
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
