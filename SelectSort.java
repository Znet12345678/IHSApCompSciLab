import java.util.*;
public class SelectSort{
	private int[] nums;
	private int max,min;
	public SearchSort(int[]nums){
		this.nums = nums;
	}
	public SearchSort(int size){
		nums = new int[size];
	}
	public void iniArrayt(){
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
				high = mid - 1;
			else if(nums[mid] < key)
				low = mid + 1;
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
			high = mid - 1;
		else if(nums[mid] < key)
			low = mid + 1;
		else
			return mid;
		return binarySearchRecursive(key);
	}
	public int bubbleSort(boolean print){
		for(int i = 0; i < nums.length;i++)
			for(int j = i;j < nums.length;j++){
				
			}
	}
}