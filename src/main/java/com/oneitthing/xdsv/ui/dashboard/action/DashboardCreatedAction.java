package com.oneitthing.xdsv.ui.dashboard.action;

import java.io.File;
import java.nio.file.Paths;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.dashboard.DashboardPanel;

public class DashboardCreatedAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        DashboardPanel dashboard = (DashboardPanel) parameterMapping.getEventSource();
        File file = dashboard.getFile();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(Paths.get(file.getAbsolutePath(), dashboard.getRootXmlFileName()).toFile());

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();

        // Signature
        Node signatureNode = (Node) xPath.evaluate(
                "//Signature",
                document,
                XPathConstants.NODE);

        // Reference uri
        DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(dashboard.getRootXmlFileName());
        DefaultMutableTreeNode documentsTreeNode = new DefaultMutableTreeNode("Documents");
        rootTreeNode.add(documentsTreeNode);
        NodeList referenceNodes = (NodeList)xPath.evaluate("//Reference", signatureNode, XPathConstants.NODESET);
        for(int i=0; i<referenceNodes.getLength(); i++) {
            Node referenceNode = referenceNodes.item(i);
            String uri = referenceNode.getAttributes().getNamedItem("URI").getNodeValue();
            System.out.println("uri = " + uri);
            
            Node digestValueNode = (Node) xPath.evaluate(
                    "DigestValue",
                    referenceNode,
                    XPathConstants.NODE);
            
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(uri);
            documentsTreeNode.add(treeNode);
            
            dashboard.getDigestValueMap().put(uri, digestValueNode.getTextContent().trim());
        }
        
        // SignatureValue
        Node signatureValueNode = (Node) xPath.evaluate(
                "//SignatureValue",
                signatureNode,
                XPathConstants.NODE);
        DefaultMutableTreeNode signatureValueTreeNode = new DefaultMutableTreeNode("SignatureValue");
        rootTreeNode.add(signatureValueTreeNode);
        dashboard.setSignatureValue(signatureValueNode.getTextContent().trim());
        
        // X509Certificate
        Node x509CertificateNode = (Node) xPath.evaluate(
                "//X509Certificate",
                signatureNode,
                XPathConstants.NODE);
        DefaultMutableTreeNode x509CertificateTreeNode = new DefaultMutableTreeNode("X509Certificate");
        rootTreeNode.add(x509CertificateTreeNode);
        dashboard.setCertificate(x509CertificateNode.getTextContent().trim());

        DefaultTreeModel treeModel = new DefaultTreeModel(rootTreeNode);

        JTree jtree = (JTree) getComponent("jtree");
        jtree.setModel(treeModel);

        return false;
    }
}
