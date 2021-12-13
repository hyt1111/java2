package savingCal;

import  javax.swing.* ;  //Swingライブラリの提供するJFrame等を使うので
import  java.awt.* ;  // AWTライブラリの提供するレイアウト等を使うので
import  java.awt.event.* ; // ボタンが押されたときに発生するイベントを
                            // 処理する必要があるので
import myGUI.AbstractGUI ;

@SuppressWarnings("serial")
public  class  SavingCal2 extends AbstractGUI  {
     // ①複数のメソッドで使う部品の宣言．
     private JTextField[] textFields ; //0番：年利率  1番：年数  2番：積立月額
     private JLabel totalLabel ;
     private JButton[] buttons ; //0番：クリア  1番：計算

     // テキストフィールドの名前を記憶する配列
     private String[]  tfNames ;

     //ボタンの名前を記憶する配列
     private String[] btnNames ;

     //フィールドを初期化するメソッド
     @Override
     public void initialize() {
    	 String[] tempArray = {"身長（cm）：", "体重（kg）：", "性別（男：１女：２）："} ;
    	 tfNames = tempArray.clone();
    	 String[] tempArray2 = {"クリア", "計算"} ;
    	 btnNames = tempArray2.clone();
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
         textFields = new JTextField[tfNames.length] ;

         //ボタンの配列を生成
    	 buttons = new JButton[btnNames.length] ;

         // 最初の3行に部品を配置する
         for (int i = 0 ; i < tfNames.length ; i++) {
        	 add( new JLabel(tfNames[i], JLabel.RIGHT) ) ;
        	 textFields[i] = new JTextField(15) ;
        	 add(textFields[i]) ;
         }

         //4行目に部品を生成して配置する
         add( new JLabel("肥満度（%）: ", JLabel.RIGHT) ) ;
         totalLabel = new JLabel("") ;
         add(totalLabel) ;
         totalLabel.setOpaque(true) ;    // 背景色が見えるようにする
         totalLabel.setBackground(Color.YELLOW);  //背景色を黄色にセット
         totalLabel.setForeground(Color.RED) ;  // 前景色を赤にセット

         // 5行目の部品を生成して配置する
         for (int i = 0 ; i < btnNames.length ; i++) {
        	 buttons[i] = new JButton(btnNames[i]) ;
        	 add( buttons[i] ) ;
         }
     }


     //部品にリスナーを付けるメソッド
     public void addListeners() {
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


     //「クリア」ボタンにリスナーを付けるメソッド
     private void addClearButtonListener() {
    	 if (buttons.length == 0) return ;
         ClearButtonListener listen = new ClearButtonListener( ) ; // 自作リスナーを生成
         buttons[0].addActionListener(listen) ; //生成したリスナーをクリアボタンに付ける
     }


     //「計算」ボタンにリスナーを付けるメソッド
     private void addCalcButtonListener() {
    	 if (buttons.length <= 1) return ;
         CalcButtonListener  listen = new CalcButtonListener( ) ; // 自作リスナーを生成
         buttons[1].addActionListener(listen) ; // 生成したリスナーを計算ボタンに付ける
     }


     // ③「クリア」ボタンが押されたときの処理を記述するクラス
     private  class  ClearButtonListener  implements  ActionListener {
          public void actionPerformed(ActionEvent e) {
        	  for (int i = 0 ; i < textFields.length ; i++)
        		  textFields[i].setText("") ;  //テキストフィールドをクリア
              totalLabel.setText("") ; //利子込み積立合計額をクリア
          }
     }



     // ③「計算」ボタンが押されたときの処理を記述するクラス
     private  class  CalcButtonListener  implements  ActionListener {
         public void actionPerformed(ActionEvent e) {
        	 try {
        		 // テキスト・フィールドの値を取り込む
			 double height = Double.parseDouble(textFields[0].getText( )) ;
        		 double weight = Double.parseDouble(textFields[1].getText( )) ;
        		 double gender = Double.parseDouble(textFields[2].getText( ));
			 //性別確認
			 if (gender<1 || gender!=1 || gender!=2)
				System.exit(0);
              		 double ave_weight  ;        //標準体重
			 if (gender == 1)
				avg_weight = (height - 80.0) * 0.7;
			 else
				avg_weight = (height - 70.0) * 0.6;
			//肥満度
        		 double obesity = (weight-avg_weight)/weight*100;

        		 // 合計をラベルとして表示する
        		 totalLabel.setText(String.valueOf(obesity)) ;
        	 } catch ( NumberFormatException ex ) {
                 // ここに例外exが発生したときの処理を書く
                 JOptionPane.showMessageDialog(null , "入力エラー") ;
        	 }
         }
    }


     //accessorメソッド
     public JTextField[] getTextFields() {
    	 return textFields ;
     }
     public JTextField getTextField(int i) {
    	 if (i >= 0 && i < textFields.length)
    		 return textFields[i] ;
    	 else return null ;
     }
     public JButton[] getButtons() {
    	 return buttons ;
     }
     public JButton getButton(int i) {
    	 if (i >= 0 && i < buttons.length)
    		 return buttons[i] ;
    	 else return null ;
     }
     public JLabel getTotalLabel() {
    	 return totalLabel ;
     }
     public void setTextField(int i, JTextField tf) {
    	 if (i >= 0 && i < textFields.length)
    		 this.textFields[i] = tf ;
     }
     public void setButton(int i, JButton btn) {
    	 if (i >= 0 && i < buttons.length)
    		 this.buttons[i] = btn ;
     }
     public void setTotalLabel(JLabel label) {
    	 this.totalLabel = label ;
     }
}
