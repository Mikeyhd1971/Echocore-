package com.michaeldale.echocore;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView overlayStatus;
    private TextView bluetoothStatus;
    private ProgressBar moodBar;
    private EditText logBox;
    private Button activateDefense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overlayStatus = findViewById(R.id.overlayStatus);
        bluetoothStatus = findViewById(R.id.bluetoothStatus);
        moodBar = findViewById(R.id.moodBar);
        logBox = findViewById(R.id.logBox);
        activateDefense = findViewById(R.id.activateDefense);

        detectBluetoothStatus();
        log("EchoCore initialized.");

        activateDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log("Full defense mode activated.");
                moodBar.setProgress(100);
                overlayStatus.setText("Overlay: MONITORED");
                overlayStatus.setTextColor(Color.YELLOW);
                bluetoothStatus.setText("Bluetooth: LOCKED");
                bluetoothStatus.setTextColor(Color.RED);
            }
        });
    }

    private void detectBluetoothStatus() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            bluetoothStatus.setText("Bluetooth: OK");
            bluetoothStatus.setTextColor(Color.GREEN);
        } else {
            bluetoothStatus.setText("Bluetooth: OFF");
            bluetoothStatus.setTextColor(Color.RED);
        }
    }

    private void log(String message) {
        logBox.append(message + "\n");
    }
}
