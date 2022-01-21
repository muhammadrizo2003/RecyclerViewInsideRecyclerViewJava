package com.example.project5.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.R;
import com.example.project5.adapters.CustomAdapter;
import com.example.project5.interfaces.OnBottomReachedListener;
import com.example.project5.models.Member;
import com.example.project5.models.MemberSub;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //   @SuppressLint("HardwareIds") String android_id = Secure.getString(context.getContentResolver(),
        //          Secure.ANDROID_ID);

        //  Secure.ANDROID_ID;

        //        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //        WifiInfo info = manager.getConnectionInfo();
        //        String address = info.getMacAddress();

        initViews();

        refreshAdapter(prepareMembersList());

    }

    // All views are initialized here
    void initViews() {
        context = this;
        this.recyclerView = findViewById(R.id.main_recycler_view);
    }

    void refreshAdapter(ArrayList<Member> members) {
        CustomAdapter customAdapter = new CustomAdapter(context, members, new OnBottomReachedListener() {
            @Override
            // this function works when recyclerview on bottom reached
            public void onBottomReached(int position) {
                Toast.makeText(context, "Main RV Bottom!", Toast.LENGTH_SHORT).show();

                // recyclerView.smoothScrollToPosition(0);
            }
        });

        // recyclerview scrolled
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) throw new AssertionError();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                //you have reached to the bottom of your recycler view
                if (totalItemCount > 0 && endHasBeenReached) {

                    //  customAdapter.setItemList(prepareMemberList());
                    //   Toast.makeText(context, "stop!!", Toast.LENGTH_SHORT).show();
                    //   customAdapter.notifyDataSetChanged();

                }
            }
        });

        recyclerView.setAdapter(customAdapter); // set adapter
    }

    ArrayList<Member> prepareMembersList() {
        ArrayList<Member> members = new ArrayList<>();

        members.add(new Member()); // for header item

        for (int i = 1; i <= 30; i++) {
            if (i == 10 || i == 20) {
                members.add(new Member("Muhammadrizo", "Nurullaxo'jayev", prepareSubMembers()));
            } else {
                members.add(new Member("Muhammadrizo", "Nurullaxo'jayev", null));
            }
        }
        members.add(new Member()); // for footer item
        return members;
    }

    // prepare list which it is for sub members
    ArrayList<MemberSub> prepareSubMembers() {
        ArrayList<MemberSub> subMembers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            subMembers.add(new MemberSub());
        }
        return subMembers;
    }
}

