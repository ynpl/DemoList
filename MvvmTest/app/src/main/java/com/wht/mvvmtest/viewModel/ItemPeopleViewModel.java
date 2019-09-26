package com.wht.mvvmtest.viewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.wht.mvvmtest.model.People;

import java.util.List;

public class ItemPeopleViewModel extends ViewModel {
    private static final String TAG = "ItemPeopleViewModel";
    private MutableLiveData<People> people = new MutableLiveData<>();

    private Context context;


    public ItemPeopleViewModel(People people, Context context) {
        this.people.setValue(people);
        this.context = context;
    }
    public LiveData<People> getPeople() {
        if (people == null) {
            people = new MutableLiveData<People>();
        }
        return people;
    }
    public String getFullName() {

        people.getValue().fullName =
                people.getValue().name.title + "." + people.getValue().name.firts + " " + people.getValue().name.last;
        return people.getValue().fullName;
    }
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
//        context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
        Log.e(TAG, "onItemClick: ");


    }

    public void setPeople(People people) {
        this.people.setValue(people);
    }
}
