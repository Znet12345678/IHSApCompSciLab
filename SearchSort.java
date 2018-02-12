import java.util.*;
public class SearchSort{
	private int[] nums;
	private int max,min;
	public SearchSort(int[]nums){
		this.nums = nums;
		min = 0;
		max = nums.length-1;
	}
	public SearchSort(int size){
		nums = new int[size];
		min = 0;
		max = nums.length-1;
		initArray();
	}
	public int []getNums(){
		return nums;
	}
	public void initArray(){
		Random r = new Random();
		for(int i = 0; i < nums.length;i++){
			nums[i] = r.nextInt(1001);
		}
	}
	public int linearSearch(int key){
		for(int i = 0; i < nums.length;i++)
			if(nums[i] == key)
				return i;
		return -1;
	}
	public void swap(int a,int b){
		int sv = nums[a];
		nums[a] = nums[b];
		nums[b] = sv;
	}
	public int binarySearch(int key){
		int max = nums.length-1;
		int min = 0;
		while(min <= max){
			int mid = (max + min)/2;
			if(nums[mid] > key)
				max = mid - 1;
			else if(nums[mid] < key)
				min = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	public int binarySearchRecursive(int key){
		if(min > max)
			return -1;
		int mid = (min+max)/2;
		if(nums[mid] > key)
			max = mid - 1;
		else if(nums[mid] < key)
			min = mid + 1;
		else
			return mid;
		return binarySearchRecursive(key);
	}
	public void bubbleSort(boolean print){
		for(int i = 0; i < nums.length;i++){
			for(int j = i+1;j < nums.length;j++){
				if(nums[i] > nums[j]){
					swap(i,j);
				}
			}
			if(print)
				System.out.printf("%s\n",Arrays.toString(nums));
		}
	}
	public void selectionSort(boolean print){
		for(int j = 0; j < nums.length;j++){
			int iMin = j;
			for(int i = j+1;i < nums.length;i++){
				if(nums[i] < nums[iMin])
					iMin = i;
			}
			if(iMin != j)
				swap(j,iMin);
			if(print)
				System.out.println(Arrays.toString(nums));
		}
	}
	public void insertionSort(boolean print){
		int i = 1;
		while(i < nums.length){
			int j = i;
			while(j > 0 && nums[j-1] > nums[j]){
				swap(j,j-1);
				j--;
			}
			if(print){
				System.out.printf("%s\n",Arrays.toString(nums));
			}
			i++;
		}
	}
	public void mergeSort(int l,int r,boolean print){
		if(l < r){
			int m = l+(r-l)/2;
			mergeSort(l,m,print);
			mergeSort(m+1,r,print);
			merge(l,m,r);
			if(print)
				System.out.printf("%s\n",Arrays.toString(nums));
		}
	}
	public void merge(int l,int m,int r){
		int i,j,k;
		int n1 = m-l + 1;
		int n2 = r-m;
		int L[] = new int[n1];
		int R[] = new int[n2];
		for(i = 0; i < n1;i++)
			L[i] = nums[l+i];
		for(j = 0; j < n2;j++)
			R[j] = nums[m + 1 + j];
		i = 0;
		j = 0;
		k = l;
		while(i < n1 && j < n2){
			if(L[i] <= R[j])
				nums[k++] = L[i++];
			else nums[k++]=R[j++];
		}
		while(i < n1)
			nums[k++]=L[i++];
		while(j < n2)
			nums[k++]=R[j++];
	}
}