package ngohoanglong.com.lifequests;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.lifequests.immutablemodel.SimpleItem;
import ngohoanglong.com.lifequests.recyclerviewhelper.CustomGodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.GodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BlueHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GreenHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.RedHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.AddHolder;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView rv;
    Toolbar toolbar;
    View wrapper;
    List<BaseHM> baseHMs = new ArrayList<>();
    CustomGodAdapter customGodAdapter;
    Mapper mp = new Mapper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setup wrapView
        wrapper = findViewById(R.id.wrapper);
        wrapper.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

//        setupActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setTitle("Touching a item for a while to drag item");
        animateToolbar(toolbar);

//        setup RV
        rv = (RecyclerView) findViewById(R.id.rv);
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        rv.setLayoutManager(staggeredGridLayoutManagerVertical);
        customGodAdapter = new CustomGodAdapter(baseHMs,new HolderFactoryImpl(),
                new GodAdapter.OnClickEvent() {
                    @Override
                    public void onItemClick( BaseHM baseHM) {
                        if (baseHM instanceof AddHM) {
                            ((GodAdapter) rv.getAdapter()).addItem(mp.mapping(Service.getItem()));
                        }
                    }
                });

//      drag and swipe item
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE||actionState == ItemTouchHelper.ACTION_STATE_DRAG){
//                    viewHolder.itemView.setAlpha(0.7f);
//                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Log.d("clearView", "clearView: ");
                viewHolder.itemView.setAlpha(1);
                super.clearView(recyclerView, viewHolder);


            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                if (viewHolder instanceof AddHolder) {
                    return false;
                }
                customGodAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                customGodAdapter.onItemDismiss(direction);
            }
        });
        mItemTouchHelper.attachToRecyclerView(rv);
        rv.setAdapter(customGodAdapter);

//       restore state
        if(getLastCustomNonConfigurationInstance()!=null){
            baseHMs = (List<BaseHM>) getLastCustomNonConfigurationInstance();
            Log.d(TAG, "onCreate: "+baseHMs.toString());
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return baseHMs;
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return super.getLastCustomNonConfigurationInstance();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        call services here

        if(baseHMs.size()==0){
            Log.d(TAG, "onPostCreate: ");
            baseHMs.addAll(mp.mapping(Service.getList()));
            final List<BaseHM> copyList = new ArrayList<>(baseHMs);
            baseHMs.add(new HorizontalListHM(copyList));
            baseHMs.add(new HorizontalListHM(copyList));
            customGodAdapter.addList(baseHMs);
        }
        customGodAdapter.addList(baseHMs);
    }

    private void animateToolbar(Toolbar toolbar) {
        View t = toolbar.getChildAt(0);
        if (t != null && t instanceof TextView) {
            TextView title = (TextView) t;
            title.setTextSize(16f);
            // fade in and space out the title.  Animating the letterSpacing performs horribly so
            // fake it by setting the desired letterSpacing then animating the scaleX ¯\_(ツ)_/¯
            title.setAlpha(0f);
            title.setScaleX(0.6f);
            title.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .setStartDelay(300)
                    .setDuration(500)
                    .setInterpolator(new AccelerateInterpolator());
        }
    }

    private class Mapper{
        List<BaseHM> mapping(List<SimpleItem> simpleItemlist){
            List<BaseHM> baseHMs = new ArrayList<>();
            for (int i = 0; i < simpleItemlist.size(); i++) {
                baseHMs.add(mapping(simpleItemlist.get(i)));
            }
            return baseHMs;
        }
        BaseHM mapping(SimpleItem simpleItem){
            BaseHM baseHM=null;
            switch (simpleItem.getPos()%3){
                case 0:
                    baseHM = new BlueHM(simpleItem.getPos());
                    break;
                case 1:
                    baseHM = new GreenHM(simpleItem.getPos());
                    break;
                case 2:
                    baseHM = new RedHM(simpleItem.getPos());
                    break;
            }
            return baseHM;
        }
    }
}
