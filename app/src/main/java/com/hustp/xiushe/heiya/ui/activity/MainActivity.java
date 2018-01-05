package com.hustp.xiushe.heiya.ui.activity;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hustp.xiushe.heiya.R;
import com.hustp.xiushe.heiya.net.CallServer;
import com.hustp.xiushe.heiya.ui.fragment.FragmentMain0;
import com.hustp.xiushe.heiya.ui.fragment.FragmentMain1;
import com.hustp.xiushe.heiya.ui.fragment.FragmentMain2;
import com.hustp.xiushe.heiya.ui.fragment.FragmentMain3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment fragment0, fragment1, fragment2, fragment3;

    public static final String fragment0Tag = "fragment0";
    public static final String fragment1Tag = "fragment1";
    public static final String fragment2Tag = "fragment2";
    public static final String fragment3Tag = "fragment3";

    private long exitTime = 0;

    private RelativeLayout layout_bottom_item_0, layout_bottom_item_1, layout_bottom_item_2, layout_bottom_item_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        MIUISetStatusBarLightMode(this.getWindow(), true);
        FlymeSetStatusBarLightMode(this.getWindow(), true);
        initView(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            setCurrentPage(2);
        }

    }

    //初始化
    private void initView(Bundle savedInstanceState) {

        initIcon();

        layout_bottom_item_0 = (RelativeLayout) findViewById(R.id.layout_bottom_item_0);
        layout_bottom_item_1 = (RelativeLayout) findViewById(R.id.layout_bottom_item_1);
        layout_bottom_item_2 = (RelativeLayout) findViewById(R.id.layout_bottom_item_2);
        layout_bottom_item_3 = (RelativeLayout) findViewById(R.id.layout_bottom_item_3);
        layout_bottom_item_0.setOnClickListener(this);
        layout_bottom_item_1.setOnClickListener(this);
        layout_bottom_item_2.setOnClickListener(this);
        layout_bottom_item_3.setOnClickListener(this);

        //如果没有缓存信息，则显示fragment2，有则直接显示上次保存的状态，避免activity因内存不足被释放后fragment出现重叠现象
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new FragmentMain0();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment0Tag).commit();
        }

    }

    private void initIcon() {
//        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
//        TextView textview_0 = (TextView) findViewById(R.id.icon_0);
//        textview_0.setTypeface(iconfont);
//        TextView textview_1 = (TextView) findViewById(R.id.icon_1);
//        textview_1.setTypeface(iconfont);
//        TextView textview_2 = (TextView) findViewById(R.id.icon_2);
//        textview_2.setTypeface(iconfont);
//        TextView textview_3 = (TextView) findViewById(R.id.icon_3);
//        textview_3.setTypeface(iconfont);
    }

    public void setCurrentPage(int item) {
        switch (item) {
            case 0:
                onClick(layout_bottom_item_0);
                break;

            case 1:
                onClick(layout_bottom_item_1);
                break;

            case 2:
                onClick(layout_bottom_item_2);
                break;

            case 3:
                onClick(layout_bottom_item_3);
                break;

            default:
                break;
        }
    }

    //隐藏所有的fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (fragment0 != null) {
            transaction.hide(fragment0);
        }
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }

        if (fragment3 != null) {
            transaction.hide(fragment3);
        }

    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        fragment0 = fm.findFragmentByTag(fragment0Tag);
        fragment1 = fm.findFragmentByTag(fragment1Tag);
        fragment2 = fm.findFragmentByTag(fragment2Tag);
        fragment3 = fm.findFragmentByTag(fragment3Tag);

        hideAllFragment(transaction);

        switch (v.getId()) {
            case R.id.layout_bottom_item_0:
                if (fragment0 == null) {
                    fragment0 = new FragmentMain0();
                    transaction.add(R.id.fragment_container, fragment0, fragment0Tag);
                } else {
                    transaction.show(fragment0);
                }
                break;
            case R.id.layout_bottom_item_1:
                if (fragment1 == null) {
                    fragment1 = new FragmentMain1();
                    transaction.add(R.id.fragment_container, fragment1, fragment1Tag);
                } else {
                    transaction.show(fragment1);
                }
                break;
            case R.id.layout_bottom_item_2:
                if (fragment2 == null) {
                    fragment2 = new FragmentMain2();
                    transaction.add(R.id.fragment_container, fragment2, fragment2Tag);
                } else {
                    transaction.show(fragment2);
                }
                break;

            case R.id.layout_bottom_item_3:
                if (fragment3 == null) {
                    fragment3 = new FragmentMain3();
                    transaction.add(R.id.fragment_container, fragment3, fragment3Tag);
                } else {
                    transaction.show(fragment3);
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }

    private void anim(ImageView imageView) {
        ObjectAnimator animator_x = ObjectAnimator.ofFloat(imageView, "scaleX", 0.8f, 1.3f, 1f);
        ObjectAnimator animator_y = ObjectAnimator.ofFloat(imageView, "scaleY", 0.8f, 1.3f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator_x).with(animator_y);
        animSet.setDuration(300);
        animSet.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CallServer.getInstance().stop();
    }
}
