package com.oneitthing.xdsv.ui.main.action;

import com.oneitthing.swingcontrollerizer.action.BaseAction;
import com.oneitthing.swingcontrollerizer.controller.ParameterMapping;

public class VersionAction extends BaseAction {

    @Override
    protected boolean prepare(ParameterMapping parameterMapping) throws Exception {
        System.out.println("Version Action!");
        showMessageDialog("proto 0.0.1", "version");

        return false;
    }
}
