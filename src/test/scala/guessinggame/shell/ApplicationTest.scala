package guessinggame.shell

import minitest.SimpleTestSuite
import scala.io.Source

object ApplicationTest extends SimpleTestSuite {
  test("Running the application") {
    val stream                   = new CapturingPrintStream
    val messagePrinter           = new MessagePrinter(stream.out)
    val inputReader: InputReader = _ => "42"
    val randomGenerator          = () => 42
    val application =
      new Application(messagePrinter, inputReader, randomGenerator)

    application.run()

    val expectedOutput = Source
      .fromURL(getClass().getResource("/expected-output.txt"))
      .getLines()
      .mkString("\n")

    assertEquals(stream.capturedOutput, expectedOutput)
  }
}
