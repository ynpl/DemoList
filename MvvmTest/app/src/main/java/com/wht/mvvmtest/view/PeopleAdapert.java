package com.wht.mvvmtest.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wht.mvvmtest.R;
import com.wht.mvvmtest.databinding.ItemPeopleBinding;
import com.wht.mvvmtest.model.People;
import com.wht.mvvmtest.viewModel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapert extends RecyclerView.Adapter<PeopleAdapert.ViewHolder> {
    private List<People> peopleList;

    public PeopleAdapert() {
        // Collections.emptyList() 返回一个空的List 占用内存底
        this.peopleList = Collections.emptyList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPeopleBinding itemPeopleBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_people,
                parent,
                false);


        return new ViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));

    }
    void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding mItemPeopleBinding;


        ViewHolder(ItemPeopleBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }
        void bindPeople(People people) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(
                        new ItemPeopleViewModel(people, itemView.getContext()));
            } else {
                mItemPeopleBinding.getPeopleViewModel().setPeople(people);
            }
        }

    }
}
