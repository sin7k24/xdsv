package com.oneitthing.xdsv.ui.verifier.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.xml.security.c14n.Canonicalizer;
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
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(Paths.get(this.dir.getAbsolutePath(), verifierPanel.getRootXmlFileName()).toFile());

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();

        
        // get PublicKey from X509Certificate
        Node x509CertificateNode = document.getElementsByTagNameNS("*", "X509Certificate").item(0); 
        String b64Certificate = x509CertificateNode.getTextContent();
        byte[] certificate = Base64.decodeBase64(b64Certificate);

        InputStream is = new ByteArrayInputStream(certificate);
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)certFactory.generateCertificate(is);
        PublicKey publicKey = cert.getPublicKey();
        
        append("X509Certificate :");
        append("-----------------------------");
        append(b64Certificate);
        append("-----------------------------");

        append("Public Key :");
        append("-----------------------------");
        append(publicKey.toString());
        append("-----------------------------");

        // convert SignatureValue to ByteArray
        Node signatureValueNode = document.getElementsByTagNameNS("*", "SignatureValue").item(0);
        String b64SignatureValue = signatureValueNode.getTextContent();
        byte[] sign = Base64.decodeBase64(b64SignatureValue);
        append("SignatureValue :");
        append("-----------------------------");
        append(b64SignatureValue);
        append("-----------------------------");
        
      // SignedInfo
        Node signedInfoNode = document.getElementsByTagNameNS("*", "SignedInfo").item(0);
        
        String hoge = domToString(signedInfoNode, false);
        System.out.println(hoge);
        
        Canonicalizer c14nizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
        
//        String signedInfoString = "<SignedInfo xmlns=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:rdf=\"http://www.w3c.org/rdf\"><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\" /><Reference URI=\"HM0501201210001.xml\"><Transforms><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\" /><DigestValue>iHCxfMcRkULXF7B+3TSl+joUQVLwjTsdaQVokj7p/yw=</DigestValue></Reference><Reference URI=\"sample/sample.pdf\"><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\" /><DigestValue>tI+bbE1NgCKRgOx6Y3nwL4sEUezmmElM0hRv0quDO14=</DigestValue></Reference><Reference URI=\"sample/sample.xml\"><Transforms><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\" /><DigestValue>IRBVihEZ9axG5HFX9z6lyhkCBm5sJM/HZEikOwDc3PY=</DigestValue></Reference><Reference URI=\"#xpointer(/)\"><Transforms><Transform Algorithm=\"http://www.w3.org/TR/1999/REC-xpath-19991116\"><XPath xmlns:dsig=\"http://www.w3.org/2000/09/xmldsig#\">not (ancestor-or-self::申請番号) and not (ancestor-or-self::初回申請番号) and not (ancestor-or-self::到達時刻) and not (ancestor-or-self::署名情報)</XPath></Transform><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\" /><DigestValue>cRH1XLj/wEpoDr4cY7cEgre9jI+p3REcCzThbe2BoIE=</DigestValue></Reference></SignedInfo>";
        byte[] c14ned = c14nizer.canonicalizeSubtree(signedInfoNode);
        
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(c14ned);
        boolean result = verifier.verify(sign);
        
        System.out.println(result);
        append("verify result :");
        append("-----------------------------");
        append(String.valueOf(result));

        return false;
    }

    private void append(String s) {
        this.jtaConsole.append(s + System.getProperty("line.separator"));
    }
 
    public String domToString(Node domNode, boolean indent) {

        try {
            Source source = new DOMSource(domNode);
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, indent ? "yes" : "no");
            transformer.transform(source, result);

            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
