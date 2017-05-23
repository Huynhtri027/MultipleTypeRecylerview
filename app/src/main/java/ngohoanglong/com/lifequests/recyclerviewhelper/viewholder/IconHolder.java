package ngohoanglong.com.lifequests.recyclerviewhelper.viewholder;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import ngohoanglong.com.lifequests.R;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.IconHM;

/**
 * Created by Long on 5/10/2017.
 */

public class IconHolder extends BaseViewHolder<IconHM> {
    private static final String TAG = IconHolder.class.getSimpleName();
    ObservableBoolean isSelected = new ObservableBoolean(false);
    public IconHolder(View itemView) {
        super(itemView);
        ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
        isSelected.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                final boolean isSelected = ((ObservableBoolean)observable).get();
                if(isSelected){
                    itemView.setAlpha(0.5f);
                }else {
                    itemView.setAlpha(1f);
                }
                Log.d(TAG, "onPropertyChanged: "+isSelected);
            }
        });
    }

    ImageView ivIcon;
    @Override
    public void bind(IconHM item) {
        if(item.isSelected()){
            itemView.setAlpha(0.5f);
        }else {
            itemView.setAlpha(1f);
        }
        isSelected.set(item.isSelected());
        ivIcon.setImageResource(item.getImageResource());
    }
}
