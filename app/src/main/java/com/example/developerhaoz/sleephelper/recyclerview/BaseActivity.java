package com.example.developerhaoz.sleephelper.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.developerhaoz.sleephelper.R;

import static com.example.developerhaoz.sleephelper.R.style.BiLiPinkTheme;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTheme();
    }

    private void initTheme(){

        //TODO:
//            int themeId = MyMusicUtil.getTheme(BaseActivity.this);
        int themeId = BiLiPinkTheme;
        switch (themeId){
            default:
            case 0:
                setTheme(BiLiPinkTheme);
                break;
            case 1:
                setTheme(R.style.ZhiHuBlueTheme);
                break;
            case 2:
                setTheme(R.style.KuAnGreenTheme);
                break;
            case 3:
                setTheme(R.style.CloudRedTheme);
                break;
            case 4:
                setTheme(R.style.TengLuoPurpleTheme);
                break;
            case 5:
                setTheme(R.style.SeaBlueTheme);
                break;
            case 6:
                setTheme(R.style.GrassGreenTheme);
                break;
            case 7:
                setTheme(R.style.CoffeeBrownTheme);
                break;
            case 8:
                setTheme(R.style.LemonOrangeTheme);
                break;
        }
    }

}