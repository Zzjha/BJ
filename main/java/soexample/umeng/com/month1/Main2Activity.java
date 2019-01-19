package soexample.umeng.com.month1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.month1.bean.RegisterBean;
import soexample.umeng.com.month1.presenter.MyPresenter;
import soexample.umeng.com.month1.utils.CheckUtils;
import soexample.umeng.com.month1.utils.Contrats;
import soexample.umeng.com.month1.view.IView;

public class Main2Activity extends AppCompatActivity implements IView {

    @BindView(R.id.telReg)
    EditText telReg;
    @BindView(R.id.pwd1Reg)
    EditText pwd1Reg;
    @BindView(R.id.pwd2Reg)
    EditText pwd2Reg;
    @BindView(R.id.emailReg)
    EditText emailReg;
    @BindView(R.id.register)
    Button register;

    private List<RegisterBean> list = new ArrayList<>();
    private MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);
    }




    @OnClick(R.id.register)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.register:
                String telRegister = telReg.getText().toString().trim();
                String pwd1Register = pwd1Reg.getText().toString().trim();
                String pwd2Register = pwd2Reg.getText().toString().trim();
                String emailRegister = emailReg.getText().toString().trim();
                if(telRegister.isEmpty()||pwd1Register.isEmpty()||pwd2Register.isEmpty()||emailRegister.isEmpty()){
                    Toast.makeText(this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    boolean b = CheckUtils.check(telRegister);
                    if(!b){
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                    /*if(pwd1Register.equals(pwd2Register)){
                        Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
                        return;
                    }else{*/
                        Map<String, String> map = new HashMap<>();
                        map.put("mobile", telRegister);
                        map.put("password", pwd1Register);
                        myPresenter.getRequest(Contrats.REGISTER, RegisterBean.class, map);
                   // }
                }
                break;
        }
    }


    //IView
    @Override
    public void success(Object success) {
        RegisterBean registerBean = (RegisterBean) success;
        if(registerBean.getCode().equals("0")){
            Toast.makeText(this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this,MainActivity.class));
        }
    }
    @Override
    public void error(Object error) {
        RegisterBean registerBean = (RegisterBean) error;
        Toast.makeText(this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
