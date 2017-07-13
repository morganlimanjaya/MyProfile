package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;

    RadioGroup rgGender;
    CheckBox ckbLike;

    Boolean ckbStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.editTextName);
        etGPA = (EditText)findViewById(R.id.editTextGPA);

        rgGender = (RadioGroup)findViewById(R.id.RadioGroupGender);
        ckbLike = (CheckBox)findViewById(R.id.checkBoxLikeProgramming);

    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Retrieve data input of the user
        String strName = etName.getText().toString();
        float cGpa = Float.parseFloat(etGPA.getText().toString());


        if(ckbLike.isChecked()){
            ckbStatus = true;
        }else{
            ckbStatus = false;
        }


        //Step 1b: Obtain an instance of the shared preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1c: Obtain an instance of the shared preference editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1d: Add the key value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", cGpa);
        prefEdit.putBoolean("ckbBox", ckbStatus);

        int selectedId = rgGender.getCheckedRadioButtonId(); // will give you the id of currently selected radio button in the radio group
        prefEdit.putInt("genderStatus", selectedId);

        //Step 1e: Call commit() method to save the changes into the shared preference
        prefEdit.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the shared preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key, greeting from the shared preferences object.
        String strName = prefs.getString("name", "John");
        Float GPA = prefs.getFloat("gpa", 0.0f);
        Boolean ckbChecked = prefs.getBoolean("ckbBox", false);
        int rgChecked = prefs.getInt("genderStatus", 0);

        //convert gpa from float to string
        String cGpa = String.valueOf(GPA);

        //Step 2c: Update the UI element with the value
        etName.setText(strName);
        etGPA.setText(cGpa);
        ckbLike.setChecked(ckbChecked);
        rgGender.check(rgChecked);

    }
}
