package com.segmnf.myapplication.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.segmnf.myapplication.Adapter.ContestAdapter;
import com.segmnf.myapplication.Model.ContestModel;
import com.segmnf.myapplication.R;
import com.segmnf.myapplication.databinding.FragmentContestBinding;
import com.segmnf.myapplication.databinding.FragmentEditorBinding;

import java.util.ArrayList;

public class ContestFragment extends Fragment {

    private FirebaseDatabase database;
    SharedPreferences preferences;
    ArrayList<ContestModel> list = new ArrayList<>();
    ArrayList<Integer> cardbg = new ArrayList<>();
    ArrayList<Integer> cardimg = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contest, container, false);
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");

        cardbg.add(R.drawable.bg1);
        cardbg.add(R.drawable.bg2);
        cardbg.add(R.drawable.bg3);
        cardbg.add(R.drawable.bg4);
        cardbg.add(R.drawable.bg5);
        cardimg.add(R.drawable.ic_undraw_cloud_hosting_a1gf);
        cardimg.add(R.drawable.ic_undraw_code_thinking_re_gka2);
        cardimg.add(R.drawable.ic_undraw_pair_programming_re_or4x);
        cardimg.add(R.drawable.ic_undraw_developer_activity_re_39tg);
        cardimg.add(R.drawable.ic_undraw_functions_re_alho);

        RecyclerView contestrecycler = view.findViewById(R.id.contestreecycler);
        ContestModel model = new ContestModel("Interview Prep 1.0", "21 Jul" ,"60", "3", "90","2","12:00","23:59","21 22 23", "100","");
//        database.getReference().child("Contests").child("112").setValue(model);
        ContestAdapter adapter = new ContestAdapter(list,cardbg,cardimg);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
       contestrecycler.setLayoutManager(manager);
       contestrecycler.setAdapter(adapter);

        database.getReference().child("Contests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                        ContestModel model = snapshot1.getValue(ContestModel.class);
                        list.add(model);
                        adapter.notifyDataSetChanged();
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  view;
    }

}