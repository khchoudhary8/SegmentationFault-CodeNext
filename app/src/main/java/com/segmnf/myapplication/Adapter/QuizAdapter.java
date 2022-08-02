package com.segmnf.myapplication.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.FirebaseDatabase;

import com.segmnf.myapplication.Model.QuizModel;

import com.segmnf.myapplication.R;
import com.segmnf.myapplication.Utils.LeaderboardModel;


import java.util.ArrayList;

import java.util.Random;



public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.Hold> {

    ArrayList<QuizModel> items;
    FirebaseDatabase database;
    ArrayList<Integer> cardbg = new ArrayList<>();
    private Random randomGenerator;


    public QuizAdapter(ArrayList<QuizModel> items, ArrayList<Integer> cardbg) {
        this.items = items;
        this.cardbg = cardbg;

    }

    @NonNull
    @Override
    public QuizAdapter.Hold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_cardview, parent, false);
        return new QuizAdapter.Hold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.Hold holder, int position) {

        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        QuizModel model = items.get(position);
        holder.name.setText(model.getName());
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(cardbg.size());
        holder.cardimage.setImageResource(cardbg.get(index));
        holder.duration.setText(model.getDuration() + " mins");
        holder.date.setText(model.getDate());
        holder.questions.setText(model.getQuestioncount() + " Questions");
        if (model.getQuizid().equals("112")) {
            holder.parent.setVisibility(View.INVISIBLE);
        }
        holder.topic.setText(model.getTopic());
        holder.tough.setText(model.getDifficulty());




//

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
//


//    public void ShowDialogForLeaderboard(Context context, ContestModel contestModel, ArrayList<LeaderboardModel> list1) {
//        Collections.sort(list1);
//        Dialog myDialog = new Dialog(context);
//        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        myDialog.setContentView(R.layout.leaderboard_dialog);
//        myDialog.setCanceledOnTouchOutside(false);
//        myDialog.setCancelable(true);
//        TextView  two, three, name1, name2, name3, score1, score2, score3, total;
//        ImageView image1,image2, image3;
//        MaterialCardView card2, card3;
//        two = myDialog.findViewById(R.id.two);
//        three = myDialog.findViewById(R.id.three);
//        image1 = myDialog.findViewById(R.id.image1);
//        image2 = myDialog.findViewById(R.id.image2);
//        image3 = myDialog.findViewById(R.id.image3);
//        name1 = myDialog.findViewById(R.id.name1);
//        name2 = myDialog.findViewById(R.id.name2);
//        name3 = myDialog.findViewById(R.id.name3);
//        score1 = myDialog.findViewById(R.id.score1);
//        score2 = myDialog.findViewById(R.id.score2);
//        score3 = myDialog.findViewById(R.id.score3);
//        total = myDialog.findViewById(R.id.total);
//        card2 = myDialog.findViewById(R.id.materialCardView2);
//        card3 = myDialog.findViewById(R.id.materialCardView3);
//        two.setVisibility(View.INVISIBLE);
//        three.setVisibility(View.INVISIBLE);
//        name2.setVisibility(View.INVISIBLE);
//        name3.setVisibility(View.INVISIBLE);
//        card2.setVisibility(View.INVISIBLE);
//        card3.setVisibility(View.INVISIBLE);
//        score2.setVisibility(View.INVISIBLE);
//        score3.setVisibility(View.INVISIBLE);
//
//        total.setText("Total: "+list1.size());
//
//        for(int i=0; i<list1.size();i++)
//        {
//            int finalI = i;
//            database.getReference().child("User_details").child(list1.get(i).getUserid()).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists()){
//                        UserModel model = snapshot.getValue(UserModel.class);
//                        if(model.getUid().equals(FirebaseAuth.getInstance().getUid()))
//                            model.setName("You");
//
//                        if(finalI ==0) {
//                            Glide.with(image1).load(model.getImage()).into(image1);
//                            name1.setText(model.getName());
//                            score1.setText(list1.get(0).getScore()+"\n"+list1.get(0).getTimetaken());
//                        }
//                        if(finalI==1){
//                            Glide.with(image2).load(model.getImage()).into(image2);
//                            name2.setText(model.getName());
//                            score2.setText(list1.get(1).getScore()+"\n"+list1.get(1).getTimetaken());
//                            name2.setVisibility(View.VISIBLE);
//                            card2.setVisibility(View.VISIBLE);
//                            score2.setVisibility(View.VISIBLE);
//                            two.setVisibility(View.VISIBLE);
//                        }
//                        if(finalI==2){
//                            Glide.with(image3).load(model.getImage()).into(image3);
//                            name3.setText(model.getName());
//                            score3.setText(list1.get(2).getScore()+"\n"+list1.get(2).getTimetaken());
//                            name3.setVisibility(View.VISIBLE);
//                            card3.setVisibility(View.VISIBLE);
//                            score3.setVisibility(View.VISIBLE);
//                            three.setVisibility(View.VISIBLE);
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//
//        RecyclerView recyclerView = myDialog.findViewById(R.id.leaderboardrecyccler);
//        LeaderboardAdpater adapter = new LeaderboardAdpater(list1);
//        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);
//        myDialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
//        myDialog.setOnShowListener(dialogInterface -> {
//        });
//        myDialog.show();
//        myDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    dialog.cancel();
//
//                    return true;
//                }
//                return false;
//            }
//        });
//
//
//    }

    public class Hold extends RecyclerView.ViewHolder {
        TextView name, marks, duration, date, questions, tough, topic;
        ImageView cardimage;
        CardView parent;

        public Hold(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.quizname);
            questions = itemView.findViewById(R.id.quizquestions);
            duration = itemView.findViewById(R.id.quizduration);
            date = itemView.findViewById(R.id.quizdate);
            cardimage = itemView.findViewById(R.id.imagequiz);
            parent = itemView.findViewById(R.id.cardparentquiz);
            tough = itemView.findViewById(R.id.difficultycardquiz);
            topic = itemView.findViewById(R.id.quiztopic);

        }

    }
}
