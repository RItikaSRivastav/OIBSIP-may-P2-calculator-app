package com.example.calculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class Calculatoractivity extends AppCompatActivity {

     private EditText inputEditText,outputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculatoractivity);

        initializeView();

    }

    private void initializeView() {
        inputEditText=findViewById(R.id.inputTextField);
        outputEditText=findViewById(R.id.outputTextField);
    }

    @SuppressLint("SuspiciousIndentation")
    public void buttonClicked(View view){

        Button button= (Button) view;
       String textButton= button.getText().toString();
       String oldInputText = inputEditText.getText().toString();


       if(textButton.equalsIgnoreCase("C")) {

           int length= oldInputText.length();
           if (length > 1) {
               String resultString = oldInputText.substring(0, length - 1);
               inputEditText.setText(resultString);
           }else {
               inputEditText.setText("");

           }
       }
       else if (textButton.equalsIgnoreCase("D"))
       {
           inputEditText.setText("");
           outputEditText.setText("");


       }
       else if (textButton.equalsIgnoreCase("*")) {
           inputEditText.setText(oldInputText + "*");

       } else if (textButton.equalsIgnoreCase("=")) {

           if (oldInputText.trim().equalsIgnoreCase("=")) {
               inputEditText.setText(outputEditText.getText().toString());
               outputEditText.setText("");


       } //calculate the expression and set to answer

               try {
                   Expression expression = new Expression(oldInputText);
                   EvaluationValue result = expression.evaluate();
                   outputEditText.setText(result.getStringValue());
                   inputEditText.setText("");
               } catch (EvaluationException e) {
                   Toast.makeText(this, "Cannot Evaluale Expression", Toast.LENGTH_LONG).show();
               } catch (ParseException e) {
                   Toast.makeText(this, "Invalid Expression", Toast.LENGTH_LONG).show();

               }

           }else{

           inputEditText.setText(oldInputText + textButton);
       }
    }
}