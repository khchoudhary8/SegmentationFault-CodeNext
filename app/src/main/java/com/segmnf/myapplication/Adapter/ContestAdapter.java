package com.segmnf.myapplication.Adapter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.segmnf.myapplication.ContestQuestionActivity;
import com.segmnf.myapplication.Model.ContestModel;
import com.segmnf.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
        holder.marks.setText(model.getMarks() + " Marks");
        holder.duration.setText(model.getDuration() + " mins");
        holder.date.setText(model.getDate());
        holder.questions.setText(model.getQuestions() + " Questions");
        if (model.getId().equals("112")) {
            holder.parent.setVisibility(View.INVISIBLE);
        }
        database.getReference().child("LeaderBoard").child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    holder.cardLeader.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).child("submissiontime").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            if (snapshot.getValue().equals("0")) {
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
                                TextView start = (TextView) dialog.findViewById(R.id.starttime);
                                TextView end = (TextView) dialog.findViewById(R.id.endtime);
                                start.setText(model.getStart());
                                end.setText(model.getEnd());
                                content.setText("Languages supported for the contest are: C++, Java, Python, C, JavaScript, Ruby, C#.\n\n" +
                                        "Each submission will be tested based on our critical test data.\n\n" +
                                        "Please refrain from discussing strategy during the contest. All submissions are run through a plagiarism detector. Any case of code plagiarism will reduce the score of the concerned participants to 0." +
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
                                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                                        String currentDate = new SimpleDateFormat("dd MMM", Locale.getDefault()).format(new Date());
                                        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                                        if (currentDate.equals(model.getDate())) {

                                            Date date_from = null;
                                            try {
                                                date_from = formatter.parse(model.getStart());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            Date date_to = null;
                                            try {
                                                date_to = formatter.parse(model.getEnd());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            Date dateNow = null;
                                            try {
                                                dateNow = formatter.parse(currentTime);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            if (date_from.before(dateNow) && date_to.after(dateNow)) {
                                                Toast.makeText(v.getContext(), "Starting Now.", Toast.LENGTH_SHORT).show();
                                                String[] splitStr = model.getQid().trim().split("\\s+");


                                                database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                        if (snapshot.exists()) {

                                                            Intent intent = new Intent(v.getContext(), ContestQuestionActivity.class);
                                                            intent.putExtra("qid", model.getQid());
                                                            intent.putExtra("duration", model.getDuration());
                                                            intent.putExtra("name", model.getName());
                                                            intent.putExtra("id", model.getId());
                                                            intent.putExtra("adminid", model.getAdminid());

                                                            v.getContext().startActivity(intent);

                                                            dialog.dismiss();
                                                        } else {
                                                            database.getReference().child("Contests").child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isComplete()) {
                                                                                for (int i = 0; i < splitStr.length; i++) {
                                                                                    int finalI = i;
                                                                                    database.getReference().child("Admins").child(model.getAdminid()).child("questions").child(splitStr[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                        @Override
                                                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                                            if (snapshot.exists()) {
                                                                                                database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).child("Question").child(splitStr[finalI]).setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                        if (task.isComplete()) {

                                                                                                            Intent intent = new Intent(v.getContext(), ContestQuestionActivity.class);
                                                                                                            intent.putExtra("qid", model.getQid());
                                                                                                            intent.putExtra("duration", model.getDuration());
                                                                                                            intent.putExtra("name", model.getName());
                                                                                                            intent.putExtra("id", model.getId());
                                                                                                            intent.putExtra("adminid", model.getAdminid());

                                                                                                            v.getContext().startActivity(intent);

                                                                                                            dialog.dismiss();
                                                                                                        } else
                                                                                                            Toast.makeText(v.getContext(), "Something went wrong, Please contact developer", Toast.LENGTH_SHORT).show();
                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                                                        }
                                                                                    });

                                                                                }

                                                                            }

                                                                        }
                                                                    });

                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });


                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });

                                            } else
                                                Toast.makeText(v.getContext(), "Contest not Started or Expired", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(v.getContext(), "Please check contest date before starting.", Toast.LENGTH_SHORT).show();
                                        }
//
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

                            } else {
                                Toast.makeText(v.getContext(), "Contest already submitted", Toast.LENGTH_SHORT).show();
                            }
                        } else {
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
                            TextView start = (TextView) dialog.findViewById(R.id.starttime);
                            TextView end = (TextView) dialog.findViewById(R.id.endtime);
                            start.setText(model.getStart());
                            end.setText(model.getEnd());
                            content.setText("Languages supported for the contest are: C++, Java, Python, C, JavaScript, Ruby, C#.\n\n" +
                                    "Each submission will be tested based on our critical test data.\n\n" +
                                    "Please refrain from discussing strategy during the contest. All submissions are run through a plagiarism detector. Any case of code plagiarism will reduce the score of the concerned participants to 0." +
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
                                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                                    String currentDate = new SimpleDateFormat("dd MMM", Locale.getDefault()).format(new Date());
                                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                                    if (currentDate.equals(model.getDate())) {

                                        Date date_from = null;
                                        try {
                                            date_from = formatter.parse(model.getStart());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Date date_to = null;
                                        try {
                                            date_to = formatter.parse(model.getEnd());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Date dateNow = null;
                                        try {
                                            dateNow = formatter.parse(currentTime);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        if (date_from.before(dateNow) && date_to.after(dateNow)) {
                                            Toast.makeText(v.getContext(), "Starting Now.", Toast.LENGTH_SHORT).show();
                                            String[] splitStr = model.getQid().trim().split("\\s+");


                                            database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                    if (snapshot.exists()) {

                                                        Intent intent = new Intent(v.getContext(), ContestQuestionActivity.class);
                                                        intent.putExtra("qid", model.getQid());
                                                        intent.putExtra("duration", model.getDuration());
                                                        intent.putExtra("name", model.getName());
                                                        intent.putExtra("id", model.getId());
                                                        intent.putExtra("adminid", model.getAdminid());

                                                        v.getContext().startActivity(intent);

                                                        dialog.dismiss();
                                                    } else {
                                                        database.getReference().child("Contests").child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isComplete()) {
                                                                            for (int i = 0; i < splitStr.length; i++) {
                                                                                int finalI = i;
                                                                                database.getReference().child("Admins").child(model.getAdminid()).child("questions").child(splitStr[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                                        if (snapshot.exists()) {
                                                                                            database.getReference().child("Score").child(FirebaseAuth.getInstance().getUid()).child("Contests").child(model.getId()).child("Question").child(splitStr[finalI]).setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                @Override
                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                    if (task.isComplete()) {

                                                                                                        Intent intent = new Intent(v.getContext(), ContestQuestionActivity.class);
                                                                                                        intent.putExtra("qid", model.getQid());
                                                                                                        intent.putExtra("duration", model.getDuration());
                                                                                                        intent.putExtra("name", model.getName());
                                                                                                        intent.putExtra("id", model.getId());
                                                                                                        intent.putExtra("adminid", model.getAdminid());

                                                                                                        v.getContext().startActivity(intent);

                                                                                                        dialog.dismiss();
                                                                                                    } else
                                                                                                        Toast.makeText(v.getContext(), "Something went wrong, Please contact developer", Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            });

                                                                                        }
                                                                                    }

                                                                                    @Override
                                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                                    }
                                                                                });

                                                                            }

                                                                        }

                                                                    }
                                                                });

                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });


                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });

                                        } else
                                            Toast.makeText(v.getContext(), "Contest not Started or Expired", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(v.getContext(), "Please check contest date before starting.", Toast.LENGTH_SHORT).show();
                                    }
//
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

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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
        TextView name, marks, duration, date, questions, start, end;
        ImageView cardimage,cardLeader;
        ImageView card;
        CardView parent;

        public Hold(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contestname);
            questions = itemView.findViewById(R.id.contestquestions);
            marks = itemView.findViewById(R.id.contestmarks);
            duration = itemView.findViewById(R.id.contestduration);
            date = itemView.findViewById(R.id.contestdate);
            card = itemView.findViewById(R.id.cardviewcontest);
            cardimage = itemView.findViewById(R.id.imagecontest);
            parent = itemView.findViewById(R.id.cardparent);
            cardLeader = itemView.findViewById(R.id.leaderboard);


        }

    }
}
