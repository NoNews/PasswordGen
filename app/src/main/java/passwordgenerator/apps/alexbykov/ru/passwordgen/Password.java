package passwordgenerator.apps.alexbykov.ru.passwordgen;



import java.util.Random;

/**
 * Created by Alexey on 05.05.2016.
 */
public class Password {



    String loverCase = "abcdefghijklmnopqrstuvwxyz";
    String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String digits = "1234567890";
    String specChar = "[]{},.\";:-+#";
    private String password="";


    int quantityOfSymbols;
    boolean isLowerCase;
    boolean isUpperCase;
    boolean isDigits;
    boolean isSpecChar;



    public Password(int quantityOfSymbols, boolean isLowerCase, boolean isUpperCase, boolean isDigits, boolean isSpecChar) {
        this.quantityOfSymbols = quantityOfSymbols;
        this.isLowerCase = isLowerCase;
        this.isUpperCase = isUpperCase;
        this.isDigits = isDigits;
        this.isSpecChar = isSpecChar;

    }



    void acceptCheckBox() {


        if (isLowerCase)
            password += loverCase;
        if (isUpperCase)
            password += upperCase;
        if (isDigits)
            password += digits;
        if (isSpecChar)
            password += specChar;
    }


    void generatePassword()  {


            char[] passwordCharArray = password.toCharArray();
            Random random = new Random();
            password="";

            for (int i = 0; i < quantityOfSymbols; i++) {
                password += passwordCharArray[random.nextInt(passwordCharArray.length)];

            }




    }


    @Override
    public String toString() {
        return password;
    }






}
