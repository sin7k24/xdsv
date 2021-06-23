package com.oneitthing.xdsv.ui.iframe;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.oneitthing.xdsv.ui.dashboard.DashboardPanel;
import com.oneitthing.xdsv.ui.verifier.VerifierPanel;

public class IFrame extends JInternalFrame {
    private File file;

    private String rootXmlFileName;
    
    public File getFile() {
        return this.file;
    }

    public String getRootXmlFileName() {
        return this.rootXmlFileName;
    }
    
    public IFrame(File file, String rootXmlFileName) {
        this.file = file;
        this.rootXmlFileName = rootXmlFileName;

        setTitle(file.getAbsolutePath());
        setSize(900, 500);
        setLocation(10, 10);
        setName("iFrame");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new DashboardPanel(file, rootXmlFileName);
        tabbedPane.addTab("dashboard", null, panel, null);

        JPanel panel_2 = new VerifierPanel(file, rootXmlFileName);
        tabbedPane.addTab("verifier", null, panel_2, null);
    }
}
