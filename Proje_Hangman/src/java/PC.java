import java.util.ArrayList;
import java.util.Arrays;

public class PC {
    private int longestLength = -1;
    private int shortestLength = 45;
    private ArrayList<String> memory;
    private ArrayList<String> tempList;//this list is always updated according to predicted letters and chosen word length
    private int[] letterCounts;
    private String letters = "abcçdefgğhıijklmnoöprsştuüvyz";

    /*
        Constructor
     */
    public PC(ArrayList<String> list){
        memory = list;
        letterCounts = new int[29];
        tempList = new ArrayList<String>();

    }

    /*
    This function counts the letters for the each word of the tempList
    PC predicts the letter that is the most found letter in the arraylist
    therefore, it increases the success rate of the artificial intelligence
     */
    public void countTheLettersinTempList(){

        for(int i = 0; i<tempList.size();i++){
            for(int j= 0; j < tempList.get(i).length();j++){
                if(tempList.get(i).charAt(j) == 'a'){
                    letterCounts[0] = letterCounts[0] + 1;
                }
                if(tempList.get(i).charAt(j) == 'b'){
                    letterCounts[1] = letterCounts[1] + 1;
                }
                if(tempList.get(i).charAt(j) == 'c'){
                    letterCounts[2] = letterCounts[2] + 1;
                }
                if(tempList.get(i).charAt(j) == 'ç'){
                    letterCounts[3] = letterCounts[3] + 1;
                }
                if(tempList.get(i).charAt(j) == 'd'){
                    letterCounts[4] = letterCounts[4] + 1;
                }
                if(tempList.get(i).charAt(j) == 'e'){
                    letterCounts[5] = letterCounts[5] + 1;
                }
                if(tempList.get(i).charAt(j) == 'f'){
                    letterCounts[6] = letterCounts[6] + 1;
                }
                if(tempList.get(i).charAt(j) == 'g'){
                    letterCounts[7] = letterCounts[7] + 1;
                }
                if(tempList.get(i).charAt(j) == 'ğ'){
                    letterCounts[8] = letterCounts[8] + 1;
                }
                if(tempList.get(i).charAt(j) == 'h'){
                    letterCounts[9] = letterCounts[9] + 1;
                }
                if(tempList.get(i).charAt(j) == 'ı'){
                    letterCounts[10] = letterCounts[10] + 1;
                }
                if(tempList.get(i).charAt(j) == 'i'){
                    letterCounts[11] = letterCounts[11] + 1;
                }
                if(tempList.get(i).charAt(j) == 'j'){
                    letterCounts[12] = letterCounts[12] + 1;
                }
                if(tempList.get(i).charAt(j) == 'k'){
                    letterCounts[13] = letterCounts[13] + 1;
                }
                if(tempList.get(i).charAt(j) == 'l'){
                    letterCounts[14] = letterCounts[14] + 1;
                }
                if(tempList.get(i).charAt(j) == 'm'){
                    letterCounts[15] = letterCounts[15] + 1;
                }
                if(tempList.get(i).charAt(j) == 'n'){
                    letterCounts[16] = letterCounts[16] + 1;
                }
                if(tempList.get(i).charAt(j) == 'o'){
                    letterCounts[17] = letterCounts[17] + 1;
                }
                if(tempList.get(i).charAt(j) == 'ö'){
                    letterCounts[18] = letterCounts[18] + 1;
                }
                if(tempList.get(i).charAt(j) == 'p'){
                    letterCounts[19] = letterCounts[19] + 1;
                }
                if(tempList.get(i).charAt(j) == 'r'){
                    letterCounts[20] = letterCounts[20] + 1;
                }
                if(tempList.get(i).charAt(j) == 's'){
                    letterCounts[21] = letterCounts[21] + 1;
                }
                if(tempList.get(i).charAt(j) == 'ş'){
                    letterCounts[22] = letterCounts[22] + 1;
                }
                if(tempList.get(i).charAt(j) == 't'){
                    letterCounts[23] = letterCounts[23] + 1;
                }
                if(tempList.get(i).charAt(j) == 'u'){
                    letterCounts[24] = letterCounts[24] + 1;
                }
                if(tempList.get(i).charAt(j) == 'ü'){
                    letterCounts[25] = letterCounts[25] + 1;
                }
                if(tempList.get(i).charAt(j) == 'v'){
                    letterCounts[26] = letterCounts[26] + 1;
                }
                if(tempList.get(i).charAt(j) == 'y'){
                    letterCounts[27] = letterCounts[27] + 1;
                }
                if(tempList.get(i).charAt(j) == 'z') {
                    letterCounts[28] = letterCounts[28] + 1;
                }
            }
        }
    }

    /*iff PC has successful guess this function eliminates
    the words that doesn't contain predicted letters
    if PC doesn't have successful guess function resets 0 the index that is the largest one in the letterCounts
     */
    public void updateTheLists(char c,String predictedWord,char predicted){
        //if PC guesses correctly
        if(c=='e'){
            int index = tempList.size()-1;
            while(index!=-1){
                boolean flag = true;
                for(int i = 0; i<predictedWord.length();i++){
                    if(predictedWord.charAt(i)== '*' || tempList.get(index).charAt(i)==predictedWord.charAt(i)){

                    }
                    else{
                        flag = false;
                    }
                }
                if(flag==false){
                    tempList.remove(index);
                }
                index--;
            }
            letterCounts[letters.indexOf(predicted)] = -10000;
            for(int i = 0 ;i<letterCounts.length;i++){
                if(letterCounts[i]<0){

                }
                else{
                    letterCounts[i]=0;
                }
            }
            countTheLettersinTempList();
        }

        //if PC has a mistake
        else{
            letterCounts[letters.indexOf(predicted)] = -10000;
        }
    }

    public char predictLetter(){
        char ch  ;
        int index = -1;
        int mostLetterNumber = -1;
        for(int i = 0 ; i<letterCounts.length;i++){
            if(letterCounts[i]>mostLetterNumber){
                mostLetterNumber = letterCounts[i];
                index = i;
            }
        }
        ch = letters.charAt(index);
        return ch;
    }

    public int defineLetterNumber(){
        int number;
        //starts from index 12 because first 11 elements have irrelevant contents
        for(int i = 12;i<memory.size();i++){
            if(memory.get(i).length()<shortestLength){
                shortestLength = memory.get(i).length();
            }
            if(memory.get(i).length()>longestLength){
                longestLength = memory.get(i).length();
            }
        }
        number = (int)(Math.random()*(longestLength-shortestLength+1)+shortestLength);
        //after defining the letter count randomly PC selects the words that have defined letter count from the whole list
        for(int i = 12;i<memory.size();i++){
            if(memory.get(i).length()==number){
                tempList.add(memory.get(i));
            }
        }

        return number;
    }
}
