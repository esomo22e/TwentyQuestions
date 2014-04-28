package QuestionsGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

import DataStructures.CommutativeExpressionReader;

import ds.BinTree;
import ds.BinTreeNode;
import ds.DefaultBinTreeNode;


public class QuestionsPanel extends JPanel implements ActionListener{

	/**Instances for my Button**/
	private JButton noButton;
	private JButton yesButton;
	private JButton colorButton;
	/**Instances for the text used**/
	private JLabel introLabel;
	private JLabel heroLabel;
	private JLabel questions;
	/**Instances from the Expression Reader and the tree class**/
	private CommutativeExpressionReader exprRead = new CommutativeExpressionReader();
	private BinTree<String> exprTree = CommutativeExpressionReader.readCommutativeExpr("TwentyQuestions.xml");
	private BinTreeNode<String> currentNode = exprTree.getRoot();
	private BinTreeNode<String> currentPare;
	/**Constructor**/
	public QuestionsPanel(){
		//call the super class
		super();
		
		
		//call the init GUI
		initGUI();
		//make a UI manager for the color to show on macs
		 try
			{
				UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		
	}
	/**The initGUI method will create the GUI and hold all its components**/
	public void initGUI(){
		
		//the layout is a new BorderLayout
		setLayout(new BorderLayout());
		
		//the label will be north	
		add(createHeroList(), BorderLayout.NORTH);
		
		//the questions will be displayed center		
		add(createQuestions(), BorderLayout.CENTER);
		//the button panel should be south
		add(createAnswerButtonPanel(), BorderLayout.SOUTH);
		
		
		    
	}
	
	/**The Yes and No buttons which is made will display which way the tree is going**/
	public JPanel createAnswerButtonPanel()
	{
		//create a new panel
		JPanel answerPanel = new JPanel();
		//the layout is a grid Layout
		answerPanel.setLayout(new GridLayout(1,2));
	
		//the no button will be added to the panel		
		noButton = new JButton("NO");
		answerPanel.add(noButton);
		noButton.setBackground(Color.RED);
		noButton.setOpaque(true);
		Font noFont = new Font("Bauhaus 93",Font.BOLD,30);
		noButton.setFont(noFont);
		noButton.addActionListener(this);
		
		//no button will be added to the panel				
		yesButton = new JButton("YES");
		answerPanel.add(yesButton);
		Font yesFont = new Font("Bauhaus 93",Font.BOLD,30);
		yesButton.setFont(yesFont);
		yesButton.setBackground(Color.GREEN);
		yesButton.setOpaque(true);
		yesButton.addActionListener(this);
		//return answerPanel
		return answerPanel;
		
	}

	/**The createQuestions method will display the curent question at the time**/
	public JPanel createQuestions(){
		//Create a new panel
		JPanel questionPanel = new JPanel();
		questionPanel.setBackground(Color.BLACK);
		questionPanel.setLayout(new BorderLayout());
		//the Image Icon will take in a new Image Icon with the picture as a jpg
		ImageIcon heroIcon = new ImageIcon("Avengers_vs_JLA.jpg");
		//create a label that will hold the picture center of the 
		JLabel heroLabel = new JLabel(" ",heroIcon,JLabel.CENTER);
		//add the the Label to the Panel
		questionPanel.add(heroLabel,BorderLayout.PAGE_START);
		
		//the Label will take in the data of the currentNode at the time when you are around the tree
		questions = new JLabel(currentNode.getData().toString());
		//make the label center
		questions.setHorizontalAlignment(SwingConstants.CENTER);
		//add Font
		Font questionsFont = new Font("Bauhaus 93",Font.PLAIN,30);
		//the Foreground is white
		questions.setForeground(Color.WHITE);
		questions.setFont(questionsFont);
		//add Label to the panel
		questionPanel.add(questions);
		
		
		//return questionPanel
		return questionPanel;
	
	}


	public void newHero() {
		//create an answer string which takes in the data of the currentNode
		String answer = currentNode.getData().toString();
		//create an option pane
		int optionPane = JOptionPane.showConfirmDialog(this,
				"Is this the right Super Hero?", "Right Super Hero",
				JOptionPane.YES_NO_OPTION);
		//if the optionPane is queal to Yes oPTION
		if (optionPane == JOptionPane.YES_OPTION) {
			//it will show a message dialog
			JOptionPane.showMessageDialog(null, "Congratulations, You are "
					+ answer);
			//then the game will restart
			int restartPane = JOptionPane.showConfirmDialog(this,
					 "Do you want to restart the game?","Restart Game",
					JOptionPane.YES_NO_OPTION);
			//if the restart pane is a yes option, restart game
			if (restartPane == JOptionPane.YES_OPTION) {
				currentNode = exprTree.getRoot();
				displayQuestions();
			}
			//if the restart is the no option, enable buttons
			if (restartPane == JOptionPane.NO_OPTION) {
				noButton.setEnabled(false);
				yesButton.setEnabled(false);
			}

		} else if (optionPane == JOptionPane.NO_OPTION) {
			// answerPane
			String answerPane = JOptionPane.showInputDialog(this, "If not "
					+ answer + ", What hero were you thinking of ?");
		
			// create answerNode
			//String commutativeOp = "";
			BinTreeNode answerNode = new DefaultBinTreeNode(answerPane);
			
			// questionPane
			String questionPane = JOptionPane.showInputDialog(this, 
					"Insert Question About your Hero?");
			
			// create questionNode
			BinTreeNode questNode = new DefaultBinTreeNode(questionPane);
			//just in case you want to cancel and have no answer input
			if(answerPane == null){
				//restart game
				currentNode = exprTree.getRoot();
				displayQuestions();
				//just in case you have no question input
				if(questionPane == null){
				//restart the game 
					currentNode = exprTree.getRoot();
					displayQuestions();
				}
			}
			
			//if the left child of the current is the current Node
			if (currentPare.getLeftChild().equals(currentNode)) {
				// if currentNode is left
				// set the question node
				//the left child will be the previous node of the question Node
				questNode.setLeftChild(currentNode);
				//the right child will be the new answer node of the question node
				questNode.setRightChild(answerNode);
				//then the left child of the parent would be the question node
				currentPare.setLeftChild(questNode);
				//then you would restart the game
				currentNode = exprTree.getRoot();
				displayQuestions();
				
				

			}
			//if the left child of the current is the current Node
			else if(currentPare.getRightChild().equals(currentNode)){
				// if currentNode is right
				// set the question node
				//the left child will be the previous node of the question Node
				questNode.setLeftChild(currentNode);
				//the right child will be the new answer node of the question node
				questNode.setRightChild(answerNode);
				//then the rightchild of the parent would be the queston node
				currentPare.setRightChild(questNode);
				//then you would restart the game
				currentNode = exprTree.getRoot();
				displayQuestions();
				
			
			}
			//then just restart the game
			currentNode = exprTree.getRoot();
			
			
		}
			
		
	}
	/**This method is used for displaying the string on the panel**/
	public void displayQuestions(){
		//create a string which would be displayed in the question label
		String q = currentNode.getData().toString();
		//set text question Label
		questions.setText(q);
	}
	/**Create hero panel which holds the labels and the button that will change the labels color **/
	public JPanel createHeroList()
	{	
		//create heroPanel
		JPanel heroPanel = new JPanel();
		//set the layout
		heroPanel.setLayout(new GridLayout(3,1));
		//create the font for the panel and the labels
		Font heroFont = new Font("Comic Sans MS",Font.BOLD,16);
		
		//create colorButton
		colorButton = new JButton("Choose the Color");
		//set font
		colorButton.setFont(heroFont);
		//add the button to the panel
		heroPanel.add(colorButton);
		colorButton.setForeground(Color.ORANGE);
		colorButton.setBackground(Color.BLACK);
		colorButton.setOpaque(true);
		//implement action listener
		colorButton.addActionListener(this);
		
		//create intro Label
		introLabel = new JLabel("Super Hero Twenty Questions!!!");
		//set the font
		introLabel.setFont(heroFont);
		introLabel.setHorizontalAlignment(SwingConstants.CENTER);
		introLabel.setForeground(Color.ORANGE);
		introLabel.setBackground(Color.BLACK);
		introLabel.setOpaque(true);
		//add to the panel
		heroPanel.add(introLabel);
		
		//create hero label and pick your favorite super hero
		heroLabel = new JLabel("Choose your Favorite Super Hero!!!");
		//set horizontal alignment
		heroLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heroLabel.setForeground(Color.ORANGE);
		heroLabel.setBackground(Color.BLACK);
		heroLabel.setOpaque(true);
		heroLabel.setFont(heroFont);
		//add the label ot panel
		heroPanel.add(heroLabel);
		//return Panel
		return heroPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if you pressed the colorButton
		if(e.getSource().equals(colorButton)){
			//create Color chooser for the introLabel and heroLabel
			Color introColor = JColorChooser.showDialog(introLabel, "Choose intro label Color", Color.BLACK);
			Color heroColor = JColorChooser.showDialog(heroLabel, "Choose hero label Color", Color.BLACK);	
			//set Background color with color chooser
			introLabel.setBackground(introColor);
			//setBackground color with color chooser
			heroLabel.setBackground(heroColor);
			//make the color visible			
			colorButton.setVisible(true);
			colorButton.setOpaque(true);
			
		}
		//if you press the no button
		if (e.getSource().equals(noButton)) {
		
			//the current Parent will beset to the current Node
			currentPare = currentNode;
			//then the currentNode will get the left child
			currentNode = currentNode.getLeftChild();
			//if the current Node is not the atom
			if (!currentNode.isLeaf()) {
				//show the questions
				displayQuestions();
				//repaint
				repaint();
				//if it is the answer
			} else {
				//create an answer string which is set to the string of the current node
				String answer = currentNode.getData().toString();
				//display that answer in a message pop up 
				JOptionPane.showMessageDialog(this,"You are " + answer +".");
				//call new hero
				newHero();
				//repaint
				repaint();

			
			}

		}
		//if I pressed the yes button
		if (e.getSource().equals(yesButton)) {
			//the parent node will be set to current node
			currentPare = currentNode;
			//current node will be set a s the next child
			currentNode = currentNode.getRightChild();
			//if the current node is not the atoms
			if (!currentNode.isLeaf()) {
				//display the questions
				displayQuestions();
				//repaint
				repaint();
				//if the current node is the answer
			} else {
				//create an answer string which will take in the current node data
				String answer = currentNode.getData().toString();
				//display the answer
				JOptionPane.showMessageDialog(this, "You are " + answer + ".");
				//call the newHero question
				newHero();
				//repaint
				repaint();
				
				

			}
		}
		
		
	}
}
