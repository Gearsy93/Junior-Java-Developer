public static void sayHello(String[] args)
{
// Создаем переменную, хранящую строку для вывода на экран.
// При помощи тернарой операции проверяем, что есть всего 1 аргумент и он равен флагу "-u"
String output = (args.length == 1 && args[0].equals("-u")) ? String.format("Hello, %s!", System.getProperty("user.name")) : "Hello!";
System.out.println(output);
}