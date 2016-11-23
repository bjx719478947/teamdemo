package comt.example.administrator.jpushdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static android.content.ContentValues.TAG;

/**
 * Author: zwf(zhaoweifeng@1000phone.com)
 * Date  : 2016-11-18
 * Time  : 11:48
 * Project: JPushDemo
 * Descrite:极光推动接收广播
 */

public class JPushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
//        cn.jpush.android.intent.MESSAGE_RECEIVED
//        cn.jpush.android.intent.MESSAGE_RECEIVED
        String stringExtra = intent.getStringExtra("cn.jpush.android.intent.MESSAGE_RECEIVED");

        Log.e(TAG, "onReceive: "+action+" values:"+stringExtra);

        Bundle extras = intent.getExtras();
        String s = printBundle(extras);
        Log.e(TAG, "onReceive: "+s);



//        key:cn.jpush.android.TITLE, value:
//        key:cn.jpush.android.MESSAGE, value:收到消息请回复谢谢
//        key:cn.jpush.android.CONTENT_TYPE, value:
//        key:cn.jpush.android.APPKEY, value:0a5663e43940ff87f4b75e13
//        key:cn.jpush.android.MSG_ID, value:2912977439
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}
