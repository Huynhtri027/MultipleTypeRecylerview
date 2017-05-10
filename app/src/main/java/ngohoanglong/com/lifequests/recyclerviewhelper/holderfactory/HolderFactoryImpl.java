package ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory;

import android.view.View;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BlueHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GreenHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.RedHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.AddHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BlueHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.GreenHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.RedHolder;


/**
 * Created by Long on 10/5/2016.
 */

public class HolderFactoryImpl implements HolderFactory {

    private static final int ITEM_BLUE = R.layout.layout_blue_item;
    private static final int ITEM_RED = R.layout.layout_red_item;
    private static final int ITEM_GREEN = R.layout.layout_green_item;
    private static final int ITEM_ADD = R.layout.layout_add_item;


    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case ITEM_BLUE:
                return new BlueHolder(view);
            case ITEM_GREEN:
                return new GreenHolder(view);
            case ITEM_RED:
                return new RedHolder(view);
            case ITEM_ADD:
                return new AddHolder(view);
        }
        return null;
    }

    @Override
    public int getHolderType(BlueHM blueHM) {
        return ITEM_BLUE;
    }

    @Override
    public int getHolderType(GreenHM greenHM) {
        return ITEM_GREEN;
    }

    @Override
    public int getHolderType(RedHM redHM) {
        return ITEM_RED;
    }

    @Override
    public int getHolderType(AddHM addHM) {
        return ITEM_ADD;
    }
}
