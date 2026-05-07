package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

// MainActivity implements Fragment2Listener to receive the name from Fragment2
public class MainActivity extends AppCompatActivity implements Fragment2.Fragment2Listener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        Fragment2 fragment2 = Fragment2.newInstance(null, null);

        // start with Fragment1
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        // this button only moves from Fragment1 to Fragment2
        binding.buttonContinue.setOnClickListener(view -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment2)
                    .addToBackStack(null)
                    .commit();

            // hide the activity button because Fragment2 has its own Continue button
            binding.buttonContinue.setVisibility(View.GONE);
        });
    }

    // this method runs when Fragment2 calls listener.onContinueClicked(name)
    @Override
    public void onContinueClicked(String name) {
        // create Fragment3 and pass the name to it
        Fragment3 fragment3 = Fragment3.newInstance(name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();
    }
}
