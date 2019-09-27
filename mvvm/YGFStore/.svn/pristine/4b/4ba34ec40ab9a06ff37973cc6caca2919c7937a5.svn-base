package com.neufmer.ygfstore.ui.task_detail;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.InspectionsheetResultBean;
import com.neufmer.ygfstore.databinding.ActivityTaskDetailBinding;
import com.neufmer.ygfstore.event.EventKeys;
import com.neufmer.ygfstore.event.param.PictureParam;
import com.neufmer.ygfstore.ui.task_detail.failreasonsitem.TaskDetailFailreasonsChildsItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.FillBlanksItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.ImageItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.MultipleSelectionItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.ParentMultiItemViewModel;
import com.neufmer.ygfstore.ui.task_detail.multiitem.SingleElectionItemViewModel;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.base.MultiItemViewModel;
import com.wangxing.code.mvvm.utils.StatusBarUtils;
import com.wangxing.code.mvvm.view.utils.VMDialogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by WangXing on 2019/6/26.
 */
public class TaskDetailActivity extends BaseActivity<ActivityTaskDetailBinding, TaskDetailViewModel> {

    private int mTaskId;

    //单选问题原因
    private Dialog failreasonsDialog;
    private Dialog picDialog;
    private MultiItemViewModel mMultiItemViewModel;
    private ParentMultiItemViewModel mParentMultiItemViewModel;
    private TimePickerView pvTime;
    private Calendar selectedDate;
    private Calendar startDate;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_task_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        mTaskId = Objects.requireNonNull(getIntent().getExtras()).getInt("taskId", -1);
    }

    @Override
    public void initData() {
        super.initData();
        //mTaskId
        viewModel.setTaskId(mTaskId);
        viewModel.getInspectionsheetsQuery();
        viewModel.queryTaskContents();
        LiveEventBus.get().with(EventKeys.LIVE_EVENT_RETURN_SELECTED_IMAGE, PictureParam.class).observe(this, new Observer<PictureParam>() {
            @Override
            public void onChanged(@Nullable PictureParam pictureParam) {
                for (TaskDetailPageItemViewModel pageItem : viewModel.pageItems) {
                    for (InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean detail : pageItem.mData.getDetails()) {
                        assert pictureParam != null;
                        if (detail.getId() == pictureParam.getId()) {
                            int index = pageItem.mData.getDetails().indexOf(detail);
                            ParentMultiItemViewModel viewModel = (ParentMultiItemViewModel) pageItem.items.get(index);
                            viewModel.imagePaths.clear();
                            viewModel.imagePaths.addAll(pictureParam.getImageList());
                        }
                    }
                }
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setStatusBar(this, false, false);
        setSupportActionBar(binding.include.toolbar);
        viewModel.initToolbar();
        binding.noScrollViewPager.setNoScroll(true);
        failreasonsDialog = new VMDialogUtil.Builder(this)
                .setLayoutId(R.layout.dialog_failreasons)
                .setWindowAnimations(R.style.AnimBottom)
                .setViewModelId(BR.viewModel)
                .setViewModel(viewModel)
                .build();
        picDialog = initDialog();
        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(1970, 0, 10, 0, 0, 0);
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.switchPagerUC.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer position) {
                binding.noScrollViewPager.setCurrentItem(position, false);
            }
        });

        viewModel.offscreenPageLimitUC.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                binding.noScrollViewPager.setOffscreenPageLimit(integer);
            }
        });

        viewModel.choiceFailreasonsUC.observe(this, new Observer<ParentMultiItemViewModel>() {
            @Override
            public void onChanged(@Nullable ParentMultiItemViewModel parentMultiItemViewModel) {
                mParentMultiItemViewModel = parentMultiItemViewModel;
                if (!failreasonsDialog.isShowing()) {
                    failreasonsDialog.show();
                }
            }
        });

        viewModel.dismissFailreasonsUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (failreasonsDialog.isShowing()) {
                    failreasonsDialog.dismiss();
                }
            }
        });

        viewModel.completeFailreasonsUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {

                for (TaskDetailFailreasonsChildsItemViewModel childsItemViewModel : viewModel.failreasonsChildsItems) {
                    if (childsItemViewModel.selectFlag.get()) {
                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(childsItemViewModel.mData.getGroupId()));
                        list.add(String.valueOf(childsItemViewModel.mData.getId()));
                        mParentMultiItemViewModel.failReasonList.add(list);
                    }
                }
                if (failreasonsDialog.isShowing()) {
                    failreasonsDialog.dismiss();
                }
            }
        });

        viewModel.getPictureUC.observe(this, new Observer<MultiItemViewModel>() {
            @Override
            public void onChanged(@Nullable MultiItemViewModel multiItemViewModel) {
                mMultiItemViewModel = multiItemViewModel;
                if (!picDialog.isShowing()) {
                    picDialog.show();
                }
            }
        });

        viewModel.selectTimeUC.observe(this, new Observer<FillBlanksItemViewModel>() {
            @Override
            public void onChanged(@Nullable FillBlanksItemViewModel fillBlanksItemViewModel) {
                InspectionsheetResultBean.InspectionQuestionsBean.DetailsBean.QuestionBean.StyleBean styleBean = fillBlanksItemViewModel.mData.getQuestion().getStyle().get(0);
                if (styleBean.isIsDate()) {
                    pvTime = getDateType(fillBlanksItemViewModel);
                } else if (styleBean.isIsTime()) {
                    pvTime = getTimeType(fillBlanksItemViewModel);
                }
                if (!pvTime.isShowing()) {
                    pvTime.show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        final MaterialDialog dialog = new MaterialDialog.Builder(TaskDetailActivity.this).customView(R.layout.dialog_save_task_tip, false).show();
        View view = dialog.getCustomView();
        assert view != null;
        view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                viewModel.saveTask();
            }
        });
        view.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
    }

    private TimePickerView getDateType(final FillBlanksItemViewModel fillBlanksItemViewModel) {
        return new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        fillBlanksItemViewModel.selectText.set(format.format(date));
                    }
                })
                .setLayoutRes(R.layout.pickerview_item_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        v.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                        v.findViewById(R.id.tv_finish).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(ContextCompat.getColor(this, R.color.color_F1F1F1))
                .setContentTextSize(20)
                .setTextColorCenter(ContextCompat.getColor(this, R.color.grey_666666))
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setBgColor(ContextCompat.getColor(this, R.color.white_ffffff))
                .setOutSideColor(0x00000000)
                .setOutSideCancelable(false)
                .build();
    }


    private TimePickerView getTimeType(final FillBlanksItemViewModel fillBlanksItemViewModel) {
        return new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        fillBlanksItemViewModel.selectText.set(format.format(date));
                    }
                })
                .setLayoutRes(R.layout.pickerview_item_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        v.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                        v.findViewById(R.id.tv_finish).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("", "", "", "时", "分", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(ContextCompat.getColor(this, R.color.color_F1F1F1))
                .setContentTextSize(20)
                .setTextColorCenter(ContextCompat.getColor(this, R.color.grey_666666))
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setBgColor(ContextCompat.getColor(this, R.color.white_ffffff))
                .setOutSideColor(0x00000000)
                .setOutSideCancelable(false)
                .build();
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
        PictureSelector.create(TaskDetailActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(10)// 最大图片选择数量
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
        PictureSelector.create(TaskDetailActivity.this)
                .openCamera(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(10)// 最大图片选择数量
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    String itemType = (String) mMultiItemViewModel.getItemType();
                    if (itemType.equals(Const.SINGLECHOICE)) {
                        SingleElectionItemViewModel singleElectionItemViewModel = (SingleElectionItemViewModel) this.mMultiItemViewModel;
                        for (LocalMedia media : localMedia) {
                            singleElectionItemViewModel.imagePaths.add(media.getPath());
                        }
                    } else if (itemType.equals(Const.MULTIPLECHOICE)) {
                        MultipleSelectionItemViewModel multipleSelectionItemViewModel = (MultipleSelectionItemViewModel) this.mMultiItemViewModel;
                        for (LocalMedia media : localMedia) {
                            multipleSelectionItemViewModel.imagePaths.add(media.getPath());
                        }
                    } else if (itemType.equals(Const.FILLINBLANK)) {
                        FillBlanksItemViewModel fillBlanksItemViewModel = (FillBlanksItemViewModel) this.mMultiItemViewModel;
                        for (LocalMedia media : localMedia) {
                            fillBlanksItemViewModel.imagePaths.add(media.getPath());
                        }
                    } else if (itemType.equals(Const.IMAGE)) {
                        ImageItemViewModel imageItemViewModel = (ImageItemViewModel) this.mMultiItemViewModel;
                        for (LocalMedia media : localMedia) {
                            imageItemViewModel.imagePaths.add(media.getPath());
                        }
                    }

                    break;
            }
        }
    }

}
