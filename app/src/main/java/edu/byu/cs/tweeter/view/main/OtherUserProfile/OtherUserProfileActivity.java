package edu.byu.cs.tweeter.view.main.OtherUserProfile;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.presenter.FollowerPresenter;
import edu.byu.cs.tweeter.presenter.FollowingPresenter;
import edu.byu.cs.tweeter.presenter.OtherUserProfilePresenter;
import edu.byu.cs.tweeter.view.asyncTasks.GetFollowersTask;
import edu.byu.cs.tweeter.view.asyncTasks.GetOtherUserProfileTask;

import static edu.byu.cs.tweeter.view.main.MainActivity.setImageViewWithByteArray;

public class OtherUserProfileActivity extends AppCompatActivity implements OtherUserProfilePresenter.View, GetOtherUserProfileTask.Observer, GetFollowersTask.Observer, FollowingPresenter.View, FollowerPresenter.View {

    private OtherUserProfilePresenter presenter;
    private FollowerPresenter followerPresenter;
    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String OTHER_USER_ALIAS = "OtherUserAlias";
    public static final String OTHER_USER_FULL_NAME = "OtherUserFullName";

    public User user;
    public String otherUserAlias;
    public String otherUserFullName;
    public Boolean isFollowing = true;
    public Button followUnFollowButton;
    public int numberOfFollowers = 0;



    // Tabs and ViewPager
    TabLayout tabLayout;                                    // Button Tabs
    ViewPager myViewPager;                                  // Widget that allows the user to swipe left or right to see an entirely new screen.
    OtherUserSectionsPagerAdapter othrUsrSecPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);// Original code

        // Set the presenter
        presenter = new OtherUserProfilePresenter(this);

        // Get the User and passed on authtoken
        user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        AuthToken authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);


        OtherUserSectionsPagerAdapter sectionsPagerAdapter = new OtherUserSectionsPagerAdapter(this, getSupportFragmentManager(), user, authToken);
//        OtherUserSectionsPagerAdapter sectionsPagerAdapter = new OtherUserSectionsPagerAdapter(getSupportFragmentManager());
        myViewPager = (ViewPager) findViewById(R.id.otherUser_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.otherUser_tabs);
        myViewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(myViewPager);


        // Get Selected User's information (UserAlias and FullName)
        otherUserAlias = (String) getIntent().getSerializableExtra(OTHER_USER_ALIAS);
        otherUserFullName = (String) getIntent().getSerializableExtra(OTHER_USER_FULL_NAME);

        // Sets the top activity bar to be the name of the selected user's alias
        getSupportActionBar().setTitle(otherUserAlias);  // provide compatibility to all the versions

        // the OtherUser's name
        TextView userFullName = findViewById(R.id.otherUsersFullName);
        // If the name is too Long
        if(otherUserFullName.length() > 10) {
            String[] arr = otherUserFullName.split(" ");
            String firstName = arr[0];
            Character lastName = arr[1].charAt(0);
            otherUserFullName = firstName + " " + lastName + ".";
        }
        userFullName.setText(otherUserFullName);
        // the OtherUser's alias
        TextView userAlias = findViewById(R.id.otherUsersAlias);
        userAlias.setText(otherUserAlias);

        // Set the user profile Image using image bytes
        ImageView userImageView = findViewById(R.id.otherUserProfilePicture);
        byte [] imageBytes = user.getImageBytes();
        setImageViewWithByteArray(userImageView, imageBytes);

        // Display the Follower Count
        TextView followerCount = findViewById(R.id.otherUserfollowerCount);
        followerCount.setText(getString(R.string.followerCount, user.getFollowersCount()));

        // Display the Followee Count
        TextView followeeCount = findViewById(R.id.otherUserfolloweeCount);
        followeeCount.setText(getString(R.string.followeeCount, user.getFollowingCount()));

        // Follow/Unfollow Button
        followUnFollowButton = findViewById(R.id.FollowUnFollowButton);
        followUnFollowButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {

                isFollowing = !isFollowing;
                FollowingStatusRequest followingStatusRequest = new FollowingStatusRequest(user, isFollowing); // Alias is the @username
                GetOtherUserProfileTask getOtherUserProfileTask = new GetOtherUserProfileTask(presenter, OtherUserProfileActivity.this);
                getOtherUserProfileTask.execute(followingStatusRequest);

                if (isFollowing) {
                    followUnFollowButton.setText("FOLLOWING");
                }
                else {
                    followUnFollowButton.setText("FOLLOW");
                }
//                // Display the Follower Count
//                TextView followerCount = findViewById(R.id.otherUserfollowerCount);
//                followerCount.setText(getString(R.string.followerCount, user.getFollowersCount()));
//                // Display the Followee Count
//                TextView followeeCount = findViewById(R.id.otherUserfolloweeCount);
//                followeeCount.setText(getString(R.string.followeeCount, user.getFollowingCount()));

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        setContentView(R.layout.activity_other_user_profile);
//        View view = inflater.inflate(R.layout.fragment_login, container, false);

//        followerPresenter = new FollowerPresenter(this);
//        FollowerRequest followerRequest = new FollowerRequest(user, 10000, null); // Alias is the @username
//        GetFollowersTask getOtherUserFollowersTask = new GetFollowersTask(followerPresenter, OtherUserProfileActivity.this);
//        getOtherUserFollowersTask.execute(followerRequest);
////        List<User> followers = followerResponse.getFollowers();
//
//        // Display the Follower Count
//        TextView followerCount = findViewById(R.id.otherUserfollowerCount);
//        followerCount.setText(getString(R.string.followerCount, numberOfFollowers));
//
//        // Display the Followee Count
//        TextView followeeCount = findViewById(R.id.otherUserfolloweeCount);
//        followeeCount.setText(getString(R.string.followeeCount, 19));


        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void followingRetrieved(FollowingStatusResponse followingStatusResponse) {
        isFollowing = followingStatusResponse.getFollowing();
        if (isFollowing) {
            followUnFollowButton.setText("FOLLOWING");
        }
        else {
            followUnFollowButton.setText("FOLLOW");
        }
        // Display the Follower Count
        TextView followerCount = findViewById(R.id.otherUserfollowerCount);
        followerCount.setText(getString(R.string.followerCount, user.getFollowersCount()));
        // Display the Followee Count
        TextView followeeCount = findViewById(R.id.otherUserfolloweeCount);
        followeeCount.setText(getString(R.string.followeeCount, user.getFollowingCount()));
    }

    @Override
    public void followersRetrieved(FollowerResponse followerResponse) {
        List<User> followersRetrieved = followerResponse.getFollowers();
        numberOfFollowers = followersRetrieved.size();
        TextView followerCount = findViewById(R.id.otherUserfollowerCount);
        followerCount.setText(getString(R.string.followerCount, numberOfFollowers));
    }

    @Override
    public void handleException(Exception exception) {

    }
}