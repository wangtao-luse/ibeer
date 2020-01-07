package com.ibeer.common;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {
    @Override
    protected void initOther() {
        groupTemplate.registerFunctionPackage("panshiro", new ShiroExt());
    }
}
