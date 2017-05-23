package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import java.io.Serializable;

/**
 * Created by Long on 5/10/2017.
 */

public abstract class BaseHM implements Serializable, Visitable {
    protected  boolean selectable = true;
    protected  boolean isSelected = false;

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
