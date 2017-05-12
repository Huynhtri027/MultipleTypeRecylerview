package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;

/**
 * Created by Long on 5/10/2017.
 */

public class AddHolder extends BaseViewHolder<AddHM> {
    public AddHolder(View view) {
        super(view);
        if(itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }

    @Override
    public void bind(AddHM item) {

    }
}
