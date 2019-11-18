package guessinggame.core.domain

import minitest.SimpleTestSuite

object GuessableNumberTest extends SimpleTestSuite {
  test("Can't create GuessableNumber from -1") {
    assertEquals(GuessableNumber.fromInt(-1), None)
  }
  test("Can't create GuessableNumber from 0") {
    assertEquals(GuessableNumber.fromInt(0), None)
  }
  test("Can create GuessableNumber from 1") {
    val received = GuessableNumber.fromInt(1).map(_.value)
    assertEquals(received, Some(1))
  }
  test("Can create GuessableNumber from 99") {
    val received = GuessableNumber.fromInt(99).map(_.value)
    assertEquals(received, Some(99))
  }
  test("Can create GuessableNumber from 100") {
    val received = GuessableNumber.fromInt(100).map(_.value)
    assertEquals(received, Some(100))
  }
  test("Can't create GuessableNumber from 101") {
    assertEquals(GuessableNumber.fromInt(101), None)
  }
  test("Can't create GuessableNumber from non-number string") {
    assertEquals(GuessableNumber.fromString(""), None)
  }
  test("Can create GuessableNumber from valid number string") {
    val received = GuessableNumber.fromString("42").map(_.value)
    assertEquals(received, Some(42))
  }
}
