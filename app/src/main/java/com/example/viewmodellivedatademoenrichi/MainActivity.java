package com.example.viewmodellivedatademoenrichi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private CounterViewModel viewModel;
    private TextView tvCount;
    private Button btnIncrement, btnIncrementBackground, btnDecrement, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tvCount);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnIncrementBackground = findViewById(R.id.btnIncrementBackground);
        btnDecrement = findViewById(R.id.btnDecrement);
        btnReset = findViewById(R.id.btnReset);

        viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        viewModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer newCount) {
                tvCount.setText(String.valueOf(newCount));
            }
        });

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.increment();
            }
        });

        btnIncrementBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.incrementFromBackground();
            }
        });

        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.decrement();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.reset();
            }
        });
    }
}
