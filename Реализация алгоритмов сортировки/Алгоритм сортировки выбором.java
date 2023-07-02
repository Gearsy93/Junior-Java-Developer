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

// Максимальное значение объема продаж, соответствующее название страны
double maxData;
String maxCountry;
for (int i = data.length-1, maxId; i >0 ; i--) {
maxId = 0;
maxData = data[maxId];
maxCountry = names[maxId];

for (int j = 1; j <= i; j++) {

// Если текущее значение массива больше текущего максимального, текущему максимальному приравнивается текущее значение массива
if (data[j]>maxData) {

// Для объема продаж
maxData = data[j];
// Синхронизация массива названий стран
maxCountry = names[j];
maxId = j;
}
}

// Дошли до начала отсортированной части, меняем местами текущее максимальное значение с последним неотсортированным элементом
data[maxId] = data[i];
data[i] = maxData;
// Синхронизация массива названий стран
names[maxId] = names[i];
names[i] = maxCountry;

// Вывести содержимое отсортированных массивов после двунаправленной перестановки
printData(names, data);
}

// Вывести содержимое отсортированных массивов
printData(names, data);
}