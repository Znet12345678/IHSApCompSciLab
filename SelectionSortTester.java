import java.util.*;
public class SelectionSortTester
{
 
  // Sort the Array
  //
  public static void selectionSort( int[] array )
  {
    
    // Find the integer that should go in each cell j of
    // the array, from cell 0 to the end
    for ( int j=0; j<array.length-1; j++ )
    {
      // Find min: the index of the integer that should go into cell j.
      // Look through the unsorted integers (those at j or higher)
      int min = j;
      for ( int k=j+1; k<array.length; k++ )
        if ( array[k] < array[min] ) min = k;  

      // Swap the int at j with the int at min 
      int temp = array[j];
      array[j] = array[min];
      array[min] = temp;
    }
  
  }
public static boolean isSorted( int[] array )
  {
    boolean inOrder = true;
    
    // scan the list starting at index 0
    for ( int j=0; j<array.length-1 && inOrder; j++ )
    {
      // check the pair starting at j
      inOrder = array[j] <= array[j+1];      
    }
    
    return inOrder;
  }
  public static int countDuplicates(int [] array){
	  int ret = 0;
	  int i = 0;
	  while(i < array.length-1){
		if(array[i] == array[i+1]){
			ret++;
			while(i < array.length-1 && array[i] == array[++i]);
		}else
			i++;
	  }
	  return ret;
  }
  public static int countDuplicatesUnsorted(int [] array){
	int [] sorted = array.clone();
	selectionSort(sorted);
	return countDuplicates(sorted);
  }
  public static void main ( String[] args )
  {
    int SIZE;
	System.out.printf("Enter a size:");
	Scanner s = new Scanner(System.in);
	SIZE = s.nextInt();
	boolean print = SIZE <= 15;
    // print out the array
    System.out.println("initial values: "); 
	int values[] = new int[SIZE];
	Random r = new Random();
	for(int i = 0; i < values.length;i++){
		values[i] = r.nextInt(SIZE);
	}
	System.out.printf("%d\n",countDuplicatesUnsorted(values));
	if(print){
		for ( int val : values )
		System.out.print( val + ", " ); 
	}
    // sort the array
	long startTime = System.currentTimeMillis();
    selectionSort( values );
	long endTime = System.currentTimeMillis();
	System.out.printf("Total Execution Time %d ms\n",endTime-startTime);
    // print out the array
    System.out.println("\n\nsorted values: "); 
	if(print)
		for ( int val : values )
			System.out.print( val + ", " ); 
	else
		System.out.printf("%b\n",isSorted(values));
    System.out.println( );
	System.out.printf("%d\n",countDuplicates(values));
	startTime = System.currentTimeMillis();
	selectionSort(values);
	endTime = System.currentTimeMillis();
	System.out.printf("Execution took %d ms the second time\n",endTime-startTime);
   }
}