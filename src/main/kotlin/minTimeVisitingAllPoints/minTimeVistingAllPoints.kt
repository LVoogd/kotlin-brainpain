package minTimeVisitingAllPoints

/**
 * On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi]. Return the minimum time in seconds to visit all the points in the order given by points.
 *
 * You can move according to these rules:
 *
 * In 1 second, you can either:
 * move vertically by one unit,
 * move horizontally by one unit, or
 * move diagonally sqrt(2) units (in other words, move one unit vertically then one unit horizontally in 1 second).
 * You have to visit the points in the same order as they appear in the array.
 * You are allowed to pass through points that appear later in the order, but these do not count as visits.
 *
 * https://leetcode.com/problems/minimum-time-visiting-all-points/
 */
class minTimeVistingAllPoints {
    operator fun invoke(points : List<Point>) : Int {
        var shortestPathWeight = 0

        // Loop through all points except the last (this one doesn't need to travel)
        for (i in 0 .. (points.size - 2)) {
            val travelingPoint = points[i];
            val destinationPoint = points[i + 1];

            while (travelingPoint.equals(destinationPoint) == false) {
                if (travelingPoint.x != destinationPoint.x) {
                    if (travelingPoint.x < destinationPoint.x) travelingPoint.x++ else travelingPoint.x--
                }

                if (travelingPoint.y != destinationPoint.y) {
                    if (travelingPoint.y < destinationPoint.y) travelingPoint.y++ else travelingPoint.y--
                }

                shortestPathWeight++
            }
        }

        return shortestPathWeight;
    }
}

data class Point(var x : Int, var y : Int) {
}
