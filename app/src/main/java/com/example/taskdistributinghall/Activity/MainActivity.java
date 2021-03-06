package com.example.taskdistributinghall.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.taskdistributinghall.Activity.MainPageAdapter;
import com.example.taskdistributinghall.Fragment.ChatRoom.ChatRoomFragment;
import com.example.taskdistributinghall.Fragment.Home.HomeFragment;
import com.example.taskdistributinghall.Fragment.Mission.MissionFragment;
import com.example.taskdistributinghall.Fragment.PersonalCenter.PersonalCenterFragment;
import com.example.taskdistributinghall.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private MainPageAdapter mainPageAdapter;
    private TabLayout.Tab homePage;
    private TabLayout.Tab chatRoom;
    private TabLayout.Tab mission;
    private TabLayout.Tab personalCenter;
    private long lastPressTime=0; //记录上一次按下返回键的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   Toolbar toolbar=findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

        initFragmentList();
        initViews();
    }

   public void initViews(){
        viewPager=findViewById(R.id.id_vp);
        mainPageAdapter=new MainPageAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(mainPageAdapter);
        tabLayout=findViewById(R.id.id_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        homePage=tabLayout.getTabAt(0);
        chatRoom=tabLayout.getTabAt(1);
        mission=tabLayout.getTabAt(2);
        personalCenter=tabLayout.getTabAt(3);
        homePage.setIcon(R.drawable.homepage);
        chatRoom.setIcon(R.drawable.chat);
        mission.setIcon(R.drawable.mission);
        personalCenter.setIcon(R.drawable.personalcenter);
   }

    public void initFragmentList(){
    fragmentList=new ArrayList<>();
    fragmentList.add(new HomeFragment());
    fragmentList.add(new ChatRoomFragment());
    fragmentList.add(new MissionFragment());
    fragmentList.add(new PersonalCenterFragment());
    }

    /**
     * 优化app退出
     */
    @Override
    public void onBackPressed(){
        if(lastPressTime==0) {
           lastPressTime = System.currentTimeMillis();
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
        }
        else if((System.currentTimeMillis()-lastPressTime)>2000){
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            lastPressTime=System.currentTimeMillis();
        }
        else System.exit(0);
    }

}
