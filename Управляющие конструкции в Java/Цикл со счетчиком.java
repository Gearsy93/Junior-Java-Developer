public static void checkWeight(double[] weights)
{
float SumWeight = 0.0f;

// Обойди массив циклом с параметром
for (int i = 0; i < weights.length; i++) {
SumWeight += (float)weights[i];
}

// Получить точное значение суммарного веса
float ceilResult = Float.parseFloat(String.format("%.7f", SumWeight).replace(",", "."));
float average = weights.length == 0 ? 0.0f : ceilResult / weights.length;
System.out.println(average <= 0.045f ? "Not enough substance" : "Ok");
}