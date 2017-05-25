package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.view.View;
import android.widget.TextView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.SimpleTextHM;

/**
 * Created by Long on 5/10/2017.
 */

public class SimpleTextHolder extends BaseViewHolder<SimpleTextHM> {
    TextView textView;
    public SimpleTextHolder(View view) {
        super(view);
        textView = (TextView) view.findViewById(R.id.tvTitle);
    }

    @Override
    public void bind(SimpleTextHM item) {
        textView.setText(item.title);
    }
}
