import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    private ArrayList<String> list;
    private PC pc;
    private Player player;
    private int remainingLives;
    private int randomNumberToDefinePCPlayer;
    private int letterNumber;
    private String chosenWord = "";
    private String predictedWord = "";
    private boolean gameFinished;

    /*
     Constructor
     */
    public Game() {
        list = new ArrayList<String>();
        remainingLives = 10;
        gameFinished = false;
        readFile("turkish_dictionary.txt");//reading turkish dictionary and adding words to the arrayList
        pc = new PC(list);
        player = new Player();

        randomNumberToDefinePCPlayer = (int)(Math.random()*2);//if this number equals 0 PC defines the word
        /*
        If you want to see the only artificial intelligence of computer comment the line above and
        uncomment the line below.
         */
        //randomNumberToDefinePCPlayer = 1;

        System.out.println("randomNumber: " + randomNumberToDefinePCPlayer);                                                    //if it is 1 Player defines the word
        if (randomNumberToDefinePCPlayer == 0) {
            pcDefines();
        } else {
            playerDefines();
        }

    }
    //Player defines the word
    private void playerDefines() {
        System.out.println("Player defines the word");
        System.out.print("Harf sayısını belirliyorum: ");
        letterNumber = pc.defineLetterNumber();
        System.out.println("letter number: " + letterNumber);

        for (int i = 0; i < letterNumber; i++) {
            predictedWord = predictedWord + '*';
        }

        System.out.println("predictedWord: " + predictedWord);

        while (remainingLives != 0 && gameFinished == false) {
            System.out.print("Harf tahmin ediyorum: ");
            pc.countTheLettersinTempList();
            char predicted = pc.predictLetter();
            System.out.println(predicted);
            System.out.print("Bildim mi? (e/h): ");
            char c = player.replyQuestion();
            if (c == 'e') {
                System.out.print("Kaç harf bildim: ");
                int trueNum = player.replyTruePredictedLetterNumber();
                int places = 0;
                for (int i = 1; i <= trueNum; i++) {
                    System.out.print(i + ".  harfin sırası: ");
                    places = player.replyOrderOfTruePredictedLetters();
                    predictedWord = predictedWord.substring(0, places - 1) + predicted + predictedWord.substring(places, letterNumber);
                }
                System.out.println(predictedWord);

            } else {
                remainingLives--;
                System.out.println(remainingLives + " hakkım kaldı.");
            }
            pc.updateTheLists(c, predictedWord, predicted);

            if (remainingLives == 0) {
                System.out.println("Oyunu Kaybettim.");
                gameFinished = true;
            }
            if (predictedWord.indexOf('*') == -1) {
                System.out.println("Oyunu kazandım.");
                gameFinished = true;
            }
        }
    }

    //PC defines the word
    private void pcDefines() {
        System.out.println("PC defines the word");
        System.out.print("Harf sayısı giriniz: ");
        letterNumber = player.defineLetterNumber();
        do {
            chosenWord = list.get((int) (Math.random() * 62108 + 12));//choose the word randomly between 13 - 62120 indices of the dictionary
        }
        while (chosenWord.length() != letterNumber);//PC chooses the word which contains the equal letter number with defined by Player

        System.out.println("word: " + chosenWord);
        for (int i = 0; i < letterNumber; i++) {
            predictedWord = predictedWord + '*';
        }
        System.out.println("predictedWord: " + predictedWord);
        while (remainingLives != 0 && gameFinished == false) {
            System.out.print("Harf tahmin edin: ");
            char a = player.predictLetter();
            String tempPredictedWord = predictedWord;
            for (int i = 0; i < chosenWord.length(); i++) {
                if (chosenWord.charAt(i) == a) {
                    predictedWord = predictedWord.substring(0, i) + a + predictedWord.substring(i + 1, chosenWord.length());
                }
            }
            if (predictedWord.equals(tempPredictedWord)) {
                System.out.println("Bilemediniz!");
                remainingLives--;
                System.out.println(remainingLives + " hakkınız kaldı.");
            } else {
                System.out.println("Bildiniz!");
                System.out.println(predictedWord);
            }
            if (predictedWord.equals(chosenWord)) {
                System.out.println("Oyunu Kazandınız.");
                gameFinished = true;
            }
            if (remainingLives == 0) {
                System.out.println("Oyunu Kaybettiniz.");
                gameFinished = true;
            }
        }
    }

    /*
        This function reads the turkish_dictionary.txt adds the words to the arrayList by extracting their
        pure word form.
        @param fileName gets the turkish_dictionary.txt from the resources you need to specify source clearly
        to work this function correctly.
     */
    private void readFile(String fileName) {
        File file = new File(getClass().getResource(fileName).getPath());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                if (temp.indexOf(" ") > 0) {
                    list.add(temp.substring(0, temp.indexOf(" ")));
                    //System.out.println(temp.substring(0,temp.indexOf(" ")));
                } else {
                    System.out.println(temp);
                    list.add(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
