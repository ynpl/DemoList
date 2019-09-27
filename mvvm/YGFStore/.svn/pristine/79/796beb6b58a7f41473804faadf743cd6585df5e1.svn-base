package com.neufmer.ygfstore.toolbar;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.neufmer.ygfstore.R;
import com.wangxing.code.mvvm.base.BaseViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.utils.ContextUtils;

/**
 * Created by WangXing on 2019/6/12.
 */
public class ToolbarViewModel<M> extends BaseViewModel<M> {

    //toolbar背景
    public ObservableField<Drawable> toolbarBg = new ObservableField<>(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.toolbar_bg));

    //标题文字资源文件
    public ObservableInt titleTextRes = new ObservableInt(R.string.main_activity_bottom_task);
    //标题字体大小
    public ObservableInt titleFontSize = new ObservableInt(ContextUtils.getContext().getResources().getDimensionPixelSize(R.dimen.font_20));
    //标题文字颜色
    public ObservableInt titleTextColor = new ObservableInt(ContextCompat.getColor(ContextUtils.getContext(), R.color.black_333333));

    //左icon
    public ObservableField<Drawable> leftIconRes = new ObservableField<>(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_back));

    //左文字
    public ObservableField<String> leftTextRes = new ObservableField<>(ContextUtils.getContext().getString(R.string.common_cancel));
    //左文字大小
    public ObservableInt leftTextSize = new ObservableInt(ContextUtils.getContext().getResources().getDimensionPixelSize(R.dimen.font_14));
    //左文字颜色
    public ObservableInt leftTextColor = new ObservableInt(ContextCompat.getColor(ContextUtils.getContext(), R.color.grey_999999));

    //右边文字
    public ObservableInt rightTextRes = new ObservableInt(R.string.common_complete);
    //右边文字颜色
    public ObservableInt rightTextColor = new ObservableInt(ContextCompat.getColor(ContextUtils.getContext(), R.color.white_ffffff));
    //右边字体大小
    public ObservableInt rightTextSize = new ObservableInt(ContextUtils.getContext().getResources().getDimensionPixelSize(R.dimen.font_14));
    //右边文字背景
    public ObservableField<Drawable> rightTextBg = new ObservableField<>(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.toolbar_confirm));
    //切换背景
    public ObservableBoolean rightHasSelect = new ObservableBoolean(false);
    //右边文字的观察者
    public ObservableInt rightTextVisibleObservable = new ObservableInt(View.GONE);

    //左边文字的观察者
    public ObservableInt leftTextVisibleObservable = new ObservableInt(View.GONE);

    public ObservableField<Drawable> rightIconRes = new ObservableField<>(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_back));
    //右边图标的观察者
    public ObservableInt rightIconVisibleObservable = new ObservableInt(View.GONE);
    //左边图标观察者
    public ObservableInt leftIconVisibleObservable = new ObservableInt(View.VISIBLE);

    public ObservableField<Drawable> lineBg = new ObservableField<>(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.bg_shadow));

    public ToolbarViewModel toolbarViewModel;

    public ToolbarViewModel(@NonNull Application application) {
        super(application);
        toolbarViewModel = this;
    }

    /**
     * 设置toolbar背景
     *
     * @param drawableResId
     */
    public void setToolbarBg(@DrawableRes int drawableResId) {
        toolbarBg.set(ContextCompat.getDrawable(ContextUtils.getContext(), drawableResId));
    }

    /**
     * 标题文字资源ID
     *
     * @param stringResId 标题文字资源ID
     */
    public void setTitleTextRes(@StringRes int stringResId) {
        titleTextRes.set(stringResId);
    }

    /**
     * 设置标题字体大小
     *
     * @param dimensResId dismens资源文件Id
     */
    public void setTitleFontSize(@DimenRes int dimensResId) {
        titleFontSize.set(ContextUtils.getContext().getResources().getDimensionPixelSize(dimensResId));
    }

    /**
     * 设置标题字体颜色
     *
     * @param colorResId 颜色资源ID
     */
    public void setTitleTextColor(@ColorRes int colorResId) {
        titleTextColor.set(ContextCompat.getColor(ContextUtils.getContext(), colorResId));
    }

    /**
     * 设置左Icon
     *
     * @param drawableResId drawable资源ID
     */
    public void setLeftIconRes(@DrawableRes int drawableResId) {
        leftIconRes.set(ContextCompat.getDrawable(ContextUtils.getContext(), drawableResId));
    }

    /**
     * 返回样式
     */
    public void setLeftBackStyle() {
        leftIconRes.set(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_back));
    }

    /**
     * 关闭样式
     */
    public void setLeftCloseStyle() {
        leftIconRes.set(ContextCompat.getDrawable(ContextUtils.getContext(), R.drawable.icon_close));
    }

    /**
     * 右边文字
     *
     * @param stringResId
     */
    public void setRightTextRes(@StringRes int stringResId) {
        rightTextRes.set(stringResId);
    }

    /**
     * 设置右边字体颜色
     *
     * @param colorResId
     */
    public void setRightTextColor(@ColorRes int colorResId) {
        rightTextColor.set(ContextCompat.getColor(ContextUtils.getContext(), colorResId));
    }

    /**
     * 设置右边字体大小
     *
     * @param dimensResId
     */
    public void setRightTextSize(@DimenRes int dimensResId) {
        rightTextSize.set(ContextUtils.getContext().getResources().getDimensionPixelSize(dimensResId));
    }

    /**
     * 设置右边Icon
     *
     * @param drawableResId
     */
    public void setRightIconRes(@DrawableRes int drawableResId) {
        rightIconRes.set(ContextCompat.getDrawable(ContextUtils.getContext(), drawableResId));
    }

    /**
     * 设置右边文字背景
     *
     * @param drawableResId
     */
    public void setRightTextBg(@DrawableRes int drawableResId) {
        rightTextBg.set(ContextCompat.getDrawable(ContextUtils.getContext(), drawableResId));
    }

    /**
     * 设置右边文字的显示和隐藏
     *
     * @param visibility
     */
    public void setRightTextVisible(int visibility) {
        rightTextVisibleObservable.set(visibility);
    }

    /**
     * 设置右边图标的显示和隐藏
     *
     * @param visibility
     */
    public void setRightIconVisible(int visibility) {
        rightIconVisibleObservable.set(visibility);
    }

    public void setLeftIconVisible(int visibility) {
        leftIconVisibleObservable.set(visibility);
    }

    /**
     * 设置线的颜色
     *
     * @param drawableResId
     */
    public void setLineBg(@DrawableRes int drawableResId) {
        lineBg.set(ContextCompat.getDrawable(ContextUtils.getContext(), drawableResId));
    }

    /**
     * 左边文字
     *
     * @param stringResId
     */
    public void setLeftTextRes(@StringRes int stringResId) {
        leftTextRes.set(ContextUtils.getContext().getString(stringResId));
    }

    /**
     * 左边文字大小
     *
     * @param dimensResId
     */
    public void setLeftTextSize(@DimenRes int dimensResId) {
        leftTextSize.set(ContextUtils.getContext().getResources().getDimensionPixelSize(dimensResId));
    }

    /**
     * 左边文字字体颜色
     *
     * @param colorResId
     */
    public void setLeftTextColor(@ColorRes int colorResId) {
        leftTextColor.set(ContextCompat.getColor(ContextUtils.getContext(), colorResId));
    }

    /**
     * 左边字体显示隐藏
     *
     * @param visibility
     */
    public void setLeftTextVisibleObservable(int visibility) {
        leftTextVisibleObservable.set(visibility);
    }

    /**
     * 返回按钮的点击事件
     */
    public final BindingCommand backOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();
        }
    });

    public BindingCommand rightTextOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            rightTextOnClick();
        }
    });
    public BindingCommand rightIconOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            rightIconOnClick();
        }
    });


    /**
     * 切换右边背景
     *
     * @param select
     */
    public void setRightHasSelect(boolean select) {
        rightHasSelect.set(select);
    }

    /**
     * 右边文字的点击事件，子类可重写
     */
    protected void rightTextOnClick() {
    }

    /**
     * 右边图标的点击事件，子类可重写
     */
    protected void rightIconOnClick() {
    }

    protected void initToolbar() {
    }

}
