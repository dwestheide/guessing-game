package guessinggame.core.domain

sealed trait Result
object Result {
  final case class Wrong(nextState: Game, targetIsLower: Boolean)
      extends Result
  case object Success  extends Result
  case object GameOver extends Result
}
