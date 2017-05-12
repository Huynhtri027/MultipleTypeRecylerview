package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class AddHM extends BaseHM {


    public AddHM() {
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }
}
