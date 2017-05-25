package ngohoanglong.com.lifequests.recyclerviewhelper;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactory;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.viewholder.BaseViewHolder;

/**
 * Created by Long on 5/10/2017.
 */

public class GodAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseHM>> {

    final List<BaseHM> baseHMs ;
    HolderFactory holderFactory ;
    AdapterListener adapterListener;

    public List<BaseHM> getList() {
        return baseHMs;
    }

    public GodAdapter(List<BaseHM> baseHMs, HolderFactory holderFactory, AdapterListener adapterListener) {
        this.baseHMs = baseHMs;
        this.holderFactory = holderFactory;
        this.adapterListener = adapterListener;
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
    public void onBindViewHolder(BaseViewHolder<BaseHM> holder, int position) {
        if(holder!=null){
            final int pos = position;
            final BaseHM baseHM = baseHMs.get(position);
            holder.bind(baseHM);
            if(adapterListener !=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapterListener.onItemClick(baseHM,pos,AdapterListener.ACTION_CLICK);
                    }
                });
            }
        }
    }
public void addItem(BaseHM item){
    baseHMs.add(item);
    notifyItemInserted(getItemCount()-1);
}
    public void addList(List<BaseHM> items){
        baseHMs.addAll(items);
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

    public interface AdapterListener {
        @IntDef({ACTION_ADD,ACTION_AFTER_ADD,ACTION_CLICK,ACTION_UPDATE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface ActionType {}
         int ACTION_ADD = 0;
         int ACTION_AFTER_ADD = 1;
         int ACTION_CLICK = 2;
         int ACTION_UPDATE = 3;

        void onItemClick(@Nullable BaseHM baseHM, @Nullable int pos, @ActionType int actionType);
    }
}
