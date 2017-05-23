package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class IconHM extends BaseHM {

    public static int[] images = {R.drawable.daredevil,R.drawable.deadpool_icon,R.drawable.ironman_icon,R.drawable.logan_icon
    ,R.drawable.spider,R.drawable.spiderman_icon};
    public int pos;

    public IconHM(int pos) {
        this.pos = pos;
    }
    public int getImageResource(){
        return images[pos%images.length];
    }


    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IconHM{ ");
        sb.append("pos=").append(pos)
                .append('\n');
        sb.append("}\n");
        return sb.toString();
    }
}
