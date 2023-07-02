//2.1. Исходный код класса Animal.
class Animal {
private String regio; // домен, к которому принадлежит семейство
public String getRegio() {
return regio;
}
protected void setRegio(String regio) {
this.regio = regio;
}
public Animal(String regio) {
this.regio = regio;
}

}
//2.2. Исходный код класса Cat.
class Cat extends Animal {
private String regnum; // царство, к которому принадлежит семейство
public String getRegnum() {
return regnum;
}
private String phylum; // тип, к котрому принадлежит семейство
public String getPhylum() {
return phylum;
}
private String classis; // класс, к которому принадлежит семейство
public String getClassis() {
return classis;
}
private String ordo; // отряд, к которому принадлежит семейство
public String getOrdo() {
return ordo;
}
public Cat(String regio, String regnum, String phylum, String classis, String ordo){
//1
super(regio);
this.regnum = regnum;
this.phylum = phylum;
this.classis = classis;
this.ordo = ordo;
}
}
//2.3. Исходный код класса Species
public class Species extends Cat {
private String nameOfSpecie; // название вида
public String getNameOfSpecie() {
return nameOfSpecie;
}
private String subfamilia; // подсемейство, к которому принадлежит данный вид
public String getSubfamilia() {
return subfamilia;
}
private String genus; // род, к которому принадлежит данный вид
public String getGenus() {
return genus;
}
public Species(String regio, String regnum, String phylum, String classis, String ordo, String subfamilia, String genus){
super(regio, regnum, phylum, classis, ordo);
this.subfamilia = subfamilia;
this.genus = genus;
}
}
//2.4. Исходный код класса Main
import animals.Species;
public class Main
{
public static void main(String[] args) {
Species lion = new Species("Eukaryota", "Animalia", "Chordata", "Mammalia", "Carnivora", "Pantherinae", "Panthera");
}
}