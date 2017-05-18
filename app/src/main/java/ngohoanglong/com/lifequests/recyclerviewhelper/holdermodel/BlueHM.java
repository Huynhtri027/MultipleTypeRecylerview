package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class BlueHM extends BaseHM {

    public int pos;

    public BlueHM(int pos) {
        this.pos = pos;
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlueHM{ ");
        sb.append("pos=").append(pos)
                .append('\n');
        sb.append("}\n");
        return sb.toString();
    }
}
