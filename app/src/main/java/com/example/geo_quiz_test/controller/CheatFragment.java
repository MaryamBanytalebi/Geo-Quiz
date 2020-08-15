package com.example.geo_quiz_test.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.geo_quiz_test.R;

public class CheatFragment extends Fragment {
    public static final String TAG = "Cheat Activity";
    public static final String BUNDLE_KEY_ANSWER = "answer";
    private boolean mIsAnswerTrue, mIsClick;
    private TextView mTxtAnswer;
    private Button mBtnShowAnswer;
    public static final String EXTRA_IS_CHEAT = "com.example.geoquiz.isCheat";


    public CheatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cheat, container, false);
        findViews(view);
        if (savedInstanceState != null) {
            Log.d(TAG, "saveInstanceState: " + savedInstanceState);
            mIsClick = savedInstanceState.getBoolean(BUNDLE_KEY_ANSWER, false);
            mTxtAnswer.setText(mIsClick ? R.string.btn_true_text : R.string.btn_false_text);
        }
        setListeners();
        mIsAnswerTrue = getActivity().getIntent().getBooleanExtra(QuizFragment.EXTRA_ANSWER, false);

        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
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


    private void findViews(View view) {
        mTxtAnswer = view.findViewById(R.id.txtview_answer);
        mBtnShowAnswer = view.findViewById(R.id.btn_show_answer);
    }
    private void setShownAnswerResult(boolean isCheat) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, isCheat);
        getActivity().setResult(getActivity().RESULT_OK, intent);
    }

}