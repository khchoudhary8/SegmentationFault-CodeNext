package com.segmnf.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.segmnf.myapplication.R;
import com.segmnf.myapplication.Utils.LeaderboardModel;

import java.util.ArrayList;

public class LeaderboardAdpater extends RecyclerView.Adapter<LeaderboardAdpater.Hold> {

    ArrayList<LeaderboardModel> items;
    FirebaseDatabase database;

    public LeaderboardAdpater(ArrayList<LeaderboardModel> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public LeaderboardAdpater.Hold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_rowview, parent, false);

        return new LeaderboardAdpater.Hold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdpater.Hold holder, int position) {
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        LeaderboardModel model = items.get(position);
//        holder.name.setText(model.getName());

//        holder.card.setImageResource(cardbg.get(index));
//        holder.cardimage.setImageResource(cardimg.get(indeximage));
//        holder.marks.setText(model.getMarks() + " Marks");
//        holder.duration.setText(model.getDuration() + " mins");
//        holder.date.setText(model.getDate());
//        holder.questions.setText(model.getQuestions() + " Questions");
//        if (model.getId().equals("112")) {
//            holder.parent.setVisibility(View.INVISIBLE);
//        }
//        holder.tough.setText(model.getTough());
//

//        database.getReference().child("LeaderBoard").child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Hold extends RecyclerView.ViewHolder {
        TextView name, score, position;
        ImageView image;

        CardView parent;

        public Hold(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameC);
            score = itemView.findViewById(R.id.name2C);
            position = itemView.findViewById(R.id.position);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.cardView7);



        }

    }
}
