package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bean.AllStories;
import com.example.http.IServiceApi;
import com.example.http.RetrofitApi;
import com.example.http.ToastUtils;
import com.example.util.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 初识RxJava
 *
 * @author master
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mContentTextView;
    private Button mGetHttpButton;
    private Button mPostHttpButton;
    private Button mTestHttpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentTextView = findViewById(R.id.tv_content);
        mGetHttpButton = findViewById(R.id.btn_http_get);
        mGetHttpButton.setOnClickListener(this);
        mPostHttpButton = findViewById(R.id.btn_http_post);
        mPostHttpButton.setOnClickListener(this);
        mTestHttpButton = findViewById(R.id.btn_http_test);
        mTestHttpButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_http_get:
                RetrofitUtil.getInstances().getService().getAllStories("latest")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                o -> mContentTextView.setText(o.toString()),
                                throwable -> mContentTextView.setText(throwable.toString()),
                                () -> {
                                    //
                                });
                break;
            case R.id.btn_http_post:
                Map<String, String> mHashMap = new HashMap<>();
                mHashMap.put("data", "{\"imei\":\"A0000082C980E4,866321032633946,866321032777214\",\"imsi\":\"865749033211112\",\"phonetime\":\"1529050871264\",\"brand\":\"HUAWEI\",\"model\":\"LLD-AL00\",\"device\":\"HWLLD-H\",\"hardware\":\"hi6250\",\"app\":[{\"appname\":\"云文件夹\",\"packagename\":\"com.huawei.hifolder\",\"crc\":\"c92618eb\"},{\"appname\":\"com.android.cts.priv.ctsshim\",\"packagename\":\"com.android.cts.priv.ctsshim\",\"crc\":\"e5a78134\"},{\"appname\":\"相机\",\"packagename\":\"com.huawei.camera\",\"crc\":\"53cd59ca\"},{\"appname\":\"玩机技巧\",\"packagename\":\"com.huawei.android.tips\",\"crc\":\"3e668064\"},{\"appname\":\"Android Services Library\",\"packagename\":\"com.google.android.ext.services\",\"crc\":\"bd8b47e7\"},{\"appname\":\"HwSynergy\",\"packagename\":\"com.huawei.synergy\",\"crc\":\"19c171f9\"},{\"appname\":\"华为桌面\",\"packagename\":\"com.huawei.android.launcher\",\"crc\":\"b15c55a3\"},{\"appname\":\"音乐\",\"packagename\":\"com.android.mediacenter\",\"crc\":\"afcc7f5e\"},{\"appname\":\"EuiReceive\",\"packagename\":\"com.huawei.android.CotaDecompressService\",\"crc\":\"48a9a962\"},{\"appname\":\"通话\\/信息存储\",\"packagename\":\"com.android.providers.telephony\",\"crc\":\"94495841\"},{\"appname\":\"UEInfoCheck\",\"packagename\":\"com.huawei.android.UEInfoCheck\",\"crc\":\"05ef4a73\"},{\"appname\":\"录音机\",\"packagename\":\"com.android.phone.recorder\",\"crc\":\"d3249657\"},{\"appname\":\"日历存储\",\"packagename\":\"com.android.providers.calendar\",\"crc\":\"59930208\"},{\"appname\":\"运动健康\",\"packagename\":\"com.huawei.health\",\"crc\":\"50aff2e8\"},{\"appname\":\"文件管理\",\"packagename\":\"com.huawei.hidisk\",\"crc\":\"6d8fefeb\"},{\"appname\":\"hiview\",\"packagename\":\"com.huawei.hiview\",\"crc\":\"07e4fb39\"},{\"appname\":\"com.huawei.iaware\",\"packagename\":\"com.huawei.iaware\",\"crc\":\"3737683e\"},{\"appname\":\"通过蓝牙导入\",\"packagename\":\"com.huawei.bluetooth\",\"crc\":\"1e563177\"},{\"appname\":\"媒体存储\",\"packagename\":\"com.android.providers.media\",\"crc\":\"ab3cd343\"},{\"appname\":\"主题\",\"packagename\":\"com.huawei.android.thememanager\",\"crc\":\"a80d8a51\"},{\"appname\":\"HwChrService\",\"packagename\":\"com.huawei.android.chr\",\"crc\":\"54bdad8e\"},{\"appname\":\"华为服务框架\",\"packagename\":\"com.huawei.android.hsf\",\"crc\":\"cd3f2997\"},{\"appname\":\"Android Shared Library\",\"packagename\":\"com.google.android.ext.shared\",\"crc\":\"4e63c211\"},{\"appname\":\"com.android.wallpapercropper\",\"packagename\":\"com.android.wallpapercropper\",\"crc\":\"a0490c5b\"},{\"appname\":\"HwNearby\",\"packagename\":\"com.huawei.nearby\",\"crc\":\"b018050d\"},{\"appname\":\"悬浮导航\",\"packagename\":\"com.huawei.android.FloatTasks\",\"crc\":\"43c85841\"},{\"appname\":\"美团\",\"packagename\":\"com.sankuai.meituan\",\"crc\":\"682c5ac7\"},{\"appname\":\"智能助手\",\"packagename\":\"com.huawei.intelligent\",\"crc\":\"af3a360c\"},{\"appname\":\"手势服务\",\"packagename\":\"com.huawei.motionservice\",\"crc\":\"35c594c7\"},{\"appname\":\"SmartcardService\",\"packagename\":\"org.simalliance.openmobileapi.service\",\"crc\":\"a9ea4e11\"},{\"appname\":\"华为应用市场\",\"packagename\":\"com.huawei.appmarket\",\"crc\":\"fb1d5077\"},{\"appname\":\"HwIntelligentRecSystem\",\"packagename\":\"com.huawei.recsys\",\"crc\":\"ffd9f558\"},{\"appname\":\"荣耀阅读\",\"packagename\":\"com.huawei.hnreader\",\"crc\":\"4dddc295\"},{\"appname\":\"学生模式\",\"packagename\":\"com.huawei.parentcontrol\",\"crc\":\"3c4a4c57\"},{\"appname\":\"Huawei Secure IME\",\"packagename\":\"com.huawei.secime\",\"crc\":\"49d22f06\"},{\"appname\":\"文件\",\"packagename\":\"com.android.documentsui\",\"crc\":\"c8aed138\"},{\"appname\":\"外部存储设备\",\"packagename\":\"com.android.externalstorage\",\"crc\":\"3b3e023a\"},{\"appname\":\"收音机\",\"packagename\":\"com.huawei.android.FMRadio\",\"crc\":\"73bf0292\"},{\"appname\":\"HTML 查看器\",\"packagename\":\"com.android.htmlviewer\",\"crc\":\"2ca7791b\"},{\"appname\":\"Companion Device Manager\",\"packagename\":\"com.android.companiondevicemanager\",\"crc\":\"f0569219\"},{\"appname\":\"RegService\",\"packagename\":\"com.huawei.regservice\",\"crc\":\"35bb1c42\"},{\"appname\":\"驾驶模式\",\"packagename\":\"com.huawei.vdrive\",\"crc\":\"58a82d13\"},{\"appname\":\"讯飞语音引擎\",\"packagename\":\"com.iflytek.speechsuite\",\"crc\":\"dc0c44fe\"}]}");
                RetrofitUtil.getInstances().getService().postReportData(mHashMap)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> mContentTextView.setText(result.toString()),
                                throwable -> {
                                    mContentTextView.setText(throwable.toString());
                                },
                                () -> {

                                });
                break;
            case R.id.btn_http_test:
                RetrofitApi.request(RetrofitApi.createApi(IServiceApi.class).getAllStories("latest"), new RetrofitApi.IResponseListener<AllStories>() {
                    @Override
                    public void onSuccess(AllStories data) {

                        Log.e("社会我伟哥", "返回结果：" + data.toString());
                        ToastUtils.getInstance().showToast(data.toString());
                    }

                    @Override
                    public void onFail() {
                        Log.e("社会我伟哥", "网络请求失败：");
                    }
                });
                break;
            default:
                break;
        }


    }
}
