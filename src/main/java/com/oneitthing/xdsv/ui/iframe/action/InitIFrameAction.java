package com.oneitthing.xdsv.ui.iframe.action;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;
import com.oneitthing.xdsv.ui.iframe.IFrame;

public class InitIFrameAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        System.out.println("IFrame opened = " + ((IFrame)parameterMapping.getEventSource()).getFile());
        System.out.println("IFrame opened = " + ((IFrame)parameterMapping.getEventSource()).getRootXmlFileName());


        return false;
    }
}
