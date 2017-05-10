package ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory;

import android.view.View;

import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;


/**
 * Created by Long on 10/5/2016.
 */

public interface HolderFactory extends HolderTypeFactory {
    BaseViewHolder createHolder(int type, View view);
}
