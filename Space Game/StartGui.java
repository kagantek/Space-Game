import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;


public class StartGui extends JFrame {
	private JMenuItem fileMenuItem, fileMenuItem2, fileMenuItem3, fileMenuItem4, helpMenuItem;
	private ImageIcon startBackGround;
	private JLabel startLabel;
	public static String filePath = System.getProperty("user.home") + ("/Desktop");
	
	public StartGui() {
		this.setTitle("CSE 212 Term Project - Space Invaders Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750,750);
		this.setLayout(new FlowLayout());
		this.setLayout(null);
		this.setResizable(false);
		
		startBackGround = new ImageIcon(System.getProperty("user.dir") + ("\\startMenu.png"));
		startLabel = new JLabel(startBackGround);
		startLabel.setSize(750,750);
		this.add(startLabel);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		fileMenuItem = new JMenuItem("Register");
		fileMenuItem2 = new JMenuItem("Play Game");
		fileMenuItem3 = new JMenuItem("High Score");
		fileMenuItem4 = new JMenuItem("Quit");
		helpMenuItem = new JMenuItem("About");
		
		fileMenu.add(fileMenuItem);
		fileMenu.add(fileMenuItem2);
		fileMenu.add(fileMenuItem3);
		fileMenu.add(fileMenuItem4);
		helpMenu.add(helpMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		InteractiveHandler handler = new InteractiveHandler();
		
		helpMenuItem.addActionListener(handler);
		fileMenuItem.addActionListener(handler);
		fileMenuItem2.addActionListener(handler);
		fileMenuItem3.addActionListener(handler);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		
		
	}
	
	private void registerUser() {
	    String username = JOptionPane.showInputDialog("Enter username:");
	    String password = JOptionPane.showInputDialog("Enter password:");
		
	    try {
			FileWriter writer = new FileWriter(filePath, true);
	        writer.write(username + "," + password + ",0\n"); 
	        writer.close();
	        JOptionPane.showMessageDialog(null, "Registration successful!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private boolean checkUserExists(String username, String password) {
	    try {
	        File file = new File(filePath);
	        Scanner scanner = new Scanner(file);
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] userData = line.split(",");
	            
	            if (userData[0].equals(username) && userData[1].equals(password)) {
	                scanner.close();
	                return true;
	            }
	        }
	        
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	public void createBeginner() {
		String username = JOptionPane.showInputDialog("Enter username:");
	    String password = JOptionPane.showInputDialog("Enter password:");

	    boolean userExists = checkUserExists(username, password);
	    
	    if (userExists) {
	    	GameGui game = new GameGui();
			beginnerLevel beginner = new beginnerLevel(username);
			
			beginner.requestFocus();
			
			beginner.addKeyListener(beginner);
			
			beginner.setFocusable(true);
			beginner.setFocusTraversalKeysEnabled(false);
			
			game.add(beginner);
			game.setVisible(true);	
	    } else {
	        JOptionPane.showMessageDialog(null, "Invalid username or password!");
	    }
		}
	
	public void createIntermediate() {
		  String username = JOptionPane.showInputDialog("Enter username:");
		    String password = JOptionPane.showInputDialog("Enter password:");

		    boolean userExists = checkUserExists(username, password);
		    
		    if (userExists) {
		    	GameGui game = new GameGui();
				intermediateLevel intermediate = new intermediateLevel(username);
				
				intermediate.requestFocus();
				
				intermediate.addKeyListener(intermediate);
				
				intermediate.setFocusable(true);
				intermediate.setFocusTraversalKeysEnabled(false);
				
				game.add(intermediate);
				game.setVisible(true);	
		    } else {
		        JOptionPane.showMessageDialog(null, "Invalid username or password!");
		    }
		}
	
	public void createAdvanced() {
		  String username = JOptionPane.showInputDialog("Enter username:");
		    String password = JOptionPane.showInputDialog("Enter password:");
		    

		    boolean userExists = checkUserExists(username, password);
		    
		    if (userExists) {
		    	GameGui game = new GameGui();
				advancedLevel advanced = new advancedLevel(username);
				
				advanced.requestFocus();
				
				advanced.addKeyListener(advanced);
				
				advanced.setFocusable(true);
				advanced.setFocusTraversalKeysEnabled(false);
				
				game.add(advanced);
				game.setVisible(true);	
		    } else {
		        JOptionPane.showMessageDialog(null, "Invalid username or password!");
		    }
		
		}
	
	public static void updateScore(String username, int newScore) {
        try {
            File file = new File(filePath);
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String currentUser = userData[0];
                int currentScore = Integer.parseInt(userData[2]);

                if (currentUser.equals(username)) {
                    currentScore = newScore;
                }

                writer.write(currentUser + "," + userData[1] + "," + currentScore + "\n");
            }

            reader.close();
            writer.close();

          
            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    System.out.println("Error occurred while updating the score.");
                }
            } else {
                System.out.println("Error occurred while updating the score.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	private void showHighScores() {
	    try {
	        File file = new File(filePath);
	        Scanner scanner = new Scanner(file);
	        
	        StringBuilder scores = new StringBuilder();
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] userData = line.split(",");
	            
	            
	            scores.append(userData[0]).append(": ").append(userData[2]).append("\n");
	        }
	        
	        scanner.close();
	        
	        JOptionPane.showMessageDialog(null, scores.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	private class InteractiveHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == helpMenuItem) {
				JOptionPane.showMessageDialog(null, "Kagan Tek - 20210702027 - kagan.tek@std.yeditepe.edu.tr");
			}
			
			else if(e.getSource() == fileMenuItem2) {
				String level = JOptionPane.showInputDialog("What level do you want to play? (1-3): ");
				if(level.equals("1")) {
					createBeginner();
				}
				else if(level.equals("2")) {
					createIntermediate();
				}
				
				else if(level.equals("3")) {
					createAdvanced();
				}
			}
			
			else if(e.getSource() == fileMenuItem) {
				registerUser();
			}
			
			else if(e.getSource() == fileMenuItem3) {
				showHighScores();
			}
		}
	}
}
