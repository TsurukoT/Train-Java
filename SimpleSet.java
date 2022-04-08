/** 
 * @author Tsuruko Terakawa
 * CS310
 * Fall 2017
 */
import java.util.*;

/**
 * SimpleSet is basically a hash talbe which keeps a set of objects
 */

class SimpleSet<T> {
  /**
   * Tombstone class represents a Tombstone after an element has been removed.
   */
 static class Tombstone { }
 
 /**
  * Initial table size of the simpleset.
  */
 private int tSize = 11;
 /**
  * size will be used to keep track of the items on the table.
  */
 private int size = 0;
 /**
  * table will be used as table.
  */
 private Object [] table = new Object[tSize];
 
 /**
  * Add the provided value to the table based on its hashCode().
  * @param value - the Object which will be added to the table.
  * @return true uppon success, else, false.
  */
 public boolean add(T value) {
   size++;
   if (this.contains(value)){
     size--;
     return false;
   }
   if (value == null){
     return false;
   }
   if (this.getLoad()<=0.7){
     if (this.contains(value)){
       size--;
       return false;
     }
     if ((table[value.hashCode()%tSize] == null) || (table[value.hashCode()%tSize] instanceof Tombstone)){
       table[value.hashCode()%tSize] = value;
       return true;
     }
     else{
       int i = 1;
       while ((table[(value.hashCode() + i)%tSize] != null) && (!(table[(value.hashCode() + i)%tSize] instanceof Tombstone))){
         i++;
       }
       table[(value.hashCode() + i) % tSize] = value;
       return true;
     }
   }
   size--;
   /**
    * newtSize will be new table size after the rehash happens when the load is bigger than 0.7.
    */
   int newtSize = nextPrime(2*tSize);
   rehash(newtSize);
   this.add(value);
   return true;
 }
   
 /**
  * Remove the given value from the table.
  * @param value - the item to be removed from the table.
  * @return true uppon success, else, false.
  */
 public boolean remove(T value) {
   size--;
   if (!(this.contains(value))){
     size++;
     return false;
   }
   int i = 0;
   while((table[(value.hashCode()+i)%tSize] != null) && (!(table[(value.hashCode()+i)%tSize] instanceof Tombstone))){
     if (table[(value.hashCode()+i)%tSize].equals(value)){
       table[(value.hashCode()+i)%tSize] = new Tombstone();
       return true;
     }
     else{
       i++;
     }
   }
   size++;
   return false;
 }

 /**
  * Get and return the value provided from the table.
  * @param value - The value to be checked of if this table contains it.
  * @return value if the value is in the table, else, return null.
  */
 @SuppressWarnings("unchecked")
 public T get(T value) {
   if ((table[value.hashCode()%tSize] != null) && (table[value.hashCode()%tSize].equals(value))){
     return (T) table[value.hashCode()%tSize];
   }
   int i =1;
   while ((table[(value.hashCode() + i) % tSize] != null) && (!(table[(value.hashCode() + i)% tSize] instanceof Tombstone))){
     if (table[(value.hashCode() + i) % tSize].equals(value)){
       return (T) table[(value.hashCode() + i) %tSize];
     }
     else{
       i++;
     }
   }
   return null;
 }

 /**
  * Check if the given value is present in this table.Used the get() method to implement.
  * @param value - The value to be checked if is present in this table.
  * @return true if it is present, else, false.
  */
 public boolean contains(T value) {
   if (this.get(value) == null){
     return false;
   }
   return true;
 }

 /**
  * Rehash the table and the items in it. The table size after rehash will be the value given.
  * @param newCapacity - The size of table that will be rehashed into.
  */
 @SuppressWarnings("unchecked")
 public boolean rehash(int newCapacity) {
   if (newCapacity < 0 || size/newCapacity > 0.7){
     return false;
   }
   int newSize = 0;
   if (tSize < newCapacity){
     newSize = tSize;
   }
   else{
     newSize = newCapacity;
   }
   SimpleSet<T> tempset = new SimpleSet<>();
   //temp.tSize = newCapacity;
   Object [] temp = new Object[newCapacity];
   tempset.table = temp;
   tempset.tSize = newCapacity;
   for (int i = 0; i < size; i++){
     tempset.add ((T) this.valuesToArray()[i]);
   }
   this.size = tempset.size;
   this.table = tempset.table;
   this.tSize = tempset.tSize;
   return true;
 }
  //rehash to a larger table size (specified as a
  //parameter to this method)
  //O(n) where n = the table size
 
 /**
  * Return the number of items on the table.
  * @return the number of items on the table.
  */
 public int size() {
  return size;
 }
 
 /**
  * Calculate the load of the table.
  * @return the load of the table.
  */
 public double getLoad() {
  double s = (double) size;
  return s/tSize;
 }
 
 /**
  * Get the values (except for null and tombstone) from this table and return the values as an array.
  * @return the values (except for null and tombstone) of this table as an array.
  */
 @SuppressWarnings("unchecked")
 public Object[] valuesToArray() {
   Object[] arr = new Object[this.size()];
   int index = 0;
   for (int i = 0; i < tSize; i++){
     if (table[i] != null && (!(table[i] instanceof Tombstone))){
       arr[index] = table[i];
       index++;
     }
   }
   return arr;
 }
 
 /**
  * Get the next prime after provided number.
  * @param x - the number that will be used to get its next prime number.
  * @return the nextPrime of x.
  */
 public int nextPrime(int x) {
  while(true) {
   boolean isPrime = true;
   for(int i = 2; i < Math.sqrt(x); i++) {
    if(x % i == 0) {
     isPrime = false;
     break;
    }
   }
   if(isPrime) return x;
   x++;
  }
 }
 }