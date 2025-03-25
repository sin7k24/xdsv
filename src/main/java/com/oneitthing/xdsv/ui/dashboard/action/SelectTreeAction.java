package com.oneitthing.xdsv.ui.dashboard.action;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.certificateviewer.CertificateViewerPanel;
import com.oneitthing.xdsv.ui.dashboard.DashboardPanel;
import com.oneitthing.xdsv.ui.digestviewer.DigestViewerPanel;
import com.oneitthing.xdsv.ui.signaturevalueviewer.SignatureValueViewerPanel;
import com.oneitthing.xdsv.ui.textviewer.TextViewerPanel;

public class SelectTreeAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        TreeSelectionEvent e = (TreeSelectionEvent) parameterMapping.getEventObject();
        DashboardPanel dashboard = (DashboardPanel) ((JComponent) parameterMapping.getEventSourceParent()).getParent()
                .getParent();

        TreePath sel = e.getNewLeadSelectionPath();

        JSplitPane splitpane = (JSplitPane) getComponent("dashboardPanel.splitpane");

        if (sel.getLastPathComponent().toString().equals(dashboard.getRootXmlFileName())) {
            TextViewerPanel textViewerPanel = new TextViewerPanel(dashboard.getFile(), dashboard.getRootXmlFileName());
            splitpane.setRightComponent(textViewerPanel);
        } else if (sel.getLastPathComponent().toString().equals("SignatureValue")) {
            SignatureValueViewerPanel signatureValueViewerPanel = new SignatureValueViewerPanel(
                    dashboard.getSignatureValue());
            splitpane.setRightComponent(signatureValueViewerPanel);
        } else if (sel.getLastPathComponent().toString().equals("X509Certificate")) {
            CertificateViewerPanel certificateViewerPanel = new CertificateViewerPanel(dashboard.getCertificate());
            splitpane.setRightComponent(certificateViewerPanel);
        } else {
            String digestValue = dashboard.getDigestValueMap().get(sel.getLastPathComponent().toString());

            DigestViewerPanel digestViewerPanel = new DigestViewerPanel(dashboard.getFile(), sel.getLastPathComponent().toString(), digestValue);
            splitpane.setRightComponent(digestViewerPanel);
        }

        return false;
    }
}
