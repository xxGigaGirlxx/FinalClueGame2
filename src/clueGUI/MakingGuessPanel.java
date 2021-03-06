package clueGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Solution;

public class MakingGuessPanel extends JDialog {
	Board board;
	JComboBox<String> guessP;
	JComboBox<String> guessW;
	boolean personGuessed;
	boolean weaponGuessed;
	clueGUI.ClueGame clueGame;
	String room;
	
	public MakingGuessPanel(Board board, char roomInitial, clueGUI.ClueGame clueGame) {
		this.clueGame = clueGame;
		personGuessed = false;
		weaponGuessed = false;
		this.board = board;
		setLayout(new GridLayout(4,2));
		JLabel roomLabel = new JLabel("Your room");
		JLabel person = new JLabel("Person");
		JLabel weapon = new JLabel("Weapon");
		
		room = " ";
		switch (roomInitial) {
		case 'C':
			room = "Conservatory";
			break;
		case 'R':
			room = "Billiards";
			break;
		case 'B':
			room = "Ballroom";
			break;
		case 'K':
			room = "Kitchen";
			break;
		case 'L':
			room = "Library";
			break;
		case 'D':
			room = "Dining";
			break;
		case 'H':
			room = "Hall";
			break;
		case 'O':
			room = "Lounge";
			break;
		case 'S':
			room = "Study";
			break;
		}
		JLabel roomName = new JLabel(room);
		guessP = new JComboBox<String>();
		guessP.addItem("");
		guessP.addItem("Miss Scarlett");		
		guessP.addItem("Colonel Mustard");
		guessP.addItem("Mr. Green");
		guessP.addItem("Mrs. White");
		guessP.addItem("Mrs. Peacock");
		guessP.addItem("Professor Plum");
		ComboListenerPerson Plistener = new ComboListenerPerson();
		guessP.addActionListener(Plistener);
		
		guessW = new JComboBox<String>();
		guessW.addItem("");
		guessW.addItem("Candlestick");
		guessW.addItem("Knife");
		guessW.addItem("Lead Pipe");
		guessW.addItem("Revolver");
		guessW.addItem("Rope");
		guessW.addItem("Wrench");
		ComboListenerWeapon Wlistener = new ComboListenerWeapon();
		guessW.addActionListener(Wlistener);
		
		JButton submit = new JButton("Submit");
		JButton cancel = new JButton("Cancel");
		submit.addActionListener(new ButtonListenerSubmit());
		cancel.addActionListener(new ButtonListenerCancel());
		
		add(roomLabel);
		add(roomName);
		add(person);
		add(guessP);
		add(weapon);
		add(guessW);
		add(submit);
		add(cancel);
		
		setTitle("Suggestion");
		setSize(300, 300);
	}
	
	public class ComboListenerPerson implements ActionListener {
		  public void actionPerformed(ActionEvent e)
		  {
			  personGuessed = true;
			  board.getHumanPlayer().setPersonGuess(guessP.getSelectedItem().toString());
		  }
	}

	public class ComboListenerWeapon implements ActionListener {
		  public void actionPerformed(ActionEvent e)
		  {
			  weaponGuessed = true;
			  board.getHumanPlayer().setWeaponGuess(guessW.getSelectedItem().toString());
		  }
	}
	
	class ButtonListenerSubmit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (personGuessed && weaponGuessed) {
				setVisible(false);
				clueGame.GameHandleSuggestion(new Solution(board.getHumanPlayer().getPersonGuess(), room, board.getHumanPlayer().getWeaponGuess()), board.getHumanPlayer(), new BoardCell(board.getHumanPlayer().getRow(), board.getHumanPlayer().getColumn()));	
			}
		}
	}
	
	class ButtonListenerCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
}
