package com.renyu.administrator.atapplication;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AtUtils {
    //匹配的正则
    public static final String regEx = "(.|\\n)*@user:1[3,5,7,8]\\d{9}(.|\\n)*";
    //分割字符串的正则
    public static final String splitRegex = "@user:1[3,5,7,8]\\d{9}";

    public static ArrayList<User> usrList;

    static {
        usrList = new ArrayList<>();
        usrList.add(new User("唐僧","13666660001"));
        usrList.add(new User("孙悟空","13666660002"));
        usrList.add(new User("猪八戒","13666660003"));
        usrList.add(new User("沙僧","13666660004"));
        usrList.add(new User("白龙马","13666660005"));
        usrList.add(new User("观世音","13666660006"));
        usrList.add(new User("如来佛","13666660007"));
    }

    public static User findUser(String phone){
        if(usrList==null || usrList.isEmpty()){
            return null;
        }
        for(User user:usrList){
            if(phone.equals(user.phone)){
                return user;
            }
        }
        return null;
    }

    public static void setText(TextView textView, String str){
        if(str.matches(regEx)){
            textView.setText("");
            String[] splitStrs = str.split(splitRegex);
            int num = splitStrs.length;
            int temp = 0;
            for(int i =0; i < num; i++){
                textView.append(splitStrs[i]);
                if((i+1) != num){
                    int n = splitStrs[i].length();
                    n += temp;
                    int m = str.indexOf(splitStrs[i+1], n);
                    String usr = str.substring(n, m);
                    temp = n + usr.length();
                    textView.append(getSpan(textView,usr));
                    Log.d("@Span",usr);
                }
            }
        }else{
            textView.setText(str);
        }
    }

    public static SpannableString getSpan(final TextView textView, String usrStr){
        final String phone = usrStr.split(":")[1];
        String str;
        User user = findUser(phone);
        if(user == null){
            str = "@" + phone;
        }else {
            str = "@" + user.name;
        }
        SpannableString spanText = new SpannableString(usrStr);
        TextView spanTv = (TextView) LayoutInflater.from(textView.getContext()).inflate(R.layout.item_at, (ViewGroup) textView.getParent(), false);
        spanTv.setText(str);
        //第二个参数其实应该是用textView的最大宽度，这里的300是随便写的
        ViewSpan span = new ViewSpan(spanTv,300);
        spanText.setSpan(span, 0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //添加点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(textView.getContext(), phone, Toast.LENGTH_SHORT).show();
            }
        };
        spanText.setSpan(clickableSpan,0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanText;
    }
}
