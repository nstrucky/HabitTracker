package com.netjob.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.netjob.habittracker.data.HabitDataContract.HabitEntry;
import com.netjob.habittracker.data.HabitDbHelper;

public class DataEditor extends AppCompatActivity {

    HabitDbHelper habitDbHelper;
    CheckBox ranCheckBox;
    EditText milesEditText;
    EditText pushupsEditText;
    EditText cupsofwaterEditText;
    EditText symptomEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_editor);

        ranCheckBox = (CheckBox) findViewById(R.id.checkBox_ran_question);
        milesEditText = (EditText) findViewById(R.id.editText_miles);
        pushupsEditText = (EditText) findViewById(R.id.editText_pushups);
        cupsofwaterEditText = (EditText) findViewById(R.id.editText_watercups);
        symptomEditText = (EditText) findViewById(R.id.editText_symptoms);

        habitDbHelper = new HabitDbHelper(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_editor, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        addHabit();
        finish();
        return super.onOptionsItemSelected(item);
    }


    private void addHabit() {

        SQLiteDatabase database = habitDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (cupsofwaterEditText.getText().length() < 1) {
            cupsofwaterEditText.setText(String.valueOf(HabitEntry.WATERCUPS_DEFAULT));
        }

        if (milesEditText.getText().length() < 1) {
            milesEditText.setText(String.valueOf(HabitEntry.MILES_DEFAULT));
        }

        if (pushupsEditText.getText().length() < 1) {
            pushupsEditText.setText(String.valueOf(HabitEntry.PUSHUPS_DEFAULT));
        }

        if (symptomEditText.getText().length() < 1) {
            symptomEditText.setText(HabitEntry.SYMPTOMS_DEFAULT);
        }


        int ran = (ranCheckBox.isChecked() ?
                HabitEntry.RAN_EQUALS_YES : HabitEntry.RAN_EQUALS_NO);
        double miles = Double.parseDouble(milesEditText.getText().toString());
        int pushups = Integer.parseInt(pushupsEditText.getText().toString());
        int water = Integer.parseInt(cupsofwaterEditText.getText().toString());
        String symptom = symptomEditText.getText().toString();

        values.put(HabitEntry.COLUMN_NAME_WATERCUPS, water);
        values.put(HabitEntry.COLUMN_NAME_SYMPTOMS, symptom);
        values.put(HabitEntry.COLUMN_NAME_PUSHUPS, pushups);
        values.put(HabitEntry.COLUMN_NAME_RAN, ran);
        values.put(HabitEntry.COLUMN_NAME_MILES, miles);

        database.insert(HabitEntry.TABLE_NAME, null, values);

    }



}
