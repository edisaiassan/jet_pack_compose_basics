sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    fun <T> fold(left: (L) -> T, right: (R) -> T): T {
        return when (this) {
            is Left -> left(value)
            is Right -> right(value)
            else -> throw IllegalStateException("$this")
        }
    }
}
