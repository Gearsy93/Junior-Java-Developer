public static void checkWeight(double[] weights)
{
int i = 0;
float SumWeight = 0.0f;
// Циклом посчитать общий вес веществ
while (i < weights.length) {
SumWeight += (float)weights[i];
i++;
}
// Получить точное значение суммарного веса
float ceilResult = Float.parseFloat(String.format("%.7f", SumWeight).replace(",", "."));

// Если веществ нет, средний вес - 0, иначе разделить сумму на чисто веществ
float average = weights.length == 0 ? 0.0f : ceilResult / weights.length;
System.out.println(average <= 0.045f ? "Not enough substance" : "Ok");
}