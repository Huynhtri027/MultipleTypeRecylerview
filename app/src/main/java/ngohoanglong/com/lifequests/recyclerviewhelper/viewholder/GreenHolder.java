package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.view.View;
import android.widget.TextView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GreenHM;

/**
 * Created by Long on 5/10/2017.
 */

public class GreenHolder extends BaseViewHolder<GreenHM> {

    TextView textView;
    public GreenHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tvPos);
    }


    @Override
    public void bind(GreenHM item) {
        textView.setText(""+item.pos);
    }


}
