//1. Исходный код класса Consumer
class Consumer {
private String fullName;
public String getFullName() {
return fullName;
}
public void setFullName(String fullName) {
this.fullName = (fullName == null || isEmpty(fullName)) ? "Default" : fullName;
}

private String shortName;
public String getShortName() {
return shortName;
}
public void setShortName(String shortName) {
this.shortName = (shortName == null || isEmpty(shortName)) ? "Default" : shortName;
}

private int country;
public int getCountry() {
return country;
}
public void setCountry(int country) {
this.country = country < 0 ? 0 : country;
}

private String uniqueId;
public String getUniqueId() {
return uniqueId;
}
public void setUniqueId(String uniqueId) {
this.uniqueId = (uniqueId == null || isEmpty(uniqueId)) ? "1" : uniqueId;
}

boolean isEmpty(String s) {
for (int i = 0; i < s.length(); ++i) {
if (!Character.isWhitespace(s.charAt(i))) {
return false;
}
}
return true;
}

public Consumer(String fullName, String shortName, int country, String uniqueId) {
setFullName(fullName);
setShortName(shortName);
setCountry(country);
setUniqueId(uniqueId);
}
}
//2. Исходный код класса Consumers
class Consumers {
private HashSet<Consumer> consumers;
public Consumers() {
consumers = new HashSet<>();
}
public Consumers(ArrayList<List<Consumer>> consumerLists) {
consumers = new HashSet<>();
for (var list : consumerLists) {
consumers.addAll(list);
}
}

public void addConsumer(Consumer consumer) {
if (consumer != null) {
consumers.add(consumer);
}
}

public List<Consumer> getConsumers() {
return List.copyOf(consumers);
}
}