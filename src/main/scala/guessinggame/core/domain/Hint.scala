package guessinggame.core.domain

sealed trait Hint
object Hint {
  case object TooHigh extends Hint
  case object TooLow  extends Hint
}
