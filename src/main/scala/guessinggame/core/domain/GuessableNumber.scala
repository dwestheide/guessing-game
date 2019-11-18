package guessinggame.core.domain

final class GuessableNumber private (val value: Int) extends AnyVal {
  def >(other: GuessableNumber): Boolean = value > other.value
  def <(other: GuessableNumber): Boolean = value < other.value
}

object GuessableNumber {
  def fromInt(n: Int): Option[GuessableNumber] = n match {
    case x if x <= 0  => None
    case x if x > 100 => None
    case _            => Some(new GuessableNumber(n))
  }
  def fromString(s: String): Option[GuessableNumber] =
    for {
      n      <- s.toIntOption
      number <- GuessableNumber.fromInt(n)
    } yield number
}
