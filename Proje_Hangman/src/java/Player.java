import java.util.Scanner;


public class Player {
    private Scanner scan;

    /*
      Constructor
     */
    public Player(){
        scan = new Scanner(System.in);

    }

    public char predictLetter(){
        scan = new Scanner(System.in);
        char a = scan.next().charAt(0);
        return a;
    }

    public int defineLetterNumber(){
        scan = new Scanner(System.in);
        int number = scan.nextInt();
        System.out.print("\n");
        return number;
    }


    /*
        Player tells the places of the true predicted letters by means of this function
    */
    public int replyOrderOfTruePredictedLetters(){
        scan = new Scanner(System.in);
        int number = scan.nextInt();
        return number;
    }

    /*
        Player tells how many true predicted letter in the chosen word by means of this function
    */
    public int replyTruePredictedLetterNumber(){
        scan = new Scanner(System.in);
        int number;
        number = scan.nextInt();
        return number;
    }

    /*
        Player tells evet(e) or hayır(h) by means of this function
    */
    public char replyQuestion(){
        scan = new Scanner(System.in);
        char ch;
        ch = scan.next().charAt(0);
        return ch;
    }
}
