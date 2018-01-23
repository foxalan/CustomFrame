package com.example.alan.customframe.delegate.home.bottom;

/**
 * Function :
 * Modify Date : 2018/1/23
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class BottomItemBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomItemBean(CharSequence ICON, CharSequence TITLE) {
        this.ICON = ICON;
        this.TITLE = TITLE;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
