// Вызов result[j] = findDebitum(services, debitor[j]);
public static double findDebitum(double[] services, double[] debitor) {
double sum = 0.0;
for (int k = 0; k < debitor.length; k++) {
sum += services[k] * debitor[k];
}
return sum;
}