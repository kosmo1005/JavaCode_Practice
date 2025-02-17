public class Main {
    public static void main(String[] args) {
        System.out.println("Создадим VersionableStringBuilder");
        VersionableStringBuilder vsb = new VersionableStringBuilder();

        System.out.println("\nПопробуем получить последнее сохраненное состояние");
        try {
            vsb.undo();
        } catch (IllegalStateException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
        System.out.println(vsb.toString());

        vsb.append("Hello World");
        System.out.println(vsb.toString());

        System.out.println("\nСохраним текущее состояние билдера");
        vsb.saveState();

        System.out.println("\nДобавим к текущей записи еще строку");
        vsb.append(", I love you!");
        System.out.println(vsb.toString());

        System.out.println("\nПопробуем восстановить первоначальную запись");
        try {
            vsb.undo();
        } catch (IllegalArgumentException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
        System.out.println(vsb.toString());

        System.out.println("\nДобавим к текущей записи еще строку");
        vsb.append(", I love you!");
        System.out.println(vsb.toString());

        System.out.println("\nСохраним текущее состояние билдера");
        vsb.saveState();

        System.out.println("\nДобавим к текущей записи еще строку");
        vsb.append(" I'am developer!");
        System.out.println(vsb.toString());

        System.out.println("\nПопробуем вернуться на два шага назад");
        try {
            vsb.undoSomeStep(2);
        } catch (IllegalArgumentException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
        System.out.println(vsb.toString());

        System.out.println("\nУдалим из истории одну запись");
        vsb.deleteLastContent();

        System.out.println("\nПопробуем вернуться ещё раз на два шага назад");
        try {
            vsb.undoSomeStep(2);
        } catch (IllegalArgumentException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
        System.out.println(vsb.toString());


    }
}