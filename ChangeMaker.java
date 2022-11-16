package Project1;

import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 *ChangeMaker Class. Used to simulate an ATM that can be withdrawn from in the
 * form of a changeBag containing coins of each denomination. Includes booleans
 * to enable and disable each denomination of coin when withdrawing.
 */
public class ChangeMaker {
    //////////////////////
    //INSTANCE VARIABLES//
    //////////////////////

    // Amount should be between 0 and 1.0E15 and only have 0, 1, or 2 decimal places
    private double amount;

    ////////////////////
    //STATIC VARIABLES//
    ///////////////////

    //Boolean triggers for whether to use or not use each denomination of change
    private static boolean quartersAvail = true;
    private static boolean dimesAvail = true;
    private static boolean nickelsAvail = true;
    private static boolean penniesAvail = true;

    ////////////////
    //CONSTRUCTORS//
    ////////////////

    /**
     * Default Constructor. Sets ChangeMaker's internal value to 0.
     */
    public ChangeMaker(){
        this.amount = 0;
    }

    /**
     * Copy Constructor. Passes another ChangeMaker object in
     * and copies the value into a new ChangeMaker object.
     * @param Other ChangeMaker Object with a valid internal value.
     */
    public ChangeMaker(ChangeMaker Other){
        this.amount = Other.amount;
    }

    /**
     * Constructor that sets the internal value of the
     * ChangeMaker object being created
     * @param amount Desired value of the new ChangeMaker
     *               object being constructed.
     * @throws IllegalArgumentException Amount must be between 0 and 1.0E15 and
     *                                  has 0, 1, or 2 decimal places
     */
    public ChangeMaker(double amount){
        validParam(amount);
        this.amount = amount;
    }


    ///////////
    //METHODS//
    ///////////


    /**
     * Getter Method for ChangeMaker's value.
     * @return The amount currently stored in the ChangeMaker object. Double.
     */
    public double getAmount(){return this.amount;}

    /**
     * Setter Method for the ChangeMaker's value. Must be between 0 and 1.0E15, and have 0, 1, or 2 decimal places.
     * @param amount The value to be set as the ChangeMaker's internal value. Double.
     */
    public void setAmount(double amount){
        validParam(amount);
        this.amount = amount;
    }

    /**
     * Getter for the Use Quarters boolean.
     * @return True or False based on whether quarters are used or not when returning change.
     */
    public static boolean getQuartersAvail(){return quartersAvail;}
    /**
     * Setter for the Use Quarters boolean.
     * @param value True or False based on whether quarters will be used or not.
     */
    public static void setQuartersAvail(boolean value){quartersAvail = value;}

    /**
     * Getter for the Use Dimes boolean.
     * @return True or False based on whether dimes are used or not when returning change.
     */
    public static boolean getDimesAvail(){return dimesAvail;}
    /**
     * Setter for the Use Dimes boolean.
     * @param value True or False based on whether Dimes will be used or not.
     */
    public static void setDimesAvail(boolean value){dimesAvail = value;}

    /**
     * Getter for the Use Nickels boolean.
     * @return True or False based on whether nickels are used or not when returning change.
     */
    public static boolean getNickelsAvail(){return nickelsAvail;}
    /**
     * Setter for the Use Nickels boolean.
     * @param value True or False based on whether Nickels will be used or not.
     */
    public static void setNickelsAvail(boolean value){nickelsAvail = value;}

    /**
     * Getter for the Use Pennies boolean.
     * @return True or False based on whether pennies are used or not when returning change.
     */
    public static boolean getPenniesAvail(){return penniesAvail;}
    /**
     * Setter for the Use Pennies boolean.
     * @param value True or False based on whether Pennies will be used or not.
     */
    public static void setPenniesAvail(boolean value){penniesAvail = value;}

    /**
     * Method to check the value of an object compared to the current ChangeMaker
     * @param other An object with a value to be compared to the ChangeMaker Object
     * @return True if the parameter object is a ChangeMaker and has the same value as the current ChangeMaker object.
     *         Returns false if not a ChangeMaker object or the values aren't the same.
     */
    public boolean equals(Object other){
        //check type
        if (other instanceof ChangeMaker){
            return equals(this, (ChangeMaker) other);
        }
        return false;
    }

    /**
     * Compares the value of another ChangeMaker Object with the current one
     * @param other A different ChangeMaker Object
     * @return  True if the values of the ChangeMaker object being passed is the same as the current ChangeMaker Object
     */
    public boolean equals(ChangeMaker other){
        return equals(this, other);
    }

    /**
     * Compares the value of two ChangeMaker Objects and returns a boolean
     * @param other1 A ChangeMaker object
     * @param other2 A ChangeMaker object
     * @return Returns true if the two changemaker objects have the same amount value.
     */
    public static boolean equals(ChangeMaker other1, ChangeMaker other2){
        return other1.amount == other2.amount;
    }

    /**
     * Compares the current ChangeMaker object and another changemaker object values to see which is
     * greater or if they are equal.
     * @param other A changemaker object
     * @return 1 if the other object is less than this object, 0 if they are the same value, or -1 if the other
     * object is greater than this object
     */
    public int compareTo(ChangeMaker other){
        return compareTo(this, other);
    }

    /**
     * Compares two changemaker object values to see which is greater or if they are equal.
     * @param other1 A changemaker object
     * @param other2 A changemaker object
     * @return 1 if the first object amount is greater than the second, 0 if they are equal, and -1 if the first is
     * less than the second
     */
    public static int compareTo(ChangeMaker other1, ChangeMaker other2){
        return Double.compare(other1.amount, other2.amount); //slick
    }

    /**
     * Adds parameter amount to the changemaker amount total. Amount added must be a valid entry, and total value cannot
     * exceed 1.0E15.
     * @param amount value being added to the changemaker
     */
    public void loadMachine(double amount){
        validParam(amount); //validate entry
        double newAmount = this.amount + amount;
        validParam(newAmount); //validate new total
        this.amount = newAmount;
    }

    /**
     * Converts the amount entered as parameter into the equivalent amount of change, prioritizing
     * quarters>dimes>nickels>pennies, unless a give coin denomination is disabled via GUI.
     * @param otherAmount the amount being taken out of the changemaker
     * @return a changebag object holding the calculated amount of coins representing the amount entered as a parameter
     */
    public ChangeBag takeOut (double otherAmount){
        validTakeOut(otherAmount); //validate with a potential zero entry???????
        this.amount -= otherAmount;
        long change;
        long quarters = 0;
        long dimes = 0;
        long nickels = 0;
        long pennies = 0;
        change = (long) (otherAmount * 100);
        //Down the line of options for change, starting with the largest denomination
        if (quartersAvail){
            quarters = change / 25;
            change -= quarters * 25;
        }
        if (dimesAvail){
            dimes = change / 10;
            change -= dimes * 10;
        }
        if (nickelsAvail){
            nickels = change / 5;
            change -= nickels * 5;
        }
        if (penniesAvail){
            pennies = change;
            change = 0;
        }
        if (change != 0){
            //create exception
            throw new IllegalArgumentException("Invalid value entered. Can't provide adequate change.");
        }

        return new ChangeBag(quarters, dimes, nickels, pennies);
    }

    /**
     * Method to provide a string output representing the value of the changemaker
     * @return a string containing the value of the changemaker
     *
     */
    public String toString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(); //$$$
        String moneyString = formatter.format(getAmount());

        return String.format("ChangeMaker{amount=%s}", moneyString);

    }

    /**
     * Saves the current amount stored in the changemaker to the harddrive.
     * @param fileName the filename being written to
     */

    //look up serialization you dummy
    public void save(String fileName){
        PrintWriter out;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        }
        catch (IOException e) { //quit trying to use the random chars that windows hates dummy
            throw new IllegalArgumentException("Invalid File");
        }
        out.println(this.amount);
        out.close();
    }

    /**
     * Loads an amount from a file on the harddrive into the changemaker.
     * @param fileName the filename being read from
     */
    public void load(String fileName){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            this.amount = scanner.nextDouble() ;
        }
        catch (Exception e) { //has to be a real file dummy
            throw new IllegalArgumentException("Invalid File");
        }
    }

    /*
     * Method to validate ChangeMaker's internal values when being set.
     * Checks that it is between 0 and 1.0E15, and only has 0, 1, or 2
     * decimal places.
     * @param amount A double that represents whatever value a ChangeMaker
     * object is being set to.
     */
    private void validParam(double amount){
        double x = ((int)(amount * 100))/100.0; //convert to a double with 2 decimal places
        if ((amount <= 0.0) || (amount > 1.0E15) || (amount != x)){
            throw new IllegalArgumentException("Invalid Amount entered");
        }
    }

    /*
     * Method to validate a parameter or the takeOut method, as it can take 0 as a valid entry.
     * @param amount double that needs to be checked as a valid parameter
     */
    private void validTakeOut(double amount){
        double x = ((int)(amount * 100))/100.0; //convert to a double with 2 decimal places
        if (amount > this.amount || amount < 0.0 || (amount != x)){ //can't take out more than ya got!
            throw new IllegalArgumentException("Invalid Amount being taken out");
        }
    }
}
