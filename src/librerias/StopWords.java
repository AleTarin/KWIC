/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class StopWords {
    private final HashSet<String> _StopWords;
    private static StopWords _words;
    private StopWords() {
        this._StopWords = new HashSet<>();
    }

    public static StopWords getStopWords() {
        if (_words == null) {
            _words = new StopWords();
        }

        return _words;
    }

    public void addWordToIgnore(String word) {
        assert(word != null);
        this._StopWords.add(word.replace(" ", ""));
    }

    public boolean isWordIgnored(String word) {
        assert(word != null);
        return this._StopWords.contains(word);
    }
    
    public String removeWordsFromInput(String input){
        for (String s : this._StopWords) {
            String regex = "\\b"+s+"\\b";
            input = input.replaceAll(regex, "");
        }
        return input;
    }
}