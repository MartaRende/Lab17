
import hevs.graphics.{FunGraphics, TurtleGraphics}

class TurtleGraphic(  val graphics : TurtleGraphics){
  graphics.forward(50)
  graphics.turn(120)
  graphics.forward(50)
  graphics.turn(120)
  graphics.forward(50)
  def circle(r:Int):Unit={
    var p = 2*r*Math.PI
    val l = p / 24
    for(i<-0 to 24){
      graphics.forward(r)
      graphics.turn(360/24)

    }
  }
}
object Task3 extends App{
  var g = new TurtleGraphic(graphics = new TurtleGraphics(400,400))
  g.circle(20)


}

