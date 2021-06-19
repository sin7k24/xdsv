package com.oneitthing.xdsv.ui.iframe.action;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;

public class ButtonAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
System.out.println("Clicked!");
        return false;
    }


}
