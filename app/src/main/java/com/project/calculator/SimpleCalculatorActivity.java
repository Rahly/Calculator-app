package com.project.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class SimpleCalculatorActivity extends Activity {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonBscp, buttonC,
            buttonChangeSign, buttonDiv, buttonMultiply, buttonMinus,
            buttonPlus, buttonResult, buttonDot;

    TextView editText;
    TextView allText;

    int cButtonCounter = 0;
    boolean digitalClicked = false;
    boolean blockOperations = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        editText = (TextView) findViewById(R.id.resultText);
        allText = (TextView) findViewById(R.id.allText);

        buttonBscp = (Button) findViewById(R.id.signBksp);
        buttonC = (Button) findViewById(R.id.signC);
        buttonChangeSign = (Button) findViewById(R.id.signOperation);
        buttonDiv = (Button) findViewById(R.id.signDiv);
        buttonMultiply = (Button) findViewById(R.id.signMultiple);
        buttonMinus = (Button) findViewById(R.id.signMinus);
        buttonPlus = (Button) findViewById(R.id.signPlus);
        buttonResult = (Button) findViewById(R.id.signSum);
        buttonDot = (Button) findViewById(R.id.signDot);

        button0 = (Button) findViewById(R.id.sign0);
        button1 = (Button) findViewById(R.id.sign1);
        button2 = (Button) findViewById(R.id.sign2);
        button3 = (Button) findViewById(R.id.sign3);
        button4 = (Button) findViewById(R.id.sign4);
        button5 = (Button) findViewById(R.id.sign5);
        button6 = (Button) findViewById(R.id.sign6);
        button7 = (Button) findViewById(R.id.sign7);
        button8 = (Button) findViewById(R.id.sign8);
        button9 = (Button) findViewById(R.id.sign9);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("9");
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeOperation("+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeOperation("-");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeOperation("*");
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeOperation("/");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!blockOperations) {
                    cButtonCounter = 0;
                    if (editText.getText().toString().length() > 0) {
                        if (Character.isDigit(editText.getText().charAt(editText.getText().toString().length() - 1))) {
                            if (!editText.getText().toString().contains(".")) {
                                editText.setText(editText.getText() + ".");
                            }
                        }
                    }
                }
            }
        });


        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText.getText().toString().equals("Niepoprawne wyrażenie")) {
                    if (!blockOperations) {
                        addNumberToAllText();
                        cButtonCounter = 0;
                        if (allText.getText().toString().length() > 0) {
                            setResult();
                            allText.setText("");
                        }
                        blockOperations = false;
                        digitalClicked = true;
                    }
                }
            }
        });

        buttonBscp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText.getText().toString().equals("Niepoprawne wyrażenie")) {
                    if (!blockOperations) {
                        cButtonCounter = 0;
                        if (editText.getText().toString().length() > 0) {
                            editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
                        }
                    }
                }
            }
        });

        buttonChangeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText.getText().toString().equals("Niepoprawne wyrażenie")) {
                    if (!blockOperations) {
                        cButtonCounter = 0;
                        if (editText.getText().toString().length() > 0) {
                            editText.setText(String.valueOf(Double.parseDouble(editText.getText().toString()) * (-1.0)));
                            //setResult();
                        }
                    }
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cButtonCounter++;
                if (cButtonCounter > 1) {
                    editText.setText("");
                    allText.setText("");
                    cButtonCounter = 0;
                    blockOperations = false;
                } else {
                    editText.setText("");
                }

            }
        });

        if (savedInstanceState != null) {
            String aText = savedInstanceState.getString("allText");
            String eText = savedInstanceState.getString("editText");
            digitalClicked = savedInstanceState.getBoolean("digitalClicked");
            allText.setText(String.valueOf(aText));
            editText.setText(String.valueOf(eText));
        }

    }

    public void setResult() {

        String expression = allText.getText().toString();

        Expression e = new Expression(expression);

        if (!Double.isNaN(e.calculate())) {
            String result = String.valueOf(e.calculate());
            editText.setText(result);
            blockOperations = false;
        } else {
            editText.setText("Niepoprawne wyrażenie");
            digitalClicked = false;
            blockOperations = false;
        }

    }

    public void addNumberToAllText() {
        if (!editText.getText().toString().startsWith("-"))
            allText.setText(allText.getText() + editText.getText().toString());
        else
            allText.setText(allText.getText() + "(" + editText.getText().toString() + ")");
    }

    public void addNumberToEditText(String number) {

        if(!editText.getText().toString().equals("Niepoprawne wyrażenie")) {
            if (!blockOperations) {
                if (digitalClicked) {
                    if (number.equals("0") && editText.getText().toString().equals("0")) {

                    } else
                        editText.setText(editText.getText() + number);
                } else
                    editText.setText(number);
                digitalClicked = true;
            }
        }
    }

    public void executeOperation(String operation) {

        int position;

        if (editText.getText().toString().contains("-"))
            position = 2;
        else
            position = 1;

        if(!editText.getText().toString().equals("Niepoprawne wyrażenie")){
            if (digitalClicked) {
                if (!blockOperations) {
                    addNumberToAllText();
                    cButtonCounter = 0;
                    if (allText.getText().toString().length() > 0) {
                        if (Character.isDigit(allText.getText().charAt(allText.getText().toString().length() - position))) {
                            setResult();
                            allText.setText(allText.getText() + operation);
                            digitalClicked = false;
                        }
                    }
                }
            } else if (editText.getText().toString().length() > 0 && allText.getText().toString().length() > 0) {
                allText.setText(allText.getText().toString().substring(0, allText.getText().length() - 1) + operation);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("allText", allText.getText().toString());
        outState.putString("editText", editText.getText().toString());
        outState.putBoolean("digitalClicked", digitalClicked);
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }
}