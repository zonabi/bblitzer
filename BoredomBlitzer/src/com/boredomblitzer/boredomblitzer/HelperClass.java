package com.boredomblitzer.boredomblitzer;

import android.app.Activity;

public abstract class HelperClass {
	protected Activity  mActivity;
    protected String    mData;

    public HelperClass() {
        // Subclass will set variables
    }

    protected abstract void callMyActivityMethod(String txtString);

    // More code for all the other stuff the class does
}
