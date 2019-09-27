package com.neufmer.ygfstore.ui.view_picture;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityViewPictureBinding;
import com.neufmer.ygfstore.event.EventKeys;
import com.neufmer.ygfstore.event.param.PictureParam;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewPictureActivity extends BaseActivity<ActivityViewPictureBinding, ViewPictureViewModel> {

    private PictureParam mPicPaths;
    private Dialog picDialog;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_view_picture;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        mPicPaths = extras.getParcelable("PIC_PATH");
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setStatusBar(this, false, false);
        setSupportActionBar(binding.include.toolbar);
        viewModel.initToolbar();
        picDialog = initDialog();
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setImages((ArrayList<String>) mPicPaths.getImageList());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.addPictureUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (!picDialog.isShowing()) {
                    picDialog.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        LiveEventBus.get().with(EventKeys.LIVE_EVENT_RETURN_SELECTED_IMAGE).post(mPicPaths);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);

                    for (int i = 0; i < localMedia.size(); i++) {
                        mPicPaths.getImageList().add(localMedia.get(i).getPath());
                        viewModel.items.add(viewModel.items.size() - 1, new ViewPictureItemViewModel(viewModel, localMedia.get(i).getPath()));
                    }
                    break;
            }
        }
    }

    private Dialog initDialog() {
        final Dialog dialog = new Dialog(this, R.style.CommonDialog);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dialog_get_pictures, null, false));
        dialog.setCanceledOnTouchOutside(true);
        // 设置dialog宽高
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.windowAnimations = R.style.AnimBottom;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialog.findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照
                if (dialog.isShowing()) {
                    openCamera();
                    dialog.dismiss();
                }
            }
        });
        dialog.findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从相册选择
                if (dialog.isShowing()) {
                    openAlbum();
                    dialog.dismiss();
                }
            }
        });
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        return dialog;
    }

    private void openAlbum() {
        PictureSelector.create(ViewPictureActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(false)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(false)// 是否显示gif图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩

                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    private void openCamera() {
        PictureSelector.create(ViewPictureActivity.this)
                .openCamera(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(false)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .setOutputCameraPath("/YGFStores")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(false)// 是否显示gif图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩

                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

}
