package com.segmnf.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;
import com.segmnf.myapplication.databinding.FragmentEditorBinding;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;

import java.util.HashSet;
import java.util.Set;


public class EditorFragment extends Fragment {

    FragmentEditorBinding binding;
    private FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditorBinding.inflate(inflater);

        String cppCode = "#include <iostream>\n" +
                "using namespace std;\n" +
                "\n" +
                "int main() {\n" +
                "\t// your code goes here\n" +
                "\treturn 0;\n" +
                "}";
        binding.codeView.setTextHighlighted(cppCode);
        SyntaxHighlighter.applyMonokaiTheme(getContext(), binding.codeView);
        binding.codeView.setEnableLineNumber(true);
        binding.codeView.setLineNumberTextColor(Color.parseColor("#BABABA"));
        binding.codeView.setLineNumberTextSize(24f);
        Set<Character> indentationStart = new HashSet<>();
        indentationStart.add('{');
        binding.codeView.setIndentationStarts(indentationStart);


        Set<Character> indentationEnds = new HashSet<>();
        indentationEnds.add('}');
        binding.codeView.setIndentationEnds(indentationEnds);

        binding.codeView.setTabLength(4);
        binding.codeView.setEnableAutoIndentation(true);
        binding.codeView.setMatchingHighlightColor(Color.parseColor("#BABABA"));

        binding.spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<String>() {
            @Override public void onItemSelected(int oldIndex, @Nullable String oldItem, int newIndex, String newItem) {
                Log.d("tag",newIndex + " selected!");
            }
        });

        return binding.getRoot();
    }
}