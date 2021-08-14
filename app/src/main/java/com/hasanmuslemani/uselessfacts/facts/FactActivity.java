package com.hasanmuslemani.uselessfacts.facts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.hasanmuslemani.uselessfacts.MyApp;
import com.hasanmuslemani.uselessfacts.R;
import com.hasanmuslemani.uselessfacts.utils.NetworkRequest;


public class FactActivity extends AppCompatActivity implements FactContract.View {

    FactContract.Presenter presenter;

    TextView textView;
    ProgressBar progressBar;
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact);

        presenter = new FactPresenter(this);

        initViews();

        presenter.loadFact();

        setUpMainLayoutOnClick();
    }

    void initViews() {
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        mainLayout = findViewById(R.id.mainLayout);
    }

    void setUpMainLayoutOnClick() {
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadFact();
            }
        });
    }

    @Override
    public void showLoading() {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showResult(String content) {
        textView.setVisibility(View.VISIBLE);
        textView.setTextColor(Color.WHITE);
        textView.setText(content);
    }

    @Override
    public void showError(String message) {
        textView.setVisibility(View.VISIBLE);
        textView.setTextColor(Color.RED);
        textView.setText(message);
    }
}