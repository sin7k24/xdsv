package com.oneitthing.xdsv.controller;

import java.awt.event.ActionListener;

import javax.swing.event.InternalFrameListener;
import javax.swing.event.TreeSelectionListener;

import com.oneitthing.swingcontrollerizer.controller.BaseController;
import com.oneitthing.swingcontrollerizer.controller.ClientConfig;
import com.oneitthing.swingcontrollerizer.controller.EventBinder;
import com.oneitthing.swingcontrollerizer.listener.ComponentCreationListener;
import com.oneitthing.xdsv.ui.dashboard.action.DashboardCreatedAction;
import com.oneitthing.xdsv.ui.dashboard.action.SelectTreeAction;
import com.oneitthing.xdsv.ui.digestviewer.action.DigestValueAction;
import com.oneitthing.xdsv.ui.iframe.action.InitIFrameAction;
import com.oneitthing.xdsv.ui.main.action.ExitAction;
import com.oneitthing.xdsv.ui.main.action.OpenAction;
import com.oneitthing.xdsv.ui.main.action.VersionAction;
import com.oneitthing.xdsv.ui.textviewer.action.InitTextViewerAction;
import com.oneitthing.xdsv.ui.verifier.action.VerifyAction;


public class XdsvController extends BaseController {

    @Override
    protected void initialize(ClientConfig config) {
//        Properties prop = ResourceUtil.instance.asProperties("conf/config.properties");
//        Map<Object, Object> permanent = getPermanent();
//        permanent.put("config.properties", prop);
    }

    @Override
    protected void bind(EventBinder eventBinder) {


//        eventBinder.addEventBinding("mainFrame", KeyListener.class, "keyPressed",VersionAction.class);
        eventBinder.addEventBinding("mainFrame.jmiOpen", ActionListener.class, "actionPerformed", OpenAction.class);
        eventBinder.addEventBinding("mainFrame.jmiVersion", ActionListener.class, "actionPerformed", VersionAction.class);
        eventBinder.addEventBinding("mainFrame.jmiExit", ActionListener.class, "actionPerformed", ExitAction.class);

        // iframe
        eventBinder.addEventBinding("iFrame", InternalFrameListener.class, "internalFrameOpened", InitIFrameAction.class);

       
        eventBinder.addEventBinding("dashboardPanel", ComponentCreationListener.class, "componentCreated", DashboardCreatedAction.class);
        eventBinder.addEventBinding("jtree", TreeSelectionListener.class, "valueChanged", SelectTreeAction.class);

        // textviewer
        eventBinder.addEventBinding("textViewerPanel", ComponentCreationListener.class, "componentCreated", InitTextViewerAction.class);

        // digest
        eventBinder.addEventBinding("digestViewerPanel.jbtCalculate", ActionListener.class, "actionPerformed", DigestValueAction.class);

        // verifier
        eventBinder.addEventBinding("verifierPanel.jbtVerify", ActionListener.class, "actionPerformed", VerifyAction.class);
        
    }
}
