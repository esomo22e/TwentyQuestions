package QuestionsGUI;

import javax.swing.JFrame;



public class QuestionsApplication {
	/**
	 * show an Ice cream shop
	 **/
	//private  static BinTree expTree;
	
	public static void main( String[] args )
	{
			// create a new JFrame to hold IceCreamPanel
		JFrame icFrame = new JFrame();
		
		// set size
		icFrame.setSize( 900, 900 );

		// create an IceCreamPanel and add it
		icFrame.add( new QuestionsPanel() );

		// exit normally on closing the window
		icFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		icFrame.setVisible( true );
	}
}
