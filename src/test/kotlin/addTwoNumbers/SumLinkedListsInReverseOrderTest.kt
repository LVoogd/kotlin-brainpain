package addTwoNumbers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SumLinkedListsInReverseOrderTest {
    @ParameterizedTest
    @MethodSource("validCasesProvider")
    fun itShouldSumTheLists(linkedList1 : LinkedList<Int>, linkedList2: LinkedList<Int>, expectedResult: LinkedList<Int>)
    {
        // Arrange
        val sumLinkedListsInReverseOrder = SumLinkedListsInReverseOrder()

        // Act
        val result = sumLinkedListsInReverseOrder(linkedList1, linkedList2)

        // Assert
        assertTrue(expectedResult == result)
    }

    companion object {
        @JvmStatic
        fun validCasesProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    LinkedList.createFromList<Int>(listOf(2,4,3)),
                    LinkedList.createFromList<Int>(listOf(5,6,4)),
                    LinkedList.createFromList<Int>(listOf(7,0,8)),
                ),
                Arguments.of(
                    LinkedList.createFromList<Int>(listOf(0)),
                    LinkedList.createFromList<Int>(listOf(0)),
                    LinkedList.createFromList<Int>(listOf(0)),
                ),
                Arguments.of(
                    LinkedList.createFromList<Int>(listOf(9,9,9,9,9,9,9)),
                    LinkedList.createFromList<Int>(listOf(9,9,9,9)),
                    LinkedList.createFromList<Int>(listOf(8,9,9,9,0,0,0,1)),
                ),
                Arguments.of(
                    LinkedList.createFromList<Int>(listOf(0,0,5)),
                    LinkedList.createFromList<Int>(listOf(0,0,5)),
                    LinkedList.createFromList<Int>(listOf(0,0,0,1)),
                ),
                Arguments.of(
                    LinkedList.createFromList<Int>(listOf(0,0,5)),
                    LinkedList.createFromList<Int>(listOf(0,0,0,5)),
                    LinkedList.createFromList<Int>(listOf(0,0,5,5)),
                ),
            )
        }
    }


}