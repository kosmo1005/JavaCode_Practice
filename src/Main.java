public class Main {
    public static void main(String[] args) {
        System.out.println("Создадим VersionableStringBuilder");
        VersionableStringBuilder vsb = new VersionableStringBuilder();

        System.out.println("\nПопробуем откатить состояние VersionableStringBuilder на шаг назад");
            vsb.undo();
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nДобавим в VersionableStringBuilder строку");
        vsb.append("Hello World");
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nДобавим к текущей записи еще строку");
        vsb.append(", I love you!");
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nПопробуем откатить состояние VersionableStringBuilder на шаг назад");
            vsb.undo();
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nСнова добавим к текущей записи еще пару строк");
        vsb.append(", I love you!");
        System.out.println("Вывод: " + vsb.toString());
        vsb.append(" I'am developer!");
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nУдалим часть строки");
        vsb.delete(2,8);
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nПопробуем откатить состояние VersionableStringBuilder на шаг назад");
        vsb.undo();
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nПопробуем откатить состояние VersionableStringBuilder на 8 шагов назад");
        vsb.undoSomeStep(8);
        System.out.println("Вывод: " + vsb.toString());

        System.out.println("\nПопробуем откатить состояние VersionableStringBuilder на 2 шага назад");
        vsb.undoSomeStep(2);
        System.out.println("Вывод: " + vsb.toString());
    }
}