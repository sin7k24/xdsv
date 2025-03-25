package com.oneitthing.xdsv.ui.main.action;

import java.awt.Component;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.iframe.IFrame;

public class OpenAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (jfc.showOpenDialog((Component) parameterMapping.getEventSource()) != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File file = jfc.getSelectedFile();

        String xmlFileName = JOptionPane.showInputDialog((JComponent) parameterMapping.getEventSource(),
                "root xml file name?", "");

        IFrame iframe = new IFrame(file, xmlFileName);

        JDesktopPane desktop = (JDesktopPane) getComponent("desktop");
        desktop.add(iframe);
        iframe.setVisible(true);

        return false;
    }
}
