package com.example.a20521566semina01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addBtn, deleteBtn;
    TextView getError;
    TextInputEditText name, phoneNumber;
    ListView dcm;
    ArrayList<String> listInfo = new ArrayList<>();
    static int index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteBtn = findViewById(R.id.deleteBtn);
        addBtn = findViewById(R.id.addBtn);
        dcm = findViewById(R.id.phoneBook);
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);

        deleteBtn.setVisibility(View.INVISIBLE);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo();
            }
        });
        dcm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeColorItem(adapterView,view,i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove();
            }
        });
    }


    void addInfo(){
        if(name.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty())
        {
            getError = findViewById(R.id.getError);
            getError.setText("Please complete all information");
        }else
        {
            listInfo.add("Name: " + name.getText().toString() + "\nPhone Number: "+ phoneNumber.getText().toString());
            name.setText("");
            phoneNumber.setText("");
        }

        ArrayAdapter<String> AdtListInfo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listInfo);
        dcm.setAdapter(AdtListInfo);
    }
    void changeColorItem(AdapterView<?> adapterView, View view, int i)
    {
        if(deleteBtn.getVisibility() == view.INVISIBLE) {
            deleteBtn.setVisibility(View.VISIBLE);
        }

        if(index == -1) {
            index = i;
            adapterView.getChildAt(i).setBackgroundColor(Color.BLUE);
        }
        else if(index != i){
            adapterView.getChildAt(index).setBackgroundColor(Color.WHITE);
            view.setBackgroundColor(Color.BLUE);
            index = i;
        }
        Toast.makeText(getApplicationContext(),listInfo.get(i),Toast.LENGTH_SHORT).show();
    }

    void remove()
    {
        if(index!=-1) {
            listInfo.remove(index);
            ArrayAdapter<String> AdtListInfo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listInfo);
            dcm.setAdapter(AdtListInfo);
            index = -1;
            if(listInfo.isEmpty())
            {
                deleteBtn.setVisibility(View.INVISIBLE);
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please select the information to delete", Toast.LENGTH_LONG).show();
        }
    }
}