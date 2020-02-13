package com.renyu.administrator.atapplication;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView listView = (RecyclerView) findViewById(R.id.listView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        Intent intentActivity = getIntent();
        Bundle bundle = intentActivity.getExtras();
        ArrayList<String> list = (ArrayList<String> )bundle.getSerializable("datalist");

        Myadapter myadapter = new Myadapter(android.R.layout.simple_list_item_1, list);
        myadapter.setOnItemClickListener( (adapter,view,postsition)->{

                Intent intent = new Intent();
                intent.putExtra("user",AtUtils.usrList.get(postsition));
                setResult(RESULT_OK,intent);
                finish();
                }
        );
        listView.setAdapter(myadapter);

    }

    class Myadapter extends BaseQuickAdapter{

        public Myadapter(int layoutResId, @Nullable List data) {
            super(layoutResId, data);
        }

        public Myadapter(@Nullable List data) {
            super(data);
        }

        public Myadapter(int layoutResId) {
            super(layoutResId);
        }

        /**
         * 初始化显示用
         * @param helper
         * @param item
         */
        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            String user = (String) item;

           ((TextView) helper.getView(android.R.id.text1)).setText(user);

        }

    }
}

