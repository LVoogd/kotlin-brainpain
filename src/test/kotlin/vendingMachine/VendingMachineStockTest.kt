package vendingMachine

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.lang.Exception

class VendingMachineStockTest {

    private lateinit var stock : VendingMachineStock

    @BeforeEach
    fun setUp() {
        this.stock = VendingMachineStock(listOf(
            VendingMachineItem("10", 2, 1_50)
        ))
    }

    @Test
    fun `it should retrieve the item if a correct number is given`() {
        // Act
        val item = this.stock.getItemWithNumber("10")

        // Assert
        assertEquals("10", item.number)
    }

    @Test
    fun `it should throw if the number given does not correspond with an item`() {
        // Assert
        assertThrows(Exception::class.java) {
            // Act
            this.stock.getItemWithNumber("9")
        }
    }

    @Test
    fun `it should return false if number does not correspond with an item`() {
        // Act
        val hasItem = this.stock.hasItemWithNumber("9")

        // Assert
        assertFalse(hasItem)
    }

    @Test
    fun `it should return true if number does correspond with an item`() {
        // Act
        val hasItem = this.stock.hasItemWithNumber("10")

        // Assert
        assertTrue(hasItem)
    }
}