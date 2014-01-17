package cookietest;

import javax.swing.*;

public class mainLoader {
	
	public static window f;
	public static int width = 800;
	public static int height = 500;
	public static double ver = 1.0;
	
	public static void main(String[] args) {
	f = new window();
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setResizable(false);
	f.setSize(width, height);
	f.setTitle("CookieClickerTest " + ver);
	f.setLocationRelativeTo(null);
	}
	
}
