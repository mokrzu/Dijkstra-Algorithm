/**
  *
  */
package dijkstra

/**
  * @author Mokrzu
  *
  */

import java.util.Scanner
import java.io.File

import org.junit._
import Assert._

class Tests {

	@Test def testTwo() {

		for (i <- 0 to 9) {
			
			println("Test nr: " + i)
			
			val fileOne = new File("file.txt")
			val scannerOne = new Scanner(fileOne)

			val in_path = new File("data" + i + ".txt")
			val out_path = new File("out" + i + ".txt")

			val scanner = new Scanner(in_path)
			val scannerOut = new Scanner(out_path)

			var max_x = scanner.nextInt()
			var max_y = scanner.nextInt()
			var museum = new Museum(max_x, max_y)
			var backup = Array.ofDim[Hall](max_x, max_y)
			var temp: Int = 0

			for (x <- 0 to max_x - 1) {
				for (y <- 0 to max_y - 1) {
					temp = scanner.nextInt()
					backup(x)(y) = new Hall(x, y, temp)
				}
			}

			var s_x = scanner.nextInt() - 1
			var s_y = scanner.nextInt() - 1
			var selected: Hall = null
			var result: String = ""

			while (s_x != -1 || s_y != -1) {

				museum = new Museum(max_x, max_y)

				for (x <- 0 to max_x - 1) {
					for (y <- 0 to max_y - 1) {
						museum.plan(x)(y) = backup(x)(y)
						museum.heap.addHall(museum.plan(x)(y))
					}
				}

				museum.setupNeighborhood
				museum.reset

				selected = museum.plan(s_x)(s_y)

				museum.heap.setFirst(selected)

				Dijsktra(selected, museum)

				result = museum.answer(s_x, s_y)
				println(result)

				assertEquals(result, scannerOut.nextLine())

				s_x = scanner.nextInt() - 1
				s_y = scanner.nextInt() - 1
				println("======")
			}

			museum = new Museum(max_x, max_y)

			for (x <- 0 to max_x - 1) {
				for (y <- 0 to max_y - 1) {
					museum.plan(x)(y) = backup(x)(y)
					museum.heap.addHall(museum.plan(x)(y))
				}
			}

			museum.setupNeighborhood
			museum.reset

			selected = museum.plan(0)(max_y - 1)

			museum.heap.setFirst(selected)

			Dijsktra(selected, museum)

			println(museum.answer(0, max_y - 1))
		}

	}

}