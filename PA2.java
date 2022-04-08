
import java.util.*;
import java.io.*;
/**
 * This class contains the main code to interact with the simulator. It will prompt
 * for the cases and murderLocation and the time to get the suspectNames.
 */
class PA2 {
 /**
  * Main code to get the murder suspects with the simulator and will print the suspect names.
  * @param takes arguments from the user.
  */

 public static void main(String[] args) {
  String trainFile = args[0];
  String peopleFile = args[1];
  
  try {
  	/**
  	 * Starts a new simulator with given files.
  	 */
   Simulator s = new Simulator(new File(trainFile), new File(peopleFile));
   /**
    * suspects will be the the value returned from simulate in Simulator class.
    */
   SimpleSet<Person> suspects = s.simulate(args[2], args[3]);
   if(suspects.size() == 0) {
    System.out.println("No suspects!");
   }
   else {
    Object[] people = suspects.valuesToArray();
    System.out.println("Suspects:");
    for(Object p : people) {
     System.out.println(p);
    }
   }
  }
  catch(IOException e) {
   System.out.println("Invalid file");
  }
  catch(RuntimeException e) {
   System.out.println(e.getMessage());
   e.printStackTrace();
  }
 }
}







