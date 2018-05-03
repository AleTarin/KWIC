
package librerias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alphabetizer {
    private List<String> lines;

    public Alphabetizer() {
        this.lines = new ArrayList<String>();
    }

    public void addLines(String[] lines) {
        for (String str : lines) {
            this.lines.add(str);
        }
    }

    public String[] getSortedLines() {
        Collections.sort(this.lines);
        return this.lines.toArray(new String[this.lines.size()]);
    }
    
    public String[] getSortedLinesDecremental() {
        Collections.sort(this.lines, Collections.reverseOrder());
        return this.lines.toArray(new String[this.lines.size()]);
    }
}
