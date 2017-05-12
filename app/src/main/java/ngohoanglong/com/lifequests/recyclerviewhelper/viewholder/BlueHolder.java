package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.view.View;
import android.widget.TextView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BlueHM;

/**
 * Created by Long on 5/10/2017.
 */

public class BlueHolder extends BaseViewHolder<BlueHM> {
    public BlueHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tvPos);
    }
    TextView textView;
    @Override
    public void bind(BlueHM item) {
        textView.setText(""+item.pos);
    }
}
