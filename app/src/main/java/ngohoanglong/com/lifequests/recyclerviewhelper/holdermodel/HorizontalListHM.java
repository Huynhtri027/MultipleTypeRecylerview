package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class HorizontalListHM extends BaseHM {
    public List<BaseHM> baseHMs = new ArrayList<>();

//   de giu trang thai cua recyclerView
    int currentPosition = 0;

    public HorizontalListHM(List<BaseHM> baseHMs) {
        this.baseHMs = baseHMs;
    }

    public List<BaseHM> getBaseHMs() {
        return baseHMs;
    }

    public void setBaseHMs(List<BaseHM> baseHMs) {
        this.baseHMs = baseHMs;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HorizontalListHM{ \n");
        sb.append("}\n");
        return sb.toString();
    }
}
