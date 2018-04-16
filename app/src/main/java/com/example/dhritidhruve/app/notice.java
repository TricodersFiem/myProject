package com.example.dhritidhruve.app;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class notice extends Fragment {

    private AppBarLayout appBar;
    private TabLayout tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice, container, false);

        assert container != null;
        View contenedor = (View)container.getParent();
        appBar = (AppBarLayout)contenedor.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabs);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);
       return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabs);
    }
        public class ViewPagerAdapter extends FragmentStatePagerAdapter {
            ViewPagerAdapter(FragmentManager fragmentManager) {
                super(fragmentManager);
            }

            String[] tabnames = {"Xplorica", "Culrav", "Sportivo", "Academic" };

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:return new noticetypes();
                    case 1:return new noticetypes();
                    case 2:return new noticetypes();
                    case 3:return new noticetypes();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabnames[position];
            }
        }
    }




