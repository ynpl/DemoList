package com.wht.bottomnavigationview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment {

    private TextView mTextView;
    //要显示的页面
    private int fragmentPage;
    private String title;

    public static TestFragment newInstance(int iFragmentPage, String title) {
        TestFragment myFragment = new TestFragment();
        myFragment.fragmentPage = iFragmentPage;
        myFragment.title = title;
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(fragmentPage, container, false);
        mTextView = view.findViewById(R.id.fragment_text);
        mTextView.setText(title);
        return view;


    }
}
