package guessinggame.shell

import guessinggame.core.domain._
import guessinggame.core.presentation.Presentation

import scala.io.StdIn
import scala.util.Random
import scala.annotation.tailrec

private[shell] class Application(
    messagePrinter: MessagePrinter = new MessagePrinter,
    inputReader: InputReader = prompt => StdIn.readLine(prompt),
    randomGenerator: () => Int = () => Random.nextInt(99) + 1
) {
  def run(): Unit = {
    messagePrinter.showIntro()
    loop(launchOrExit())
  }

  @tailrec
  private def loop(game: Game): Unit = {
    import messagePrinter._
    readGuess(Presentation.prompt) match {
      case None =>
        showErrorMessage()
        loop(game)
      case Some(guess) =>
        game.processGuess(guess) match {
          case Result.GameOver => showGameOverMessage()
          case Result.Success  => showSuccessMessage(game.targetNumber)
          case Result.Wrong(nextState, targetIsLower) =>
            showHint(guess, targetIsLower)
            loop(nextState)
        }
    }
  }

  private def readGuess(prompt: String): Option[GuessableNumber] =
    GuessableNumber.fromString(inputReader(prompt))

  private def launchOrExit(): Game =
    Game.fromTargetNumber(randomGenerator()).getOrElse {
      messagePrinter.showProblemWithSetup()
      sys.exit(-1)
    }
}

object Application {
  def main(args: Array[String]): Unit = {
    val app = new Application()
    app.run()
  }
}
