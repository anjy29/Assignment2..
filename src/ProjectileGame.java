/*
name: Anjy Alassaf
Date: Mar 9,2026
Description: A two player projectile game where each player chooses a speed and angle to fire.
The program calculates where the projectile lands and announces the winner when it hits near the opponent.
*/

import java.util.Scanner;
import java.util.Random;

public class ProjectileGame {

    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();
    static final double GRAVITY = 9.81;

    public static void main(String[] args) {
        System.out.println("Welcome to the Projectile Motion Game!");


        // Ask player names
        System.out.print("Enter name for Player 1: ");
        String player1 = input.nextLine();
        System.out.print("Enter name for Player 2: ");
        String player2 = input.nextLine();

        boolean playAgain = true;




}
