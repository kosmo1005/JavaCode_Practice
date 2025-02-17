
import java.util.TreeMap;

public class VersionableStringBuilder {
    private StringBuilder builder;
    private final Memento memento;


    /**
     * Конструкторы
     */
    public VersionableStringBuilder() {
        this.builder = new StringBuilder();
        this.memento = new Memento();
    }

    public VersionableStringBuilder(int capacity) {
        this.builder = new StringBuilder(capacity);
        this.memento = new Memento();
    }

    public VersionableStringBuilder(String str) {
        this.builder = new StringBuilder(str);
        this.memento = new Memento();
    }

    public VersionableStringBuilder(CharSequence seq) {
        this.builder = new StringBuilder(seq);
        this.memento = new Memento();
    }


    /**
     * Делегированные методы
     */
    public int compareTo(StringBuilder another) {

        return builder.compareTo(another);
    }

    public VersionableStringBuilder append(String str) {
        builder.append(str);
        return this;
    }

    public VersionableStringBuilder append(Object obj) {
        builder.append(obj);
        return this;
    }

    public VersionableStringBuilder append(StringBuffer sb) {
        builder.append(sb);
        return this;
    }

    public VersionableStringBuilder append(CharSequence s) {
        builder.append(s);
        return this;
    }

    public VersionableStringBuilder append(char[] str) {
        builder.append(str);
        return this;
    }

    public VersionableStringBuilder appendCodePoint(int codePoint) {
        builder.appendCodePoint(codePoint);
        return this;
    }

    public VersionableStringBuilder delete(int start, int end) {
        builder.delete(start, end);
        return this;
    }

    public VersionableStringBuilder deleteCharAt(int index) {
        builder.deleteCharAt(index);
        return this;
    }

    public VersionableStringBuilder insert(int offset, Object obj) {
        builder.insert(offset, obj);
        return this;
    }

    public VersionableStringBuilder insert(int offset, String str) {
        builder.insert(offset, str);
        return this;
    }

    public VersionableStringBuilder insert(int offset, char[] str) {
        builder.insert(offset, str);
        return this;
    }

    public VersionableStringBuilder insert(int dstOffset, CharSequence s) {
        builder.insert(dstOffset, s);
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
        return this;
    }

    public char charAt(int index) {
        return builder.charAt(index);
    }

    /**
     * Сохранить состояние StringBuilder
     */
    public void saveState() {
        memento.save(builder.toString());
    }

    /**
     * Вернуть StringBuilder в состояние на момент последнего сохранения
     *
     * @return StringBuilder в состоянии на момент последнего сохранения
     * @throws IllegalStateException если сохраненные состояния отсутствуют
     */
    public VersionableStringBuilder undo() {
        var lastContent = memento.getLastContent();
        if (lastContent == null) {
            throw new IllegalStateException("Нет сохраненных состояний");
        }
        builder = new StringBuilder(lastContent);
        return this;
    }

    /**
     * Вернуть StringBuilder в состояние на n шагов назад.<br>
     * При этом отсчет всякий раз начинается с последнего сохранённого элемента.
     *
     * @param step количество шагов назад
     * @return StringBuilder в состоянии на n шагов назад
     * @throws IllegalArgumentException если шаг больше количества сохраненных состояний
     */
    public VersionableStringBuilder undoSomeStep(int step) {
        var content = memento.getSomeContent(step);
        if (content == null) {
            throw new IllegalArgumentException("Нет столько сохраненных состояний");
        }

        builder = new StringBuilder(content);
        return this;
    }

    /**
     * Удалить последний элемент StringBuilder
     */
    public void deleteLastContent() {
        memento.deleteLastContent();
    }

    private final class Memento {
        private TreeMap<Integer, String> history;

        private Memento() {
            history = new TreeMap<Integer, String>();
        }

        /**
         * Сохраняет текущее состояние строки
         *
         * @param content содержимое для сохранения
         */
        private void save(String content) {
            Integer actualVersion = history.isEmpty() ? 0 : history.lastKey() + 1;
            history.put(actualVersion, content);
        }

        /**
         * Возвращает последнее сохраненное состояние
         *
         * @return последнее сохраненное состояние или null, если история пуста
         */
        private String getLastContent() {
            return history.isEmpty() ? null : history.get(history.lastKey());
        }

        /**
         * Возвращает состояние на n шагов назад
         *
         * @param step количество шагов назад
         * @return состояние на n шагов назад или null, если шаг больше размера истории
         */
        private String getSomeContent(int step) {
            if (step <= 0) {
                throw new IllegalArgumentException("Шаг должен быть положительным числом");
            }
            var size = history.size();
            if (size < step) {
                return null;
            }
            return history.get(history.lastKey() - (step - 1));
        }

        /**
         * Удалить последний элемент StringBuilder
         *
         * @throws IllegalStateException если сохраненные состояния отсутствуют
         */
        private void deleteLastContent() {
            if (history.isEmpty()) {
                throw new IllegalStateException("Нет сохраненных состояний");
            }
            history.remove(history.lastKey());
        }
    }
}
