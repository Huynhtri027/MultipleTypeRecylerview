package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class GridHM extends BaseHM {

    public final List<BaseHM> baseHMs;
    public int coloum=1;


    public GridHM(List<BaseHM> baseHMs) {
        this.baseHMs = baseHMs;
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GridHM{ ");
        sb.append("baseHMs=").append(baseHMs.toString())
                .append('\n');
        sb.append("}\n");
        return sb.toString();
    }
}
