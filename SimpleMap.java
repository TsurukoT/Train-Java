/** 
 * @author Tsuruko Terakawa
 * CS310
 * Fall 2017
 */
import java.util.*;
/**
 * This class acts as a map to map the key and the value together.
 */
class SimpleMap<K,V> {
 /**
  * Pair class has a key and a value and they are mapped together.
  */
 private static class Pair<K,V> {
   /**
    * key is the key of the pair.
    */
   private K key;
   /*
    * value is the value of the pair.
    */
   private V value;
  /**
   * Constructor of the Pair class.
   * @param key - the key of the pair.
   * @param value - the value of the pair.
   */
  private Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }
  /**
   * Checks equality of the given object with this Pair. Equality is checked with the key.
   * @param o - the object to be checked with equality.
   * @return true if the object and this pair is equal, else, return false.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (!(o instanceof Pair)){
      return false;
    }
    Pair other = (Pair) o;
    if (this.key.equals( other.key)){
      return true;
    }
    return false;
  }
  
  /**
   * Return the hashCode of this pair based on its key.
   * @return the hashcode of the Pair based on its key.
   */
  public int hashCode() {
    return Math.abs(this.key.hashCode());
  }
  
  /**
   * Return the String representation of the Pair.
   * @return the String representation of the Pair.
   */
  public String toString() {
   //this method is done for you
   return "<" + getKey() + "," + getValue() + ">";
  }
  
  /**
   * Return the Key of this Pair.
   * @return the Key of this Pair.
   */
  public K getKey() {
   return this.key;
  }
  /**
   * Return the Value of this Pair.
   * @return the value of this Pair.
   */
  public V getValue() {
   return this.value;
  }
 }
 
 
 /*****************************************************************/
 /****************** DO NOT EDIT BELOW THIS LINE ******************/
 /*****************************************************************/
 
 /**
  * set is the field of the SimpleMap.
  */
 private SimpleSet<Pair<K,V>> set = new SimpleSet<>();
 
 /**
  * Add the Pair with given key and value to the set.
  * @param key - the Key of the Pair that will be added to the set.
  * @param value - the value of the Pair that will be added to the set.
  * @return true if success, else, false.
  */
 public boolean add(K key, V value) {
   /**
    * Create a pair with given key and value which will be added to the set.
    */
  Pair<K,V> pair = new Pair<>(key, value);
  return set.add(pair);
 }

 public boolean update(K key, V value) {
  Pair<K,V> pair = new Pair<>(key, value);
  if(!remove(key)) {
   return false;
  }
  return set.add(pair);
 }
 /**
  * Remove the Pair with given key and value from the set.
  * @param key - the key of the Pair that will be removed from the set.
  * @param value - the value of the Pair that will be removed from the set.
  * @return true if success, else, false.
  */
 @SuppressWarnings("unchecked")
 public boolean remove(K key) {
  Pair<K,V> pair = new Pair<>(key, null);
  return set.remove(pair);
 }
 /**
  * Return the value of Pair with given key.
  * @param the key of the value that will be returned.
  * @return the value of the key provided.
  */
 @SuppressWarnings("unchecked")
 public V getValue(K key) {
  Pair<K,V> pair = new Pair<>(key, null);
  return set.get(pair).getValue();
 }
 /**
  * Rehash the set.
  * @param newCapacity - the new set's size that will be rehashed into.
  * @return true if success, else, false.
  */
 @SuppressWarnings("unchecked")
 public boolean rehash(int newCapacity) {
  return set.rehash(newCapacity);
 }
 
 /**
  * Return the number of items in the set.
  * @return the number of items in the set.
  */
 public int size() {
  return set.size();
 }
 /**
  * Get the load of the set.
  * @return the load of the set.
  */
 public double getLoad() {
  return set.getLoad();
 }
 
 /**
  * Get the values excluding null and tombston from the set and put them into the array and return the array.
  *@return the array of all values except for null and tombston in the set.
  */
 @SuppressWarnings("unchecked")
 public Object[] valuesToArray() {
  Object[] setValues = set.valuesToArray();
  Object[] arr = new Object[setValues.length];
  
  for(int i = 0; i < arr.length; i++) {
   arr[i] = ((Pair<K,V>)setValues[i]).getValue();
  }
  
  return arr;
 }
}