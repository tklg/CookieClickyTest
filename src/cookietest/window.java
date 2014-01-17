package cookietest;

import java.awt.*;
import javax.swing.*;

public class window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public boolean gameStarted = false;//Use this for a menu or something later
	
	public game panel;
	
	public window() {
		
		if (gameStarted) {
			
		panel = new game(this);
		setLayout(new GridLayout(1, 1, 0, 0));
		add(panel);
			} 
		if (!gameStarted) {
			panel = new game(this);
			setLayout(new GridLayout(1, 1, 0, 0));
			add(panel);
			}
		}
	}
	



