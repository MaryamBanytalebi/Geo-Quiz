package com.example.geo_quiz_test.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geo_quiz_test.R;

public class CheatActivity extends AppCompatActivity {
    public static final String TAG = "Cheat Activity";
    public static final String BUNDLE_KEY_ANSWER = "answer";
    private boolean mIsAnswerTrue, mIsClick;
    private TextView mTxtAnswer;
    private Button mBtnShowAnswer;
    public static final String EXTRA_IS_CHEAT = "com.example.geoquiz.isCheat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        findViews();

        if (savedInstanceState != null) {
            Log.d(TAG, "saveInstanceState: " + savedInstanceState);
            mIsClick = savedInstanceState.getBoolean(BUNDLE_KEY_ANSWER, false);
            mTxtAnswer.setText(mIsClick ? R.string.btn_true_text : R.string.btn_false_text);
        }


        setListeners();

        mIsAnswerTrue = getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER, false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "saveInsatanceState: " + mIsClick);
        outState.putBoolean(BUNDLE_KEY_ANSWER, mIsClick);
    }

    private void setListeners() {
        mBtnShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsAnswerTrue)
                    mTxtAnswer.setText(R.string.btn_true_text);
                else
                    mTxtAnswer.setText(R.string.btn_false_text);
                mIsClick = true;
                setShownAnswerResult(true);
            }
        });
    }

    private void setShownAnswerResult(boolean isCheat) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, isCheat);
        setResult(RESULT_OK, intent);
    }

    private void findViews() {
        mTxtAnswer = findViewById(R.id.txtview_answer);
        mBtnShowAnswer = findViewById(R.id.btn_show_answer);
    }
}