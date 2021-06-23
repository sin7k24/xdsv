package com.oneitthing.xdsv.ui.textviewer;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class TextViewerPanel extends JPanel {
    private File file;
    
    public File getFile() {
        return this.file;
    }
    
    public TextViewerPanel(File file) {
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        this.file = file;
        
        setLayout(new BorderLayout(0, 0));
        setName("textViewerPanel");
        
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setTabSize(2);
        textArea.setName("textViewerPanel.textArea");
        scrollPane.setViewportView(textArea);
    }

}
