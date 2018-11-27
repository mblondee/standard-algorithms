package sorting.sort;

import java.util.Comparator;

public class Person  {

    private String name;
    private int age;
    private String address;

    public Person(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getAddress(){
        return address;
    }

    public static Comparator compareByAge = new Comparator<Person>(){

        @Override
        public int compare(Person person1, Person person2){
            return person1.getAge() - person2.getAge();
        }

    };

    public static Comparator compareByName = new Comparator<Person>(){

        @Override
        public int compare(Person person1, Person person2){
            return person1.getName().compareTo(person2.getName());
        }


    };

    public static Comparator compareByAddress = new Comparator<Person>(){

        @Override
        public int compare(Person person1, Person person2){
            return person1.getAddress().compareTo(person2.getAddress());
        }


    };



}
