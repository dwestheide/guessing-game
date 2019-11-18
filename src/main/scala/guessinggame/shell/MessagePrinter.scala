package guessinggame.shell

import guessinggame.core.domain.GuessableNumber
import guessinggame.core.presentation.Presentation

import java.io.PrintStream

private[shell] class MessagePrinter(out: PrintStream = System.out) {
  def showIntro(): Unit = out.println(Presentation.introduction)

  def showErrorMessage(): Unit = out.println(Presentation.invalidNumber)

  def showSuccessMessage(
      targetNumber: GuessableNumber
  ): Unit =
    out.println(Presentation.successMessage(targetNumber))

  def showGameOverMessage(): Unit = out.println(Presentation.gameOver)

  def showHint(
      guess: GuessableNumber,
      targetIsLower: Boolean
  ): Unit = out.println(Presentation.hint(guess, targetIsLower))

  def showProblemWithSetup(): Unit = println(Presentation.problemWithSetup)
}
