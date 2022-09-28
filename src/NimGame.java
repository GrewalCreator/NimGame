import java.util.Random;
import java.util.Scanner;

public class NimGame {
    // Declare Variables
    private int marbles;
    private int player;
    private int win;
    private int loss;
    private final Scanner scanUser;
    private final Random random_nbr;
    private final int MAX_MARBLES;
    private final int MIN_MARBLES;

    // Initialize Variables
    public NimGame(){
        random_nbr = new Random();
        MAX_MARBLES = 50;
        MIN_MARBLES = 10;
        marbles = random_nbr.nextInt(MIN_MARBLES, MAX_MARBLES);
        win = 0;
        loss = 0;
        scanUser = new Scanner(System.in);
        player = random_nbr.nextInt(1, 2); // Creates players (We have two players)

    }

    // main function
    public static void main(String[] args) {
        NimGame game = new NimGame();
        game.play();
    }

    public void play(){
        while (marbles > 0) {
            if (player == 1) { // user's turn
                player = 2; // next turn will be the machine
                playerTurn();
            }
            else {   // player == 2 : machine's turn
                player = 1; // next turn will be the user
                computerTurn();
            }
        }
        checkWinner();
    }

    public void playerTurn() {
        while(true){
            try {
                System.out.println("There are " + marbles + " marbles left. How many would you like to take: 1, 2 or 3? ");
                String input_user = scanUser.nextLine(); // from console input
                int number = Integer.parseInt(input_user); // converts a String into an int value
                if (number > 3 || number < 1) {
                    System.out.println("Invalid number. Please take: 1,2, or 3");
                } else {
                    marbles = marbles - number;
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid Entry. Please Try Again");
            }
        }
    }

    public void computerTurn(){
        int computer = smartCheck();

        System.out.println("There are " + marbles + " marbles left. Machine's turn.");
        System.out.println("The machine picked: " + computer);

        marbles = marbles - computer; // remove marbles computer picked
    }

    public void points() {
        System.out.println(" \n *************** \n The points are ");
        System.out.println( "* Win: " + win );
        System.out.println( "* Loss: " + loss);
        System.out.println(" *************** \n\n");

        boolean cont = true;
        while(cont)
            try {
                System.out.println("Would you like to play again? (Enter 1 to play again and 0 to exit) ");
                String input = scanUser.nextLine();
                int playAgainInt = Integer.parseInt(input);

                if (playAgainInt == 1) {
                    // reset the number of marbles
                    marbles = random_nbr.nextInt(MIN_MARBLES, MAX_MARBLES);

                    player = 2; // reset the player => user will start
                    play(); // calls the function play()


                } else {
                    System.out.println("Game over!");
                }
                cont = false;
            }catch(NumberFormatException e){
                System.out.println("Invalid Entry. Please Try Again");
            }
    }

    public void checkWinner(){
        if (player == 1) {
            System.out.println("You won!");
            win++; // increments the number of wins by 1
        } else {
            System.out.println("The machine won :( Better Luck Next Time!");
            loss++; // increments the number of losses by 1
        }
        points();
    }

    public int smartCheck(){
        return switch (marbles) {
            case 10, 6, 5, 2, 1 -> 1;
            case 7, 3 -> 2;
            default -> 3;

        };
    }


}