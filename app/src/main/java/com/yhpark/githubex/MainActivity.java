package com.yhpark.githubex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.yhpark.githubex.fragments.FrgGetAllUser;
import com.yhpark.githubex.fragments.FrgGetAuthUser;
import com.yhpark.githubex.fragments.FrgGetSingleUser;
import com.yhpark.githubex.fragments.FrgUpdateAuthUser;
import com.yhpark.githubex.model.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.flFragmentView)
    FrameLayout flFragmentView;
    @BindView(R.id.naviView)
    NavigationView naviView;
    @BindView(R.id.dlMain)
    DrawerLayout dlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        naviView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            dlMain.openDrawer(naviView);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSupportActionBar().setTitle(item.getTitle());
        flFragmentView.removeAllViews();
        switch (item.getItemId()) {
            case R.id.menu_get_single_user:
                getFragmentManager().beginTransaction().add(flFragmentView.getId(), new FrgGetSingleUser(), "").commit();
                break;
            case R.id.menu_get_auth_user:
                getFragmentManager().beginTransaction().add(flFragmentView.getId(), new FrgGetAuthUser(), "").commit();
                break;
            case R.id.menu_update_auth_user:
                getFragmentManager().beginTransaction().add(flFragmentView.getId(), new FrgUpdateAuthUser(), "").commit();
                break;
            case R.id.menu_get_all_user:
                getFragmentManager().beginTransaction().add(flFragmentView.getId(), new FrgGetAllUser(), "").commit();
                break;

            default:
                break;
        }

        dlMain.closeDrawers();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBHelper.getInstance(getApplicationContext()).close();
    }
}
