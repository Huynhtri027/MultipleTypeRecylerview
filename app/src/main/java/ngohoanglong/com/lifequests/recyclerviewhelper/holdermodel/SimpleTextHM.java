package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/25/2017.
 */

public class SimpleTextHM extends BaseHM {

    public String title;

    public SimpleTextHM(String title) {
        this.title = title;
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }
}
