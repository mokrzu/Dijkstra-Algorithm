/**
  * Main class of Dijkstra program.
  */
package dijkstra

/**
  * @author Mokrzu
  *
  */
object Main {

	def main(args: Array[String]): Unit = {
		
		val test = new Museum(2,2)
		test.plan(0)(0) = new Hall
		test.plan(0)(1) = new Hall(1,1,19)
		test.plan(1)(0) = new Hall(2,2,42)
		test.plan(1)(1) = new Hall(3,3,108)
		
		test.print
	}
}