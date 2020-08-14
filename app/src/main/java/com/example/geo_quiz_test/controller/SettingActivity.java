package com.example.geo_quiz_test.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.geo_quiz_test.R;
import com.example.geo_quiz_test.model.Setting;

public class SettingActivity extends AppCompatActivity {

    public static final String TAG = "SettingActivity";
    public static final String EXTRA_SETTING = "setting";
    public static final String EXTRA_COLOR = "color";
    public static final String BUNDLE_KEY_QUESTION_SIZE = "size";
    private RadioButton mBtnSmall, mBtnMedium, mBtnLarge,mBtnGreen, mBtnBlue, mBtnRed;
    private CheckBox mCheckBoxTrue,mCheckBoxFalse,mCheckBoxPerv,mCheckBoxNext,mCheckBoxFirst,mCheckBoxLast;
    private Button mBtnSave, mBtnDiscare;
    private TextView mTxtFalse;
    private int mQuestinSize;
    private RadioGroup mFontGroup;
    private RadioGroup mColorGroup;
    private CheckBox mCheckBox;
    private Setting mSetting;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: "+mQuestinSize);
        outState.putInt(BUNDLE_KEY_QUESTION_SIZE,mQuestinSize);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setContentView(R.layout.activity_setting);
        mSetting= (Setting) getIntent().getSerializableExtra(EXTRA_SETTING);
        findViews();
        if (savedInstanceState != null){
            Log.d(TAG, "SaveInstanceState "+savedInstanceState);
            mQuestinSize=savedInstanceState.getInt(BUNDLE_KEY_QUESTION_SIZE,0);

        }
        initUI();
        setListeners();


    }
    private void setListeners() {
        mFontGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.radioBtn_small:
                        mSetting.setTextSize(14);
                        break;
                    case R.id.radioBtn_large:
                        mSetting.setTextSize(25);
                        break;
                    default:
                        mSetting.setTextSize(18);
                }
            }
        });

        mColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_Btn_Green:
                        mSetting.setColor(getResources().getColor(R.color.green));
                        break;
                    case R.id.radio_Btn_Blue:
                        mSetting.setColor(getResources().getColor(R.color.blue));
                        break;
                    default:
                        mSetting.setColor(getResources().getColor(R.color.red));
                }

            }
        });


/*        mBtnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestinSize = 14;
            }
        });

        mBtnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestinSize = 18;

            }
        });
        mBtnLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestinSize = 25;
            }
        });*/
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(QuizActivity.EXTRA_SETTING_SIZE, mSetting);
                mSetting.setFalseButton(mCheckBoxFalse.isChecked());
                mSetting.setTrueButton(mCheckBoxTrue.isChecked());
                mSetting.setPrevButton(mCheckBoxPerv.isChecked());
                mSetting.setNextButton(mCheckBoxNext.isChecked());
                mSetting.setFirstButton(mCheckBoxFirst.isChecked());
                mSetting.setLastButton(mCheckBoxLast.isChecked());
                setResult(Activity.RESULT_OK, intent);
                finish();


            }
        });
        mBtnDiscare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void findViews() {
        mBtnSmall = findViewById(R.id.radioBtn_small);
        mBtnMedium = findViewById(R.id.radioBtn_medium);
        mBtnLarge = findViewById(R.id.radioBtn_large);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnDiscare = findViewById(R.id.btn_discare);
        mBtnGreen = findViewById(R.id.radio_Btn_Green);
        mBtnBlue = findViewById(R.id.radio_Btn_Blue);
        mBtnRed = findViewById(R.id.radio_Btn_Red);
        mFontGroup = findViewById(R.id.font_radio_group);
        mColorGroup=findViewById(R.id.color_radio_group);
        mCheckBoxFalse=findViewById(R.id.check_false);
        mCheckBoxTrue=findViewById(R.id.check_true);
        mCheckBoxNext=findViewById(R.id.check_next);
        mCheckBoxPerv=findViewById(R.id.check_perv);
        mCheckBoxFirst=findViewById(R.id.check_first);
        mCheckBoxLast=findViewById(R.id.check_last);
    }

    private void initUI(){
        mCheckBoxFalse.setChecked(mSetting.isFalseButton());
        mCheckBoxTrue.setChecked(mSetting.isTrueButton());
        mCheckBoxNext.setChecked(mSetting.isNextButton());
        mCheckBoxPerv.setChecked(mSetting.isPrevButton());
        mCheckBoxFirst.setChecked(mSetting.isTrueButton());
        mCheckBoxLast.setChecked(mSetting.isTrueButton());
    }
}