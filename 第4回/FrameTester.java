package testers ;

//import savingCal.* ;
//import forStudent.* ;
//import painter.* ;

import java.awt.EventQueue;

import  javax.swing.JFrame ;

public class FrameTester {
	//インスタンスの生成を禁止するためのコンストラクタ
	private FrameTester( ) { }

	public static void main(String arg[ ]) {
		EventQueue.invokeLater ( new Runnable( ) {
			public void run( ) {
				JFrame  frame = new JFrame("アニメ") ; // フレームを生成
				frame.setSize(500, 400) ;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				forStudent.SimpleAnime2S3 c = null ;
				try {
					c = new forStudent.SimpleAnime2S3() ;
				} catch (Exception ex) {
					ex.printStackTrace() ;
				}

				if (c != null) {
					frame.add(c) ;
					c.start() ;
				}
				//frame.pack() ;
				frame.setLocationByPlatform( true ) ;
				frame.setVisible( true ) ;
			}
		}) ;
	}
}