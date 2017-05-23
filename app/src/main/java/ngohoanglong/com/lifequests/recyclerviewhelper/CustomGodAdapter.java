package ngohoanglong.com.lifequests.recyclerviewhelper;

import android.util.Log;
import android.view.View;

import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactory;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;

/**
 * Created by Long on 5/10/2017.
 */

public class CustomGodAdapter extends GodAdapter  {
    private static final String TAG = CustomGodAdapter.class.getSimpleName();

    public CustomGodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, GodAdapter.OnClickEvent onClickEvent) {
        super(baseHMs,holderFactory,onClickEvent);
    }


    private AddHM addHM = new AddHM();
    @Override
    public int getItemCount() {
        if(addHM!=null){
            return getListSize()+1;
        }
        return baseHMs.size();
    }
    private int getListSize(){
        return baseHMs.size();
    }
    private int getAddPosition(){
        return baseHMs.size();
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<BaseHM> holder,  int position) {

        if(position==getAddPosition()&&onClickEvent!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEvent.onItemClick(addHM);
                }
            });
        }else{

            super.onBindViewHolder(holder, position);
        }
    }
    public int getDataListSize(){
        return baseHMs.size();
    }
    @Override
    public void addItem(BaseHM item) {
        baseHMs.add(item);
        notifyItemInserted(getDataListSize()-1);
    }

    @Override
    public int getItemViewType(int position) {
        if(position!=getAddPosition()){
            return baseHMs.get(position).getHolderType(holderFactory);
        }
        return addHM.getHolderType(holderFactory);

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
//            Collections.swap(baseHMs, fromPosition, toPosition);
//            why it not work
            baseHMs.set(fromPosition, baseHMs.set(toPosition, baseHMs.get(fromPosition)));
            Log.d(TAG, "onItemMove: "+baseHMs);
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }
        return false;
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseHM> holder) {
        super.onViewRecycled(holder);
    }
}
