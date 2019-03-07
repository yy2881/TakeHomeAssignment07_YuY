package com.example.android.inclass0213;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue){
        mAnswerTrue = answerTrue;
        mTextResId = textResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}
