package ngohoanglong.com.lifequests;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 5/25/2017.
 */

public class EventWrapper {
    private static final String TAG = EventWrapper.class.getSimpleName();
    public static List<Abc> abcs = new ArrayList<>();

    public static void chooseHolderType(int pos){
        for (Abc abc: abcs
             ) {
            abc.wrapperListener.doWork(new EventAbc("chooseHolderType",pos));
        }
    }

    public static void pushEvent(EventAbc eventAbc){
        Log.d(TAG, "pushEvent: ");
        for (Abc abc: abcs
                ) {
            abc.wrapperListener.doWork(eventAbc);
        }
    }
    public static class Abc{
         String channel;
         WrapperListener wrapperListener;

        public Abc(String channel, WrapperListener wrapperListener) {
            this.channel = channel;
            this.wrapperListener = wrapperListener;
        }
    }
    public static class EventAbc{
        String event;
        Object object;

        public EventAbc(String event, Object object) {
            this.event = event;
            this.object = object;
        }
    }
    public static void subscribe(Abc abc){
        abcs.add(abc);
        Log.d(TAG, "subscribe: "+abcs.size());
    }
    public static void unSubscribe(Abc abc){
        abcs.remove(abc);
        Log.d(TAG, "unsubscribe: "+abcs.size());
    }
    public interface WrapperListener{
        void doWork(EventAbc  eventAbc);
    }
}
