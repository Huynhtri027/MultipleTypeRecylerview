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

import ngohoanglong.com.lifequests.model.SimpleItem;
import ngohoanglong.com.lifequests.recyclerviewhelper.CustomGodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.GodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GridHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.IconHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.InfoHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.AddHolder;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView rv;
    Toolbar toolbar;
    View wrapper;
    CustomGodAdapter customGodAdapter;
    Mapper mp = new Mapper();
    boolean needCreate = true;

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


    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return new State(customGodAdapter.getList(),needCreate);
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return super.getLastCustomNonConfigurationInstance();
    }


    private BaseHM createGridHM(){
        List<BaseHM> icons= new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            icons.add(new IconHM(i));
        }
        return new GridHM(icons);

    }
    private BaseHM createHorizontalHM(){
        final List<BaseHM> baseHMs = new ArrayList<>(mp.mapping(Service.getList()));
        return new HorizontalListHM(baseHMs);

    }
    private List<BaseHM> createInfoList() {
        List<BaseHM> icons= new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            icons.add(new InfoHM(i));
        }
        return icons;
    }
    void onRestoreState(){
        //       restore state
        List<BaseHM> baseHMs = new ArrayList<>();
        if(getLastCustomNonConfigurationInstance()!=null){
            State state = (State) getLastCustomNonConfigurationInstance();
            baseHMs.addAll(state.baseHMs);
            needCreate = state.needCreate;
            needCreate = baseHMs.size() <= 0;
        }else {
            needCreate = true;
        }

        customGodAdapter = new CustomGodAdapter(baseHMs,new HolderFactoryImpl(),
                    new GodAdapter.AdapterListener() {
                        @Override
                        public void onItemClick( BaseHM baseHM,int pos,int actionType) {
                            if (actionType == ACTION_CLICK) {
                                ((GodAdapter) rv.getAdapter()).addItem(mp.mapping(Service.getItem()));
                            }
                            if (actionType == ACTION_ADD) {
                                rv.smoothScrollToPosition(pos);
                            }
                        }
                    });

        rv.setAdapter(customGodAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        onRestoreState();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        call services here

        if(needCreate){
            Log.d(TAG, "createList ");
            List<BaseHM> newBaseHMs = new ArrayList<>();
            newBaseHMs.add(createGridHM());
            newBaseHMs.add(createHorizontalHM());
            newBaseHMs.addAll(createInfoList());
            customGodAdapter.addList(newBaseHMs);
        }

    }



    private void animateToolbar(Toolbar toolbar) {
        View t = toolbar.getChildAt(0);
        if (t != null && t instanceof TextView) {
            TextView title = (TextView) t;
            title.setTextSize(16f);

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
            switch (simpleItem.getPos()%1){
                case 0:
                    baseHM = new IconHM(simpleItem.getPos());
                    break;
            }
            return baseHM;
        }
    }

    static class State{
        List<BaseHM> baseHMs;
        boolean needCreate ;
        public State(List<BaseHM> baseHMs, boolean needCreate) {
            this.baseHMs = baseHMs;
            this.needCreate = needCreate;
        }
    }
}
