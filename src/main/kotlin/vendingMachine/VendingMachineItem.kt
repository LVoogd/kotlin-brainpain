package vendingMachine

import java.lang.Exception

data class VendingMachineItem(
    val number : String,
    var stock : Int,
    val price : Int,
) {
   init {
      val numberRegex = Regex("^[0-9].$")

      if (numberRegex.containsMatchIn(this.number) == false) {
         throw Exception("Number should consist of two digits (e.g. 14)")
      }
  }

   fun hasStock(): Boolean {
      return this.stock > 0;
   }

    fun purchase() {
        this.stock--
    }
}
