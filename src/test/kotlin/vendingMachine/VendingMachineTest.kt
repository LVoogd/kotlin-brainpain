package vendingMachine

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import vendingMachine.exception.VendingMachineItemOutOfStockException
import vendingMachine.stateMachine.RecordingStateMachine
import java.lang.Exception

class VendingMachineTest {

    private lateinit var vendingMachine: VendingMachine
    private lateinit var stateMachine: RecordingStateMachine<VendingMachineStates>

    @BeforeEach
    fun setup() {
        this.stateMachine = RecordingStateMachine()
        this.vendingMachine = VendingMachine(
            this.stateMachine,
            VendingMachineStock(
                listOf(
                    VendingMachineItem("10", 10, 1_00),
                    VendingMachineItem("15", 10, 1_20),
                    VendingMachineItem("22", 0, 2_30),
                )
            )
        )
    }

    @Test
    fun `it should accept numbers`() {
        // Act
        vendingMachine.insertNumber(1)
        vendingMachine.insertNumber(0)

        // Assert
        assertEquals("10", vendingMachine.number)
    }

    @Test
    fun `it should update the state if the given numbers correlate with an item`() {
        // Act
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(0)

        // Assert
        assertSame(VendingMachineStates.AWAITING_CASH, this.stateMachine.getState())
    }

    @Test
    fun `it should deny numbers in a different state`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(0)

        // Assert
        assertThrows(Exception::class.java) {
            // Act
            this.vendingMachine.insertNumber(1)
        }
    }

    @Test
    fun `it should throw when the chosen item is not in stock`() {
        // Assert
        assertThrows(VendingMachineItemOutOfStockException::class.java) {
            // Act
            this.vendingMachine.insertNumber(2)
            this.vendingMachine.insertNumber(2)
        }
    }

    @Test
    fun `it should accept cash`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(5)

        // Act
        this.vendingMachine.insertCash(1_00)
        this.vendingMachine.insertCash(50)

        // Assert
        assertEquals(1_50, this.vendingMachine.cash)
    }

    @Test
    fun `it should not accept cash in the wrong state`() {
        // Assert
        assertThrows(Exception::class.java) {
            // Act
            this.vendingMachine.insertCash(1_00)
        }
    }

    @Test
    fun `it should update the state when sufficient cash is inserted`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(5)

        // Act
        this.vendingMachine.insertCash(5_00)

        // Assert
        assertSame(VendingMachineStates.SUFFICIENT_CASH, this.stateMachine.getState())
    }

    @Test
    fun `it should update to the initial state on reset`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(5)

        this.vendingMachine.insertCash(10_00)

        // Act
        this.vendingMachine.reset()

        // Assert
        assertEquals(VendingMachineStates.AWAITING_NUMBER, this.stateMachine.getState())
        assertEquals("", this.vendingMachine.number)
        assertEquals(0, this.vendingMachine.cash)
    }

    @Test
    fun `it should wipe number on reset`() {
        // Arrange
        this.vendingMachine.insertNumber(9)

        // Act
        this.vendingMachine.reset()

        // Assert
        assertSame("", this.vendingMachine.number)
    }

    @Test
    fun `it should eject cash on reset`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(5)

        this.vendingMachine.insertCash(100_00)

        // Act
        this.vendingMachine.reset()

        // Assert
        assertSame(0, this.vendingMachine.cash)

        this.stateMachine.assertRecordedStateSequence(listOf(
            VendingMachineStates.AWAITING_NUMBER,
            VendingMachineStates.AWAITING_CASH,
            VendingMachineStates.SUFFICIENT_CASH,
            VendingMachineStates.CHANGE_EJECTED,
            VendingMachineStates.AWAITING_NUMBER,
        ))


    }

    @Test
    fun `it should eject the item on purchase`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(0)

        this.vendingMachine.insertCash(1_00)

        // Act
        this.vendingMachine.confirmPurchase()

        // Assert
        this.stateMachine.assertRecordedStateSequence(listOf(
            VendingMachineStates.AWAITING_NUMBER,
            VendingMachineStates.AWAITING_CASH,
            VendingMachineStates.SUFFICIENT_CASH,
            VendingMachineStates.ITEM_EJECTED,
            VendingMachineStates.CHANGE_EJECTED,
            VendingMachineStates.AWAITING_NUMBER,
        ))
    }

    @Test
    fun `it should eject change on purchase`() {
        // Arrange
        this.vendingMachine.insertNumber(1)
        this.vendingMachine.insertNumber(0)

        this.vendingMachine.insertCash(5_00)

        // Act
        this.vendingMachine.confirmPurchase()

        // Assert
        assertSame(0, this.vendingMachine.cash)
    }

    @Test
    fun `it should throw when trying to purchase in the wrong state`() {
        // Assert
        assertThrows(Exception::class.java) {
            // Act
            this.vendingMachine.confirmPurchase()
        }
    }
}
