package com.renyu.administrator.atapplication;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;


public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,AtUtils.usrList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("user",AtUtils.usrList.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
