package com.oneitthing.xdsv.ui.iframe;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class IFrame extends JInternalFrame {
    private File file;

    public File getFile() {
        return this.file;
    }

    public IFrame(File file) {
        this.file = file;

        setTitle(file.getAbsolutePath());
        setSize(500, 300);
        setLocation(10, 10);
        setName("iFrame");
        setVisible(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        tabbedPane.addTab("New tab", null, panel, null);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("New tab", null, panel_1, null);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setName("iFrame.btnTest");
        panel_1.add(btnNewButton);

        addInternalFrameListener(new InternalFrameListener() {

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("opened");
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("Closing");

            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("Closed");

            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("iconified");

            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("deiconified");

            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("activated");

            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                // TODO 自動生成されたメソッド・スタブ
                System.out.println("deactivated");

            }
        });
    }

}
