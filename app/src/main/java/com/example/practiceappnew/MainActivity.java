package com.example.practiceappnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public double sumGrade = 0, sumCredit = 0, result = 0;
    String[] gradesList = new String[]{"Select","A+","A","B+","B","B-","C+","C","C-","D+","D","F"};
    String[] creditsList = new String[]{"Select","1","2","3","4"};
    int[] gradeIds, creditIds, pointIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gradeIds = new int[]{R.id.gradeList1,R.id.gradeList2,R.id.gradeList3,R.id.gradeList4,R.id.gradeList5,R.id.gradeList6,R.id.gradeList7,R.id.gradeList8,};
        creditIds = new int[]{R.id.creditList1,R.id.creditList2,R.id.creditList3,R.id.creditList4,R.id.creditList5,R.id.creditList6,R.id.creditList7,R.id.creditList8,};
        pointIds = new int[]{R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,R.id.point6,R.id.point7,R.id.point8};
        Button calculate = findViewById(R.id.calculate);

        for (int id : gradeIds) {
            Spinner gradeSpinner = findViewById(id);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gradesList);
            gradeSpinner.setAdapter(adapter);
            gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String pointName = adapterView.getSelectedItem().toString();
                    TextView pointField = findViewById(getPointIdByGradeId(id));
                    pointField.setText(String.valueOf(getGpaNoFromGradeName(pointName)));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        for (int id : creditIds) {
            Spinner creditSpinner = findViewById(id);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, creditsList);
            creditSpinner.setAdapter(adapter);
        }

        calculate.setOnClickListener(view -> CalculateGPA());
    }

    private void CalculateGPA() {
        sumGrade = 0; sumCredit =0;
        for(int i=0;i<8;i++){
            Spinner gradeSpinner = findViewById(gradeIds[i]);
            Spinner creditSpinner = findViewById(creditIds[i]);
            if((gradeSpinner.getSelectedItem().toString())!="Select" && (creditSpinner.getSelectedItem().toString())!="Select"){
                double gpa = getGpaNoFromGradeName(gradeSpinner.getSelectedItem().toString());
                double credit = Double.parseDouble(creditSpinner.getSelectedItem().toString());
                sumGrade += gpa * credit;
                sumCredit += Double.parseDouble(creditSpinner.getSelectedItem().toString());
            }
            else{
                sumGrade += 0;
                sumCredit +=0;
            }
        }
        result = sumGrade/sumCredit;

        if(!String.valueOf(result).equals("NaN"))
            Toast.makeText(this, String.format("Your GPA is : %.2f", result), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Invalid Way", Toast.LENGTH_LONG).show();

    }

    private double getGpaNoFromGradeName(String gradeName) {
        switch (gradeName) {
            case "A+":
                return 4.00;
            case "A":
                return 3.66;
            case "B+":
                return 3.33;
            case "B":
                return 3.0;
            case "B-":
                return 2.66;
            case "C+":
                return 2.33;
            case "C":
                return 2.0;
            case "C-":
                return 1.66;
            case "D+":
                return 1.33;
            case "D":
                return 1.0;
            default:
                return 0;
        }
    }

    private int getPointIdByGradeId(int id){
        if(id==R.id.gradeList1)
            return R.id.point1;
        else if(id==R.id.gradeList2)
            return R.id.point2;
        else if(id==R.id.gradeList3)
            return R.id.point3;
        else if(id==R.id.gradeList4)
            return R.id.point4;
        else if(id==R.id.gradeList5)
            return R.id.point5;
        else if(id==R.id.gradeList6)
            return R.id.point6;
        else if(id==R.id.gradeList7)
            return R.id.point7;
        else
            return R.id.point8;
    }

}

