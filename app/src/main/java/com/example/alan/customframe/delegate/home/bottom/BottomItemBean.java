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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BottomItemBean)) {
            return false;
        }

        BottomItemBean that = (BottomItemBean) o;
        if (!getICON().equals(that.getICON())) {
            return false;
        }

        return getTITLE().equals(that.getTITLE());
    }

    @Override
    public int hashCode() {
        int result = getICON().hashCode();
        result = 31 * result + getTITLE().hashCode();
        return result;
    }
}
