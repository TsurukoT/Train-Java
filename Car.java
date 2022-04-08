/** 
 * @author Tsuruko Terakawa
 * CS310
 * Fall 2017
 */
import java.util.*;

/**
 * Car class represents each car in a train. Can be accessed with the name and can access the previous car and next car.
 * @param name - the name of this car. 
 * @param next - the next train of this car.
 * @param prev - the previous train of this car.
 */
class Car {
  /*
   * String name to save the car's name.
   */
  private String name = "";
  /*
   * Car next is a pointer to this car's next car.
   */
  private Car next;
  /*
   * Car prev is a pointer to this car's previous car.
   */
  private Car prev;
  /**
   * Constructor of Car class.Creates a car with given name.
   * @param name - the name of this car.
   */
 public Car(String name) {
   this.name = name;
 }

 /**
  * Head of the train
  */
 private Car head;
 /** 
  * Tail of the train
   */
 private Car tail;
 /**
  * Size of the train
  */
 private int size = 0;

 /**
  * Get the next car of this car.
  * @return the next car from this one. If this car was the last one, return null.
  */
 public Car getNext() {
   if (this == null || this.next == null)
   {
     return null;
   }
   else
   {
     return this.next;
   }
 }

 /**
  * Get the previous car of this car.
  * @return the previous car for this car. If this car was the first one, return null.
  */
 public Car getPrevious() {

   return this.prev;
 }
 
 /**
  * Set the next car of this car to the given car.
  * @param next - the car that will be the next car of this car.
  */
 public void setNext(Car next) {
   this.next = next;
 }
 
 /**
  * Set the previous car of this car to the given car.
  * @param previous - the car that will be the previous car of this car.
  */
 public void setPrevious(Car previous) {
   this.prev = previous;
 }
 
 /**
  * Get the name of this car.
  * @return the name of this car.
  */
 public String getName() {
   return this.name;
 }
 
 /**
  * Check if the given object is equal to this Car.
  * @param o - the object to be checked if equals to this car. 
  * @return true if they are equals otherwise, false.
  */
 @Override public boolean equals(Object o) {
  if ((o instanceof Car)== false){
    return false;
  }
  /*
   * Create a Car other with the provided object in other to compare the specific values of car.
   */
  Car other = (Car) o;
  if (!(this.name.equals(other.name))){
    return false;
  }
  return true;
 }
 
 /**
  * Return the Hash Code of this car based on its name.
  * @return the Hash Code of this car.
  */
 @Override public int hashCode() {
  return Math.abs(this.name.hashCode());
}
 
 /**
  * Return the name of this car.
  * @return the string representation of the car.
  */
 public String toString() {
  return this.name;
 }
 
 
 /*****************************************************************/
 /****************** DO NOT EDIT BELOW THIS LINE ******************/
 /*****************************************************************/
 
 /**
  * Print the visual representation of this car.
  */
 public void printAscii() {
  /*
  From: http://www.ascii-art.de/ascii/t/train.txt
   _________
   |O O O O|
  -|_______|
     o   o  
  */
  
  Car current = this;
  while(current != null) {
   System.out.print(" _________");
   current = current.getNext();
  }
  System.out.println();
  
  current = this;
  while(current != null) {
   System.out.print(" | "+String.format("%-5s",current.getName())+" |");
   current = current.getNext();
  }
  System.out.println();
  
  current = this;
  while(current != null) {
   System.out.print("-|_______|");
   current = current.getNext();
  }
  System.out.println();
  
  current = this;
  while(current != null) {
   System.out.print("   o   o  ");
   current = current.getNext();
  }
  System.out.println();
 }
}