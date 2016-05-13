package org.dzhou.practice.easy;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * For example, Given input array nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new
 * length.
 * 
 * @author zhoudong
 *
 */
public class RemoveDuplicatesFromSortedArray {

	public int removeDuplicates(int[] nums) {
		int i = 0, j = 1;
		while (i < nums.length && j < nums.length) {
			if (nums[i] == nums[j]) {
				j++;
			} else {
				i++;
				nums[i] = nums[j];
				j++;
			}
		}
		return i + 1;
	}

	// optimize the code
	public int removeDuplicates1(int[] nums) {
		int i = 0;
		for (int j = 1; j < nums.length; j++)
			if (nums[i] != nums[j])
				nums[++i] = nums[j];
		return i + 1;
	}

}
