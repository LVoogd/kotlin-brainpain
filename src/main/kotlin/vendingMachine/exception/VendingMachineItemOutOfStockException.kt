package vendingMachine.exception

import vendingMachine.VendingMachineItem

class VendingMachineItemOutOfStockException(message : String) : Exception(message) {
    companion object {
        fun withItem(item : VendingMachineItem) : VendingMachineItemOutOfStockException {
            return VendingMachineItemOutOfStockException(
                java.lang.String.format("Item %s is out of stock", item.stock)
            )
        }
    }
}