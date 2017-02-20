package com.example.nikhiljadhav.pocrealm;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.nikhiljadhav.pocrealm.model.CrimePinData;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by nikhil.jadhav on 2/7/2017.
 */

public class DataManager {

    private static DataManager _instance;
    private Realm mRealm;

    private DataManager(){

    }

    public static DataManager getInstance(){
        if(_instance == null){
            _instance = new DataManager();
        }
        return _instance;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = MainApplication.getAppContext().getAssets().open("db.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private Realm getRealmInstance(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(MainApplication.getAppContext()).deleteRealmIfMigrationNeeded()
                .build();
        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex){
            }
        }
        return null;
    }


    public void loadDB() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson =new GsonBuilder()
                        .setExclusionStrategies(new ExclusionStrategy() {
                            @Override
                            public boolean shouldSkipField(FieldAttributes f) {
                                return f.getDeclaringClass().equals(RealmObject.class);
                            }

                            @Override
                            public boolean shouldSkipClass(Class<?> clazz) {
                                return false;
                            }
                        })
                        .create();
//                Type listType = new TypeToken<ArrayList<CrimePinData>>() {}.getType();
                ArrayList<CrimePinData> data = new ArrayList<CrimePinData>();
                JSONArray arrays = null;
                try {
                    arrays = new JSONArray();
                    StringTokenizer st = new StringTokenizer(loadJSONFromAsset(),"}" );
                    while (st.hasMoreTokens()) {
                        String tmp = st.nextToken().replace("[","").replace("]","");
                        if(tmp.charAt(0) == ',')
                            tmp = tmp.replace(",{","{");
                        data.add(gson.fromJson(tmp+"}", CrimePinData.class));
                    }

                } catch (Exception e) {
//                    e.printStackTrace();
                }
//                mRealm = Realm.getInstance(MainApplication.getAppContext());


              mRealm = getRealmInstance();
                long time;
                if(data.size() >0);{

                    mRealm.beginTransaction();
                    time =  System.currentTimeMillis();

                    List<CrimePinData> realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 1 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    time = System.currentTimeMillis();
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 2 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    time = System.currentTimeMillis();
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 3 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 4 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    time = System.currentTimeMillis();
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 5 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    time = System.currentTimeMillis();
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 6 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 7 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    time = System.currentTimeMillis();
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 8 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    time = System.currentTimeMillis();
                    realmRepos = mRealm.copyToRealm(data);
                    Log.d("RealmPOC", "Time Difference 9 insert 10006 items " + (System.currentTimeMillis() - time)+" ms");
                    mRealm.commitTransaction();
                }
                mRealm.close();
                mRealm = getRealmInstance();
                time = System.currentTimeMillis();
               long size =  mRealm.allObjects(CrimePinData.class).size();
                Log.d("RealmPOC", "Time Difference Fetch "+ size + " data " + (System.currentTimeMillis() - time)+" ms");
                time = System.currentTimeMillis();
                size = mRealm.where(CrimePinData.class)
                        .between("la", -12.347624986470729, 77.31508043549685)
                        .between("lo",-150.1667584106326, -43.542502708733075)
                        .count();
                //Cursor crimesInRange = db.getSpecific(-12.347624986470729, -150.1667584106326,77.31508043549685, -43.542502708733075, 100);

                Log.d("RealmPOC", "Time Differecnce search query (count "+ size + ") " + (System.currentTimeMillis() - time)+" ms");
//                = gson.fromJson(loadJSONFromAsset(), listType);
            }
        }).start();


    }

}
