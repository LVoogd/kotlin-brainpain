package twoSum

import java.lang.Exception
import java.lang.RuntimeException

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Explanation: Because 2 [1] and 4 [4] add up to 6

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
Explanation: Because 3 [0] + 3 [1] = 6

https://leetcode.com/problems/two-sum/
 */

class IndicesOfTwoNumbers {
    operator fun invoke(numbers: List<Int>, target: Int) : List<Int> {
        for (current in numbers.indices) {
            for (competitor in numbers.indices) {
                if (current == competitor) {
                    continue
                }

                if (numbers[current] + numbers[competitor] != target) {
                    continue
                }

                return listOf(numbers[current], numbers[competitor])
            }
        }

        // Not found
        throw RuntimeException("Could not find the two numbers that add up to the target in the given list.");
    }
}