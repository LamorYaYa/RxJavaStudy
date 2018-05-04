package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.util.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 初识RxJava
 *
 * @author master
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mContentTextView;
    private Button mHttpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentTextView = findViewById(R.id.tv_content);
        mHttpButton = findViewById(R.id.btn_http);
        mHttpButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        RetrofitUtil.getInstances().getService().getAllStories("latest")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        o -> mContentTextView.setText(o.toString()),
                        throwable -> mContentTextView.setText(throwable.toString()),
                        () -> {
                        });
    }
}
