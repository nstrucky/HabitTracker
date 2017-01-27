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

    HabitDbHelper mHelper;
    CheckBox mRanCheckBox;
    EditText mMilesEditText;
    EditText mPushupEditText;
    EditText mCupsOfWaterEditText;
    EditText mSymptomEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_editor);

        mRanCheckBox = (CheckBox) findViewById(R.id.checkBox_ran_question);
        mMilesEditText = (EditText) findViewById(R.id.editText_miles);
        mPushupEditText = (EditText) findViewById(R.id.editText_pushups);
        mCupsOfWaterEditText = (EditText) findViewById(R.id.editText_watercups);
        mSymptomEditText = (EditText) findViewById(R.id.editText_symptoms);

        mHelper = new HabitDbHelper(this);

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

        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (mCupsOfWaterEditText.getText().length() < 1) {
            mCupsOfWaterEditText.setText(String.valueOf(HabitEntry.WATERCUPS_DEFAULT));
        }

        if (mMilesEditText.getText().length() < 1) {
            mMilesEditText.setText(String.valueOf(HabitEntry.MILES_DEFAULT));
        }

        if (mPushupEditText.getText().length() < 1) {
            mPushupEditText.setText(String.valueOf(HabitEntry.PUSHUPS_DEFAULT));
        }

        if (mSymptomEditText.getText().length() < 1) {
            mSymptomEditText.setText(HabitEntry.SYMPTOMS_DEFAULT);
        }


        int ran = (mRanCheckBox.isChecked() ?
                HabitEntry.RAN_EQUALS_YES : HabitEntry.RAN_EQUALS_NO);
        double miles = Double.parseDouble(mMilesEditText.getText().toString());
        int pushups = Integer.parseInt(mPushupEditText.getText().toString());
        int water = Integer.parseInt(mCupsOfWaterEditText.getText().toString());
        String symptom = mSymptomEditText.getText().toString();

        values.put(HabitEntry.COLUMN_NAME_WATERCUPS, water);
        values.put(HabitEntry.COLUMN_NAME_SYMPTOMS, symptom);
        values.put(HabitEntry.COLUMN_NAME_PUSHUPS, pushups);
        values.put(HabitEntry.COLUMN_NAME_RAN, ran);
        values.put(HabitEntry.COLUMN_NAME_MILES, miles);

        database.insert(HabitEntry.TABLE_NAME, null, values);

    }



}
