package com.oneitthing.xdsv.ui.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	public MainFrame() {

		initComponent();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Open Directory or Zip File");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int selected = jfc.showOpenDialog(mntmNewMenuItem);
			    if (selected == JFileChooser.APPROVE_OPTION){
			        File file = jfc.getSelectedFile();
			        System.out.println(file.getName());
			      }else if (selected == JFileChooser.CANCEL_OPTION){
			    	  System.out.println("cannel");
			      }else if (selected == JFileChooser.ERROR_OPTION){
			    	  System.out.println("error");
			      }
			}
		});


		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit App");
		mnNewMenu.add(mntmNewMenuItem_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("index.rdf", null, panel, null);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Annexes", null, panel_2, null);

	}
}
