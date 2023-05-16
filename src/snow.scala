import hevs.graphics.TurtleGraphics

import java.awt.Color

class Snowflake(width: Int, height: Int) extends TurtleGraphics(width, height) {
  def drawSnowflake(level: Int, length: Double, angle: Double): Unit = {
    drawSide(level, length, angle) // dessiner les trois cotes du triangle
    turn(120)
    drawSide(level, length, angle)
    turn(120)
    drawSide(level, length, angle)
    turn(120)
  }

  private def drawSide(level: Int, length: Double, angle: Double): Unit = {
    if (level == 0) {
      forward(length)
    } else {
      val segmentLength = length / 3.0

      drawSide(level - 1, segmentLength, angle)
      turn(-angle)
      drawSide(level - 1, segmentLength, angle)
      turn(angle * 2)
      drawSide(level - 1, segmentLength, angle)
      turn(-angle)
      drawSide(level - 1, segmentLength, angle)
    }
  }
}

object SnowflakeApp {
  def main(args: Array[String]): Unit = {
    val width = 800
    val height = 600
    val snowflake = new Snowflake(width, height)
    snowflake.changeColor(Color.BLUE)

    val level = 5
    val length = 300.0
    val angle = 60.0

    snowflake.drawSnowflake(level, length, angle)
  }
}
