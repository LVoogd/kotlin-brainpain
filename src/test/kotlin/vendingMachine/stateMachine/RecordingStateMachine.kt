package vendingMachine.stateMachine

class RecordingStateMachine<E : Enum<E>> : HistoryTrackingStateMachine<E>  {
    private val recordedStates: MutableList<E> = mutableListOf<E>()

    override fun getState() : E {
        return this.recordedStates.last
    }

    override fun newState(newState : E) {
        this.recordedStates.add(newState)
    }

    override fun hasState(): Boolean {
        return this.recordedStates.isNotEmpty()
    }

    override fun getHistory(): List<E> {
        return this.recordedStates.toList()
    }

    fun assertRecordedStateSequence(expectedStates : List<E>) {
        if (expectedStates.size != this.getHistory().size) {
            throw Exception(java.lang.String.format(
                "Expected sequence %s, got %s", expectedStates.toString(), this.getHistory().toString()
            ))
        }

        for (i in expectedStates.indices) {
            if (expectedStates[i] != this.getHistory()[i]) {
                throw Exception(java.lang.String.format(
                    "Expected sequence %s, got %s", expectedStates.toString(), this.getHistory().toString()
                ))
            }
        }
    }
}
