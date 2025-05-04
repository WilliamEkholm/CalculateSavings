import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double totalFutureValue = 0.0;
        double carriedOver = 0.0;
        double totalInvested = 0.0;

        while(true) {
            System.out.println("Welcome to future value calculator");
            System.out.println("Please add the sum you are going to invest monthly (0 to exit program):");
            int monthlyInvestment = input.nextInt();

            if(monthlyInvestment == 0){
                System.out.println("Program shuts down");
                break;
            }

            System.out.println("Please add the expected annual interest rate:");
            double interestRate = input.nextDouble();
            System.out.println("Please add the period of time in months (10 years = 120, 20 years = 240, 30 years = 360, 40 years = 480):");
            int months = input.nextInt();
            input.nextLine();

            double futureValue = calcFutureValue(monthlyInvestment, interestRate, months);
            if (carriedOver > 0) {
                carriedOver = compoundLumpSum(carriedOver, interestRate, months);
            }

            // Step 3: Add this future value to any previously carried over future value
            totalFutureValue = futureValue + carriedOver;
            carriedOver = totalFutureValue; // carry forward for next round

            // Step 4: Output
            double ownInvested = monthlyInvestment * months;
            totalInvested += ownInvested;
            System.out.printf("Future value this period: %.2f kr\n", futureValue);
            System.out.printf("Amount earned this round: %.2f kr\n\n", futureValue - ownInvested);
            System.out.printf("Total accumulated value including previous investments: %.2f kr\n", totalFutureValue);
            System.out.printf("Amount earned in total: %.2f kr\n\n", totalFutureValue - totalInvested);


        }

        }

    public static double calcFutureValue(int monthlyInvestment, double annualInterestRate, int months){
        double monthlyInterestRate = Math.pow(1 + annualInterestRate/100, 1.0 / 12) - 1;
        return monthlyInvestment * (Math.pow(1 + monthlyInterestRate, months) - 1)/monthlyInterestRate;
    }

    public static double compoundLumpSum(double amount, double annualInterestRate, int months) {
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        return amount * Math.pow(1 + monthlyInterestRate, months);
    }

}
