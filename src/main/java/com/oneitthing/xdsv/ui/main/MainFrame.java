package com.oneitthing.xdsv.ui.main;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Xml Degital Signature Verifire");
        setSize(800, 600);
        setName("mainFrame");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Menu");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Open Directory or Zip File");
        mnNewMenu.add(mntmNewMenuItem);
        mntmNewMenuItem.setName("mainFrame.jmiOpen");

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Show Version");
        mnNewMenu.add(mntmNewMenuItem_2);
        mntmNewMenuItem_2.setName("mainFrame.jmiVersion");

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit App");
        mnNewMenu.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.setName("mainFrame.jmiExit");

        JDesktopPane desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        desktopPane.setName("desktop");


	}
}
