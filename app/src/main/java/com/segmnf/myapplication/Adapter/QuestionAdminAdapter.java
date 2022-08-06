package com.segmnf.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.FirebaseDatabase;
import com.segmnf.myapplication.ContestQuestionActivity;
import com.segmnf.myapplication.ContestQuestionDetailActivity;
import com.segmnf.myapplication.Model.QuestionModel;
import com.segmnf.myapplication.Model.QuizQuestionModel;
import com.segmnf.myapplication.R;

import java.util.ArrayList;

public class QuestionAdminAdapter extends RecyclerView.Adapter<QuestionAdminAdapter.Hold> {

    ArrayList<QuizQuestionModel> items;
    FirebaseDatabase database;


    public QuestionAdminAdapter(ArrayList<QuizQuestionModel> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public QuestionAdminAdapter.Hold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_quiz_question_admin, parent, false);
        return new QuestionAdminAdapter.Hold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdminAdapter.Hold holder, int position) {
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        QuizQuestionModel model = items.get(position);
        holder.name.setText(model.getQuestion());
        holder.marks.setText("Scores: "+model.getMarks());
        holder.check.isChecked();



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Hold extends RecyclerView.ViewHolder {
        TextView name, marks;
        MaterialCardView card;
        CheckBox check;


        public Hold(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namequestion);
            marks = itemView.findViewById(R.id.scores10);
            check = itemView.findViewById(R.id.check);
            card = itemView.findViewById(R.id.qcard);


        }

    }
}
