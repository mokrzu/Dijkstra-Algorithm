/**
  * Museum class.
  */
package dijkstra

/**
  * @author Mokrzu
  *
  */
class Museum(val x: Int, val y: Int) {
	
	// Main board of halls.
	var plan = Array.ofDim[Hall](x,y)
	
	// Heap for dijkstra algorithm.
	val heap = new Heap(x*y)
	
	def print {
		Dijsktra.printPlan(plan)
		// todo: better print function
	}
	
	def setupNeighborhood {
		plan.foreach((array) => 
			array.foreach((hall) =>
				hall.setNeighbors(x, y)))
	}
	
	def reset {
		
		plan.foreach((a) => a.foreach((h) => {
			
			h.bestPathCost = 1000000
			h.visited = false
			h.previous = null
		}))
	}
	
	def answer(c_x: Int, c_y: Int): String = {
		
		var pathTo, pathFrom: String = ""
		var counter: Int = 0
		var temp: Hall = plan(x-1)(0)
		var totalCost: Int = plan(x-1)(0).bestPathCost + 
								plan(0)(y-1).bestPathCost -
								plan(c_x)(c_y).cost
		
		
		while (temp != plan(c_x)(c_y) && temp.previous != null) {
			
			
			if (temp.previous.x > temp.x) {
				pathTo += "S"
			} else if (temp.previous.x < temp.x) {
				pathTo += "N"
			} else if (temp.previous.y > temp.y) {
				pathTo += "E"
			} else {
				pathTo += "W"
			}
			
			if (!temp.visited) {
				counter += 1
			}
			
			temp.visited = true
			temp = temp.previous
		}
		
		//println("----------")
		temp = plan(0)(y-1)
		
		while (temp != plan(c_x)(c_y) && temp.previous != null) {
			
			//println(temp)
			
			if (temp.previous.x > temp.x) {
				pathFrom += "N"
			} else if (temp.previous.x < temp.x) {
				pathFrom += "S"
			} else if (temp.previous.y > temp.y) {
				pathFrom += "W"
			} else {
				pathFrom += "E"
			}			
			
			if (!temp.visited) {
				counter += 1
			}
			
			temp.visited = true
			temp = temp.previous
		}
		
		totalCost + " " + (counter + 1) + " " + pathTo + pathFrom.reverse
	}
}