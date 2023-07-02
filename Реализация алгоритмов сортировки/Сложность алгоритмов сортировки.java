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

//Алгритм быстрой сортировки
quickSort(data, names, 0, data.length - 1);

// Вывод результата сортировки 1 раз
printData(names, data);
}

public static void quickSort(double data[], String names[], int begin, int end) {
if (begin < end) {
// Индекс отсортированного pivot
int partitionIndex = partition(data, names, begin, end);

// Рекурсивные вызовы для левой и правой частей
quickSort(data, names, begin, partitionIndex-1);
quickSort(data, names, partitionIndex+1, end);
}
}

public static int partition(double data[], String names[], int begin, int end) {
// Разделитель
double pivot = data[end];
int i = (begin - 1);

// Все элементы меньше pivot слева, а больше - справа
for (int j = begin; j < end; j++) {
if (data[j] <= pivot) {
i++;

double swapTempData = data[i];
data[i] = data[j];
data[j] = swapTempData;
String swapTempString = names[i];
names[i] = names[j];
names[j] = swapTempString;
}
}

double swapTempData = data[i+1];
data[i+1] = data[end];
data[end] = swapTempData;
String swapTempString = names[i + 1];
names[i + 1] = names[end];
names[end] = swapTempString;

return i+1;
}