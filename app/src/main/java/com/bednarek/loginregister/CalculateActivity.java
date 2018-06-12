package com.bednarek.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalculateActivity extends AppCompatActivity implements View.OnClickListener{

    EditText Age, Height, Weight;
    RadioGroup radGroupGender, radGroupActive;
    int radioIDGender, radioIDActive, k;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Age = (EditText) findViewById(R.id.Age);
        Height = (EditText) findViewById(R.id.Height);
        Weight = (EditText) findViewById(R.id.Weight);

        radGroupGender = (RadioGroup) findViewById(R.id.radioGrpGender);
        radGroupActive = (RadioGroup) findViewById(R.id.radioGrpActive);

        findViewById(R.id.BtnLose).setOnClickListener(this);

        findViewById(R.id.BtnKeep).setOnClickListener(this);

        findViewById(R.id.BtnGain).setOnClickListener(this);

    }

    private void calculateCPM(int i){
        int age = Integer.parseInt(Age.getText().toString().trim());
        int height = Integer.parseInt(Height.getText().toString().trim());
        int weight = Integer.parseInt(Weight.getText().toString().trim());

        radioIDGender = radGroupGender.getCheckedRadioButtonId();
        View radioButtonGender = radGroupGender.findViewById(radioIDGender);
        int idGender = radGroupGender.indexOfChild(radioButtonGender);
        RadioButton btnGender = (RadioButton) radGroupGender.getChildAt(idGender);
        String selectionGender = (String) btnGender.getText();

        radioIDActive = radGroupActive.getCheckedRadioButtonId();
        View radioButtonActive = radGroupActive.findViewById(radioIDActive);
        int idActive = radGroupActive.indexOfChild(radioButtonActive);
        RadioButton btnActive = (RadioButton) radGroupActive.getChildAt(idActive);
        String selectionActive = (String) btnActive.getText();

        double activeFactor = 1, PPM = 1, CPM;

        switch (selectionGender){
            case "Kobieta":
                PPM = 665.09 + (9.56 * weight) + (1.85 * height) - (4.67 * age);
                break;

            case "Mężczyzna":
                PPM = 66.47 + (13.75 * weight) + (5 * height) - (6.75 * age);
                break;
        }

        switch (selectionActive){
            case "Znikomy":         activeFactor = 1.2; break;
            case "Niski":           activeFactor = 1.3; break;
            case "Średni":          activeFactor = 1.5; break;
            case "Wysoki":          activeFactor = 1.75; break;
            case "Bardzo wysoki":   activeFactor = 2.0; break;
        }

        CPM = ((PPM + (0.1 * PPM)) * activeFactor) + i;
        long CPMrounded = Math.round(CPM);

        Toast.makeText(getApplicationContext(),"Twoje zapotrzebowanie kaloryczne wynosi " + CPMrounded + "kcal", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnLose:
                calculateCPM(-600);
                break;
            case R.id.BtnKeep:
                calculateCPM(0);
                break;
            case R.id.BtnGain:
                calculateCPM(600);
                break;
        }
        int age = Integer.parseInt(Age.getText().toString().trim());



        startActivity(new Intent(CalculateActivity.this, ProfileActivity.class));
    }


}
