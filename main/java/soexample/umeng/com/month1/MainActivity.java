package soexample.umeng.com.month1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.month1.bean.LoginBean;
import soexample.umeng.com.month1.bean.RegisterBean;
import soexample.umeng.com.month1.presenter.MyPresenter;
import soexample.umeng.com.month1.utils.CheckUtils;
import soexample.umeng.com.month1.utils.Contrats;
import soexample.umeng.com.month1.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.telLog)
    EditText telLog;
    @BindView(R.id.pwdLog)
    EditText pwdLog;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.Login)
    Button Login;
    @BindView(R.id.qqLog)
    ImageView qqLog;

    private MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);
    }

    @OnClick({R.id.Login, R.id.qqLog,R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login:
                String telLogin = telLog.getText().toString().trim();
                String pwdLogin = pwdLog.getText().toString().trim();
                if(telLogin.isEmpty()||pwdLogin.isEmpty()){
                    Toast.makeText(this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    boolean b = CheckUtils.check(telLogin);
                    if(!b){
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("mobile", telLogin);
                    map.put("password", pwdLogin);
                    myPresenter.getRequest(Contrats.LOGIN, LoginBean.class, map);
                }
                break;
            case R.id.qqLog:
                break;
            case R.id.reg:
                startActivity(new Intent(this,Main2Activity.class));
                break;
        }
    }


    //IView
    @Override
    public void success(Object success) {
        LoginBean loginBean = (LoginBean) success;
        if(loginBean.getCode().equals("0")){
            Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(MainActivity.this,Main3Activity.class));
        }

    }
    @Override
    public void error(Object error) {

    }


}
