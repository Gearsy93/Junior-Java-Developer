public class Index {

private SortedMap<String, Integer> frequencyTable;
public Index() {
frequencyTable = new TreeMap<>();
}

public Index(String text){
frequencyTable = new TreeMap<>();
appendText(text);
}

public int getWordFrequency(String word) {
Integer freq = frequencyTable.get(word);
return freq == null ? 0 : freq;
}

public void appendText(String text) {
List<String> words = Arrays.stream((text + " ").replaceAll("\\p{IsPunctuation}", "").split(" ")).toList().stream().map(String::toLowerCase).collect(Collectors.toList());
System.out.println(words);
for (var word : words) {
if (frequencyTable.containsKey(word)) {
frequencyTable.put(word, frequencyTable.get(word) + 1);
}
else {
frequencyTable.put(word, 1);
}
}
}

public SortedMap<String, Integer> getFrequencyTable() {
return this.frequencyTable;
}
}