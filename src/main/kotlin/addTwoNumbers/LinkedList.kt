package addTwoNumbers
/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]

https://leetcode.com/problems/add-two-numbers/
 */
class LinkedList<T>(var listNode : ListNode<T>?) {
    companion object {
        fun <T> createFromList(list: List<T>) : LinkedList<T> {
            val result = LinkedList<T>(null)

            var index = list.size - 1

            while(index >= 0) {
                result.prepend(list[index])

                --index
            }

            return result
        }
    }

    fun prepend(item: T) {
        val oldListNode = this.listNode
        this.listNode = ListNode<T>(item, oldListNode)
    }

    fun shift() : T {
        val listNode = this.listNode ?: throw IndexOutOfBoundsException("No more nodes in list.")

        val currentValue = listNode.value
        this.listNode = listNode.next

        return currentValue
    }

    fun isEmpty() : Boolean
    {
        return this.listNode == null
    }

    override fun equals(other : Any?) : Boolean
    {
        if (other !is LinkedList<*>) {
            return false
        }

        if (this.listNode == null && other.listNode == null) {
            return true
        }

        return this.listNode?.equals(other.listNode) ?: false

    }

    override fun hashCode(): Int {
        return listNode?.hashCode() ?: 0
    }
}