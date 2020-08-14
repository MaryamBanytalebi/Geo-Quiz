package com.example.geo_quiz_test.model;

import java.io.Serializable;

public class Setting implements Serializable {

        private  int mTextSize = 18;
        private  int mColor ;
        private  boolean mNextButton;
        private  boolean mPrevButton;
        private  boolean mFirstButton;
        private  boolean mLastButton;
        private  boolean mTrueButton;
        private  boolean mFalseButton;

        public int getTextSize() {
            return mTextSize;
        }

        public void setTextSize(int textSize) {
            mTextSize = textSize;
        }

        public int getColor() {
            return mColor;
        }

        public Setting(int color) {
            mColor = color;
        }

        public void setColor(int color) {
            mColor = color;
        }

        public boolean isNextButton() {
            return mNextButton;
        }

        public void setNextButton(boolean nextButton) {
            mNextButton = nextButton;
        }

        public boolean isPrevButton() {
            return mPrevButton;
        }

        public void setPrevButton(boolean prevButton) {
            mPrevButton = prevButton;
        }

        public boolean isFirstButton() {
            return mFirstButton;
        }

        public void setFirstButton(boolean firstButton) {
            mFirstButton = firstButton;
        }

        public boolean isLastButton() {
            return mLastButton;
        }

        public void setLastButton(boolean lastButton) {
            mLastButton = lastButton;
        }

        public boolean isTrueButton() {
            return mTrueButton;
        }

        public void setTrueButton(boolean trueButton) {
            mTrueButton = trueButton;
        }

        public boolean isFalseButton() {
            return mFalseButton;
        }

        public void setFalseButton(boolean falseButton) {
            mFalseButton = falseButton;
        }
    }



