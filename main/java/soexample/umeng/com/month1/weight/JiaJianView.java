package soexample.umeng.com.month1.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import soexample.umeng.com.month1.R;

/**
 * author:author${朱佳华}
 * data:2019/1/19
 */
public class JiaJianView extends LinearLayout implements View.OnClickListener {

    private TextView jia;
    private TextView num;
    private TextView jian;

    private int count;

    public JiaJianView(Context context,AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.jiajianview, this);
        jia = findViewById(R.id.jia);
        num = findViewById(R.id.num);
        jian = findViewById(R.id.jian);
        //点击事件
        jian.setOnClickListener(this);
        jia.setOnClickListener(this);
    }

    //给num赋值   数据从集合中获取
    public void setCount(int count) {
        this.count = count;
        num.setText(count+"");
    }


    //点击
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jian:
                if(count>1){
                    count--;
                    num.setText(count+"");
                    if(jiaJianCallBack!=null){
                        jiaJianCallBack.setNumChange(count);
                    }
                }else{
                    Toast.makeText(getContext(), "商品数量不能为0", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.jia:
                count++;
                num.setText(count+"");
                if(jiaJianCallBack!=null){
                    jiaJianCallBack.setNumChange(count);
                }
                break;
        }
    }


    //接口回调
    public interface JiaJianCallBack{
        void setNumChange(int count);
    }
    private JiaJianCallBack jiaJianCallBack;
    public void setJiaJianNumChange(JiaJianCallBack jiaJianCallBack){
        this.jiaJianCallBack = jiaJianCallBack;
    }
}
