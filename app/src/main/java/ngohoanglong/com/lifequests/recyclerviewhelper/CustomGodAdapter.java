package ngohoanglong.com.lifequests.recyclerviewhelper;

import android.view.View;

import java.util.Collections;
import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.helper.ItemTouchHelperAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactory;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;

/**
 * Created by Long on 5/10/2017.
 */

public class CustomGodAdapter extends GodAdapter implements ItemTouchHelperAdapter {
    private static final String TAG = CustomGodAdapter.class.getSimpleName();

    public CustomGodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, GodAdapter.OnClickEvent onClickEvent) {
        super(baseHMs,holderFactory,onClickEvent);
    }
    public CustomGodAdapter(HolderFactory holderFactory, GodAdapter.OnClickEvent onClickEvent) {
        super(holderFactory,onClickEvent);
    }
    AddHM addHM = new AddHM();
    @Override
    public int getItemCount() {
        if(addHM!=null){
            return getListSize()+1;
        }
        return baseHMs.size();
    }
    int getListSize(){
        return baseHMs.size();
    }
    int getAddPosition(){
        return baseHMs.size();
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<BaseHM> holder, final int position) {

        if(position==getAddPosition()){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEvent.onItemClick(position, addHM);
                }
            });
        }else{

            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position!=getAddPosition()){
            return baseHMs.get(position).getHolderType(holderFactory);
        }
        return addHM.getHolderType(holderFactory);

    }

    @Override
    public void onItemDismiss(int position) {
        baseHMs.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(baseHMs, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
