package vendingMachine

enum class VendingMachineStates {
    AWAITING_NUMBER,
    AWAITING_CASH,
    SUFFICIENT_CASH,
    ITEM_EJECTED,
    CHANGE_EJECTED,
}