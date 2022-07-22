package com.segmnf.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.segmnf.myapplication.Adapter.ContestAdapter;
import com.segmnf.myapplication.Adapter.QuestionInsideAdapter;
import com.segmnf.myapplication.Model.QuestionModel;
import com.segmnf.myapplication.databinding.ActivityContestQuestionBinding;
import com.segmnf.myapplication.databinding.ActivityContestQuestionDetailBinding;

import java.util.ArrayList;

public class ContestQuestionActivity extends AppCompatActivity {
    ActivityContestQuestionBinding binding;
    private FirebaseDatabase database;


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideStatus() {
        /* To make the status bar transparent*/

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityContestQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            hideStatus();

        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        Intent intent = getIntent();
        String qid = intent.getStringExtra("qid");
        String name = intent.getStringExtra("name");
        String duration = intent.getStringExtra("duration");
        String id = intent.getStringExtra("id");
        String adminid = intent.getStringExtra("adminid");
        ArrayList<QuestionModel> list= new ArrayList<>();
        QuestionInsideAdapter adapter = new QuestionInsideAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(ContestQuestionActivity.this, LinearLayoutManager.VERTICAL,false);
        binding.questionrecycler.setLayoutManager(manager);
        binding.questionrecycler.setAdapter(adapter);

        String[] splitStr = qid.trim().split("\\s+");
//        this.contestid = contestid;
//        this.name = name;
//        this.avgtime = avgtime;
//        this.difficulty = difficulty;
//        this.marks = marks;
//        this.description = description;
//        this.eg1 = eg1;
//        this.eg2 = eg2;
//        this.constraints = constraints;
//        this.testcases = testcases;
//        this.testoutputs = testoutputs;
//        this.cpu = cpu;
//        this.memory = memory;
//        database.getReference().child("Admins").child(adminid).child("questions").child("23").setValue(new QuestionModel("2","Maximum Number of Pairs in Array"
//        ,"20","Easy","10",
//                "You are given a 0-indexed integer array nums. In one operation, you may do the following:\n" +
//                "\n" +
//                "Choose two integers in nums that are equal.\n" +
//                "Remove both integers from nums, forming a pair.\n" +
//                "The operation is done on nums as many times as possible.\n" +
//                "\n" +
//                "Return a 0-indexed integer array answer of size 2 where answer[0] is the number of pairs that are formed and answer[1] is the number of leftover integers in nums after doing the operation as many times as possible."
//        ,"Input: nums = [1,3,2,1,3,2,2]\n\n" +
//                "Output: [3,1]\n\n" +
//                "Explanation:\n" +
//                "Form a pair with nums[0] and nums[3] and remove them from nums. Now, nums = [3,2,3,2,2].\n" +
//                "Form a pair with nums[0] and nums[2] and remove them from nums. Now, nums = [2,2,2].\n" +
//                "Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [2].\n" +
//                "No more pairs can be formed. A total of 3 pairs have been formed, and there is 1 number leftover in nums.",
//                "Input: nums = [1,1]\n\n" +
//                        "Output: [1,0]\n\n" +
//                        "Explanation: Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [].\n" +
//                        "No more pairs can be formed. A total of 1 pair has been formed, and there are 0 numbers leftover in nums.",
//                "1 <= nums.length <= 100\n" +
//                        "0 <= nums[i] <= 100","[0]$[1,3,2,1,3,2,2]$[1,1]","[0,1]$[3,1]$[1,0]","1.00","6000000",""));
        for (int i = 0; i < splitStr.length; i++) {
            database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(id).child("Question").child(splitStr[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists())
                    {
                        QuestionModel model1 = snapshot.getValue(QuestionModel.class);
//                        Toast.makeText(getApplicationContext(), ""+model1.getAvgtime(), Toast.LENGTH_SHORT).show();
                        list.add(model1);
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}