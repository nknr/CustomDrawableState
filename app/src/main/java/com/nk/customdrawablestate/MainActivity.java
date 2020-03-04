package com.nk.customdrawablestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    enum States {
        ENABLED,
        DISABLED,
        NEW_MAIL
    }

    // initialize the enum with the Enabled state
    States enumStates = States.ENABLED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final CustomStatesImageButton imageButton = findViewById(R.id.custom_image_button);

        // when you click on the Change State button your image from the image button will change
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (enumStates == States.ENABLED) {
                    imageButton.setEnabled(false);
                    enumStates = States.DISABLED;
                } else if (enumStates == States.DISABLED) {
                    imageButton.setNewData(true);
                    enumStates = States.NEW_MAIL;
                } else if (enumStates == States.NEW_MAIL) {
                    imageButton.setEnabled(true);
                    enumStates = States.ENABLED;
                }

            }
        });
    }
}
