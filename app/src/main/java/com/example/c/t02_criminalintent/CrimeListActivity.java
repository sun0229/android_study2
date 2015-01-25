package com.example.c.t02_criminalintent;

import android.support.v4.app.Fragment;


public class CrimeListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
