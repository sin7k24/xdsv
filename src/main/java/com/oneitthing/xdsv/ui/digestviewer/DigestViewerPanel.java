package com.oneitthing.xdsv.ui.digestviewer;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

public class DigestViewerPanel extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private File file;
    private String documentName;
    
    public File getFile() {
        return this.file;
    }
    public String getDocumentName() {
        return this.documentName;
    }
    public DigestViewerPanel(File file, String documentName, String digestValue) {
        this.file = file;
        this.documentName = documentName;
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setName("digestViewerPanel");
        
        JLabel lblNewLabel = new JLabel("DigestValue on SignedInfo");
        
        textField = new JTextField(digestValue);
        textField.setEditable(false);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("calculate now");
        btnNewButton.setName("digestViewerPanel.jbtCalculate");      
        textField_1 = new JTextField();
        textField_1.setName("digestViewerPanel.digestValue");
        textField_1.setColumns(10);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                        .addComponent(lblNewLabel)
                        .addComponent(btnNewButton)
                        .addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnNewButton)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(188, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}
