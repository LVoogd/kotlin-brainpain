package vendingMachine

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.Exception

class VendingMachineItemTest {
    @Test
    fun `it should create the item`() {
        // Act
        val item = VendingMachineItem(
            "10",
            10,
            1_50
        )

        // Assert
        assertSame("10", item.number)
        assertSame(10, item.stock)
        assertSame(1_50, item.price)
    }

    @Test
    fun `it should throw if the identifier is in the wrong format`() {
        // Assert
        assertThrows(Exception::class.java) {
            // Act
            VendingMachineItem(
                "100",
                10,
                1_50
            )
        }

    }
}