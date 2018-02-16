import java.util.*;
public class Insertion{
		public static void insertionSort(double []arr,int prefix){
			int i = 1;
			while(i < prefix){
				int j = i;
				while(j > 0 && arr[j-1] > arr[j]){
					swap(arr,j,j-1);
					j--;
				}
				i++;
			}
		}
		public static void swap(double []a,int b,int c){
			double sv = a[b];
			a[b] = a[c];
			a[c] = sv;
		}
		public static boolean isSorted( double[] array )
		{
			boolean inOrder = true;

			for ( int j=0; j<array.length-1 && inOrder; j++ )
			{
					inOrder = array[j] <= array[j+1];      
			}
    
			return inOrder;
		}
		public static double Median(double[] arr){
			return arr[arr.length/2];
		}
		public static double Average(double[] arr){
			double sum = 0;
			for(int i = 0; i < arr.length;i++)
				sum+=arr[i];
			return sum/arr.length;
		}
		public static double MedianUnsorted(double []arr){
			return QuickSelect(arr,arr.length/2);
		}
		public static double QuickSelect(double _arr[],int n){
			double arr[] = _arr.clone();
			for(int i = 0; i < n;i++){
				int minIndex = i;
				double minValue = arr[i];
				for(int j = i + 1; j < arr.length-1;i++){
					if(arr[j] < minValue){
						minIndex = j;
						minValue = arr[j];
						swap(arr,i,minIndex);
					}
				}
			}
			return arr[n];
		}
		public static void insertionSortRight(double arr[]){
			int i = arr.length-1;
			while(i >= 0){
				int j = i;
				while(j < arr.length-1 && arr[j] > arr[j+1]){
					swap(arr,j,j+1);
					j++;
				}
				i--;
			}
		}
		public static void main(String[] args){
			System.out.printf("Enter size of the array:");
			Scanner s = new Scanner(System.in);
			int size = s.nextInt();
			Random r = new Random();
			double []arr = new double[size];
			for(int i = 0; i < size;i++)
				arr[i] = size* r.nextDouble();
			s.close();
			System.out.printf("Before: %s\n", isSorted(arr) ? "SORTED" : "NOT sorted");
			long startTime = System.currentTimeMillis();
			insertionSort(arr,size);
			long endTime = System.currentTimeMillis();
			System.out.printf("Sort took %d seconds\n",endTime-startTime);
			System.out.printf("Before: %s\n", isSorted(arr) ? "SORTED" : "NOT sorted");
			insertionSortRight(arr);
			if(arr.length <= 15)
				System.out.printf("%s\n",Arrays.toString(arr));
		}
}