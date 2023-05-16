import hevs.graphics.FunGraphics
import sun.security.util.Length

import java.awt
import java.awt.{Color, Polygon}
import scala.math.{cos, sin}
import scala.util.Random
import scala.util.Random.nextInt
class Tree(){
  var angleTurtle = 0.0
  var graphics = new FunGraphics(500,300)
  var currentPositionx = 250
  var currentPositiony= 250
  var counter = 0
  var originaln =40
  def drawBranch(length: Double): Unit = {
    val newX = currentPositionx + sin(angleTurtle.toRadians) * length
    val newY = currentPositiony - cos(angleTurtle.toRadians) * length
    graphics.drawLine(currentPositionx, currentPositiony, newX.toInt, newY.toInt)
    currentPositionx = newX.toInt
    currentPositiony = newY.toInt
    counter += 1
  }

  def drawTree(n: Int, length: Double): Unit = {
      if (n > 1) {
        drawBranch(length)
        val savedPositionx = currentPositionx
        val savedPositiony = currentPositiony
        val savedAngle = angleTurtle

        angleTurtle = -45.0
        drawTree(n - 1, length / 2)

        currentPositionx = savedPositionx
        currentPositiony = savedPositiony
        angleTurtle = savedAngle

        angleTurtle = 45.0
        drawTree(n - 1, length / 2)

        currentPositionx = savedPositionx
        currentPositiony = savedPositiony
        angleTurtle = savedAngle
      } else {
        drawBranch(length)
        drawLeavesRecursive(originaln)
      }
    }
  def drawleaves(n : Int):Polygon={
    val polygon = new Polygon()
    if (n > 0) {
      val noise = 10 // quanto rumore aggiungere
      val centerX = currentPositionx
      val centerY = currentPositiony
      val size = n / 3 // grandezza delle foglie
      for (i <- 0 until 15) {
        val x = centerX + (Random.nextInt(n) - n / 2 + Random.nextInt(noise) - noise / 2)
        val y = centerY + (Random.nextInt(n) - n / 2 + Random.nextInt(noise) - noise / 2)
        polygon.addPoint(x, y)
      }
      polygon.addPoint(centerX - size, centerY - size)
      polygon.addPoint(centerX + size, centerY - size)
      polygon.addPoint(centerX, centerY)

      // aggiungi i punti alle estremità laterali del poligono
      val lateralSize = size / 2
      polygon.addPoint(centerX - size, centerY)
      polygon.addPoint(centerX - lateralSize, centerY + lateralSize)
      polygon.addPoint(centerX - lateralSize, centerY - lateralSize)
      polygon.addPoint(centerX + lateralSize, centerY + lateralSize)
      polygon.addPoint(centerX + lateralSize, centerY - lateralSize)
      polygon.addPoint(centerX + size, centerY)
    }
    polygon
  }

  def drawLeavesRecursive(n: Int): Unit = {
    println(n)
    if (n > 0) {
      graphics.drawFilledPolygon(drawleaves(n-1), Color.PINK)
      graphics.drawFilledPolygon(drawleaves(n-1), Color.WHITE)
      drawLeavesRecursive(n - 1)
    }
  }

  def draw(): Unit = {
    graphics.clear()
    drawTree(9, 100)
    originaln = 90
  }
}

object Test extends App {
  val tree = new Tree()
  tree.draw()
}
