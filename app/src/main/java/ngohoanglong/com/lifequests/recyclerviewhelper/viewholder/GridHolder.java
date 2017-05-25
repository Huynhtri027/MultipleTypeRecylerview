package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.CustomGodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.GodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GridHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.IconHM;

/**
 * Created by Long on 5/10/2017.
 */

public class GridHolder extends BaseViewHolder<GridHM> {

    RecyclerView rvIcons;
    public GridHolder(View itemView) {
        super(itemView);
        rvIcons = (RecyclerView) itemView.findViewById(R.id.rvIcons);
        if(itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }


    @Override
    public void bind(final GridHM item) {
        for (BaseHM baseHM: item.baseHMs
             ) {
            baseHM.setSelectable(true);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        CustomGodAdapter customGodAdapter = new CustomGodAdapter(item.baseHMs, new HolderFactoryImpl(), new GodAdapter.AdapterListener() {
            @Override
            public void onItemClick(BaseHM baseHM,int pos,int actionType) {
                if (baseHM instanceof AddHM) {
                    final BaseHM baseHM1 = new IconHM(item.baseHMs.size());
                    baseHM1.setSelectable(true);
                    ((GodAdapter) rvIcons.getAdapter()).addItem(baseHM1);
                    rvIcons.smoothScrollToPosition(pos);
                }
            }
        },CustomGodAdapter.SelectedManager.SELECTED_MODE_MUTI);
        rvIcons.setLayoutManager(gridLayoutManager);
        rvIcons.setAdapter(customGodAdapter);
    }


}
