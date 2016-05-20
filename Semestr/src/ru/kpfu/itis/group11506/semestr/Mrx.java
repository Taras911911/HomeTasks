package ru.kpfu.itis.group11506.semestr;


public class Mrx {
    public static void main(String[] args) {


        String num = "7656757572";
        String num2 = "-212424";

        LongArithmetic longArithmetic = new LongArithmetic();
        try {

            System.out.println("+: " + longArithmetic.addition(num, num2));
            System.out.println("-: " + longArithmetic.subtraction(num, num2));
            System.out.println("*: " + longArithmetic.multiplication(num, num2));
            System.out.println("^: " + longArithmetic.degree(3, num2));
            System.out.println("<<: " + longArithmetic.leftLogicalShift(5, num2));
            System.out.print(">>: ");
            longArithmetic.rightLogicalShift(5, num2);
            System.out.println();
            System.out.print("/: ");
            longArithmetic.showArray(longArithmetic.division(num, num2));
            System.out.println();

        } catch (NotANumberExeption notANumberExeption) {
            notANumberExeption.printStackTrace();
        }


    }
}
