package cookietest;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

//VARS
public class game extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	/*
	public Rectangle buttonCookie;
	public Rectangle button1;
	public Rectangle button2;
	public Rectangle button3;
	public Rectangle button4;
	public Rectangle infoboard;
	*/
	public int keyCookie = KeyEvent.VK_SPACE;
	public int keyBuylvl1 = KeyEvent.VK_Z;
	public int keyBuylvl2 = KeyEvent.VK_X;
	public int keyBuylvl3 = KeyEvent.VK_C;
	public int keyBuylvl4 = KeyEvent.VK_V;
	public int keyBuylvl5 = KeyEvent.VK_B;
	public int keyPause = KeyEvent.VK_ESCAPE;
	public int keyDebug = KeyEvent.VK_F3;
	public int keyMusicMute = KeyEvent.VK_0;
	public int keyRestart = KeyEvent.VK_R;
	
	public int fps = 1000;
	public int cookies = 0;
	public int lvl1multiplier = 1;
	public int lvl1cost = 50;
	public int lvl1owned = 0;
	public int lvl2multiplier = 5;
	public int lvl2cost = 300;
	public int lvl2owned = 0;
	public int lvl3multiplier = 15;
	public int lvl3cost = 1000;
	public int lvl3owned = 0;
	public int lvl4multiplier = 30;
	public int lvl4cost = 2000;
	public int lvl4owned = 0;
	public int lvl5multiplier = 60;
	public int lvl5cost = 5000;
	public int lvl5owned = 0;
	public int time = 0;
	public int seconds = 0;
	public int minutes = 0;
	
	public boolean objectsDefined = false;
	private boolean running = true;
	public static boolean inGame = true;
	public boolean restart = false;
	public boolean inDebug = false;
	public static boolean musicOn = true;
	public boolean musicMuted = false;
	public boolean canBuylvl1, canBuylvl2, canBuylvl3, canBuylvl4, canBuylvl5 = false;
	public boolean spaceIsPressed = false;
	
	public static Thread game;
	public static Clip theme;
	
	public game(window f) {
		setBackground(Color.BLACK);
		
		defineObjects();
		
		game = new Thread(this);
		game.start();
		//calls the method that starts the theme music
		BGMusic();

		
		   //Keylistener for LEFT and RIGHT and SPACE and PAUSE
		f.addKeyListener(new KeyAdapter() {
			//Pressed
			public void keyPressed(KeyEvent e) {
				
				repaint();
				
				if(e.getKeyCode() == keyCookie) {
					if (!spaceIsPressed) {
						spaceIsPressed = true;
						cookies++;
					}
				}
				
				if(e.getKeyCode() == keyBuylvl1) {
					if (canBuylvl1) {
						lvl1owned++;
						cookies -= lvl1cost;
					}
				}
				if(e.getKeyCode() == keyBuylvl2) {
					if (canBuylvl2) {
						lvl2owned++;
						cookies -= lvl2cost;
					}
				}
				if(e.getKeyCode() == keyBuylvl3) {
					if (canBuylvl3) {
						lvl3owned++;
						cookies -= lvl3cost;
					}
				}
				if(e.getKeyCode() == keyBuylvl4) {
					if (canBuylvl4) {
						lvl4owned++;
						cookies -= lvl4cost;
					}
				}
				if(e.getKeyCode() == keyBuylvl5) {
					if (canBuylvl5) {
						lvl5owned++;
						cookies -= lvl5cost;
					}
				}

			}
			//Released
			public void keyReleased(KeyEvent e) {
			
				if(e.getKeyCode() == keyCookie) {
					spaceIsPressed = false;
				}
				
			}
			
		});
	} 

	//plays opens and plays theme music
	public void BGMusic() {
		   
	      try {
	         // Open an audio input stream.
	    		 File soundFile = new File("resources/kml1.wav");
	    	     AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	    	     theme = AudioSystem.getClip();
	         // Get a sound clip resource.
	         
	         // Open audio clip and load samples from the audio input stream.
	         
	         if (musicOn) {
		         theme.open(audioIn);
		         theme.loop(Clip.LOOP_CONTINUOUSLY);
		         } 
	         
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	
	//Specify object height + width
	public void defineObjects() {
		
		objectsDefined = true;
		//refresh window
		repaint();
	}
	//Paint window COMPONENTS (Graphics) for MAIN 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//baackground
			
				//g.drawImage(textures.background, -100-(xs/10), -100-(ys/10), null);
			
			if(objectsDefined) {
				/*			
				seconds = time++;
				seconds = seconds/1000;
				if(time%60000 == 0) {
					minutes++;
					time = 0;
				}
				*/
				if(!inDebug){
					//InfoBoard
					g.setColor(Color.ORANGE);
					g.drawString("Level 1 Upgrades: "+lvl1owned+", Giving: "+lvl1owned * lvl1multiplier+" cookies per second.", 10, 35);
					g.drawString("Level 2 Upgrades: "+lvl2owned+", Giving: "+lvl2owned * lvl2multiplier+" cookies per second.", 10, 50);
					g.drawString("Level 3 Upgrades: "+lvl3owned+", Giving: "+lvl3owned * lvl3multiplier+" cookies per second.", 10, 65);
					g.drawString("Level 4 Upgrades: "+lvl4owned+", Giving: "+lvl4owned * lvl4multiplier+" cookies per second.", 10, 80);
					g.drawString("Level 5 Upgrades: "+lvl5owned+", Giving: "+lvl5owned * lvl5multiplier+" cookies per second.", 10, 95);
					
					g.setColor(Color.LIGHT_GRAY);
					g.drawString("Press SPACE to increment cookie count.", 10, 120);
					g.drawString("You can buy stuff to press space for you with cookies.", 10, 135);
					//Time inGame
				 	g.setColor(Color.CYAN);
					g.drawString("Cookies: " + cookies, 10, 20);
					
					//Toggleables
					if(!inGame) {
						g.setColor(Color.RED);
						g.drawString("Paused", 60, 50);
						}
					if (canBuylvl1) {
						g.setColor(Color.GREEN);
						g.drawString("Press Z to buy a " + lvl1multiplier + "c/s upgrade for " + lvl1cost + " cookies.", 10, 165);
					}
					if (canBuylvl2) {
						g.setColor(Color.GREEN);
						g.drawString("Press X to buy a " + lvl2multiplier + "c/s upgrade for " + lvl2cost + " cookies.", 10, 180);
					}
					if (canBuylvl3) {
						g.setColor(Color.GREEN);
						g.drawString("Press C to buy a " + lvl3multiplier + "c/s upgrade for " + lvl3cost + " cookies.", 10, 195);
					}
					if (canBuylvl4) {
						g.setColor(Color.GREEN);
						g.drawString("Press V to buy a " + lvl4multiplier + "c/s upgrade for " + lvl4cost + " cookies.", 10, 210);
					}
					if (canBuylvl5) {
						g.setColor(Color.GREEN);
						g.drawString("Press B to buy a " + lvl5multiplier + "c/s upgrade for " + lvl5cost + " cookies.", 10, 225);
					}
				
				}
				//DEBUG YEAH
				if(inDebug) {
					//not much to put in debug yet
					}
				}
			}
		
		public void run() {
			
			while(running) {
				if(inGame) {
					
					//cookies += ((lvl1multiplier * lvl1owned) + (lvl2multiplier * lvl2owned) + (lvl3multiplier * lvl3owned) + (lvl4multiplier * lvl4owned) + (lvl5multiplier * lvl5owned));
					if (((lvl1multiplier * lvl1owned) + (lvl2multiplier * lvl2owned) + (lvl3multiplier * lvl3owned) + (lvl4multiplier * lvl4owned) + (lvl5multiplier * lvl5owned)) != 0) {
						cookies++;
					}
					
					if (cookies >= lvl1cost) {
						canBuylvl1 = true;
					} else {
						canBuylvl1 = false;
					}
					
					if (cookies >= lvl2cost) {
						canBuylvl2 = true;
					} else {
						canBuylvl2 = false;
					}
					
					if (cookies >= lvl3cost) {
						canBuylvl3 = true;
					} else {
						canBuylvl3 = false;
					}
					
					if (cookies >= lvl4cost) {
						canBuylvl4 = true;
					} else {
						canBuylvl4 = false;
					}
					
					if (cookies >= lvl5cost) {
						canBuylvl5 = true;
					} else {
						canBuylvl5 = false;
					}
				}
			
				fpsSetter();
				//refreshes screen
				repaint();
			}
		}
		
		@SuppressWarnings("static-access")
		public void fpsSetter() {
			try {
				
				if (((lvl1multiplier * lvl1owned) + (lvl2multiplier * lvl2owned) + (lvl3multiplier * lvl3owned) + (lvl4multiplier * lvl4owned) + (lvl5multiplier * lvl5owned)) == 0) {
					game.sleep(fps/1);// + (lvl1multiplier * lvl1owned) + (lvl2multiplier * lvl2owned) + (lvl3multiplier * lvl3owned) + (lvl4multiplier * lvl4owned) + (lvl5multiplier * lvl5owned))); //only for 1 second, because this isn't really a game... maybe 100th of a second
				} else {
					game.sleep(fps/((lvl1multiplier * lvl1owned) + (lvl2multiplier * lvl2owned) + (lvl3multiplier * lvl3owned) + (lvl4multiplier * lvl4owned) + (lvl5multiplier * lvl5owned)));
				}
			}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
