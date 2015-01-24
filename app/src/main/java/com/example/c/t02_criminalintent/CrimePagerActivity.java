package com.example.c.t02_criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by c on 2015-01-24.
 */
public class CrimePagerActivity extends ActionBarActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);   // viewPager는 별도의 id를 세팅해야한다.
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID)getIntent().getExtras().get(CrimeFragment.EXTRA_CRIME_ID);
        for (i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        //Crime crim1 = mCrimes.get(i);
        //setTitle(crim1.getTitle());
        setTitle(CrimeLab.get(this).getCrime(crimeId).getTitle());  // 엑티비티 첫 화면 타이틀에서도 해당 페이지의 타이틀이 보일 수 있게..

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = mCrimes.get(position);
                if(crime.getTitle() != null){
                    setTitle(crime.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });


    }
}
