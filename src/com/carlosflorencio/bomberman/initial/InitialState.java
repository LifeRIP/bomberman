package com.carlosflorencio.bomberman.initial;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class InitialState {

	public static final int TIME = initialState("TIME");
	public static final int LIVES = initialState("LIVES");
	public static final int LEVEL = initialState("LEVEL");

    public static int initialState(String out) {
		String line; 
		String path = "src/com/carlosflorencio/bomberman/initial/InitialState.txt";
		int TIME = 0, LIVES = 0, LEVEL = 0;
		try {
            RandomAccessFile file = new RandomAccessFile(path, "r");
            line = file.readLine();
			StringTokenizer tokens = new StringTokenizer(line, "|");
			TIME = Integer.parseInt(tokens.nextToken());
			LIVES = Integer.parseInt(tokens.nextToken());
			LEVEL = Integer.parseInt(tokens.nextToken());
			file.close();
        } catch (IOException e) {  
			JOptionPane.showMessageDialog(null, path + " (System cannot find the specified file)", "Error", 0);
			System.exit(0);
        } catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, path + " (System cannot read the entered values)", "Error", 0);
			System.exit(0);
		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(null, path + " (System cannot find all the values)", "Error", 0);
			System.exit(0);
		}
		if(out.equals("TIME")) {
			return TIME;
		} else if(out.equals("LIVES")) {
			return LIVES;
		}
		return LEVEL;
	}
    
}
