public static void sortStoresSales(String[] names, double[] data) {
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

// Иницилизируем левые и правые части для обоих массив (длины попарно совпадают)
double[] leftArrayData = new double[data.length / 2];
double[] rightArrayData = new double[data.length - leftArrayData.length];
String[] leftArrayShops = new String[leftArrayData.length];
String[] rightArrayShops = new String[rightArrayData.length];

// Заполняем левые и правые части по порядку (в левые части первые половины, в правые части - оставшиеся элементы)
for (int i = 0; i < data.length; i++) {
if(i < leftArrayData.length) {
leftArrayData[i] = data[i];
leftArrayShops[i] = names[i];
} else {
rightArrayData[i - leftArrayData.length] = data[i];
rightArrayShops[i - leftArrayShops.length] = names[i];
}
}

// Рекурсивно разбиваем до длины 1
if(rightArrayData.length>1) {
sortStoresSales(leftArrayShops, leftArrayData);
sortStoresSales(rightArrayShops, rightArrayData);
}

int leftId = 0;
int rightId = 0;

// Последовательно сливаем левые и правые части обоих массивов
for (int i = 0; i < data.length; i++) {
// Если остался последний элемент в правой части
if(leftId==leftArrayData.length && rightId<rightArrayData.length) {
// Указатель не инкрементируем сразу
names[i] = rightArrayShops[rightId];
data[i] = rightArrayData[rightId++];
}
// Если остался последний элемент в левой части
else if (rightId==rightArrayData.length && leftId<leftArrayData.length) {
names[i] = leftArrayShops[leftId];
data[i] = leftArrayData[leftId++];
}
// Иначе сравниваем текущие элементы по текущим позициям
else
if (leftArrayData[leftId]<rightArrayData[rightId]) {
names[i] = leftArrayShops[leftId];
data[i] = leftArrayData[leftId++];
} else {
names[i] = rightArrayShops[rightId];
data[i] = rightArrayData[rightId++];
}
}
printData(names, data);
}