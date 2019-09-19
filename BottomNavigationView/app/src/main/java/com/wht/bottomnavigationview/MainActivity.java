package com.wht.bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FrameLayout frameLayout ;

    private BottomNavigationView bottomNavigationView;
    private TestFragment f1,f2,f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.navigation_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (menuItem.getItemId()){
            case  R.id.menu1:
                if (f1 == null){
                    f1 = TestFragment.newInstance(R.layout.fragment_test,"Fragment1");
                    fragmentTransaction.add(R.id.frame_layout,f1);
                }else {
                    fragmentTransaction.show(f1);
                }


                break;
            case R.id.menu2:
                if (f2 == null){
                    f2 = TestFragment.newInstance(R.layout.fragment_test,"Fragment2");
                    fragmentTransaction.add(R.id.frame_layout,f2);
                }else {
                    fragmentTransaction.show(f2);
                }
                break;
            case R.id.menu3:
                if (f3 == null){
                    f3 = TestFragment.newInstance(R.layout.fragment_test,"Fragment3");
                    fragmentTransaction.add(R.id.frame_layout,f3);
                }else {
                    fragmentTransaction.show(f3);
                }
                break;

        }
        fragmentTransaction.commit();

        return true;
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }

    }
}
