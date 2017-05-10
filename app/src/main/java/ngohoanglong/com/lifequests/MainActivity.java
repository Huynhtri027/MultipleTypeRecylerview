package ngohoanglong.com.lifequests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.lifequests.immutablemodel.SimpleItem;
import ngohoanglong.com.lifequests.recyclerviewhelper.CustomGodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.GodAdapter;
import ngohoanglong.com.lifequests.recyclerviewhelper.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.AddHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BaseHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.BlueHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.GreenHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.HorizontalListHM;
import ngohoanglong.com.lifequests.recyclerviewhelper.holdermodel.RedHM;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        rv.setLayoutManager(staggeredGridLayoutManagerVertical);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        List<BaseHM> baseHMs = new Mapper().mapping(Service.getList());
        baseHMs.add(new HorizontalListHM(baseHMs));
        rv.setAdapter(new CustomGodAdapter(baseHMs, new HolderFactoryImpl(),
                new GodAdapter.OnClickEvent() {
                    @Override
                    public void onItemClick(int pos, BaseHM baseHM) {
                        if(baseHM instanceof AddHM){
                            ((GodAdapter)rv.getAdapter()).addItem(new Mapper().mapping(Service.getItem()));
                        }
                    }
                }));
    }
    class Mapper{
        List<BaseHM> mapping(List<SimpleItem> simpleItemlist){
            List<BaseHM> baseHMs = new ArrayList<>();
            for (int i = 0; i < simpleItemlist.size(); i++) {
                switch (i%3){
                    case 0: baseHMs.add(new BlueHM(simpleItemlist.get(i).getText()));
                        break;
                    case 1: baseHMs.add(new GreenHM(simpleItemlist.get(i).getText()));
                        break;
                    case 2: baseHMs.add(new RedHM(simpleItemlist.get(i).getText()));
                        break;
                }

            }
            return baseHMs;
        }
        BaseHM mapping(SimpleItem simpleItem){

            return new BlueHM(simpleItem.getText());
        }
    }
}
