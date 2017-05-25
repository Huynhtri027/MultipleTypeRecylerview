package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import java.io.Serializable;

import ngohoanglong.com.lifequests.EventWrapper;

/**
 * Created by Long on 5/10/2017.
 */

public abstract class BaseHM implements Serializable, Visitable {
    protected  boolean selectable = false;
    protected  boolean isSelected = false;
    protected EventWrapper.EventAbc eventAbc;

    public EventWrapper.EventAbc getEventAbc() {
        return eventAbc;
    }

    public void setEventAbc(EventWrapper.EventAbc eventAbc) {
        this.eventAbc = eventAbc;
    }

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
