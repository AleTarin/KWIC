package kwic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import librerias.Alphabetizer;
import librerias.CircularShift;
import librerias.StopWords;

public class KWIC {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<String> inputs = new ArrayList<String>();
        StopWords wordsToIgnore = StopWords.getStopWords();

        
        // Add words to be ignored (part 2).
        System.out.println("Do you want to add words to ignore? (yes or y)");
        String userInput = sc.nextLine();

        if(userInput.toLowerCase().contains("y")){
            
            System.out.println("Enter words to ignore (empty line to terminate)");
            String inputWordToIgnore = sc.nextLine();
            
            // You can add whatever words you want
            while (!inputWordToIgnore.isEmpty()) {
                wordsToIgnore.addWordToIgnore(inputWordToIgnore);
                inputWordToIgnore = sc.nextLine();
            }    
        }
        
        // Input the lines to be shifted
        System.out.println("Enter a sentence (empty line to terminate)");
        String sentence = sc.nextLine();
        
        while (!sentence.isEmpty()) {
            sentence = wordsToIgnore.removeWordsFromInput(sentence);
            inputs.add(sentence);
            sentence = sc.nextLine();
        }
        
        // Remove an specific line
        System.out.println("Do you want to remove a line ? (yes or y)");
        userInput = sc.nextLine();
        if(userInput.toLowerCase().contains("y")){
            System.out.println("Enter the line to ignore (empty line to terminate)");
            String LineToIgnore = sc.nextLine();
            while(!LineToIgnore.isEmpty() && !inputs.isEmpty()){
                int iLine = Integer.parseInt(LineToIgnore);
                if (iLine <= inputs.size() && iLine > 0) {
                    inputs.remove(iLine - 1);
                    System.out.println(inputs);
                } else {
                    System.out.println("That line doesnt exists");
                }
                
                System.out.println("Another line? (empty line to terminate)");
                LineToIgnore = sc.nextLine();
            }

        }
        
        Alphabetizer alphabetizer = new Alphabetizer();
        for (String str : inputs) {
            CircularShift shifter = new CircularShift(str);
            alphabetizer.addLines(shifter.getCircularShifts());
        }

        String[] result = alphabetizer.getSortedLines();
        
        StringBuilder builder = new StringBuilder();
        String separator = System.lineSeparator();
        for (String str : result) {
            builder.append(str).append(separator);
        }
        
        System.out.print(builder.toString());

        System.exit(0);
    }
}
