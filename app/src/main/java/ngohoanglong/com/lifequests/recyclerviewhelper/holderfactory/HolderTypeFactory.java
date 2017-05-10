package ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory;


import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BlueHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GreenHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.RedHM;

/**
 * Created by Long on 11/10/2016.
 */
public interface HolderTypeFactory {
    int getHolderType(BlueHM blueHM);

    int getHolderType(GreenHM greenHM);

    int getHolderType(RedHM redHM);

    int getHolderType(AddHM addHM);

    int getHolderType(HorizontalListHM horizontalListHM);
}
