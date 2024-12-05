package com.example.paintapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private int currentColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);

        Button btnClear = findViewById(R.id.btnClear);
        Button btnUndo = findViewById(R.id.btnUndo);
        Button btnRedo = findViewById(R.id.btnRedo);
        Button colorPicker = findViewById(R.id.colorPicker);
        SeekBar brushSizeSeekBar = findViewById(R.id.brushSizeSeekBar);

        btnClear.setOnClickListener(v -> drawingView.clearCanvas());

        btnUndo.setOnClickListener(v -> drawingView.undo());

        btnRedo.setOnClickListener(v -> drawingView.redo());

        brushSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drawingView.changeBrushSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        colorPicker.setOnClickListener(v -> showColorPicker());
    }

    private void showColorPicker() {
        final String[] colors = {"Red", "Green", "Blue", "Black", "Yellow"};
        final int[] colorValues = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.YELLOW};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Color")
                .setItems(colors, (dialog, which) -> {
                    currentColor = colorValues[which];
                    drawingView.changeColor(currentColor);
                })
                .create()
                .show();
    }
}
