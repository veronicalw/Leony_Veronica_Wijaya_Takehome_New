package com.example.leony_veronica_wijaya_takehome.View.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;
import com.example.leony_veronica_wijaya_takehome.Model.Users;
import com.example.leony_veronica_wijaya_takehome.R;
import com.example.leony_veronica_wijaya_takehome.View.Fragment.FollowersFragment;
import com.example.leony_veronica_wijaya_takehome.View.Fragment.FollowingFragment;
import com.example.leony_veronica_wijaya_takehome.ViewModel.ViewModelDetail;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailActivity extends AppCompatActivity {
    public static final String SEARCH_DATA = "details";
    ImageView imgProfile;
    TextView name, username, userbio, useremail, userwebsite;
    private UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getDetail();

        TabLayout tabLayout = findViewById(R.id.tabLayoutDetail);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        userData = getIntent().getParcelableExtra(SEARCH_DATA);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Followers");
                    break;
                case 1:
                    tab.setText("Following");
                    break;
                default:
                    break;
            }
        }).attach();
    }

    public static class ViewPagerAdapter extends FragmentStateAdapter{

        ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new FollowersFragment();
                case 1:
                    return new FollowingFragment();
            }
            return new FollowersFragment();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
    @Nullable
    private void getDetail(){
        UserData data = getIntent().getParcelableExtra(SEARCH_DATA);
        if (data!=null){
            imgProfile = findViewById(R.id.imgUserDetail);
            name = findViewById(R.id.txtUserNameDetail);
            username = findViewById(R.id.txtUserDetailID);
            userbio = findViewById(R.id.txtUserBioDetail);
            useremail = findViewById(R.id.txtUserEmailDetail);
            userwebsite = findViewById(R.id.txtUserWebsiteDetail);

            ViewModelDetail viewModelDetail = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelDetail.class);
            viewModelDetail.loadData(data.getLogin());
            viewModelDetail.getLiveDataDetail().observe(DetailActivity.this, new Observer<Users>() {
                @Override
                public void onChanged(Users users) {
                    name.setText(users.getLogin());
                    username.setText(users.getName());
                    userbio.setText(users.getBio());
                    useremail.setText(users.getEmail());
                    userwebsite.setText(users.getBlog());
                    Glide.with(getApplicationContext())
                            .load(users.getAvatarUrl())
                            .into(imgProfile);
                }
            });
        } else if (data == null){
            Toast.makeText(DetailActivity.this, "Error Data Not found :  " ,Toast.LENGTH_LONG).show();
        }
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail User " + data.getLogin());
        }
    }

}