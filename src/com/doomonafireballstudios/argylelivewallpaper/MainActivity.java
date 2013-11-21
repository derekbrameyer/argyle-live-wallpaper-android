package com.doomonafireballstudios.argylelivewallpaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private Button previewBTN;
    private Button saveImageBTN;
    private Button setAsWallpaperBTN;
    private CheckBox startLinesInCornerCB;
    private Context mContext;
    private EditText primaryColorET;
    private EditText secondaryColorET;
    private EditText bgColorET;
    private EditText lineColorET;
    private EditText lineThicknessET;
    private NumberPicker diamondsXNP;
    private NumberPicker diamondsYNP;
    private View primaryColorVW;
    private View secondaryColorVW;
    private View bgColorVW;
    private View lineColorVW;
    private Pattern hex = Pattern.compile("^[0-9A-Fa-f]+$");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;

        previewBTN = (Button) findViewById(R.id.BTN_preview);
        saveImageBTN = (Button) findViewById(R.id.BTN_save_image);
        setAsWallpaperBTN = (Button) findViewById(R.id.BTN_set_as_wallpaper);
        startLinesInCornerCB = (CheckBox) findViewById(R.id.CB_start_lines_in_corner);
        primaryColorET = (EditText) findViewById(R.id.ET_primary_color);
        secondaryColorET = (EditText) findViewById(R.id.ET_secondary_color);
        bgColorET = (EditText) findViewById(R.id.ET_background_color);
        lineColorET = (EditText) findViewById(R.id.ET_line_color);
        lineThicknessET = (EditText) findViewById(R.id.ET_line_thickness);
        diamondsXNP = (NumberPicker) findViewById(R.id.NP_diamonds_x);
        diamondsYNP = (NumberPicker) findViewById(R.id.NP_diamonds_y);
        primaryColorVW = (View) findViewById(R.id.VW_primary_color);
        secondaryColorVW = (View) findViewById(R.id.VW_secondary_color);
        bgColorVW = (View) findViewById(R.id.VW_background_color);
        lineColorVW = (View) findViewById(R.id.VW_line_color);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setUpDefaultValues();

        previewBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start PreviewActivity with parameters
                if (validateTextFields()) {
                    Intent i = new Intent(MainActivity.this, PreviewActivity.class);
                    i.putExtra("start_lines_in_corner", startLinesInCornerCB.isChecked());
                    i.putExtra("primary_color", primaryColorET.getText().toString());
                    i.putExtra("secondary_color", secondaryColorET.getText().toString());
                    i.putExtra("bg_color", bgColorET.getText().toString());
                    i.putExtra("line_color", lineColorET.getText().toString());
                    i.putExtra("line_thickness", Float.parseFloat(lineThicknessET.getText().toString()));
                    i.putExtra("diamonds_x", diamondsXNP.getValue());
                    i.putExtra("diamonds_y", diamondsYNP.getValue());
                    startActivity(i);
                }
            }
        });
        saveImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start SaveActivity with parameters
                if (validateTextFields()) {
                    Intent i = new Intent(MainActivity.this, SaveActivity.class);
                    i.putExtra("start_lines_in_corner", startLinesInCornerCB.isChecked());
                    i.putExtra("primary_color", primaryColorET.getText().toString());
                    i.putExtra("secondary_color", secondaryColorET.getText().toString());
                    i.putExtra("bg_color", bgColorET.getText().toString());
                    i.putExtra("line_color", lineColorET.getText().toString());
                    i.putExtra("line_thickness", Float.parseFloat(lineThicknessET.getText().toString()));
                    i.putExtra("diamonds_x", diamondsXNP.getValue());
                    i.putExtra("diamonds_y", diamondsYNP.getValue());
                    startActivity(i);
                }
            }
        });
        setAsWallpaperBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start SetAsWallpaperActivity with parameters
                if (validateTextFields()) {
                    Intent i = new Intent(MainActivity.this, SetAsWallpaperActivity.class);
                    i.putExtra("start_lines_in_corner", startLinesInCornerCB.isChecked());
                    i.putExtra("primary_color", primaryColorET.getText().toString());
                    i.putExtra("secondary_color", secondaryColorET.getText().toString());
                    i.putExtra("bg_color", bgColorET.getText().toString());
                    i.putExtra("line_color", lineColorET.getText().toString());
                    i.putExtra("line_thickness", Float.parseFloat(lineThicknessET.getText().toString()));
                    i.putExtra("diamonds_x", diamondsXNP.getValue());
                    i.putExtra("diamonds_y", diamondsYNP.getValue());
                    startActivity(i);
                }
            }
        });
        primaryColorVW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UberColorPickerDialog(mContext, new UberColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        primaryColorET.setText("");
                        primaryColorET.append(getColorStringFromInt(color).substring(2));
                        setViewColors();
                        setBgGradient();
                    }
                }, getColorIntFromString(primaryColorET.getText().toString()), false).show();
            }
        });
        secondaryColorVW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UberColorPickerDialog(mContext, new UberColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        secondaryColorET.setText("");
                        secondaryColorET.append(getColorStringFromInt(color).substring(2));
                        setViewColors();
                        setBgGradient();
                    }
                }, getColorIntFromString(secondaryColorET.getText().toString()), false).show();
            }
        });
        bgColorVW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UberColorPickerDialog(mContext, new UberColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        bgColorET.setText("");
                        bgColorET.append(getColorStringFromInt(color).substring(2));
                        setViewColors();
                        setBgGradient();
                    }
                }, getColorIntFromString(bgColorET.getText().toString()), false).show();
            }
        });
        lineColorVW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UberColorPickerDialog(mContext, new UberColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        lineColorET.setText("");
                        lineColorET.append(getColorStringFromInt(color).substring(2));
                        setViewColors();
                        setBgGradient();
                    }
                }, getColorIntFromString(lineColorET.getText().toString()), false).show();
            }
        });
    }

    private boolean validateTextFields() {
        boolean isValid = true;

        if (!validateStringAsHexColor(primaryColorET.getText().toString())) {
            isValid = false;
            primaryColorET.setError("Must be valid hex!");
        }
        if (!validateStringAsHexColor(secondaryColorET.getText().toString())) {
            isValid = false;
            secondaryColorET.setError("Must be valid hex!");
        }
        if (!validateStringAsHexColor(bgColorET.getText().toString())) {
            isValid = false;
            bgColorET.setError("Must be valid hex!");
        }
        if (!validateStringAsHexColor(lineColorET.getText().toString())) {
            isValid = false;
            lineColorET.setError("Must be valid hex!");
        }
        try {
            float f = Float.parseFloat(lineThicknessET.getText().toString());
        } catch (Exception e) {
            isValid = false;
            lineColorET.setError("Must be a numerical (float) value!");
        }
        try {
            int i = diamondsXNP.getValue();
        } catch (Exception e) {
            isValid = false;
            Toast.makeText(mContext, "Must have a valid number as width.", Toast.LENGTH_SHORT).show();
        }
        try {
            int i = diamondsYNP.getValue();
        } catch (Exception e) {
            isValid = false;
            Toast.makeText(mContext, "Must have a valid number as height.", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    private boolean validateStringAsHexColor(String s) {
        if (s.length() == 6 && hex.matcher(s).matches()) {
            return true;
        }
        return false;
    }

    private void setUpDefaultValues() {
        startLinesInCornerCB.setChecked(false);
        primaryColorET.append("ffff00");
        secondaryColorET.append("ff0000");
        bgColorET.append("000000");
        lineColorET.append("ffffff");
        lineThicknessET.append("1.0");
        diamondsXNP.setValue(3);
        diamondsYNP.setValue(3);

        setViewColors();
        setBgGradient();
    }

    private void setViewColors() {
        primaryColorVW.setBackgroundColor(getColorIntFromString(primaryColorET.getText().toString()));
        secondaryColorVW.setBackgroundColor(getColorIntFromString(secondaryColorET.getText().toString()));
        bgColorVW.setBackgroundColor(getColorIntFromString(bgColorET.getText().toString()));
        lineColorVW.setBackgroundColor(getColorIntFromString(lineColorET.getText().toString()));
    }

    private String getColorStringFromInt(int i) {
        return Integer.toHexString(i);
    }

    private int getColorIntFromString(String s) {
        return Integer.parseInt(s, 16) + 0xFF000000;
    }

    private void setBgGradient() {
        int prim = Integer.parseInt(primaryColorET.getText().toString(), 16) + 0xFF000000;
        int second = Integer.parseInt(secondaryColorET.getText().toString(), 16) + 0xFF000000;
        int backg = Integer.parseInt(bgColorET.getText().toString(), 16) + 0xFF000000;
        int[] i = new int[]{prim, second, backg};
        GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.BL_TR, i);
        getWindow().setBackgroundDrawable(g);
    }
}
