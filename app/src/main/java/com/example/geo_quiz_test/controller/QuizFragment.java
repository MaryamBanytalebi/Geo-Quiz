package com.example.geo_quiz_test.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geo_quiz_test.R;
import com.example.geo_quiz_test.model.Questions;
import com.example.geo_quiz_test.model.Setting;

public class QuizFragment extends Fragment {
    public static final String TAG = "QuizActivity";
    public static final String BUNDLE_KEY_CURRENT_INDEX = "CurrentIndex";
    public static final String BUNDLE_KEY_SCORE = "Score";
    public static final String BUNDLE_KEY_DISABLE = "Disable";
    public static final String BUNDLE_KEY_GAME_OVER = "GameOver";
    public static final String EXTRA_ANSWER = "answer";
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final int REQUEST_CODE_ANSWER = 1;
    public static final String BUNDLE_KEY_IS_CHEAT = "cheat";
    public static final String EXTRA_SETTING_SIZE = "setting";
    public static final String EXTRA_SETTING_COLOR = "color";
    public static final String BUNDLE_KEY_SETTING = "setting";
    private ImageButton mFalseButton, mTrueButton, mNextBtn, mPrevBtn, mDoubleNext, mDoublePrev, mResetGame;
    private Button mCheatButton, mSettingButton;
    private TextView mQuestionTextView, mScoreTextView, mFinalScore;
    private int mCurrentIndex = 0, mScore = 0, mNumOfAnswers = 0;
    private boolean mIsCheater = false;
    private Setting mIsSetting ;
    private LinearLayout mMainLayout, mScoreLayout;
    private Questions[] mQuestionsBank = {
            new Questions(R.string.question_tehran, true)
            , new Questions(R.string.question_oceans, true)
            , new Questions(R.string.question_middle_east, false)
            , new Questions(R.string.question_australia, false)
            , new Questions(R.string.question_egypt, true)
            , new Questions(R.string.question_america, false)
            , new Questions(R.string.question_asia, false)
    };
    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz, container, false);


        findViews(view);
        mIsSetting = new Setting(getResources().getColor(R.color.green));
        if (savedInstanceState != null) {
            Log.d(TAG, "saveInstanceState: " + savedInstanceState);
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX, 0);
            mScore = savedInstanceState.getInt(BUNDLE_KEY_SCORE, 0);
            mQuestionsBank = (Questions[]) savedInstanceState.getSerializable(BUNDLE_KEY_DISABLE);
            mNumOfAnswers = savedInstanceState.getInt(BUNDLE_KEY_GAME_OVER, 0);
            mIsCheater = savedInstanceState.getBoolean(BUNDLE_KEY_IS_CHEAT, false);
            mIsSetting=(Setting) savedInstanceState.getSerializable(BUNDLE_KEY_SETTING);
        } else {
            Log.d(TAG, "saveInstanceState is null !");
        }

        setListeners();
        setQuestionText(mCurrentIndex);
        setScore();
        onEndScreen();
        return view;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: " + mCurrentIndex);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        Log.d(TAG, "onSaveInstanceState: " + mScore);
        outState.putInt(BUNDLE_KEY_SCORE, mScore);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putSerializable(BUNDLE_KEY_DISABLE, mQuestionsBank);
        Log.d(TAG, "onSaveInstanceState: " + mNumOfAnswers);
        outState.putInt(BUNDLE_KEY_GAME_OVER, mNumOfAnswers);
        Log.d(TAG, "onSaveInstanceState:" + mIsCheater);
        outState.putBoolean(BUNDLE_KEY_IS_CHEAT, mIsCheater);
        Log.d(TAG, "onSaveInstanceState: "+ mIsSetting);
        outState.putSerializable(BUNDLE_KEY_SETTING,mIsSetting);

    }

    private void findViews(View view) {
        mFalseButton = view.findViewById(R.id.false_btn);
        mTrueButton = view.findViewById(R.id.true_btn);
        mQuestionTextView = view.findViewById(R.id.question_text);
        mNextBtn = view.findViewById(R.id.btn_next);
        mPrevBtn = view.findViewById(R.id.btn_prev);
        mDoubleNext =view. findViewById(R.id.btn_double_next);
        mDoublePrev = view.findViewById(R.id.btn_double_prev);
        mScoreTextView = view.findViewById(R.id.score_text);
        mMainLayout = view.findViewById(R.id.main_layout);
        mScoreLayout = view.findViewById(R.id.score_layout);
        mFinalScore = view.findViewById(R.id.score_final);
        mResetGame = view.findViewById(R.id.reset_btn);
        mCheatButton = view.findViewById(R.id.cheat_btn);
        mSettingButton = view.findViewById(R.id.setting_btn);
    }

    public void setListeners() {
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].setMdisable(true);
                checked(false);
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].setMdisable(true);
                mTrueButton.setEnabled(false);
                checked(true);

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(++mCurrentIndex);
                mIsCheater = false;
            }
        });
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(--mCurrentIndex);
                mIsCheater = false;
            }
        });

        mDoubleNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(mQuestionsBank.length - 1);
            }
        });
        mDoublePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(mQuestionsBank.length);
            }
        });
        mResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScore = 0;
                mCurrentIndex = 0;
                mNumOfAnswers = 0;
                setQuestionText(mCurrentIndex);
                setScore();
                mMainLayout.setVisibility(View.VISIBLE);
                mScoreLayout.setVisibility(View.GONE);
                for (int i = 0; i < mQuestionsBank.length; i++) {
                    mQuestionsBank[i].setMdisable(false);
                }
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);

            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheatActivity.class);
                intent.putExtra(EXTRA_ANSWER, mQuestionsBank[mCurrentIndex].isAnswerTrue());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.putExtra(SettingActivity.EXTRA_SETTING, mIsSetting);
                startActivityForResult(intent, REQUEST_CODE_ANSWER);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void checked(boolean pressedUser) {
        mFalseButton.setEnabled(false);
        mTrueButton.setEnabled(false);
        mNumOfAnswers++;
        if (mQuestionsBank[mCurrentIndex].isMcheat()) {
            Toast.makeText(getActivity(), R.string.toast_judgment, Toast.LENGTH_LONG).show();
        } else {
            if (pressedUser == mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].isAnswerTrue()) {
                Toast toast = Toast.makeText(getActivity(), R.string.tost_true_text, Toast.LENGTH_SHORT);
                toast.getView().setBackground(getActivity().getResources().getDrawable(R.color.true_toast_bg));
                TextView toastTextView = toast.getView().findViewById(android.R.id.message);
                toastTextView.setTextColor(getResources().getColor(R.color.black_color));
                toastTextView.setTextSize(30);
                toastTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
                toast.show();
                mQuestionTextView.setTextColor(getResources().getColor(R.color.true_toast_bg));
                mScore++;

            } else {
                Toast toast = Toast.makeText(getActivity(), R.string.tost_false_text, Toast.LENGTH_SHORT);
                toast.getView().setBackground(getActivity().getResources().getDrawable(R.color.false_toast_bg));
                toast.setGravity(Gravity.TOP, 0, 0);
                TextView toastTextView = toast.getView().findViewById(android.R.id.message);
                toastTextView.setTextColor(getResources().getColor(R.color.black_color));
                toastTextView.setTextSize(30);
                toastTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                toast.show();
                mQuestionTextView.setTextColor(getResources().getColor(R.color.false_toast_bg));
            }
        }
        onEndScreen();
    }

    private void onEndScreen() {
        if (mNumOfAnswers == mQuestionsBank.length) {
            mMainLayout.setVisibility(View.GONE);
            mScoreLayout.setVisibility(View.VISIBLE);
            mFinalScore.setText(mQuestionsBank.length + " / " + mScore + getString(R.string.score_text));
        }
    }
    public void setQuestionText(int index) {
        setScore();
        if (mQuestionsBank[(index + mQuestionsBank.length) % mQuestionsBank.length].isMdisable()) {

            mQuestionTextView.setText(mQuestionsBank[(index + mQuestionsBank.length) % mQuestionsBank.length].getQuestionResId());
            mQuestionTextView.setTextColor(getResources().getColor(R.color.black_color));
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        } else {

            mQuestionTextView.setText(mQuestionsBank[(index + mQuestionsBank.length) % mQuestionsBank.length].getQuestionResId());
            mQuestionTextView.setTextColor(getResources().getColor(R.color.black_color));
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);


        }


    }

    public void setScore() {
        mScoreTextView.setText(mQuestionsBank.length + " / " + mScore + getString(R.string.score_text));

    }


}