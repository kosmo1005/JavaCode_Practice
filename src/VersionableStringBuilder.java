
import java.util.LinkedList;

public class VersionableStringBuilder {
    private StringBuilder builder;
    private LinkedList<String> history;
    private final int MAX_HISTORY_SIZE = 20;


    /**
     * Конструкторы
     */
    public VersionableStringBuilder() {
        this.builder = new StringBuilder();
        this.history = new LinkedList<>();
    }

    public VersionableStringBuilder(int capacity) {
        this.builder = new StringBuilder(capacity);
        this.history = new LinkedList<>();
    }

    public VersionableStringBuilder(String str) {
        this.builder = new StringBuilder(str);
        this.history = new LinkedList<>();
    }

    public VersionableStringBuilder(CharSequence seq) {
        this.builder = new StringBuilder(seq);
        this.history = new LinkedList<>();
    }


    /**
     * Делегированные методы
     */
    public int compareTo(StringBuilder another) {
        return builder.compareTo(another);
    }

    public VersionableStringBuilder append(String str) {
        builder.append(str);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder append(Object obj) {
        builder.append(obj);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder append(StringBuffer sb) {
        builder.append(sb);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder append(CharSequence s) {
        builder.append(s);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder append(char[] str) {
        builder.append(str);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder appendCodePoint(int codePoint) {
        builder.appendCodePoint(codePoint);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder delete(int start, int end) {
        builder.delete(start, end);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder deleteCharAt(int index) {
        builder.deleteCharAt(index);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder insert(int offset, Object obj) {
        builder.insert(offset, obj);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder insert(int offset, String str) {
        builder.insert(offset, str);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder insert(int offset, char[] str) {
        builder.insert(offset, str);
        saveState(builder.toString());
        return this;
    }

    public VersionableStringBuilder insert(int dstOffset, CharSequence s) {
        builder.insert(dstOffset, s);
        saveState(builder.toString());
        return this;
    }

    public int length() {
        return builder.length();
    }

    public String toString() {
        return builder.toString();
    }

    public VersionableStringBuilder reverse() {
        builder.reverse();
        saveState(builder.toString());
        return this;
    }

    public char charAt(int index) {
        return builder.charAt(index);
    }

    /**
     * Сохранить состояние StringBuilder
     */
    public void saveState(String str) {
        if (history.size() == MAX_HISTORY_SIZE) {
            history.removeFirst();
        }
        history.addLast(str);
    }

    /**
     * @return StringBuilder в состоянии на шаг назад
     */
    public VersionableStringBuilder undo() {
        if (history.isEmpty()) {return this;}

        history.removeLast();
        builder = new StringBuilder(history.getLast());
        return this;
    }

    /**
     * Вернуть StringBuilder в состояние на n шагов назад.<br>
     * При этом отсчет всякий раз начинается с последнего сохранённого элемента.
     *
     * @param step количество шагов назад
     * @return StringBuilder в состоянии на n шагов назад
     */
    public VersionableStringBuilder undoSomeStep(int step) {
        if (history.size() < step) {return this;}

        for (int i = 0; i < step; i++) {
            history.removeLast();
        }

        if (history.isEmpty()) {
            builder = new StringBuilder();
            return this;
        }

        builder = new StringBuilder(history.getLast());
        return this;
    }
}
