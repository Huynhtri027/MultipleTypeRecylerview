package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import ngohoanglong.com.lifequests.recyclerviewhelper.GodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;

import static ngohoanglong.com.lifequests.R.id.rv;

/**
 * Created by Long on 5/10/2017.
 */

public class HorizontalListHolder extends BaseViewHolder<HorizontalListHM> {
    public HorizontalListHolder(View view) {
        super(view);
    }

    @Override
    public void bind(HorizontalListHM item) {
        RecyclerView recyclerView = (RecyclerView) itemView.findViewById(rv);
        if(itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
        final LinearLayoutManager lm =
                new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(new GodAdapter(item.getBaseHMs(),new HolderFactoryImpl(),null));
    }
}
