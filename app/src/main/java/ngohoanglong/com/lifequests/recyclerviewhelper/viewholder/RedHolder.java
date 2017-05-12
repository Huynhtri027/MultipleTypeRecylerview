package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.RedHM;

/**
 * Created by Long on 5/10/2017.
 */

public class RedHolder extends BaseViewHolder<RedHM> {

    public RedHolder(View itemView) {
        super(itemView); textView = (TextView) itemView.findViewById(R.id.tvPos);
        if(itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }
    TextView textView;

    @Override
    public void bind(RedHM item) {
        textView.setText(""+item.pos);

    }


}
