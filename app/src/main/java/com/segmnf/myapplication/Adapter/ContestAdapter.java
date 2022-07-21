package com.segmnf.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.segmnf.myapplication.Model.ContestModel;
import com.segmnf.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.Hold> {

    ArrayList<ContestModel> items;
    FirebaseDatabase database;
    ArrayList<Integer> cardbg = new ArrayList<>();
    ArrayList<Integer> cardimg = new ArrayList<>();
    private Random randomGenerator, random;

    public ContestAdapter(ArrayList<ContestModel> items, ArrayList<Integer> cardbg, ArrayList<Integer> cardimg) {
        this.items = items;
        this.cardbg = cardbg;
        this.cardimg = cardimg;
    }

    @NonNull
    @Override
    public ContestAdapter.Hold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_cardview, parent, false);

        return new ContestAdapter.Hold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestAdapter.Hold holder, int position) {
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ContestModel model = items.get(position);
        holder.name.setText(model.getName());
        randomGenerator = new Random();
        random = new Random();
        int index = randomGenerator.nextInt(cardbg.size());
        int indeximage = random.nextInt(cardimg.size());
        holder.card.setImageResource(cardbg.get(index));
        holder.cardimage.setImageResource(cardimg.get(indeximage));
        holder.marks.setText(model.getMarks()+" Marks");
        holder.duration.setText(model.getDuration()+" mins");
        holder.date.setText(model.getDate());
        holder.questions.setText(model.getQuestions()+" Questions");



        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.contest_dialog);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
                TextView content = (TextView) dialog.findViewById(R.id.dialogcontent);
                TextView yes = (TextView) dialog.findViewById(R.id.back_Ok);
                TextView no = (TextView) dialog.findViewById(R.id.backCancel);
                content.setText("Languages supported for the contest are: C++, Java, Python, C, JavaScript, Ruby, C#.\n" +
                        "Each submission will be tested based on our critical test data.\n" +
                        "Please refrain from discussing strategy during the contest. All submissions are run through a plagiarism detector. Any case of code plagiarism will reduce the score of the concerned participants to 0.\n" +
                        "");

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(v.getContext(), OthersProfileActivity.class);
//                    intent.putExtra("userid", model.getUsrid());
//                    v.getContext().startActivity(intent);
                    }
                });
                dialog.show();


                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                            dialog.cancel();
                            return true;
                        }
                        return false;
                    }
                });

//


            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Hold extends RecyclerView.ViewHolder {
        TextView name, marks, duration, date, questions;
        ImageView cardimage;
        ImageView card;

        public Hold(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contestname);
            questions = itemView.findViewById(R.id.contestquestions);
            marks = itemView.findViewById(R.id.contestmarks);
            duration = itemView.findViewById(R.id.contestduration);
            date = itemView.findViewById(R.id.contestdate);
            card = itemView.findViewById(R.id.cardviewcontest);
            cardimage = itemView.findViewById(R.id.imagecontest);
        }

    }
}
