package guessinggame.core.domain

import minitest.SimpleTestSuite
import scala.annotation.tailrec
import guessinggame.core.domain.Result._

object GameTest extends SimpleTestSuite {
  test("Created game has 5 remaining guesses") {
    val received = Game.fromTargetNumber(50).map(_.remainingGuesses)
    assertEquals(received, Some(5))
  }
  test("Created game uses passed in target number") {
    val validTargetNumbers = Seq(1, 23, 99, 100)
    validTargetNumbers.foreach { n =>
      val received = Game.fromTargetNumber(n).map(_.targetNumber.value)
      assertEquals(received, Some(n))
    }
  }
  test("Game cannot be created from invalid target numbers") {
    val invalidTargetNumbers = Seq(-1, 0, 101)
    invalidTargetNumbers.foreach { n =>
      assertEquals(Game.fromTargetNumber(n), None)
    }
  }

  test("Processing guess leads to game over after five attempts") {
    val (game, guess) = setupTest(target = 50, guess = 1)
    val endResult     = performGuess(game, guess, 5)
    assertEquals(endResult, GameOver)
  }
  test("Processing correct guess leads to success") {
    val (game, guess) = setupTest(target = 50, guess = 50)
    val endResult     = performGuess(game, guess, 1)
    assertEquals(endResult, Success)
  }
  test("Processing guess that is too high") {
    val (game, guess) = setupTest(target = 50, guess = 51)
    val endResult     = performGuess(game, guess, 1)
    assertWrong(endResult, _.targetIsLower)
  }
  test("Processing guess that is too low") {
    val (game, guess) = setupTest(target = 50, guess = 49)
    val endResult     = performGuess(game, guess, 1)
    assertWrong(endResult, wrong => !wrong.targetIsLower)
  }

  private def setupTest(target: Int, guess: Int): (Game, GuessableNumber) =
    (createGame(target), createNumber(guess))

  private def createNumber(n: Int): GuessableNumber =
    GuessableNumber
      .fromInt(n)
      .getOrElse(sys.error("Guessable number must be between 1 and 100"))

  private def createGame(targetNumber: Int): Game =
    Game
      .fromTargetNumber(targetNumber)
      .getOrElse(sys.error("Target number must be between 1 and 100"))

  private def assertWrong(result: Result, check: Wrong => Boolean): Unit =
    result match {
      case wrong: Wrong => assert(check(wrong))
      case other        => fail(s"Result is not Wrong, but $other")
    }

  @tailrec
  private def performGuess(
      game: Game,
      guess: GuessableNumber,
      attempts: Int
  ): Result =
    if (attempts > 1) {
      game.processGuess(guess) match {
        case res @ (Success | GameOver) => res
        case Wrong(nextState, _) =>
          performGuess(nextState, guess, attempts - 1)
      }
    } else game.processGuess(guess)
}
