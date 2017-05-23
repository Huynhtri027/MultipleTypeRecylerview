package ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderTypeFactory;

/**
 * Created by Long on 5/10/2017.
 */

public class InfoHM extends BaseHM {
    public static int[] images = {R.drawable.daredevil,R.drawable.deadpool_icon,R.drawable.ironman_icon,R.drawable.logan_icon
            ,R.drawable.spider,R.drawable.spiderman_icon};
    public static String[] titles = {"Daredevil","Deadpool","Ironman","logan"
            ,"Spiderman","Spiderman"};
    public int pos;
    public InfoHM(int pos) {
        this.pos = pos;
    }

    @Override
    public int getHolderType(HolderTypeFactory vmTypeFactory) {
        return vmTypeFactory.getHolderType(this);
    }
    public int getImageResource(){
        return images[pos%images.length];
    }
    public String getTitle(){
        return titles[pos%titles.length];
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InfoHM{ ");
        sb.append("pos=").append(pos);
        sb.append("}\n");
        return sb.toString();
    }

    public InfoHM() {
    }

    public float getRating() {
        return Math.max(3f,pos%6) ;
    }
}
