/*
Name: Anjy Alassaf
Date: Mar 9,2026
Description: A two player projectile game where each player chooses a speed and angle to fire.
The program calculates where the projectile lands and announces the winner when it hits near the opponent.
*/

import java.util.Scanner;
import java.util.Random;

public class ProjectileGame {

    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();


    // Constants
    static final double GRAVITY = 9.81;
    static final int MAX_POSITION = 120;
    static final double HIT_THRESHOLD = 1.0;

    // Input validation constants
    static final int MIN_POWER = 1;
    static final int MAX_POWER = 1000;
    static final int MIN_ANGLE = 0;
    static final int MAX_ANGLE = 180;



    public static void main(String[] args) {

        System.out.println("Welcome to the Projectile Motion Game!");


        // Ask player names
        System.out.print("Enter name for Player 1: ");
        String player1 = input.nextLine();

        System.out.print("Enter name for Player 2: ");
        String player2 = input.nextLine();

        boolean playAgain = true;


        // Main game loop
        while (playAgain) {
            int winner = runGame(player1, player2);

            System.out.println("🎉 Congratulations, " + (winner == 1 ? player1 : player2) + " wins this round!");


            // Ask if they want to play again
            System.out.print("Do you want to play again? (yes/no): ");

            String response = input.nextLine().trim().toLowerCase();
            if (response.equals("no")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing! Goodbye!");


    }


    // Function to get projectile power from user
    public static double getPower() {
        double power = 0;
        boolean valid = false;


        while (!valid) {
            System.out.print("Enter shot power (" + MIN_POWER + "-" + MAX_POWER + "): ");
            if (input.hasNextDouble()) {
                power = input.nextDouble();
                if (power >= MIN_POWER && power <= MAX_POWER) {
                    valid = true;
                } else { System.out.println("Power must be between " + MIN_POWER + " and " + MAX_POWER + ".");
                }
            } else {
                System.out.println("Invalid input. Enter a number.");
                input.next();
            }
        }
        input.nextLine();
        return power;
    }


    // Function to get projectile angle from user
    public static double getAngle() {
        double angle= 0;
        boolean valid = false;

        while (!valid) {

            System.out.print("Enter shot angle (" + MIN_ANGLE + "-" + MAX_ANGLE + " degrees): ");
            if (input.hasNextDouble()) {
                angle = input.nextDouble();
                if (angle >= MIN_ANGLE  && angle <= MAX_ANGLE) {
                    valid = true;
                } else {
                    System.out.println("Angle must be between " + MIN_ANGLE + " and " + MAX_ANGLE + " degrees.");
                }
            } else {
                System.out.println("Invalid input. Enter a number.");
                input.next();

            }
        }
        input.nextLine(); // clear buffer
        return angle;
    }


    // Function to calculate where the shot lands
    public static double getShot(double startX) {
        double v0 = getPower();
        double angleDeg = getAngle();
        double angleRad = Math.toRadians(angleDeg);

        // Calculate flight time using vertical motion formula
        double flightTime = (2 * v0 * Math.sin(angleRad)) / GRAVITY;

        // Calculate horizontal landing position
        double landingX = startX + (v0 * Math.cos(angleRad)) * flightTime;

        System.out.printf("Shot lands at x = %.2f\n", landingX);
        return landingX;
    }



    // Function to run one game between two players
    public static int runGame(String player1, String player2) {
        double player1Pos = rand.nextInt(MAX_POSITION + 1);
        double player2Pos = rand.nextInt(MAX_POSITION + 1);



        System.out.printf("%s starts at x = %.2f\n", player1, player1Pos);
        System.out.printf("%s starts at x = %.2f\n", player2, player2Pos);

        boolean gameOver = false;
        int currentPlayer = 1;

        while (!gameOver) {
            System.out.printf("\n%s's turn:\n", currentPlayer == 1 ? player1 : player2);
            double shotX = getShot(currentPlayer == 1 ? player1Pos : player2Pos);
            double targetX = currentPlayer == 1 ? player2Pos : player1Pos;

            if (Math.abs(shotX - targetX) <= HIT_THRESHOLD) {
                gameOver = true;
            } else {
                System.out.println("Missed! Next player's turn.");
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }

        return currentPlayer; // winner
    }



}