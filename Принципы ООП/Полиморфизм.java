//1. Исходный код класса Client
public class Client {
String lastName;
String firstName;
String secondName;
int amountOfBoughtDrawings;
int fortune;
Client(String lastName, String firstName, String secondName, int amountOfBoughtDrawings, int fortune) {
this.lastName = lastName == null ? "default" : lastName;
this.firstName = firstName == null ? "client" : firstName;
this.secondName = secondName == null ? "absolute" : secondName;
this.amountOfBoughtDrawings = amountOfBoughtDrawings < 0 ? 0 : amountOfBoughtDrawings;
this.fortune = fortune < 0 ? 0 : fortune;
}

public String preferredStyleOfDrawings() {
return "No preferred style";
}

@Override
public String toString() {
return String.format(
"Name: %s {%s %s\n" +
"\n" +
"Amount of bought drawings: %d\n" +
"\n" +
"Fortune: %d\n" +
"\n" +
"Style: %s}",
this.lastName, this.firstName, this.secondName, this.amountOfBoughtDrawings, this.fortune, preferredStyleOfDrawings()
);
}
}
//2. Исходный код класса Impressionist
class Impressionist extends Client {
Impressionist(String lastName, String firstName, String secondName, int amountOfBoughtDrawings, int fortune) {
super(lastName, firstName, secondName, amountOfBoughtDrawings, fortune);
}

@Override
public String preferredStyleOfDrawings() {
return "I prefer impressionism";
}
}
//3. Исходный код класса Cubist
class Cubist extends Client {
Cubist(String lastName, String firstName, String secondName, int amountOfBoughtDrawings, int fortune) {
super(lastName, firstName, secondName, amountOfBoughtDrawings, fortune);
}
@Override
public String preferredStyleOfDrawings() {
return "I prefer cubism";
}
}