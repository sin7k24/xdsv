package com.oneitthing.xdsv.ui.digestviewer.action;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.dashboard.DashboardPanel;
import com.oneitthing.xdsv.ui.digestviewer.DigestViewerPanel;

public class DigestValueAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        System.out.println("calculate digest value = " + parameterMapping.getEventSourceParent());

        DigestViewerPanel digestViewerPanel = (DigestViewerPanel)parameterMapping.getEventSourceParent();
        File target = Paths.get(digestViewerPanel.getFile().getAbsolutePath(), digestViewerPanel.getDocumentName()).toFile();
        
        String ext = FilenameUtils.getExtension(target.getName());
        byte[] sha256;
        if(ext.equals("xml")) {
            Canonicalizer c14nizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
            
            byte[] xml = FileUtils.readFileToByteArray(target);
            byte[] c14ned = c14nizer.canonicalize(xml);
            sha256 = DigestUtils.sha256(c14ned);
        }else if(digestViewerPanel.getDocumentName().startsWith("#xpointer")) {
            String rootXmlFileName = ((DashboardPanel)digestViewerPanel.getParent().getParent()).getRootXmlFileName();
            System.out.println(rootXmlFileName);
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document document = builder.parse(Paths.get(digestViewerPanel.getFile().getAbsolutePath(), rootXmlFileName).toFile());

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xPath = xpf.newXPath();

            Node removeNode = (Node) xPath.evaluate(
                    "//申請番号",
                    document,
                    XPathConstants.NODE);
            removeNode.getParentNode().removeChild(removeNode);
            
            removeNode = (Node) xPath.evaluate(
                    "//初回申請番号",
                    document,
                    XPathConstants.NODE);
            removeNode.getParentNode().removeChild(removeNode);
            
            removeNode = (Node) xPath.evaluate(
                    "//到達時刻",
                    document,
                    XPathConstants.NODE);
            removeNode.getParentNode().removeChild(removeNode);

            removeNode = (Node) xPath.evaluate(
                    "//署名情報",
                    document,
                    XPathConstants.NODE);
            removeNode.getParentNode().removeChild(removeNode);

            Canonicalizer c14nizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
            
            byte[] c14ned = c14nizer.canonicalizeSubtree(document.getDocumentElement());
            sha256 = DigestUtils.sha256(c14ned);
        }else {
            sha256 = DigestUtils.sha256(new FileInputStream(target));
        }
        
        String b64Sha256 = Base64.encodeBase64String(sha256);
        
        ((JTextField)getComponent(digestViewerPanel, "digestViewerPanel.digestValue")).setText(b64Sha256);
        return false;
    }

}
