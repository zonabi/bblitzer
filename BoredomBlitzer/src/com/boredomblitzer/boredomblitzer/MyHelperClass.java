package com.boredomblitzer.boredomblitzer;

public class MyHelperClass extends HelperClass {
	private ShowActivity mInstance;

    public MyHelperClass(ShowActivity act, String data) {
        super();
        this.mInstance = act;
        this.mActivity = act;  // Useful for calling generic Activity methods in the HelperClass
        this.mData = data;
    }

    @Override
	protected void callMyActivityMethod(String txtString) {
       // mInstance.setActTextField(txtString);
    }
}
