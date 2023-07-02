public static void sayHello(String[] args)
{
switch (args.length) {
// Если всего 1 параметр, проверяем его
case 1:
switch (args[0]) {
// Пользователь
case "-u":
System.out.println(String.format("Hello, %s!", System.getProperty("user.name")));
break;
// ОС
case "-s":
System.out.println(String.format("Hello, %s!", System.getProperty("os.name")));
break;
// Пользователь и система
case "-su":
System.out.println(String.format("Hello, %s of %s!", System.getProperty("user.name"), System.getProperty("os.name")));
break;
case "-us":
System.out.println(String.format("Hello, %s of %s!", System.getProperty("user.name"), System.getProperty("os.name")));
break;
default:
System.out.println("Hello!");
break;
}
break;
// Любое другое количество флагов
default:
System.out.println("Hello!");
break;
}
}