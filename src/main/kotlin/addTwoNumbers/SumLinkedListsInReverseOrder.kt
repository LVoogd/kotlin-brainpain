package addTwoNumbers

class SumLinkedListsInReverseOrder {
    operator fun invoke(linkedList1 : LinkedList<Int>, linkedList2 : LinkedList<Int>): LinkedList<Int> {
        val resultList = mutableListOf<Int>()
        var sum = 0

        while (linkedList1.isEmpty() == false || linkedList2.isEmpty() == false) {

            sum += if (linkedList1.isEmpty()) 0 else linkedList1.shift()
            sum += if (linkedList2.isEmpty()) 0 else linkedList2.shift()

            // The sum exceeded a single digit, a leftover is created for the following iteration
            if (sum > 9) {
                resultList.add(sum - 10)
                sum = 1
            } else {
                resultList.add(sum)
                sum = 0
            }
        }

        // There is an unprocessed leftover, we should add it as the last node
        if (sum != 0) {
            resultList.add(sum)
        }

        return LinkedList.createFromList(resultList)
    }
}