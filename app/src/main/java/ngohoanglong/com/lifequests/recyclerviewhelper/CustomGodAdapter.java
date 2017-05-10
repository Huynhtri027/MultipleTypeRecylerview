package ngohoanglong.com.lifequests.recyclerviewhelper;

import android.view.View;

import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactory;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;

/**
 * Created by Long on 5/10/2017.
 */

public class CustomGodAdapter extends GodAdapter {


    public CustomGodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, GodAdapter.OnClickEvent onClickEvent) {
        super(baseHMs,holderFactory,onClickEvent);
    }

    AddHM addHM = new AddHM(1);
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
    public void onBindViewHolder(BaseViewHolder<BaseHM> holder, final int position) {
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
}
