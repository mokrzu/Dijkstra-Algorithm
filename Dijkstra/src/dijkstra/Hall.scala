/**
  * Hall class for Museum.
  */
package dijkstra

/**
  * @author Mokrzu
  *
  */
class Hall(val x: Int, val y: Int, val cost: Int) {
	
	var bestPathCost = 1000000
	var visited: Boolean = false
	var indexInHeap = 0
	var previous: Hall = null
	var neighbors: Array[(Int, Int)] = null
	
	def this() = this(0,0,0)
	
	def setNeighbors(max_x: Int, max_y: Int){
		
		var w, n, e, s: Boolean = false
		var arraySize: Int = 0
		
		if (x - 1 >= 0 ) {
			w = true
			arraySize += 1
		}
		
		if (x + 1 < max_x) {
			e = true
			arraySize += 1
		}
		
		if (y - 1 >= 0) {
			n = true
			arraySize += 1
		}
		
		if (y + 1 < max_y) {
			s = true
			arraySize += 1
		}
		
		neighbors = new Array[(Int, Int)](arraySize)
		var iter: Int = 0
		
		if (w) {
			neighbors(iter) = (x-1, y)
			iter += 1
		}
		
		if (e) {
			neighbors(iter) = (x+1, y)
			iter += 1
		}
		
		if (n) {
			neighbors(iter) = (x, y-1)
			iter += 1
		}
		
		if (s) {
			neighbors(iter) = (x, y+1)
			iter += 1
		}
	}
	
	override def toString(): String = {
		"Hall position: x = " + x + ", y = " + y + ". Cost: " + cost + "."
	}
}