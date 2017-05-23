package ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory;


import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GridHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.IconHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.InfoHM;

/**
 * Created by Long on 11/10/2016.
 */
public interface HolderTypeFactory {
    int getHolderType(IconHM iconHM);

    int getHolderType(GridHM gridHM);

    int getHolderType(InfoHM redHM);

    int getHolderType(AddHM addHM);

    int getHolderType(HorizontalListHM horizontalListHM);
}
