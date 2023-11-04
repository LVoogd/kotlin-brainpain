package vendingMachine.stateMachine

interface StateMachine<E : Enum<E>> {
    fun getState() : E
    fun newState(newState : E)
    fun hasState() : Boolean
}
