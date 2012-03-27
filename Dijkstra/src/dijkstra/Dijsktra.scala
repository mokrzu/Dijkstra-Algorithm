/**
  * Dijkstra algorithm.
  */
package dijkstra

/**
  * @author Mokrzu
  *
  */
object Dijsktra {
	
	def printPlan(plan: Array[Array[Hall]]) {
		plan.foreach((array) => array.foreach(println))
	}
	
	// Relax 
	def relax(v: Hall, u: Hall, museum: Museum) {
		
		if (v.bestPathCost > u.bestPathCost + v.cost) {
			
			//println("relax from: " + v.cost + " to " + u.cost)
			v.bestPathCost = u.bestPathCost + v.cost
			v.previous = u
			
			museum.heap.upHeap(v.indexInHeap)
		}
	}
	
	def apply (selected: Hall, museum: Museum) {
		
		selected.bestPathCost = selected.cost
		
		var minimal: Hall = null
		
		while(museum.heap.current >= 0) {
			
			minimal = museum.heap.content(0)
			//println("i'm in: (" + minimal.x + ", " + minimal.y + ") = " + minimal.cost)
			
			minimal.neighbors.foreach((ng) => {
				
				relax(museum.plan(ng._1)(ng._2), minimal, museum)
			})
			
			museum.heap.deleteMin
		}
	}
}