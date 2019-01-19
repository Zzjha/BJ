package soexample.umeng.com.month1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.month1.fragment.GoodsFragment;
import soexample.umeng.com.month1.fragment.InfoFragment;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.goods)
    RadioButton goods;
    @BindView(R.id.info)
    RadioButton info;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.frame)
    FrameLayout frame;

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame,new GoodsFragment()).commitAllowingStateLoss();
    }

    @OnClick({R.id.goods, R.id.info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goods:
                manager.beginTransaction().replace(R.id.frame,new GoodsFragment()).commitAllowingStateLoss();
                break;
            case R.id.info:
                manager.beginTransaction().replace(R.id.frame,new InfoFragment()).commitAllowingStateLoss();
                break;
        }
    }
}
