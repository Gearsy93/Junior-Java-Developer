public static void sayHello(String[] args) {
// Проверка одиночного флага на соответствие -u или -s
if (args.length == 1) {
if (args[0].equals("-u")) {
// Вывод форматированной строки с именем пользователя
System.out.println(String.format("Hello, %s!", System.getProperty("user.name")));
}
else if (args[0].equals("-s")) {
// Вывод форматированной строки с названием операционной системы
System.out.println(String.format("Hello, %s!", System.getProperty("os.name")));
}
// Любой другой одиночный флаг
else {
System.out.println("Hello!");
}
}
// Проверка двух флагов на соответствие -u и -s в разном порядке
else if (args.length == 2) {
if ((args[0].equals("-u") && args[1].equals("-s")) || (args[0].equals("-s") && args[1].equals("-u"))) {
System.out.println(String.format("Hello, %s of %s!", System.getProperty("user.name"), System.getProperty("os.name")));
}
// Любая другая комбинация флагов
else {
System.out.println("Hello!");
}
}
// Любое другое число флагов
else {
System.out.println("Hello!");
}
}