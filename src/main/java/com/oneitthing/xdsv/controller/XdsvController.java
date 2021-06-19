package com.oneitthing.xdsv.controller;

import java.awt.event.ActionListener;

import javax.swing.event.InternalFrameListener;

import com.oneitthing.swingcontrollerizer.controller.BaseController;
import com.oneitthing.swingcontrollerizer.controller.ClientConfig;
import com.oneitthing.swingcontrollerizer.controller.EventBinder;
import com.oneitthing.xdsv.ui.iframe.action.ButtonAction;
import com.oneitthing.xdsv.ui.iframe.action.InitIFrameAction;
import com.oneitthing.xdsv.ui.main.action.ExitAction;
import com.oneitthing.xdsv.ui.main.action.OpenAction;
import com.oneitthing.xdsv.ui.main.action.VersionAction;


public class XdsvController extends BaseController {

    @Override
    protected void initialize(ClientConfig config) {
//        Properties prop = ResourceUtil.instance.asProperties("conf/config.properties");
//        Map<Object, Object> permanent = getPermanent();
//        permanent.put("config.properties", prop);
    }

    @Override
    protected void bind(EventBinder eventBinder) {

        // メイン画面
//        eventBinder.addEventBinding("mainFrame", KeyListener.class, "keyPressed",VersionAction.class);
        eventBinder.addEventBinding("mainFrame.jmiOpen", ActionListener.class, "actionPerformed", OpenAction.class);
        eventBinder.addEventBinding("mainFrame.jmiVersion", ActionListener.class, "actionPerformed", VersionAction.class);
        eventBinder.addEventBinding("mainFrame.jmiExit", ActionListener.class, "actionPerformed", ExitAction.class);

        // internalFrameOpenedが発火しないバグあり、internalFrameActivatedで代用する
//        eventBinder.addEventBinding("iFrame", InternalFrameListener.class, "internalFrameOpened", InitIFrameAction.class);
        eventBinder.addEventBinding("iFrame", InternalFrameListener.class, "internalFrameActivated", InitIFrameAction.class);
        eventBinder.addEventBinding("iFrame.btnTest", ActionListener.class, "actionPerformed", ButtonAction.class);

    }
}
