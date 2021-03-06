package com.example.developerhaoz.sleephelper.recyclerview;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.developerhaoz.sleephelper.R;
import com.example.developerhaoz.sleephelper.recyclerview.fragment.AlbumFragment;
import com.example.developerhaoz.sleephelper.recyclerview.fragment.FolderFragment;
import com.example.developerhaoz.sleephelper.recyclerview.fragment.SingerFragment;
import com.example.developerhaoz.sleephelper.recyclerview.fragment.SingleFragment;
import com.example.developerhaoz.sleephelper.recyclerview.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class

LocalMusicActivity extends PlayBarBaseActivity {

    private static final String TAG = LocalMusicActivity.class.getName();
    public static final int SEARCH_FINISHED=1;
    public static final int SEARCHING_MUSIC=2;
    public static final int MUSIC_OPTIONS=0;
    public static final int MUSIC_PLAY=1;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private MyViewPager viewPager;
    private MyAdapter fragmentAdapter;

    private SingleFragment singleFragment;
    private SingerFragment singerFragment;
    private AlbumFragment albumFragment;
    private FolderFragment folderFragment;
    private TextView nothingTv;

    private List<String> titleList = new ArrayList<>(4);
    private List<Fragment> fragments = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        toolbar = (Toolbar)findViewById(R.id.local_music_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("本地音乐");
        }

        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.local_music_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.scan_local_menu){
            Intent intent = new Intent(LocalMusicActivity.this,ScanActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(LocalMusicActivity.this,MP3PlayerActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void initView(){

        addTabData();

        viewPager = (MyViewPager)findViewById(R.id.local_viewPager);
        tabLayout = (TabLayout)findViewById(R.id.local_tab);
        fragmentAdapter = new MyAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(2); //预加载页面数
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        nothingTv = (TextView)findViewById(R.id.local_nothing_tv);
        nothingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalMusicActivity.this,MP3PlayerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addTabData(){

        titleList.add("单曲");
        titleList.add("歌手");
        titleList.add("专辑");
        titleList.add("文件夹");

        if (singleFragment == null) {
            singleFragment = new SingleFragment();
            fragments.add(singleFragment);
        }
        if (singerFragment == null) {
            singerFragment = new SingerFragment();
            fragments.add(singerFragment);
        }
        if (albumFragment == null) {
            albumFragment = new AlbumFragment();
            fragments.add(albumFragment);
        }
        if (folderFragment == null) {
            folderFragment = new FolderFragment();
            fragments.add(folderFragment);
        }

    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * 用来显示tab上的名字
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
