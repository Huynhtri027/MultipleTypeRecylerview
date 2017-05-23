package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.InfoHM;

/**
 * Created by Long on 5/10/2017.
 */

public class InfoHolder extends BaseViewHolder<InfoHM> {

    public InfoHolder(View itemView) {
        super(itemView);
        ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
        textView = (TextView) itemView.findViewById(R.id.tvTitle);
        ratingBar = (RatingBar) itemView.findViewById(R.id.rating);
    }
    ImageView ivIcon;
    TextView textView;
    RatingBar ratingBar;

    @Override
    public void bind(InfoHM item) {
        ivIcon.setImageResource(item.getImageResource());
        textView.setText(item.getTitle());
        ratingBar.setRating(item.getRating());
    }


}
