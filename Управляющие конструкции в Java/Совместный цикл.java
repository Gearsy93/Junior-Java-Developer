public static void checkWeight(double[] weights)
{
if (!(weights.length == 0)) {
boolean OK = true;

// Обойди массив совместным циклом
for (double element : weights) {
if (element <= 0.045f) {
OK = false;
System.out.println(element);
}
}
if (OK) {
System.out.println("Ok");
}
}
else {
System.out.println("Not enough substance");
}
}