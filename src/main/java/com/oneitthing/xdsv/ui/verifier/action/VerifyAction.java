package com.oneitthing.xdsv.ui.verifier.action;

import java.io.File;
import java.nio.file.Paths;

import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.verifier.VerifierPanel;

public class VerifyAction extends BaseAction {

    private JTextArea jtaConsole;

    private File dir;
    
    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        VerifierPanel verifierPanel = (VerifierPanel)parameterMapping.getEventSourceParent();
        this.dir = verifierPanel.getFile();
        
        this.jtaConsole = (JTextArea)getComponent(verifierPanel, "verifierPanel.jtaConsole");
        append("vefify start");

        append("target directory : " + this.dir.getAbsolutePath());
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(Paths.get(this.dir.getAbsolutePath(), verifierPanel.getRootXmlFileName()).toFile());

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();

        // X509Certificate
        Node x509CertificateNode = (Node) xPath.evaluate(
                "//X509Certificate",
                document,
                XPathConstants.NODE);
        String certificate = x509CertificateNode.getTextContent();
        
        append("X509Certificate :");
        append("-----------------------------");
        append(certificate);
        append("-----------------------------");

        // SignedInfo
        Node signedInfoNode = (Node) xPath.evaluate(
                "//SignedInfo",
                document,
                XPathConstants.NODE);

        // SignatureValue
        Node signatureValueNode = (Node) xPath.evaluate(
                "//SignatureValue",
                document,
                XPathConstants.NODE);
        String signatureValue = signatureValueNode.getTextContent();
        append("SignatureValue :");
        append("-----------------------------");
        append(signatureValue);
        append("-----------------------------");
        
        
        return false;
    }

    private void append(String s) {
        this.jtaConsole.append(s + System.getProperty("line.separator"));
    }
}
