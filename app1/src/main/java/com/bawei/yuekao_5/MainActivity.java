package com.bawei.yuekao_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.mvp.base.IBaseView;
import com.bawei.mvp.model.bean.NewBean;
import com.bawei.mvp.net.MyMessage;
import com.bawei.mvp.presenetr.PresenetrImpl;
import com.bawei.yuekao_5.adapter.GouAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IBaseView<String>{

    private RecyclerView mRlvShopcart;
    /**
     * 全选
     */
    private TextView mTvShopcartAddselect;
    /**
     * 总价：¥0
     */
    private TextView mTvShopcartTotalprice;
    /**
     * 共0件商品
     */
    private TextView mTvShopcartTotalnum;
    /**
     * 去结算
     */
    private TextView mTvShopcartSubmit;
    private LinearLayout mLlPay;
    private RelativeLayout mRlShopcartHave;
    private PresenetrImpl presenetr;
    private List<NewBean.DataBean> data;
    private GouAdapter gouAdapter;
    private boolean flag;
    private int total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化控件
        initView();
//        注册Eventbus
        EventBus.getDefault().register(this);
        //调用 P层
        presenetr = new PresenetrImpl();
        //绑定
        presenetr.onAttachView(this);

        presenetr.getData();
    }

    private void initView() {
        mRlvShopcart =  findViewById(R.id.rlv_shopcart);
        mTvShopcartAddselect = (TextView) findViewById(R.id.tv_shopcart_addselect);
        mTvShopcartTotalprice = (TextView) findViewById(R.id.tv_shopcart_totalprice);
        mTvShopcartTotalnum = (TextView) findViewById(R.id.tv_shopcart_totalnum);
        mTvShopcartSubmit = (TextView) findViewById(R.id.tv_shopcart_submit);
        mLlPay = (LinearLayout) findViewById(R.id.ll_pay);
        mRlShopcartHave = (RelativeLayout) findViewById(R.id.rl_shopcart_have);
    }

    @Override
    public void onSuccess(final String reulst) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(MainActivity.this, reulst, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                NewBean newBean = gson.fromJson(reulst, NewBean.class);
                data = newBean.getData();
                mRlvShopcart.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                gouAdapter = new GouAdapter(MainActivity.this, data);
                mRlvShopcart.setAdapter(gouAdapter);
                selectAll(data);
                mTvShopcartAddselect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag = ! flag;
                        selectAll(data);
                    }
                });
            }
        });
    }
    private void selectAll(List<NewBean.DataBean> data){
        if (flag){
            mTvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shopcart_selected,0,0,0);
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).getList().size(); j++) {
                    data.get(i).setSelect(true);
                    data.get(i).getList().get(j).setSelected(1);
                }
            }
        }else{
            mTvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shopcart_unselected,0,0,0);
            for (int i = 0; i <data.size() ; i++) {
                for (int j = 0; j < data.get(i).getList().size(); j++) {
                    data.get(i).setSelect(false);
                    data.get(i).getList().get(j).setSelected(0);
                }
            }
        }
        EventBus.getDefault().post(new MyMessage("2"));
        gouAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenetr.isAttachView()){
            presenetr.onDestory();
        }
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AllPrice(MyMessage myMessage){
        total = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j <data.get(i).getList().size() ; j++) {
                if (data.get(i).getList().get(j).getSelected() % 2 == 1){
                    total += (int) (data.get(i).getList().get(j).getNum() * data.get(i).getList().get(j).getPrice());

                }
            }
        }
        mTvShopcartTotalprice.setText(String.valueOf(total));
    }
}
