public static void replace(String input) {
if (input == null || input == "") {
System.out.println("");
}
else {
String answer = "";
// Обойти каждый символ начальной строки
for (int i = 0; i < input.length(); i++) {
// Если это не звездочка
if (input.charAt(i) != '*') {
// Добавить в строку для вывода на экран
answer += input.charAt(i);
}
}
System.out.println(answer);
}
}