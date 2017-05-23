package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import ngohoanglong.com.lifequests.recyclerviewhelper.GodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;

import static ngohoanglong.com.lifequests.R.id.rv;

/**
 * Created by Long on 5/10/2017.
 */

public class HorizontalListHolder extends BaseViewHolder<HorizontalListHM> {
    RecyclerView recyclerView;
    public HorizontalListHolder(View view) {
        super(view);
        recyclerView = (RecyclerView) itemView.findViewById(rv);

//        lam holder cao chieu dai fit voi parentView
        if(itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }

        final LinearLayoutManager lm =
                new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(lm);
    }

    @Override
    public void bind(final HorizontalListHM item) {
//        phuc hoi trang thai recyclerView
        recyclerView.scrollToPosition(item.getCurrentPosition());

        recyclerView.getLayoutManager().onSaveInstanceState();
        recyclerView.setAdapter(new GodAdapter(item.getBaseHMs(), new HolderFactoryImpl(), new GodAdapter.OnClickEvent() {
            @Override
            public void onItemClick(BaseHM baseHM) {

            }
        }));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                luu trang thai cua recyclerView
                final int pos = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                item.setCurrentPosition(pos);
            }
        });
    }

}
