class Installer implements Subsystem {
private String name;
public String getName() {
return name;
}
public void setName(String name) {
this.name = (name == null || isEmpty(name)) ? "default" : name;
}
protected boolean isEmpty(String s) {
for (int i = 0; i < s.length(); ++i) {
if (!Character.isWhitespace(s.charAt(i))) {
return false;
}
}
return true;
}
private int version;
public int getVersion() {
return this.version;
}
public void setVersion(int version) {
this.version = version < 1 ? 1 : version;
}
private Subsystem[] prerequisites;
public Subsystem[] getPrerequisites() {
return prerequisites;
}
public void setPrerequisites(Subsystem[] prerequisites) {
this.prerequisites = prerequisites == null ? new Subsystem[0] : prerequisites;
}

public Installer(String name, int version, Subsystem[] prerequisites) {
setName(name);
setVersion(version);
setPrerequisites(prerequisites);
}

public Queue<Subsystem> setUpPlan() {
if (getPrerequisites() == null) return null;
var thisPrerequisites = sortPrerequisite(buildQueue(getPrerequisites(), new LinkedList<Subsystem>()));
if (thisPrerequisites != null) {
thisPrerequisites.add(this);
}
return thisPrerequisites;
}

private LinkedList<Subsystem> buildQueue(Subsystem[] prerequisites, LinkedList<Subsystem> history) {
LinkedList<Subsystem> linkedList = new LinkedList<>();
LinkedList<Subsystem> currentHistory = new LinkedList<>(history);
boolean contains = false;
boolean containsCurrent = false;
//Alphabetic Sort
Arrays.sort(prerequisites, Comparator.comparing(Subsystem::getName).reversed());
for (var prerequisite : prerequisites) {
if (prerequisite == null) {
if (history.size() > 0) {
System.out.print(String.format("SetUp plan calculation failed. Wrong prerequisite description at %s %d.", history.getLast().getName(), history.getLast().getVersion()) + System.lineSeparator());
}
return null;
}
//Check if exists in history (cycle)
for (var i : history) {
if (i.getName() == prerequisite.getName() && i.getVersion() == prerequisite.getVersion()) {
System.out.print(String.format("SetUp plan calculation failed. Wrong prerequisite description at %s %d.", prerequisite.getName(), prerequisite.getVersion()) + System.lineSeparator());
return null;
}
}
contains = false;
// Check if this prerequisite already in queue
for (var currentprerequisite : linkedList) {
if (prerequisite.getName() == currentprerequisite.getName() && prerequisite.getVersion() == currentprerequisite.getVersion()) {
contains = true;
}
}
if (!contains) {
currentHistory.add(prerequisite);
if (prerequisite.getPrerequisites() == null) {
System.out.print(String.format("SetUp plan calculation failed. Wrong prerequisite description at %s %d.", prerequisite.getName(), prerequisite.getVersion()) + System.lineSeparator());
return null;
}
Queue<Subsystem> temp = buildQueue(prerequisite.getPrerequisites(), currentHistory);
if (temp == null) return null;
currentHistory.removeLast();
for (var currentprerequisite : temp) {
containsCurrent = false;
for (var currentListPrerequisite : linkedList) {
if (currentprerequisite.getVersion() == currentListPrerequisite.getVersion() && currentprerequisite.getName() == currentListPrerequisite.getName()) {
containsCurrent = true;
}
}
if (!containsCurrent) {
linkedList.add(currentprerequisite);
}
}
linkedList.add(prerequisite);
}
}
return linkedList;
}

public void setUp(Queue<Subsystem> linkedList) {
if (linkedList == null) return;
for (var i : linkedList) {
i.install();
}
}

private Queue<Subsystem> sortPrerequisite(LinkedList<Subsystem> linkedList) {
if (linkedList == null) return null;
HashMap<String, Integer> hashMap = new HashMap<>();
for (var i : linkedList) {
if (hashMap.containsKey(i.getName())) {
hashMap.put(i.getName(), hashMap.get(i.getName()) + 1);
}
else {
hashMap.put(i.getName(), 1);
}
}

for (var i : hashMap.keySet()) {
if (hashMap.get(i) > 1) {
LinkedList<Subsystem> tempList= new LinkedList<>();
for (var j : linkedList) {
if (j.getName() == i) {
tempList.add(j);
}
}
Collections.sort(tempList, (o1, o2) -> {
return o1.getVersion() < o2.getVersion() ? -1 : o1.getVersion() == o2.getVersion() ? 0 : 1;
});
int firstOccastion = -1;
for (int j = 0; j < linkedList.size(); j++){
if (linkedList.get(j).getName() == i) {
firstOccastion = j;
break;
}
}
LinkedList<Subsystem> tempRemoveable = new LinkedList<>(linkedList);
for (var j : tempRemoveable) {
if (j.getName() == i) {
linkedList.remove(j);
}
}
linkedList.addAll(firstOccastion, tempList);
}
}

return linkedList;
}

public void install() {
System.out.print(String.format("%s version %d installed successfully", this.getName(), this.getVersion()) + System.lineSeparator());
}
}