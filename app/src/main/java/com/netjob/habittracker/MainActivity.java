package com.netjob.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.netjob.habittracker.data.HabitDataContract.HabitEntry;
import com.netjob.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {


    Button addButton;
    public HabitDbHelper helper;
    TextView dataTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataTextView = (TextView) findViewById(R.id.textView_data);
        addButton = (Button) findViewById(R.id.button_add);
        helper = new HabitDbHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataEditor.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_data_editor, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        addDummyData();
        displayDatabaseContent();
        return super.onOptionsItemSelected(item);
    }


    private void addDummyData() {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME_MILES, 5);
        values.put(HabitEntry.COLUMN_NAME_RAN, HabitEntry.RAN_DEFAULT);
        values.put(HabitEntry.COLUMN_NAME_PUSHUPS, 10);
        values.put(HabitEntry.COLUMN_NAME_SYMPTOMS, HabitEntry.SYMPTOMS_DEFAULT);
        values.put(HabitEntry.COLUMN_NAME_WATERCUPS, 2);

        long newRowID = db.insert(HabitEntry.TABLE_NAME, null, values);

    }

    private void displayDatabaseContent() {

        StringBuilder stringBuilder = new StringBuilder();
        SQLiteDatabase database = helper.getReadableDatabase();

        String[] projection = {
                HabitEntry.COLUMN_NAME_RAN,
                HabitEntry.COLUMN_NAME_MILES,
                HabitEntry.COLUMN_NAME_PUSHUPS,
                HabitEntry.COLUMN_NAME_WATERCUPS,
                HabitEntry.COLUMN_NAME_SYMPTOMS

        };

        Cursor cursor = database.query(HabitEntry.TABLE_NAME, projection,
                null,
                null,
                null,
                null,
                null);

        int ranIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_RAN);
        int milesColIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_MILES);
        int pushupsColIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_PUSHUPS);
        int watercupsColIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_WATERCUPS);
        int symptomsColIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME_SYMPTOMS);

        while (cursor.moveToNext()) {

            String ran = (cursor.getInt(ranIndex) == 1 ?
                            getString(R.string.yes) : getString(R.string.no));
            int miles = cursor.getInt(milesColIndex);
            int pushups = cursor.getInt(pushupsColIndex);
            int watercups = cursor.getInt(watercupsColIndex);
            String symptom = cursor.getString(symptomsColIndex);


            stringBuilder.append(String.format("%s - %d - %d - %d - %s%n",
                    ran,
                    miles,
                    pushups,
                    watercups,
                    symptom));

        }

               dataTextView.setText(stringBuilder.toString());

    }


}
