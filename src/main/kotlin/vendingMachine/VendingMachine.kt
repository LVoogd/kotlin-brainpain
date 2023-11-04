package vendingMachine

import vendingMachine.exception.VendingMachineItemOutOfStockException
import vendingMachine.stateMachine.StateMachine
import java.lang.Exception

class VendingMachine(private val stateMachine: StateMachine<VendingMachineStates>, private val stock: VendingMachineStock) {
    init {
        if (this.stateMachine.hasState() == false) {
            this.stateMachine.newState(VendingMachineStates.AWAITING_NUMBER)
        }
    }

    var number: String = ""
        private set
    var cash: Int = 0
        private set

    fun insertNumber(integer: Int) {
        if (this.stateMachine.getState() != VendingMachineStates.AWAITING_NUMBER) {
            throw Exception("Can't insert number in current state")
        }

        this.number += integer.toString()

        if (this.stock.hasItemWithNumber(this.number) == false) {
            return
        }

        val item = this.stock.getItemWithNumber(this.number)

        if (item.hasStock() == false) {
            throw VendingMachineItemOutOfStockException.withItem(item)
        }

        this.stateMachine.newState(VendingMachineStates.AWAITING_CASH)
    }

    fun insertCash(amount: Int) {
        if (this.stateMachine.getState() != VendingMachineStates.AWAITING_CASH) {
            throw Exception("Can't insert cash in current state")
        }

        val item = this.stock.getItemWithNumber(this.number)

        this.cash += amount

        if (this.cash < item.price) {
            return
        }

        this.stateMachine.newState(VendingMachineStates.SUFFICIENT_CASH)
    }

    fun reset() {
        this.ejectChange()
        this.number = ""

        this.stateMachine.newState(VendingMachineStates.AWAITING_NUMBER);
    }

    fun confirmPurchase() {
        if (this.stateMachine.getState() != VendingMachineStates.SUFFICIENT_CASH) {
            throw Exception("Can't confirm purchase in current state")
        }

        this.ejectItem()
        this.reset()
    }

    private fun ejectItem() {
        val item = this.stock.getItemWithNumber(this.number)

        item.purchase()

        this.stateMachine.newState(VendingMachineStates.ITEM_EJECTED)
    }

    private fun ejectChange() {
        this.cash = 0
        this.stateMachine.newState(VendingMachineStates.CHANGE_EJECTED)
    }
}
