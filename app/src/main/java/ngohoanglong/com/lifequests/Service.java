package ngohoanglong.com.lifequests;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.lifequests.immutablemodel.SimpleItem;

/**
 * Created by Long on 5/10/2017.
 */

public class Service {
    static int count = 0;
    public static List<SimpleItem> getList(){
        List<SimpleItem> simpleItems = new ArrayList<>();
        for (int i = count; i < count+10; i++) {
            simpleItems.add(new SimpleItem(i));
        }
        count =+simpleItems.size();
        return  simpleItems;
    }
    public static SimpleItem getItem(){
        return  new SimpleItem(++count);
    }
}
