package guessinggame.shell

import minitest.SimpleTestSuite

object ApplicationTest extends SimpleTestSuite {
  test("Running the application") {
    val stream = new CapturingPrintStream
    val messagePrinter = new MessagePrinter(stream.out)
    val inputReader: InputReader = _ => "42"
    val randomGenerator = () => 42
    val application = new Application(messagePrinter, inputReader, randomGenerator)
    application.run()
    // println(stream.capturedOutput)
  }
}
