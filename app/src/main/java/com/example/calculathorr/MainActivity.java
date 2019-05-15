package com.example.calculathorr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView inpTextView;
    private TextView oprTextView;
    private TextView resTextView;
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button badd;
    private Button bsub;
    private Button bmlt;
    private Button bdiv;
    private Button bpoint;
    private Button bequals;
    private Button bclear;
    private Button bswitcher;
    private Button bdel;
    private String input;
    private String output;
    private String operator;
    private Boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resTextView=findViewById(R.id.res);
        inpTextView=findViewById(R.id.inp);
        oprTextView=findViewById(R.id.opr);

        b0=findViewById(R.id.b0);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);
        bpoint=findViewById(R.id.bpoint);
        bclear=findViewById(R.id.bclear);
        badd=findViewById(R.id.badd);
        bsub=findViewById(R.id.bsub);
        bmlt=findViewById(R.id.bmlt);
        bdiv=findViewById(R.id.bdiv);
        bswitcher=findViewById(R.id.bswitcher);
        bdel=findViewById(R.id.bdel);
        bequals=findViewById(R.id.bequals);
        bclear=findViewById(R.id.bclear);

        input="0";
        output="";
        operator="";
        check=false;
        updateInput();

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("0");
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandClick("9");
            }
        });

        bpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check) clearAll();
                if(!input.contains(".")) input+=".";
                else if(input.endsWith(".")) input=input.substring(0, input.length()-1);
                updateInput();
            }
        });


        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input="0";
                updateInput();
            }
        });

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClick("+");
            }
        });

        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClick("-");
            }
        });

        bmlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClick("*");
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClick("/");
            }
        });

        bswitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!input.equals("0")){
                    if(input.charAt(0)=='-'){
                        input=input.substring(1);
                    } else {
                        input="-"+input;
                    }
                }
                updateInput();
            }
        });

        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.length()<=1){
                    input="0";
                } else input=input.substring(0, (input.length()-1));
                updateInput();
            }
        });

        bequals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.contains(".")){
                    while(input.endsWith("0")) input=input.substring(0, input.length()-1);
                }
                if(input.endsWith(".")) input=input.substring(0, input.length()-1);
                if(!output.equals("")&&!operator.equals("")&&!output.equals("undefined")) {
                    if(operator.equals("+")) output=String.valueOf(Double.parseDouble(output)+Double.parseDouble(input));
                    else if(operator.equals("-")) output=String.valueOf(Double.parseDouble(output)-Double.parseDouble(input));
                    else if(operator.equals("*")){
                        double out = Double.parseDouble(output);
                        double in = Double.parseDouble(input);
                        long multi = 1L;
                        while(out%1>0){
                            out*=10;
                            multi*=10;
                        }
                        while(in%1>0){
                            in*=10;
                            multi*=10;
                        }
                        //result should be ok unless an unusual decimal is used in the equation
                        double res=out*in;
                        output=String.valueOf(res/multi);
                    }
                    else if(operator.equals("/")){
                        if(input.equals("0")) output="undefined";
                        else output=String.valueOf(Double.parseDouble(output)/Double.parseDouble(input));
                    }
                    if(output.endsWith(".0")) output=output.substring(0, output.length()-2);
                    else if(output.endsWith(".")) output=output.substring(0, output.length()-1);
                    operator="";
                } else if(output.equals("")||check==true) output=input;
                input="0";
                updateOpr();
                updateOutput();
                updateInput();
                check=true;
            }
        });

        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });
    }
    private void operandClick(String operand){
        int limit=12;
        if(input.startsWith("-")) limit++;
        if(!output.equals("undefined") && input.length()<limit){
            if(check) clearAll();
            if(!input.equals("0")){
                input+=operand;
            } else {
                input=operand;
            }
        }
        updateInput();
    }
    private void operatorClick(String operator){
        if(output.equals("")){
            if(input.contains(".")){
                while(input.endsWith("0")) input=input.substring(0, input.length()-1);
            }
            if(input.endsWith(".")) input=input.substring(0, input.length()-1);
            output=input;
            input="0";
        }
        this.operator=operator;
        updateInput();
        updateOutput();
        updateOpr();
        check=false;
    }
    private void clearAll(){
        output="";
        operator="";
        input="0";
        updateInput();
        updateOutput();
        updateOpr();
        check=false;
    }
    private void updateInput(){inpTextView.setText(input);}
    private void updateOutput(){resTextView.setText(output);}
    private void updateOpr(){oprTextView.setText(operator);}
}