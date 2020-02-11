package com.renyu.administrator.atapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tips1,tips2;
    private EditText editText;
    private String testStr = "@测试@user:13666660002123功能";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tips1 = (TextView) findViewById(R.id.tips1);
        tips2 = (TextView) findViewById(R.id.tips2);
        editText = (EditText) findViewById(R.id.editText);

        tips2.setMovementMethod(LinkMovementMethod.getInstance());
        editText.setMovementMethod(LinkMovementMethod.getInstance());

        AtUtils.setText(editText,testStr);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged","start="+start+" before="+before+" count="+count);
                if(start==s.length()-1 && s.toString().endsWith("@")){
                    Intent intent = new Intent(MainActivity.this,UserActivity.class);
                    startActivityForResult(intent,123);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();
        tips1.setText(str);
        AtUtils.setText(tips2,str);
        Toast.makeText(this, tips2.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null){
            User user = (User) data.getSerializableExtra("user");
            if(user!=null){
                appendAt(user);
            }
        }
    }

    private void appendAt(User user){
        editText.getText().delete(editText.getText().length()-1,editText.getText().length());
        editText.append(AtUtils.getSpan(editText,"@user:"+user.phone));
        editText.append(",");
    }
}
