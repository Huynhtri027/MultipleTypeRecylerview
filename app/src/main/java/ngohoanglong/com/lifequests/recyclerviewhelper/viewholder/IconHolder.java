package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.view.View;
import android.widget.ImageView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.IconHM;

/**
 * Created by Long on 5/10/2017.
 */

public class IconHolder extends BaseViewHolder<IconHM> {
    public IconHolder(View itemView) {
        super(itemView);
        ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
    }

    ImageView ivIcon;
    @Override
    public void bind(IconHM item) {
        ivIcon.setImageResource(item.getImageResource());
    }
}
