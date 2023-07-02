public static void checkWeight(double[] weights)
{
int i = 0;
float SumWeight = 0.0f;

do {
// Если массив пустой, сразу выводим сообщение о недостатке веществ
if (weights.length == 0) {
System.out.println("Not enough substance");
return;
}
// Иначе добавляем вес текущего итерируемого вещество
SumWeight += (float)weights[i];
} while (++i < weights.length);

// Получить точное значение суммарного веса
float ceilResult = Float.parseFloat(String.format("%.7f", SumWeight).replace(",", "."));
float average = ceilResult / weights.length;
System.out.println(average <= 0.045f ? "Not enough substance" : "Ok");
}