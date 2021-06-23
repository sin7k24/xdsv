package com.oneitthing.xdsv.ui.signaturevalueviewer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

public class SignatureValueViewerPanel extends JPanel {
    public SignatureValueViewerPanel(String signatureValue) {
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        JLabel lblNewLabel = new JLabel("SignatureValue");
        
        JTextArea textArea = new JTextArea(signatureValue);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                        .addComponent(lblNewLabel))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addGap(17))
        );
        setLayout(groupLayout);
    }
}
