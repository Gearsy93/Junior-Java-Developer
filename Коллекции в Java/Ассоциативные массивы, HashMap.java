class Customer {
class Client {
private int id;
public int getId() {
return id;
}

private String name;
public String getName() {
return name;
}

private String phone;
public String getPhone() {
return phone;
}

private double purchaseCount;
public double getPurchaseCount() {
return purchaseCount;
}

public Client(int id, String name, String phone, double purchaseCount) {
this.id = id < 0 ? 0 : id;
this.name = (name == null || isEmpty(name)) ? "DefaultName" : name;
this.phone = (name == null || isEmpty(phone)) ? "88005553535" : phone;
this.purchaseCount = purchaseCount < 0 ? 0 : purchaseCount;
}
}

boolean isEmpty(String s) {
for (int i = 0; i < s.length(); ++i) {
if (!Character.isWhitespace(s.charAt(i))) {
return false;
}
}
return true;
}

public static Map<Integer, Client> load(Client[] clients) {
Map<Integer, Client> result = new HashMap<>();
for (var i : clients)
{
result.put(i.id, i);
}
return result;
}

public static Client getById(Map<Integer, Client> clients, int id) {
return clients.get(id);
}
}