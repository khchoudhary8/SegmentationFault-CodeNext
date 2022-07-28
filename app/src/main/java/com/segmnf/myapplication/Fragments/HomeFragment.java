package com.segmnf.myapplication.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.segmnf.myapplication.Adapter.LeaderboardAdpater;
import com.segmnf.myapplication.ContestQuestionDetailActivity;
import com.segmnf.myapplication.NewsActivity;
import com.segmnf.myapplication.R;
import com.segmnf.myapplication.Model.UserModel;
import com.segmnf.myapplication.Utils.LeaderboardModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class HomeFragment extends Fragment {

    private FirebaseDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
        ArrayList<LeaderboardModel> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView name = view.findViewById(R.id.username);
        ImageView pp = view.findViewById(R.id.pp);
        ImageView news= view.findViewById(R.id.imageView4);
        TextView contests = view.findViewById(R.id.contestsdone);


        database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    contests.setText(+snapshot.getChildrenCount()+" ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.getReference().child("User_details").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel model = snapshot.getValue(UserModel.class);
                            name.setText(model.getName());
                            if(getContext()!=null)
                            Glide.with(getContext()).load(model.getImage()).into(pp);
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), NewsActivity.class);
                    startActivity(intent);
                }
            });
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        CardView card;
        switch (day) {
            case Calendar.SUNDAY:
                 card = view.findViewById(R.id.cardSunday);
                 card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;
            case Calendar.MONDAY:
                card = view.findViewById(R.id.cardMon);
                card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;
            case Calendar.TUESDAY:
                card = view.findViewById(R.id.cardTue);
                card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;
            case Calendar.WEDNESDAY:
                card = view.findViewById(R.id.card);
                card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;
            case Calendar.THURSDAY:
                card = view.findViewById(R.id.cardThu);
                card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;
            case Calendar.FRIDAY:
                card = view.findViewById(R.id.cardFri);
                card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;
            case Calendar.SATURDAY:
                card = view.findViewById(R.id.cardSat);
                card.setCardBackgroundColor(Color.parseColor("#AC0000"));
                break;

        }

        return view;
    }

}
