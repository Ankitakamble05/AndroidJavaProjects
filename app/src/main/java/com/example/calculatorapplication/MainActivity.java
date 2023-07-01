package com.example.calculatorapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;

    private String input, output, newOutput;


    private Button button0, button1, button2, button3, button4, button5, button6, button7,
            button8, button9, buttonAdd, buttonMultiply, buttonSubs, buttonDivision, buttonPoint, buttonPercent,
            buttonPower, buttonEqual, buttonClear, buttonMemoryStore, buttonMemoryRecall,
            buttonOpenParenthesis, buttonCloseParenthesis;

    private Stack<String> memoryStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);

        button0 = findViewById(R.id.btn_0);
        button1 = findViewById(R.id.btn_1);
        button2 = findViewById(R.id.btn_2);
        button3 = findViewById(R.id.btn_3);
        button4 = findViewById(R.id.btn_4);
        button5 = findViewById(R.id.btn_5);
        button6 = findViewById(R.id.btn_6);
        button7 = findViewById(R.id.btn_7);
        button8 = findViewById(R.id.btn_8);
        button9 = findViewById(R.id.btn_9);
        buttonAdd = findViewById(R.id.btn_plus);
        buttonMultiply = findViewById(R.id.btn_multiply);
        buttonDivision = findViewById(R.id.btn_divide);
        buttonSubs = findViewById(R.id.btn_minus);
        buttonPoint = findViewById(R.id.btn_decimal);
        buttonEqual = findViewById(R.id.btn_equals);
        buttonPercent = findViewById(R.id.btn_percent);
        buttonClear = findViewById(R.id.btn_clear);
        buttonMemoryStore = findViewById(R.id.btn_memory_store);
        buttonMemoryRecall = findViewById(R.id.btn_memory_recall);
        buttonOpenParenthesis = findViewById(R.id.btn_open_parenthesis);
        buttonCloseParenthesis = findViewById(R.id.btn_close_parenthesis);

        memoryStack = new Stack<>();
    }

    public void onButtonClicked(View view) {

        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "C":
                input = null;
                output=null;
                newOutput=null;
                outputText.setText("");
                break;

            case "^":
                solve();
                input += "^";
                break;
            case "*":
                solve();
                input += "*";
                break;

            case "=":
                solve();
                break;

            case "%":
                input += "%";
                double d = Double.parseDouble(inputText.getText().toString()) / 100;
                outputText.setText(String.valueOf(d));
                break;
            case "MS":
                storeInMemory();
                break;
            case "MR":
                recallMemory();
                break;
            case "(":
                input += "(";
                break;
            case ")":
                input += ")";
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("/") || data.equals("-")) {
                    solve();
                }
                input += data;
        }
        inputText.setText(input);
    }

    private void solve() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e) {
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText("-" + newOutput);
                    input = d +"";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = d + "";
                }
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
    }
    private String cutDecimal(String number){
        String n [] = number.split("\\.");
        if (n.length >1){
            if (n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }

    private void storeInMemory() {
        String input = inputText.getText().toString();
        memoryStack.push(input);
    }

    private void recallMemory() {
        if (!memoryStack.isEmpty()) {
            input = memoryStack.pop();
            inputText.setText(input);
        }
    }

}
