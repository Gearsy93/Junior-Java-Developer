class Task {
public static double[] findAllWinners(double[] times) {
if (times == null) {
return new double[]{};
}
else if (times.length == 0) {
return times;
}
else {
Arrays.sort(times);
double currentmax = Array.getDouble(times, times.length - 1);
int counter = 1;
for (int i = times.length - 2; i >= 0; i--) {
if (Array.getDouble(times, i) != currentmax) {
currentmax = Array.getDouble(times, i);
counter++;
}
if (counter == 3) {
if (!(i != 0 && times[i - 1] == currentmax)) {
return Arrays.copyOfRange(times, i, times.length);
}
}
}
return times;
}
}
public static int peopleChoiceAward(double[] times, int winners) {
if (times == null || times.length == 0 || winners < 0 || winners > times.length) return -1;
Arrays.sort(times);
double[] nonWinners = Arrays.copyOfRange(times, 0, times.length - winners);
if (!Arrays.asList(nonWinners).contains(1500.0)) return -1;
return Arrays.binarySearch(nonWinners, 1500.0);
}
}