package librerias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CircularShift {
    public static String LIMIT = " ";
    private String _line;

    public CircularShift(String line) {
        assert(line != null);
        this._line = line.toLowerCase();
    }

    public String[] getCircularShifts() {
        String[] words = this._line.split(LIMIT);
        String[] shifts = new String[words.length];
        shifts[0] = this._line;

        for (int i=1;i<words.length;i++) {
            shifts[i] = this.getShiftedLine(i, words);
        }

        String[] filters = getShiftsWithoutIgnoredWordLeading(shifts);
        for (int i=0;i<filters.length;i++) {
            filters[i] = IgnoreNull(filters[i]);
        }

        return filters;
    }

    private String getShiftedLine(int index, String[] words) {
        StringBuilder builder = new StringBuilder();

        for (int i=index;i<words.length;i++) {
            builder.append(words[i]);
            builder.append(LIMIT);
        }
        for (int i=0;i<index;i++) {
            builder.append(words[i]);
            builder.append(LIMIT);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    private String[] getShiftsWithoutIgnoredWordLeading(String[] shifts) {
        List<String> shiftList = new ArrayList<String>(Arrays.asList(shifts));

        Iterator<String> iter = shiftList.iterator();

        return shiftList.toArray(new String[shiftList.size()]);
    }


    private String IgnoreNull(String shift) {
        String[] words = shift.split(LIMIT);
        StringBuilder builder = new StringBuilder();

        for (String str : words) {
            if (str.trim().isEmpty()) {
                continue;
            } else {
                builder.append(str);
            }
            builder.append(LIMIT);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
