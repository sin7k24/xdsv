package com.oneitthing.xdsv.ui.dashboard;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;

import com.oneitthing.xdsv.ui.textviewer.TextViewerPanel;

public class DashboardPanel extends JPanel {
    private File file;

    private String rootXmlFileName;

    private Map<String, String> digestValueMap = new HashMap<String, String>();

    private String signatureValue;

    private String certificate;

    public File getFile() {
        return this.file;
    }

    public String getRootXmlFileName() {
        return rootXmlFileName;
    }

    public void setRootXmlFileName(String rootXmlFileName) {
        this.rootXmlFileName = rootXmlFileName;
    }

    public Map<String, String> getDigestValueMap() {
        return digestValueMap;
    }

    public void setDigestValueMap(Map<String, String> digestValueMap) {
        this.digestValueMap = digestValueMap;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public DashboardPanel(File file, String rootXmlFileName) {
        this.file = file;
        this.rootXmlFileName = rootXmlFileName;

        setLayout(new BorderLayout(0, 0));
        setName("dashboardPanel");

        JTree jt = new JTree();
        jt.setName("jtree");

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(new BorderLayout());
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(jt, BorderLayout.CENTER);

        JPanel panel = new TextViewerPanel(file, rootXmlFileName);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_1, panel);
        splitPane.setName("dashboardPanel.splitpane");

        splitPane.setResizeWeight(0.3);
        add(splitPane, BorderLayout.CENTER);
    }
}
