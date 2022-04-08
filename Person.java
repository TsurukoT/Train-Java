/** 
 * @author Tsuruko Terakawa
 * CS310
 * Fall 2017
 */
import java.util.*;

/**
 * Person class represents each person. Each has name and can gets the car 
 * they are on currently.
 * @param name - the name of the person
 * @param currentCar - the can the person currently on
 */

class Person {
 /**
  * A string representation of this person.
  */
 private String name = "";
 /**
  * The car this person is on currently.
  */
 private Car currentCar = null;

 /**
  * Constructor of Person class.
  * Creates a new person with the given name and put the person on currentCar
  * @param name - the name of this person
  * @param currentCar - the car this person is on
  */

 public Person(String name, Car currentCar) {
  this.name = name;
  this.currentCar = currentCar;
 }
 
 /**
  * Get the name of this student.
  * @return this person's name.
  */
 public String getName() {
  return this.name;
 }
 
 /**
  * Get the current car this student is on.
  * @return this person's car he/she is on currently.
  */
 public Car getCurrentCar() {
  return this.currentCar;
 }
 
 /**
  * Moves a person frmo his current car to the car passed in as a parameter.
  * @param c - the car this person will be moving to 
  * @return trun if the person moved succesfully, false if currentCar and provided car is not adjacent.
  */
 public boolean moveToCar(Car c) {
  if ((c.getPrevious() == this.getCurrentCar()) || (c.getNext() == this.getCurrentCar())){
    this.currentCar = c;
    return true;
  }

  return false;
}
 /**
  * Checks if given object and this person is same.
  * @param o - the object to be checked if equals with this person. 
  * @return true if they are same, otherwise, return false.
  */
 public boolean equals(Object o) {
  if (!(o instanceof Person)){
   return false;
  }
  Person other = (Person) o;
  if (!(this.name.equals(other.name))){
    return false;
  }
  //Uses the equals method in Car class
  if (!(this.currentCar.equals(other.currentCar))){
    return false;
  }
  return true;
 }
 
 /**
  * Gets the unique hashCode for given person.
  * @return the unique hashcode this person.
  */
 public int hashCode() {
  //uses the java default hashCode method.
  return Math.abs(this.name.hashCode());
 }
 
 /**
  * Return the string representation of this person.
  * @return the string representation of this person.
  */
 public String toString() {
  return this.name;
 }
}