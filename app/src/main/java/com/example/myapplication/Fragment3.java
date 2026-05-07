package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    // interface to handle checkbox state change
    public interface CheckBoxStateListener {
        void onCheckBoxChanged(boolean isChecked, Button button);
    }

    private Fragment3Binding binding;
    private static final String ARG_NAME = "userName";

    public Fragment3() {}

    // use newInstance to pass the name safely using Bundle
    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);

        // show the user's name received from MainActivity
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_NAME, "Guest");
            binding.tvUserName.setText(name);
        }

        // implement the checkbox interface here
        CheckBoxStateListener checkBoxStateListener = (isChecked, button) -> {
            if (isChecked) {
                button.setEnabled(true);
                button.setText("Finish");
            } else {
                button.setEnabled(false);
                button.setText("Continue");
            }
        };

        // listen to checkbox changes and call the interface
        binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBoxStateListener.onCheckBoxChanged(isChecked, binding.btnFinish);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
