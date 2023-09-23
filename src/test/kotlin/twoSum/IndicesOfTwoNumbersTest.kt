package twoSum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class IndicesOfTwoNumbersTest {
    @ParameterizedTest
    @MethodSource("validCasesProvider")
    fun itShouldReturnIndicesOfTheTwoNumbersSuchThatTheyAddUpToTarget(
        numbers: List<Int>,
        target: Int,
        expectedResult: List<Int>
    ) {
        // Arrange
        val indicesOfTwoNumbers = IndicesOfTwoNumbers()

        // Act
        val result = indicesOfTwoNumbers.invoke(numbers, target)

        // Assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun itShouldThrowAnExceptionIfTheNumbersDontAddUpToTarget()
    {
        // Arrange
        val indicesOfTwoNumbers = IndicesOfTwoNumbers()

        // Act
        val exception = assertThrows(RuntimeException::class.java) {
            indicesOfTwoNumbers(listOf(2,5,6), 10)
        }

        // Assert
        assertSame("Could not find the two numbers that add up to the target in the given list.", exception.message)
    }

    companion object {
        @JvmStatic
        private fun validCasesProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(2, 7, 11, 15), 9, listOf(2, 7),
                ),
                Arguments.of(
                    listOf(3,2,4), 6, listOf(2,4),
                ),
                Arguments.of(
                    listOf(3,3), 6, listOf(3,3),
                )
            )
        }
    }
}