package com.oneitthing.xdsv.ui.certificateviewer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

public class CertificateViewerPanel extends JPanel {
    public CertificateViewerPanel(String certificate) {
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        JLabel lblNewLabel = new JLabel("X509Certificate");
        
        JTextArea textArea = new JTextArea(certificate);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel)
                        .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                    .addGap(8))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(textArea, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGap(8))
        );
        setLayout(groupLayout);
    }

}
