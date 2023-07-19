
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class beginnerLevel extends JPanel implements KeyListener, ActionListener{
	Timer timer = new Timer(25, this);
	
	private String userName;
	private int score = 0;
	private spaceShip ship = new spaceShip();
	private BufferedImage shipImage, alienImage, bulletImage;

	
	private ArrayList<bullet> bullets = new ArrayList<bullet>();
	private ArrayList<alien> aliens = new ArrayList<alien>();
	
	
	public beginnerLevel(String userName) {
		this.userName = userName;
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		setVisible(true);
		
		aliens.add(new greenAlien(0,300));
		aliens.add(new greenAlien(100,300));
		aliens.add(new greenAlien(450,100));
		aliens.add(new greenAlien(650,100));
		aliens.add(new greenAlien(550,100));
		aliens.add(new greenAlien(200,0));
		aliens.add(new greenAlien(300,0));
		aliens.add(new greenAlien(400,0));
		aliens.add(new greenAlien(100,-100));
		aliens.add(new greenAlien(200,-100));
		aliens.add(new greenAlien(300,-100));
		aliens.add(new greenAlien(450,-100));
		aliens.add(new greenAlien(650,-100));
		aliens.add(new greenAlien(550,-100));
		aliens.add(new greenAlien(100,-250));
		aliens.add(new greenAlien(200,-250));
		aliens.add(new greenAlien(550,-400));
		aliens.add(new greenAlien(350,-700));
		
		
		
		try {
			shipImage = ImageIO.read(new FileImageInputStream(new File("spaceShip.png")));
			alienImage = ImageIO.read(new FileImageInputStream(new File("alien1_1.png")));
			bulletImage = ImageIO.read(new FileImageInputStream(new File("bullet.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBackground(Color.BLACK);
		
		timer.start();
		
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int j = aliens.size() - 1; j >= 0; j--) {
            Rectangle alienHitbox_ = new Rectangle(aliens.get(j).getX(), aliens.get(j).getY(), 85, 90);
            Rectangle shipHitbox = new Rectangle(ship.getX(), ship.getY(), 100, 75);
            if(shipHitbox.getBounds().intersects(alienHitbox_.getBounds())) {
            	aliens.remove(j);
            	ship.setHp(ship.getHp() - 1);
            	break;
            }
		}
		
		for (int i = bullets.size() - 1; i >= 0; i--) {
	        Rectangle bulletHitbox = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), 30, 50);

	        for (int j = aliens.size() - 1; j >= 0; j--) {
	            Rectangle alienHitbox = new Rectangle(aliens.get(j).getX(), aliens.get(j).getY(), 85, 90);

	            if (bulletHitbox.getBounds().intersects(alienHitbox.getBounds())) {
	            	bullets.remove(i);
            		aliens.get(j).setHp(aliens.get(j).getHp() - 1);
            		score += 5;
	             
	            	if (aliens.get(j).getHp() == 0) {
	                    aliens.remove(j);
	                }
	                break;
	            }
	        }
	    }
		
		for(alien alien_1 : aliens) {
			if(alien_1.getHp() == 0) {
				aliens.remove(alien_1);
			}
		}
		
		for(alien alien_ : aliens) {
			if(alien_.getType().equals("green"))
			g.drawImage(alienImage, alien_.getX(), alien_.getY(), alienImage.getWidth() - 7, alienImage.getHeight() - 8, this);
		}
		
		
		g.drawImage(shipImage, ship.getX(), ship.getY(), shipImage.getWidth(), shipImage.getHeight(), this);
		
		for(bullet bullet_ : bullets) {
			if(bullet_.getY() < 0) {
				bullets.remove(bullet_);
			}
		}
		
		
		for(bullet bullet_1 : bullets) {
			g.drawImage(bulletImage , bullet_1.getX(), bullet_1.getY(), bulletImage.getWidth(), bulletImage.getHeight() - 28, this);
		}
		
	    g.setColor(Color.RED);
	    g.drawString("Score: " + score, 10, 20);
	    g.drawString("Lives: " + ship.getHp(), 80, 20);
		
		
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}
	

	

	@Override
	public void actionPerformed(ActionEvent e) {
		for(bullet bullet_ : bullets) {
			bullet_.moveUp();
		}
		
		for(alien alien_ : aliens) {
			alien_.moveDown();
		}
	
		
		if (ship.getHp() <= 0) {
	        System.out.println("Game Over");
	        timer.stop();
	        StartGui.updateScore(userName, score);
	    }
		
		boolean allAliensOffScreen = true;
	    for (alien alien_1 : aliens) {
	        if (alien_1.getY() <= 750) {
	            allAliensOffScreen = false;
	            break;
	        }
	    }

	    if (allAliensOffScreen) {
	        System.out.println("Game Over");
	        timer.stop();
	        StartGui.updateScore(userName, score);
	    }
		
		repaint();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		
		
		if(c == KeyEvent.VK_LEFT) {
			if(ship.getX() <= 0) {
				ship.setX(0);
			}
			
			else {
			ship.moveLeft();
			}
		}
		
		if(c == KeyEvent.VK_RIGHT) {
			if(ship.getX() >= 650) {
				ship.setX(650);
			}
			
			else {
			ship.moveRight();
			}
		}
		
		if(c == KeyEvent.VK_UP) {
			if(ship.getY() <= 0) {
				ship.setY(0);
			}
			
			else {
			ship.moveUp();
			}
		}
		
		if(c == KeyEvent.VK_DOWN) {
			
			if(ship.getY() >= 635) {
				ship.setY(635);
			}
			
			else {
			ship.moveDown();
			}
		}
		
		else if(c == KeyEvent.VK_SPACE) {
			bullets.add(new bullet(ship.getX() + 34, ship.getY() - 35));
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
