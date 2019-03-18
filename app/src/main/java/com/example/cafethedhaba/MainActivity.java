package com.example.cafethedhaba;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int Num;
    String summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton minus = (FloatingActionButton) findViewById(R.id.decrease);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Num>0)
                {
                    Num = Num - 1;
                updateValue(Num);
                }
            }
        });

        FloatingActionButton plus = (FloatingActionButton) findViewById(R.id.increase);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Num = Num + 1;
                updateValue(Num);
            }
        });

        Button order = (Button) findViewById(R.id.sendEmail);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"cafethedhaba@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Your Order Has been Placed !!!");
                i.putExtra(Intent.EXTRA_TEXT, summary);
                startActivity(i);
            }
        });
    }

    public void updateValue(int a){
        int amt =0;
        TextView numberOfCups = (TextView) findViewById(R.id.integer_number);
        numberOfCups.setText(""+a);

        EditText name = (EditText) findViewById(R.id.getNameTextView);
        String n = name.getText().toString();
        summary = "Hi "+n+",\n\n";

        summary +=a + " number of Cups with ";
        CheckBox capp = (CheckBox) findViewById(R.id.checkBox);
        CheckBox cold = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox hot = (CheckBox) findViewById(R.id.checkBox3);

        if (capp.isChecked()){
            amt = amt + (200 * a);
            summary += "cappuccinos,";
        }
        if (cold.isChecked()){
            amt = amt + (250 * a);
            summary += "cold coffee,";
        }
        if (hot.isChecked()){
            amt = amt + (100 * a);
            summary += "hot coffee";
        }

        TextView amountText = (TextView) findViewById(R.id.amtTextView);
        amountText.setText("Rs. "+ amt);

        summary += "of Total Amount $" + amt;

    }

}