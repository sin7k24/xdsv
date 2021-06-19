package com.oneitthing.xdsv.ui.main.action;

import java.awt.Component;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.iframe.IFrame;

public class OpenAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {

//        JFileChooser jfc = new JFileChooser("/Users/nakanishishingo/src/job/marsnet3/src/assets/HM0501201210001");
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(jfc.showOpenDialog((Component)parameterMapping.getEventSource()) != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File file = jfc.getSelectedFile();

        IFrame iframe = new IFrame(file);

        JDesktopPane desktop = (JDesktopPane)getComponent("desktop");
        desktop.add(iframe);

        SwingUtilities.invokeLater(() -> {
            desktop.getDesktopManager().openFrame(iframe);
            desktop.getDesktopManager().activateFrame(iframe);
        });

        return false;
    }
}
