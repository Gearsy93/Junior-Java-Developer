public static void sort(int[][] data) {
// Если массив null
if (data == null) {
System.out.println("Empty data");
return;
}
for (int i = 0; i < data.length; i++) {
// Если очередной элемент массива null
if (data[i] == null) {
System.out.println("Empty data at index " + i);
return;
}
// Если очередной элемент массива содержит не 3 элемента
else if (data[i].length != 3) {
System.out.println("Unavailable data at index " + i);
return;
}
}

// Сортировка пузырьком
int[] maxData;
// Всего data.length -1 раз обойти массивы
for(int i = 1; i < data.length; ++i) {
// Каждый раз проверяем на 1 пару меньше
for(int j = 0, max; j < data.length - i; ++j) {
// Если текущий больше следующего, меняем местами
// Сортировка по номеру клиента
if (data[j][0] < data[j + 1][0]) {
maxData = data[j];
data[j] = data[j + 1];
data[j + 1] = maxData;
}
}
}
}