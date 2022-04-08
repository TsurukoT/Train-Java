/** 
 * @author Tsuruko Terakawa
 * CS310
 * Fall 2017
 */
import java.util.*;

/**
 * Train class represents a train that can connects multiple cars. It implements the interface Iterable  
 * @param Car - the type of elements held in this class.
 */
class Train implements Iterable<Car> {
 private String name;

 public Train(String name) {
  this.name = name;
  
 }
  /**
   * Head of the train
   */
  private Car head = null;
  /** 
   * Tail of the train
    */
  private Car tail = null;
  /**
   * Size of the train
   */
  private int size = 0;

 /**
  * Get and return the name of this train.
  * @return the name of this train.
  */
 public String getName() {
  return this.name;
 }
 
 /**
  * This will return an iterator that can traverse the train from first car to the last.
  * @return an iterator that traverses the train from first car to the last one. 
  */
 public Iterator<Car> iterator() {
  /**
   * This is an anonymous class that will a Iterator
   */
  return new Iterator<Car>() {
   /**
    * Set a current to traverse the train
    */
   Car current = head;

   /**
    * Required Iterator Method which checks if the traversing reached the end.
    * @return true if traversing reached end, otherwise, false.
    */
   public boolean hasNext(){
    if (current != null){
     return true;
    }
    return false;
   }

   /**
    * Required Iterator Method which goes to the next element.
    * @return the next car in current train.
    */
   public Car next(){
    /**
     * Temp is set to get the current car's name in order to return.
     */
    Car temp = current;
    current = current.getNext();
    return temp;
   }
  };
 }

 
 /**
  * Check if the given object and this train is equal.
  * @param o - the object that will be comapring with this train.
  * @return true if given object and this train is equals, else, false.
  */
 public boolean equals(Object o) {
  if (!(o instanceof Train)){
   return false;
  }
  /**
   * Create a other of type Train with given object to compare equality.
   */
  Train other = (Train) o;
  return (this.name.equals(other.name));
 }
 

 /** 
  * Connect the given car at the end of the car attached to this train. Any cars connected to the Car c
  * will remain connected and also attached to the end of this train.
  * @param c - the car that will be connects with this train.
  */
 public void connectCar(Car c) {
  if (head == null){
   head = c;
   tail = c;
   size++;
  }
  else{
    tail.setNext(c);
    c.setPrevious(tail);
  }
   while (tail.getNext() != null)
   {
     tail = tail.getNext();
     size++;
  }
 }
 
 /**
  * Disconnect the given car from this train. If there is any car attached to the Car c,
  * it will remain attached and will be disconnect with the Car c.
  * @param c - the car that will be disconnects from this train.
  * @return the removed car's name.
  */
 public Car disconnectCar(Car c) {
   /*
    * tempsize to keep track of the size of train after the removal of Car c.
    */
   int tempsize = 0;
   /*
    * returnval will be the Car returned conect with anycar not disconected.
    */
   Car returnval = head;
   /*
    * Car temp to iterate through the train.
    */
   Car temp = head;
   if(head == c){
     tail = head = null;
     size = 0;
     return returnval;
   }
   while(!(temp.equals(c))){
     temp = temp.getNext();
     returnval = returnval.getNext();
     tempsize++;
   }
   if (temp.getPrevious() != null){
     this.tail = temp.getPrevious();
     temp.setPrevious(null);
   }
   if(tail != null){
     temp.setPrevious(null);
     tail.setNext(null);
   }
   if(tail == null){
     temp.setPrevious(null);
     head = tail = null;
   }
   size = tempsize;
   return temp;
 }
 /**
  * Calculate a hashcode for this train.
  * @return the hashcode of this train.
  */
 @Override public int hashCode() {
  return Math.abs(this.name.hashCode());
 }
 
 /*****************************************************************/
 /****************** DO NOT EDIT BELOW THIS LINE ******************/
 /*****************************************************************/
 
 /**
  * Print the visual representation of this train.
  */
 public String toString() {
  String s = getName();
  for(Car c : this) {
   s += " " + c;
  }
  return s;
 }
 
 public void printAscii() {
  /*
  From: http://www.ascii-art.de/ascii/t/train.txt
      o O___ _________
    _][__|o| |O O O O|
   <_______|-|_______|
    /O-O-O     o   o  
  */
  
  System.out.print(String.format("%-4s",getName())+"o O___");
  for(Car c : this) {
   System.out.print(" _________");
  }
  System.out.println();
  
  System.out.print("  _][__|o|");
  for(Car c : this) {
   System.out.print(" | "+String.format("%-5s",c.getName())+" |");
  }
  System.out.println();
  
  System.out.print(" |_______|");
  for(Car c : this) {
   System.out.print("-|_______|");
  }
  System.out.println();
  
  System.out.print("  /O-O-O  ");
  for(Car c : this) {
   System.out.print("   o   o  ");
  }
  System.out.println();
 }
}