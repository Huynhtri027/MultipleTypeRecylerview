package ngohoanglong.com.lifequests.recyclerviewhelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactory;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;

/**
 * Created by Long on 5/10/2017.
 */

public class CustomGodAdapter extends GodAdapter  {
    private static final String TAG = CustomGodAdapter.class.getSimpleName();
    boolean enableAddbutton = true;

    public boolean isEnableAddbutton() {
        return enableAddbutton;
    }

    public void setEnableAddbutton(boolean enableAddbutton) {
        this.enableAddbutton = enableAddbutton;
    }

    public CustomGodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, AdapterListener adapterListener, int selectMode) {
        this(baseHMs,holderFactory, adapterListener);
        selectedManager = new SelectedManager(baseHMs, selectMode,
                new SelectedManager.OnSelectItemListener() {
                    @Override
                    public void afterSelected(Object pos) {
                        notifyDataSetChanged();
                    }
                });
        addHM = new AddHM();
    }
    public CustomGodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, AdapterListener adapterListener) {
        super(baseHMs,holderFactory, adapterListener);
        addHM = new AddHM();
    }

    private AddHM addHM = new AddHM();
    int addType = R.layout.layout_add_item;

    @Override
    public int getItemCount() {
        if(enableAddbutton){
            return getListSize()+1;
        }
        Log.d(TAG, "getItemCount: "+getListSize());
        return getListSize();
    }


    private int getListSize(){
        return baseHMs.size();
    }
    private int getAddPosition(){
        if(enableAddbutton&&addHM!=null){
            return baseHMs.size();
        }else {
            return -99;
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<BaseHM> holder, int position) {
        final int pos = position;
        if(enableAddbutton && pos==getAddPosition()){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(adapterListener !=null){
                        adapterListener.onItemClick(addHM,pos,AdapterListener.ACTION_ADD);
                    }
                }
            });
        }else{
            if(holder!=null){
                final BaseHM baseHM = baseHMs.get(pos);
                holder.bind(baseHM);
                if(adapterListener !=null)
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(selectedManager!=null){
                                selectedManager.onSelectedChange(baseHM);
                            }
                        }
                    });
            }
        }

    }
    public int getDataListSize(){
        return baseHMs.size();
    }
    @Override
    public void addItem(BaseHM item) {
        baseHMs.add(item);
        notifyItemInserted(getDataListSize()-1);
        adapterListener.onItemClick(item,getAddPosition(),AdapterListener.ACTION_AFTER_ADD);
    }

    public void setItem(int pos,BaseHM item) {
        baseHMs.set(pos,item);
        notifyItemChanged(pos);
    }
    @Override
    public int getItemViewType(int position) {
        if(enableAddbutton&&position==getAddPosition()){
            return addHM.getHolderType(holderFactory);
        }
        return baseHMs.get(position).getHolderType(holderFactory);
    }

    public void onItemDismiss(int position) {
        if(avaiablePosition(position)){
            baseHMs.remove(position);
            notifyItemRemoved(position);
        }
    }
    private boolean avaiablePosition(int position){
        return position < getListSize();
    }
    public boolean onItemMove(int fromPosition, int toPosition) {
        if(avaiablePosition(toPosition)){
            baseHMs.set(fromPosition, baseHMs.set(toPosition, baseHMs.get(fromPosition)));
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }
        return false;
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseHM> holder) {
        super.onViewRecycled(holder);
    }

    public static class SelectedManager{
        public final static int SELECTED_MODE_NONE = -1;
        public final static int SELECTED_MODE_SINGLE = 0;
        public final static int SELECTED_MODE_MUTI = 1;
        private int selectedMode = SELECTED_MODE_SINGLE;
        OnSelectItemListener  onSelectItemListener;
        List<BaseHM> hms ;

        public SelectedManager(List<BaseHM> baseHMs,int selectedMode,OnSelectItemListener  onSelectItemListener) {
            this.selectedMode = selectedMode;
            hms=baseHMs;
            this.onSelectItemListener = onSelectItemListener;
        }

        void onSelectedChange(BaseHM item){
            if(selectedMode==-1)return;
            if (!item.isSelectable())return;
            if(selectedMode==SELECTED_MODE_SINGLE){
                for (BaseHM baseHM:hms
                     ) {
                    baseHM.setSelected(false);
                }
                item.setSelected(!item.isSelected());

            }
            if(selectedMode==SELECTED_MODE_MUTI){
                item.setSelected(!item.isSelected());
            }
            onSelectItemListener.afterSelected(item);
        }

        interface OnSelectItemListener{
            void afterSelected(Object pos);
        }
    }

    SelectedManager selectedManager ;

    public MyItemDecoration myItemDecoration = new MyItemDecoration();

    public class MyItemDecoration extends RecyclerView.ItemDecoration{

        private Paint paintRed;
        private int offset;

        public MyItemDecoration(){
            offset = 5;
            paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintRed.setColor(Color.RED);
            paintRed.setStyle(Paint.Style.STROKE);
            paintRed.setStrokeWidth(1);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(offset, offset, offset, offset);
            super.getItemOffsets(outRect, view, parent, state);

        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            for(int i=0; i<parent.getChildCount(); i++){
                if(isDecorable(i)){
                    final View child = parent.getChildAt(i);
                    c.drawRect(
                            layoutManager.getDecoratedLeft(child) + offset,
                            layoutManager.getDecoratedTop(child) + offset,
                            layoutManager.getDecoratedRight(child) - offset,
                            layoutManager.getDecoratedBottom(child) - offset,
                            paintRed);
                }


            }
        }
        private boolean isDecorable(int pos){
            return true;
        }
    }
}
