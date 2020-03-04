package com.nk.customdrawablestate;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class CustomStatesImageButton extends ImageButton {

    // this is used when we want to merge our state with the ones from the system
    private static final int[] HAS_NEW_DATA_STATE_SET = {R.attr.state_has_new_data};

    private boolean hasNewData;

    public CustomStatesImageButton(Context context) {
        super(context);
    }

    public CustomStatesImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        loadAttributes(context, attrs);
    }

    public CustomStatesImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        loadAttributes(context, attrs);
    }

    private void loadAttributes(Context context, AttributeSet attributeSet) {

        // R.styleable.CustomStates is the id of the custom state and CustomStates it's the name of your styleable
        // from attributes.xml
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomStates, 0, 0);

        // R.styleable.CustomStates_has_new_data is an ID that is created automatically when you create your
        // custom state list in attributes.xml
        hasNewData = typedArray.getBoolean(R.styleable.CustomStates_has_new_data, false);
        typedArray.recycle();

    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (hasNewData) {
            mergeDrawableStates(drawableState, HAS_NEW_DATA_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public void setEnabled(boolean enabled) {

        // If the image button was set to Has New Data state and then the user changed the state to Enabled
        // then we have to make the hasNewData flag to false
        if (enabled) {
            hasNewData = false;
        }


        super.setEnabled(enabled);

        // Call this to force a view to update its drawable state. This will cause drawableStateChanged to be
        // called on this view. Views that are interested in the new state should call getDrawableState.
        refreshDrawableState();
    }

    public boolean hasNewData(){
        return hasNewData;
    }

    // this method will be used when you will need to set your image button state in the code
    public void setNewData(boolean hasNewData){
        this.hasNewData = hasNewData;

        // Call this to force a view to update its drawable state. This will cause drawableStateChanged to be
        // called on this view. Views that are interested in the new state should call getDrawableState.
        refreshDrawableState();
    }
}

