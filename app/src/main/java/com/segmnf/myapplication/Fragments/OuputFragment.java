package com.segmnf.myapplication.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;
import com.segmnf.myapplication.ApiHandler;
import com.segmnf.myapplication.ApiService;
import com.segmnf.myapplication.Utils.PostData;
import com.segmnf.myapplication.databinding.FragmentOuputBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuputFragment extends Fragment {
    FragmentOuputBinding binding;
    private FirebaseDatabase database;
    public static final String MY_SHARED_PREFERENCES = "CODE";
    String language = "cpp", versionIndex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOuputBinding.inflate(inflater);
        database = FirebaseDatabase.getInstance("https://tynkr-3915c-default-rtdb.asia-southeast1.firebasedatabase.app/");


        String clientId = "681fc3e94a1a80550c41aaa7ab53ede4"; //Replace with your client ID
        String clientSecret = "a12c4c9f6354909cb749ecae8774aac3025853f1d40b80bde7e4fd0c611528b"; //Replace with your client Secret
        binding.status.setText("Awaiting run");

        binding.runCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myPreferences = getActivity().getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                String restoredText = myPreferences.getString("code", null);
                language = myPreferences.getString("lang", "");
                String script = restoredText;
                String stdin = binding.inputwindow.getText().toString();
                if (language.equals("cpp")) {
                    language = "cpp14";
                    versionIndex = "4";
                }
                if (language.equals("java"))
                    versionIndex = "4";

                if (language.equals("c"))
                    versionIndex = "5";

                Log.d("TAG", language + versionIndex);
                Log.d("TAG", script);
                Log.d("TAG", stdin);
                ApiService apiService = ApiHandler.getRetrofitInstance();
                Call<String> execute = apiService.execute(new PostData(script, stdin, language, versionIndex));

                binding.status.setText("Queued");

                execute.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        binding.status.setText("");

                        try {

                            if (response.isSuccessful()) {
                                Log.d("TAG", response.toString());
                                JSONObject responseJson = new JSONObject(response.body().toString());
                                String output = responseJson.getString("output");
                                String memory = responseJson.getString("memory");
                                String cpu = responseJson.getString("cpuTime");
                                binding.outputwindow.setText(output+"\nMemory: "+memory+"\nCpu: "+cpu);

                            } else {

                                binding.status.setText(response.errorBody().string());
                            }

                        } catch (JSONException e) {
//                            Toast.makeText(getContext(), "Gagal Parsing JSON : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        binding.status.setText("Failed to fetch response");
                        Toast.makeText(getContext(), "Gagal : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        return binding.getRoot();
    }
}