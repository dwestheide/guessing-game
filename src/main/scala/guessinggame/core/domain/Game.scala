package guessinggame.core.domain

final case class Game private (
    targetNumber: GuessableNumber,
    remainingGuesses: Int
) {
  def processGuess(guess: GuessableNumber): Result = guess match {
    case `targetNumber` => Result.Success
    case _ if isOver    => Result.GameOver
    case n              => Result.Wrong(nextState, hint(n))
  }

  private def nextState: Game =
    if (isOver) this
    else this.copy(remainingGuesses = remainingGuesses - 1)

    private def isOver: Boolean = remainingGuesses <= 1

    private def hint(n: GuessableNumber): Hint =
    if (n > targetNumber) Hint.TooHigh
    else Hint.TooLow
}
object Game {
  def initialState(targetNumber: GuessableNumber): Game =
    Game(targetNumber, remainingGuesses = 5)

    def fromTargetNumber(n: Int): Option[Game] =
    GuessableNumber.fromInt(n).map(initialState)
}
