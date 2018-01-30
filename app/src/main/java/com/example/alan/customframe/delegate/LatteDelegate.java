package com.example.alan.customframe.delegate;

/**
 * @author Alan
 */

public abstract  class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
