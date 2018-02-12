import java.util.*;
public class Sorting
{
 
  // Determine if an array is sorted in ascending order
  //
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
  public static int countOut(int [] array){
	boolean inOrder = true;
    int ret = 0;
    // scan the list starting at index 0
    for ( int j=0; j<array.length-1; j++ )
    {
      // check the pair starting at j
      inOrder = array[j] <= array[j+1];      
	  if(!inOrder)
		  ret++;
    }
    return ret;
  }
  public static double percentSorted(int[] array){
	  double percent = 100;
	  for(int i = 0; i < array.length-1;i++){
		  if(array[i] > array[i+1]){
			  percent-=100.0/(new Double(array.length-1));
		  }
	  }
	  return percent;
  }
  public static void swap(int a, int b, int[] x){
	  int s = x[a];
	  x[a] = x[b];
	  x[b] = s;
  }
  	public static void insertionSort(int nums[]){
		int i = 1;
		while(i < nums.length){
			int j = i;
			while(j > 0 && nums[j-1] > nums[j]){
				swap(j,j-1,nums);
				j--;
			}
			i++;
		}
	}
  public static void main ( String[] args )
  {
    int[] values = {12, 23, -5, 56, 32, 17, -4, -49, -1, -23, 45 };
    
    // print out the array
    System.out.println("values: "); 
    for ( int val : values )
      System.out.print( val + ", " );
    
    System.out.println( );
	insertionSort(values);
    // determine if it is in order
    if ( isSorted(values) )
      System.out.println( "Is in order" );
    else
      System.out.println( "Is NOT in order" );
	System.out.printf("%d %f\n",countOut(values),percentSorted(values));
   }
}