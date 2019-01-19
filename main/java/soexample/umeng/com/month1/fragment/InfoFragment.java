package soexample.umeng.com.month1.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soexample.umeng.com.month1.R;
import soexample.umeng.com.month1.bean.InfoBean;
import soexample.umeng.com.month1.bean.LoginBean;
import soexample.umeng.com.month1.bean.UploadBean;
import soexample.umeng.com.month1.presenter.MyPresenter;
import soexample.umeng.com.month1.utils.Contrats;
import soexample.umeng.com.month1.utils.RetrofitUtils;
import soexample.umeng.com.month1.utils.RxUtils;
import soexample.umeng.com.month1.view.IView;

public class InfoFragment extends Fragment implements IView {

    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.nick)
    TextView nick;
    Unbinder unbinder;
    @BindView(R.id.jcvideoplayer)
    JZVideoPlayerStandard jcvideoplayer;

    private MyPresenter myPresenter;
    private PopupWindow pop;
    private Uri mUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        myPresenter = new MyPresenter(this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", 23830 + "");
        myPresenter.getRequest(Contrats.INFO, InfoBean.class, map);

        //视频播放
        jcvideoplayer.setUp("http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"视频");
        Glide.with(getActivity()).load(R.mipmap.ic_launcher).into(jcvideoplayer.thumbImageView);//给默认显示的图片赋值
        jcvideoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return  view;
    }


    //IView
    @Override
    public void success(Object success) {
        if(success instanceof InfoBean){
            InfoBean infoBean = (InfoBean) success;
            //赋值
            String icon = infoBean.getData().getIcon();
            String replace = icon.replace("https", "http");
            Glide.with(getActivity()).load(replace).into(this.icon);
            nick.setText(infoBean.getData().getNickname());
        }else if(success instanceof UploadBean){
            UploadBean bean = (UploadBean) success;
            Toast.makeText(getActivity(), bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void error(Object error) {
        Log.e("aaa", error.toString());
    }


    //点击拍照
    @OnClick(R.id.icon)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon:
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view = View.inflate(getActivity(), R.layout.pop, null);
                        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                        pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                        TextView paizhao = (TextView) view.findViewById(R.id.paizhao);
                        TextView xiangce = (TextView) view.findViewById(R.id.xiangce);
                        TextView cancel = (TextView) view.findViewById(R.id.cancel);
                        //拍照
                        paizhao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                mUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/bai.png"));
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                                startActivityForResult(intent,1000);
                            }
                        });
                        //相册
                        xiangce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 2000);
                            }
                        });
                        //取消
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pop.dismiss();
                            }
                        });
                    }
                });
                break;
        }
    }

    //成功回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000&&resultCode==getActivity().RESULT_OK) {
            crop(mUri);
        }
        if (requestCode == 2000 && resultCode==getActivity().RESULT_OK) {
            Uri uri = data.getData();
            crop(uri);
            pop.dismiss();
        }
        if (requestCode == 3000 && resultCode==getActivity().RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            String crop = saveImage("crop", bitmap);
            MultipartBody.Part body = null;
            if(crop!=null){
                File file=new File(crop);
                if(file.exists()) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
                    body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                }
            }
            Map<String,String> map2=new HashMap<>();
            map2.put("uid",""+23830);
            myPresenter.postImage(Contrats.UPLOAD,UploadBean.class,map2,body);
            icon.setImageBitmap(bitmap);
        }
    }

    //裁剪
    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //裁减比例1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪后图片大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDatection", false);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3000);
    }


    //保存图片
    private String saveImage(String crop, Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = crop + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
