package soexample.umeng.com.month1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.month1.R;
import soexample.umeng.com.month1.adapter.GoodsAdapter;
import soexample.umeng.com.month1.bean.GoodsBean;
import soexample.umeng.com.month1.presenter.MyPresenter;
import soexample.umeng.com.month1.utils.Contrats;
import soexample.umeng.com.month1.view.IView;

public class GoodsFragment extends Fragment implements IView {

    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.allCheck)
    CheckBox allCheck;
    @BindView(R.id.total)
    TextView total;
    Unbinder unbinder;

    private MyPresenter myPresenter;
    private List<GoodsBean.DataBean> list = new ArrayList<>();
    private GoodsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        myPresenter = new MyPresenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(manager);
        adapter = new GoodsAdapter(list, getActivity());
        recy.setAdapter(adapter);
        Map<String, String> map = new HashMap<>();
        map.put("pscid", 1 + "");
        myPresenter.getRequest(Contrats.GOOGS, GoodsBean.class, map);


        //接口回调
        adapter.setOnLinstener(new GoodsAdapter.CallBack() {
            @Override
            public void setChecked(View view, int position) {
                boolean b = adapter.isItemGoodChecked(position);
                adapter.setItemGoodChecked(position, !b);
                adapter.notifyDataSetChanged();
                flushBottom();
            }

            @Override
            public void setNum(int position, int num) {
                adapter.setGoodsNumber(position, num);
                adapter.notifyDataSetChanged();
                flushBottom();
            }
        });

        return view;
    }


    //点击
    @OnClick({R.id.allCheck})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.allCheck:
                boolean allGoods = adapter.isAllGoods();
                adapter.setAllGoodsIsChecked(!allGoods);
                flushBottom();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    //刷新底部数据
    private void flushBottom(){
        boolean allGoods = adapter.isAllGoods();
        allCheck.setChecked(allGoods);
        double allGoodsPrice = adapter.getAllGoodsPrice();
        total.setText("合计："+"￥"+allGoodsPrice+"");
    }


    //IView
    @Override
    public void success(Object success) {
        GoodsBean bean = (GoodsBean) success;
        list.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void error(Object error) {
        Log.e("zzz", "error: " + error.toString());
    }

    //解绑
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
