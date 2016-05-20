package passwordgenerator.apps.alexbykov.ru.passwordgen;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button buttonDecQuantity;
    Button buttonIncQuantity;
    Button buttonGenerate;
    Button buttonCopyToClipboard;

    CheckBox lowerCaseCheckBox;
    CheckBox upperCaseCheckBox;
    CheckBox digitsCheckBox;
    CheckBox specialCharactersCheckBox;

    TextView resultPasswordTextView;
    TextView quantityOfCharsTextView;
    private static final String TAG = "myLogs";



    boolean isLowerCase;
    boolean isUpperCase;
    boolean isDigits;
    boolean isSpecChar;
    int quantity = 6;
    String tmpPassword="";


    private static final String KEY_QUANTITY = "QUANTITY";
    private static final String KEY_PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonDecQuantity = (Button) findViewById(R.id.buttonDecQuantity);
        buttonIncQuantity = (Button) findViewById(R.id.buttonIncQuantity);
        buttonGenerate = (Button) findViewById(R.id.buttonGenerate);
        buttonCopyToClipboard = (Button) findViewById(R.id.buttonCopyToClipboard);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.buttonDecQuantity:
                        decQuantity();
                        break;
                    case R.id.buttonIncQuantity:
                        incQuantity();
                        break;
                    case R.id.buttonGenerate:
                        createPassword();
                        break;
                    case R.id.buttonCopyToClipboard:
                        copyToClipboard();
                }

            }
        };

        buttonIncQuantity.setOnClickListener(onClickListener);
        buttonDecQuantity.setOnClickListener(onClickListener);
        buttonGenerate.setOnClickListener(onClickListener);
        buttonCopyToClipboard.setOnClickListener(onClickListener);


        // Check save data for screen change
        if (savedInstanceState != null) {
            quantity = savedInstanceState.getInt(KEY_QUANTITY, 6);
            printQuantity();
            tmpPassword =  savedInstanceState.getString(KEY_PASSWORD);
            printPassword(tmpPassword);
        }


    }


    @Override //Save data changeScreen
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_QUANTITY, quantity);
        outState.putString(KEY_PASSWORD, tmpPassword);
    }


    void decQuantity() {

        Log.d(TAG, "decrement method");
        if (quantity <= 6) quantity = 6;
        else --quantity;
        printQuantity();

    }

    void incQuantity() {

        Log.d(TAG, "increment method");
        if (quantity >= 24) quantity = 24;
        else ++quantity;
        printQuantity();
    }

    void createPassword() {

        Log.d(TAG, "createPassword method");
        chekCheckBox();

        Password password = new Password(quantity, isLowerCase, isUpperCase, isDigits, isSpecChar);

        try {
            password.acceptCheckBox();
            password.generatePassword();
        }

        catch (IllegalArgumentException e)
        {
            showToast("Please choose a password conditions");
        }




        tmpPassword = password.toString();
        printPassword(tmpPassword);

    }


    void chekCheckBox() {


        lowerCaseCheckBox = (CheckBox) findViewById(R.id.lowerCaseCheckBox);
        if (lowerCaseCheckBox.isChecked()) isLowerCase = true;
        else isLowerCase = false;

        upperCaseCheckBox = (CheckBox) findViewById(R.id.upperCaseCheckBox);
        if (upperCaseCheckBox.isChecked()) isUpperCase = true;
        else isUpperCase = false;

        digitsCheckBox = (CheckBox) findViewById(R.id.digitsCheckBox);
        if (digitsCheckBox.isChecked()) isDigits = true;
        else isDigits = false;

        specialCharactersCheckBox = (CheckBox) findViewById(R.id.specialCharactersCheckBox);
        if (specialCharactersCheckBox.isChecked()) isSpecChar = true;
        else isSpecChar = false;


    }


    void printQuantity() {
        quantityOfCharsTextView = (TextView) findViewById(R.id.quantityOfCharsTextView);

        quantityOfCharsTextView.setText(String.valueOf(quantity));
    }

    void printPassword(String tmpPassword) {

        resultPasswordTextView = (TextView) findViewById(R.id.resultPasswordTextView);
        resultPasswordTextView.setText(tmpPassword);


    }


    void copyToClipboard() {

        android.text.ClipboardManager clipboardMgr = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        try {
            String result = resultPasswordTextView.getText().toString();
            if (result.equals("")) throw new NullPointerException();
            clipboardMgr.setText(result);
            showToast("Password copied");
        } catch (NullPointerException e) {
            showToast("First generate your password");
        }
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }


}


