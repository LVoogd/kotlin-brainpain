package vendingMachine

import kotlin.Exception

data class VendingMachineStock(
    val items: List<VendingMachineItem>
) {
    fun getItemWithNumber(number: String): VendingMachineItem {
        for (item in this.items) {
            if (item.number != number) {
                continue
            }

            return item
        }

        throw Exception(java.lang.String.format("Item with number %s does not exist!", number))
    }

    fun hasItemWithNumber(number: String): Boolean {
        try {
            this.getItemWithNumber(number)
        } catch(exception : Exception) {
            return false;
        }

        return true
    }
}
