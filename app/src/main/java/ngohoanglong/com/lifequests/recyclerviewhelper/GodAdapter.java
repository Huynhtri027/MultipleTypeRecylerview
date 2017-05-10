package ngohoanglong.com.lifequests.recyclerviewhelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactory;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;

/**
 * Created by Long on 5/10/2017.
 */

public class GodAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseHM>> {

    List<BaseHM> baseHMs;
    protected HolderFactory holderFactory ;
    protected OnClickEvent onClickEvent;

    public GodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, OnClickEvent onClickEvent) {
        this.baseHMs = baseHMs;
        this.holderFactory = holderFactory;
        this.onClickEvent = onClickEvent;
    }

    @Override
    public BaseViewHolder<BaseHM> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent != null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return holderFactory.createHolder(viewType, view);
        }
        throw new RuntimeException("Parent is null");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseHM> holder, final int position) {
        if(holder!=null){
            final BaseHM baseHM = baseHMs.get(position);
            holder.bind(baseHM);
            if(onClickEvent!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickEvent.onItemClick(position, baseHM);
                    }
                });
            }
        }
    }
public void addItem(BaseHM item){
    baseHMs.add(item);
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return baseHMs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return baseHMs.get(position).getHolderType(holderFactory);
    }

    public interface OnClickEvent {
        void onItemClick(int pos, BaseHM baseHM);
    }
}
