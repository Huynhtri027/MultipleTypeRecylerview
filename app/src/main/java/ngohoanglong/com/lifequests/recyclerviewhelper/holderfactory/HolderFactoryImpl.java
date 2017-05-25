package ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory;

import android.view.View;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GridHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.IconHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.InfoHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.SimpleTextHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.AddHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.GridHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.HorizontalListHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.IconHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.InfoHolder;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.SimpleTextHolder;


/**
 * Created by Long on 10/5/2016.
 */

public class HolderFactoryImpl implements HolderFactory {

    private static final int ITEM_BLUE = R.layout.layout_blue_item;
    private static final int ITEM_RED = R.layout.layout_red_item;
    private static final int ITEM_GREEN = R.layout.layout_grid_item;
    private static final int ITEM_ADD = R.layout.layout_add_item;
    private static final int ITEM_HL = R.layout.layout_hl_item;
    private static final int ITEM_TEXT = R.layout.layout_text;


    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case ITEM_BLUE:
                return new IconHolder(view);
            case ITEM_GREEN:
                return new GridHolder(view);
            case ITEM_RED:
                return new InfoHolder(view);
            case ITEM_ADD:
                return new AddHolder(view);
            case ITEM_HL:
                return new HorizontalListHolder(view);
            case ITEM_TEXT:
                return new SimpleTextHolder(view);
        }
        return null;
    }

    @Override
    public int getHolderType(IconHM iconHM) {
        return ITEM_BLUE;
    }

    @Override
    public int getHolderType(GridHM gridHM) {
        return ITEM_GREEN;
    }

    @Override
    public int getHolderType(InfoHM redHM) {
        return ITEM_RED;
    }

    @Override
    public int getHolderType(AddHM addHM) {
        return ITEM_ADD;
    }

    @Override
    public int getHolderType(HorizontalListHM horizontalListHM) {
        return ITEM_HL;
    }

    @Override
    public int getHolderType(SimpleTextHM simpleTextHM) {
        return ITEM_TEXT;
    }

}
