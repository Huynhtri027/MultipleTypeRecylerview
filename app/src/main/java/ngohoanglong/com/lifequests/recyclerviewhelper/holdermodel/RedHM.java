package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class RedHM extends BaseHM {

    public int pos;
    public RedHM(int pos) {
        this.pos = pos;
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RedHM{ ");
        sb.append("pos=").append(pos);
        sb.append("}\n");
        return sb.toString();
    }

    public RedHM() {
    }

}
