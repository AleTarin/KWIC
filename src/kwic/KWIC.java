package kwic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import librerias.Alphabetizer;
import librerias.CircularShift;
import librerias.Ignore;


public class KWIC {
    
    public static void addIgnoreWords(Scanner sc,  Ignore wordsToIgnore){
        // Add words to be ignored (part 2).
        System.out.println("Do you want to add words to ignore? (yes or y)");
        String userInput = sc.nextLine();

        if(userInput.toLowerCase().contains("y")){
            
            System.out.println("\nEnter a word peer line (empty line to terminate)");
            String inputWordToIgnore = sc.nextLine();
            
            // You can add whatever words you want
            while (!inputWordToIgnore.isEmpty()) {
                wordsToIgnore.addWordToIgnore(inputWordToIgnore);
                inputWordToIgnore = sc.nextLine();
            }    
        } 
    }
    
    public static void addInputLines(Scanner sc, Ignore wordsToIgnore, List<String> inputs) {
        // Input the lines to be shifted
        System.out.println("\n Enter a sentence (empty line to terminate)");
        String sentence = sc.nextLine();
        
        while (!sentence.isEmpty()) {
            sentence = wordsToIgnore.removeWordsFromInput(sentence);
            inputs.add(sentence);
            sentence = sc.nextLine();
        }
    }
    
    public static void removeLines(Scanner sc,  List<String> inputs ){
        // Remove an specific line
        System.out.println("\nDo you want to remove a line ? (yes or y)");
        String userInput = sc.nextLine();
        
        if(userInput.toLowerCase().contains("y")){
            System.out.println("\nEnter the line to ignore (empty line to terminate)");
            String LineToIgnore = sc.nextLine();
            try{
                while(!LineToIgnore.isEmpty() && !inputs.isEmpty()){
                    int iLine = Integer.parseInt(LineToIgnore);
                    if (iLine <= inputs.size() && iLine > 0) {
                        inputs.remove(iLine - 1);
                        System.out.println(inputs);
                    } else {
                        System.out.println("\nThat line doesnt exists");
                    }

                    System.out.println("\nAnother line? (line number or empty line go to the next step)");
                    LineToIgnore = sc.nextLine();
                }
            }catch(NumberFormatException e){
                System.out.println("Only numbers allowed, passing to next stage");
            }
        }
    }
    
    public static String[] selectOrder(Scanner sc, Alphabetizer alphabetizer){
        // Change the order 
        System.out.println("\nIn what order do you want to accommodate the phrases? (i to incremental / d to decremental)");
        String userInput = sc.nextLine();
        if(userInput.toLowerCase().contains("d")){
            return alphabetizer.getSortedLinesDecremental();
        }else{
            return alphabetizer.getSortedLines();
        }  
    }

    public static void main(String[] args){
        //Declare variables for scanner
        Scanner sc = new Scanner(System.in);
        
        //Declare variables to store data
        List<String> inputs = new ArrayList<>();
        Ignore wordsToIgnore = Ignore.getStopWords();
        StringBuilder builder = new StringBuilder();
        String separator = System.lineSeparator();
        String[] result;
        
        // Alphabetizer to order the data
        Alphabetizer alphabetizer = new Alphabetizer();

        addIgnoreWords(sc, wordsToIgnore);
        addInputLines(sc, wordsToIgnore, inputs);
        removeLines(sc,inputs);
        
        for (String str : inputs) {
            CircularShift shifter = new CircularShift(str);
            alphabetizer.addLines(shifter.getCircularShifts());
        }

        // Get result in order
        result = selectOrder(sc,alphabetizer);
        
        // Separate the phrases
        for (String str : result ) {
            builder.append(str).append(separator);
        }
        
        // Print the result
        System.out.print(builder.toString());

        System.exit(0);
    }
}
