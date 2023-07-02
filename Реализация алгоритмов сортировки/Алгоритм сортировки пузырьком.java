public static void sortSales(String[] names, double[] data) {
// Проверить совпадение длин массивов
if (names.length != data.length) {
System.out.println("Corrupted Data");
return;
}
// Длина совпадает, проверяем массивы на пустоту
if (names.length == 0) {
System.out.println("Empty Data");
return;
}

// Временные переменные
double maxData;
String maxDataName;

// Всего data.length -1 раз обойти массивы
for(int i = 1; i < data.length; ++i) {
// Каждый раз проверяем на 1 пару меньше
for(int j = 0, max; j < data.length - i; ++j) {
// Если текущий больше следующего, меняем местами
if (data[j] < data[j + 1]) {
// В массиве объемов продаж
maxData = data[j];
data[j] = data[j + 1];
data[j + 1] = maxData;

// В массиве названий стран
maxDataName = names[j];
names[j] = names[j + 1];
names[j + 1] = maxDataName;

// Вывести состояние синхронизированых массивов после очередной перестановки
printData(names, data);
}
}
}

// Вывести содержимое отсортированных массивов
printData(names, data);
}public static void sayHello(String[] args)
{
// Создаем переменную, хранящую строку для вывода на экран.
// При помощи тернарой операции проверяем, что есть всего 1 аргумент и он равен флагу "-u"
String output = (args.length == 1 && args[0].equals("-u")) ? String.format("Hello, %s!", System.getProperty("user.name")) : "Hello!";
System.out.println(output);
}