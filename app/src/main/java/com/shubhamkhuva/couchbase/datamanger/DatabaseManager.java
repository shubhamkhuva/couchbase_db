package com.shubhamkhuva.couchbase.datamanger;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Document;
import com.couchbase.lite.EncryptionKey;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private static String dbName = "DbCouchbase";
    private static String password = "Shubham";
    public String openAndStoreString(Context context, String key, String value){
        DatabaseConfiguration config = new DatabaseConfiguration(context);

        try {
            Database database = new Database(dbName, config);
            JsonObject json = new JsonObject();
            json.addProperty(key,value);
            Map<String,Object> map = new HashMap<>();

            Object obj = new Gson().fromJson(json, Object.class);
            map.put("json",obj);

            MutableDocument doc = new MutableDocument(map);

            database.save(doc);
            return doc.getId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
    public String getAuthKey(Context context,String docid){
        DatabaseConfiguration config = new DatabaseConfiguration(context);

        Database database = null;
        try {
            database = new Database(dbName, config);
            Document getdoc = database.getDocument(docid);
            Map<String,Object> mapnew;
            mapnew = getdoc.toMap();
            Object jso = mapnew.values();
            Gson gson = new Gson();
            String jsonStr = gson.toJson(jso);
            JsonArray tos = gson.fromJson(jsonStr, JsonArray.class);
            JsonObject jsonob = (JsonObject) tos.get(0);
            return jsonob.get("Key").getAsString();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
