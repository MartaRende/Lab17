class Hanoi {
  def hanoi(n: Int, start: Char, aux: Char, end: Char): Unit ={
    if (n == 1) {
      println(s"Moving disk 1 from $start to $end")
    } else {
      hanoi(n - 1, start, end, aux)
      println(s"Moving disk $n from $start to $end")
      hanoi(n - 1, aux, start, end)
    }
  }
}
object Task1 extends App{
  val hanoi = new Hanoi()
  println(hanoi.hanoi(3,'a','b','c'))
}
