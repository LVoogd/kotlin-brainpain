package vendingMachine.stateMachine;

interface HistoryTrackingStateMachine<E : Enum<E>> : StateMachine<E> {
    fun getHistory() : List<E>
}
