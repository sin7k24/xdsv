package com.oneitthing.xdsv.ui.verifier;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VerifierPanel extends JPanel {
    private File file;
    
    private String rootXmlFileName;
    
    public File getFile() {
        return this.file;
    }
    
    public String getRootXmlFileName() {
        return this.rootXmlFileName;
    }
    
    public VerifierPanel(File file, String rootXmlFileName) {
        this.file = file;
        this.rootXmlFileName = rootXmlFileName;
        
        setLayout(new BorderLayout(0, 0));
        setName("verifierPanel");
        
        JButton btnNewButton = new JButton("start verify");
        add(btnNewButton, BorderLayout.NORTH);
        btnNewButton.setName("verifierPanel.jbtVerify");
        
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setTabSize(4);
        textArea.setName("verifierPanel.jtaConsole");
        scrollPane.setViewportView(textArea);
    }
}
