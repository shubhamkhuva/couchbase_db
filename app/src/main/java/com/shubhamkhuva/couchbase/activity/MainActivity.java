package com.shubhamkhuva.couchbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.couchbase.lite.ResultSet;
import com.shubhamkhuva.couchbase.R;
import com.shubhamkhuva.couchbase.datamanger.DatabaseManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager dm = new DatabaseManager();
        String dociD = dm.openAndStoreString(getApplicationContext(),"Key","ABCDEFGHIJKSL");

        String results = dm.getAuthKey(getApplicationContext(),dociD);
        Toast.makeText(this, results, Toast.LENGTH_SHORT).show();

    }
}
