package com.neufmer.ygfstore.ui.main.fragment.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.MineModel;
import com.neufmer.ygfstore.bean.UserBean;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.neufmer.ygfstore.ui.common.aboutus.AboutUsActivity;
import com.neufmer.ygfstore.ui.common.mystore.MyStoreActivity;
import com.neufmer.ygfstore.ui.login.LoginActivity;
import com.neufmer.ygfstore.ui.password.ChangePwdActivity;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.manager.CacheInfoManager;

/**
 * Created by WangXing on 2019/6/13.
 */
public class MineFragmentViewModel extends ToolbarViewModel<MineModel> {

    public ObservableField<String> nickName = new ObservableField<>();

    public ObservableField<String> phone = new ObservableField<>();

    public SingleLiveEvent<UserBean> setDataUC = new SingleLiveEvent<>();

    public MineFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.mine_fragment_title);
        setLeftIconVisible(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getUser();
    }

    /**
     * 修改密码
     */
    public BindingCommand goChangePwd = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangePwdActivity.class);
        }
    });

    /**
     * 关于我们
     */
    public BindingCommand goAboutUs = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(AboutUsActivity.class);
        }
    });
    /**
     * 管辖门店
     */
    public BindingCommand goMyStore = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MyStoreActivity.class);
        }
    });

    public BindingCommand logoutClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            request(model.postLogout(Const.header()), new ApiCallBack<Void>() {

                @Override
                protected void onSuccess(Void aVoid, String message) {
                    startActivity(LoginActivity.class);
                    CacheInfoManager.getInstance().clearCache();
                    finish();
                }

                @Override
                protected void onFailed(ResponseThrowable exception) {
                    if (exception.handleCode(Const.errorCode())) {
                        exception.toast();
                    }
                }

            });
        }
    });

    private void getUser() {
        request(model.getUser(Const.header(), CacheInfoManager.getInstance().getUserId()), new ApiCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean userBean, String message) {
                setDataUC.setValue(userBean);
            }

            @Override
            protected void onFailed(ResponseThrowable exception) {
                if (exception.handleCode(Const.errorCode())) {
                    exception.toast();
                }
            }
        });
    }
}
