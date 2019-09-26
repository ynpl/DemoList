package com.wht.mvvmtest.viewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.wht.mvvmtest.MvvmApp;
import com.wht.mvvmtest.R;
import com.wht.mvvmtest.data.PeopleFactory;
import com.wht.mvvmtest.data.PeopleResponse;
import com.wht.mvvmtest.data.PeopleService;
import com.wht.mvvmtest.model.People;

import org.json.JSONArray;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PeopleViewModel extends ViewModel {

    public MutableLiveData<Integer> peopleProgress;
    public MutableLiveData<Integer> peopleRecycler;
    public MutableLiveData<Integer> peopleLabel;
    public MutableLiveData<String> messageLabel;

    private MutableLiveData<List<People>> peopleList;

    public LiveData<List<People>> getPeopleList() {
        if (peopleList == null) {
            peopleList = new MutableLiveData<List<People>>();
            loadUsers();
        }
        return peopleList;
    }

    public void init() {
        peopleProgress = new MutableLiveData<>();
        peopleRecycler = new MutableLiveData<>();
        peopleLabel = new MutableLiveData<>();
        messageLabel = new MutableLiveData<>();

        peopleProgress.setValue(View.GONE);
        peopleRecycler.setValue(View.GONE);
        peopleLabel.setValue(View.VISIBLE);
        messageLabel.setValue(MvvmApp.sApplication.getString(R.string.default_loading_people));
    }

    private void loadUsers() {
        MvvmApp mvvmApp = MvvmApp.create(MvvmApp.sApplication);
        PeopleService peopleService = mvvmApp.getPeopleService();

        Disposable disposable = peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL)
                .subscribeOn(mvvmApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PeopleResponse>() {
                    @Override
                    public void accept(PeopleResponse peopleResponse) {
                        changePeopleDataSet(peopleResponse.getPeopleList());
                        peopleProgress.setValue(View.GONE);
                        peopleLabel.setValue(View.GONE);
                        peopleRecycler.setValue(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        messageLabel.setValue(MvvmApp.sApplication.getString(R.string.error_loading_people));
                        peopleProgress.setValue(View.GONE);
                        peopleLabel.setValue(View.VISIBLE);
                        peopleRecycler.setValue(View.GONE);
                        throwable.printStackTrace();
                    }
                });

    }


    public void onClickFabLoad(View view) {
        initializeViews();
        getPeopleList();
    }


    public void initializeViews() {
        peopleLabel.setValue(View.GONE);
        peopleRecycler.setValue(View.GONE);
        peopleProgress.setValue(View.VISIBLE);
    }

    private void changePeopleDataSet(List<People> peoples) {
        peopleList.setValue(peoples);

    }
}
