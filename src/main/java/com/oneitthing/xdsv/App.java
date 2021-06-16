package com.oneitthing.xdsv;

import javax.swing.SwingUtilities;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.oneitthing.xdsv.ui.main.MainFrame;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		SwingUtilities.invokeLater(() -> {

			LafManager.install(new DarculaTheme());

//			JFrame frame = new JFrame("Darklaf - A themeable LaF for Swing");
//			frame.setSize(600, 400);
//
//			JButton button = new JButton("Click here!");
//
//			JPanel content = new JPanel();
//			content.add(button);
//
//			frame.setLocationRelativeTo(null);
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setContentPane(content);
//			frame.setVisible(true);

			MainFrame mainFrame = new MainFrame();
			mainFrame.setVisible(true);
		});
	}
}
