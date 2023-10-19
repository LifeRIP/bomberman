package com.carlosflorencio.bomberman.gui.menu;

import javax.swing.JMenuBar;

import com.carlosflorencio.bomberman.gui.Frame;

public class Menu extends JMenuBar {
	
	public Menu(Frame frame, com.carlosflorencio.bomberman.Game game) {
		add( new Game(frame, game) );
		add( new Options(frame) );
		add( new Help(frame) );
	}
	
}
