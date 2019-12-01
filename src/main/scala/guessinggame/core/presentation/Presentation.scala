package guessinggame.core.presentation

import guessinggame.core.domain._

object Presentation {
  val introduction: String =
    """
    ____                     _                ____
    / ___|_   _  ___  ___ ___(_)_ __   __ _   / ___| __ _ _ __ ___   ___
   | |  _| | | |/ _ \/ __/ __| | '_ \ / _` | | |  _ / _` | '_ ` _ \ / _ \
   | |_| | |_| |  __/\__ \__ \ | | | | (_| | | |_| | (_| | | | | | |  __/
    \____|\__,_|\___||___/___/_|_| |_|\__, |  \____|\__,_|_| |_| |_|\___|
                                      |___/

     #I just came up with a number between 1 and 100 (inclusive)!
     #If you guess it, you'll win!
     #If you're wrong, I will tell if it's higher or lower.
     #You have five attempts. Good luck!""".stripMargin('#')

  val prompt: String = "\n> "

  val invalidNumber: String =
    "\nPlease enter a valid number between 1 and 100."

  def successMessage(number: GuessableNumber): String =
    s"\nThat's damn right! ${number.value} it is!"

  def hint(guess: GuessableNumber, hint: Hint): String = {
    val comparator = hint match {
      case Hint.TooHigh => "lower"
      case Hint.TooLow  => "higher"
    }
    s"""
    |Your guess, ${guess.value}, is wrong.
    |The target number is ${comparator} than that.""".stripMargin
  }

  val gameOver: String =
    """
    ____                                            _
    / ___| __ _ _ __ ___   ___    _____   _____ _ __| |
   | |  _ / _` | '_ ` _ \ / _ \  / _ \ \ / / _ \ '__| |
   | |_| | (_| | | | | | |  __/ | (_) \ V /  __/ |  |_|
    \____|\__,_|_| |_| |_|\___|  \___/ \_/ \___|_|  (_)

    """

  val problemWithSetup: String =
    "Problem setting up the game. Will exit. So sorry! :("
}
