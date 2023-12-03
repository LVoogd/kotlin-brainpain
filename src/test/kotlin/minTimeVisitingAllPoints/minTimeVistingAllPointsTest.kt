package minTimeVisitingAllPoints

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class minTimeVistingAllPointsTest {
    @ParameterizedTest
    @MethodSource("validCasesProvider")
    fun `it should calculate the shortest route between all points in given order`(input : List<Point>, shortestRouteWeight : Int) {
        // Assert
        val minTimeVisitingAllPoints = minTimeVistingAllPoints()

        // Act
        val result = minTimeVisitingAllPoints(input)

        // Assert
        assertSame(shortestRouteWeight, result)

    }
    companion object {
        @JvmStatic
        fun validCasesProvider() : Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(Point(1,1), Point(1,2)), 1
                ),
                Arguments.of(
                    listOf(Point(1,1), Point(2,1)), 1
                ),
                Arguments.of(
                  listOf(Point(1,1), Point(2,2)), 1
                ),
                Arguments.of(
                    listOf(Point(1,1), Point(3,4), Point(-1,0)), 7
                ),
                Arguments.of(
                    listOf(Point(3,2), Point(-2,2)), 5
                ),
                Arguments.of(
                    listOf(Point(1,1)), 0
                ),
            )
        }
    }
}