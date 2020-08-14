package com.example.geo_quiz_test.model;

import java.io.Serializable;

public class Questions implements Serializable {

        private int mQuestionResId;
        private boolean mIsAnswerTrue;
        private boolean mdisable;
        private boolean mcheat;
        private int mSize;

        public int getSize() {
            return mSize;
        }

        public void setSize(int size) {
            mSize = size;
        }

        public boolean isMcheat() {
            return mcheat;
        }

        public void setMcheat(boolean mcheat) {
            this.mcheat = mcheat;
        }

        public boolean isMdisable() {
            return mdisable;
        }

        public void setMdisable(boolean mdisable) {
            this.mdisable = mdisable;
        }


        public int getQuestionResId() {
            return mQuestionResId;
        }

        public void setQuestionResId(int questionResId) {
            mQuestionResId = questionResId;
        }

        public boolean isAnswerTrue() {
            return mIsAnswerTrue;
        }

        public void setAnswerTrue(boolean answerTrue) {
            mIsAnswerTrue = answerTrue;
        }

        public Questions(int questionResId, boolean isAnswerTrue) {
            mQuestionResId = questionResId;
            mIsAnswerTrue = isAnswerTrue;
            mdisable=false;
        }

        public Questions() {
            mdisable=false;
        }
    }

