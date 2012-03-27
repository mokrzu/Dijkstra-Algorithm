/**
  * Heap class used in dijkstra algorithm.
  */
package dijkstra

/**
  * @author Mokrzu
  *
  */
class Heap(val size: Int) {
	
	// Index of last element in heap.
	var current = size - 1
	var iter = 0
	
	val content = new Array[Hall](size)
	
	def addHall(h: Hall) {
		
		//println("(" + h.x + ", " + h.y + ") = " + h.cost)
		
		h.indexInHeap = iter
		content(iter) = h
		iter += 1
	}
	
	def setFirst(h: Hall) {
		
		var tmp: Hall = content(0)
		content(0) = h
		
		content(h.indexInHeap) = tmp
		
		tmp.indexInHeap = h.indexInHeap
		h.indexInHeap = 0
		h.bestPathCost = h.cost
	}
	
	def deleteMin {

		//println("DEL: " + content(0).cost)
		
		content(0) = content(current)
		current -= 1
		downHeap(0, current + 1)
		downHeap(0, current)
	}
	
	def upHeap(position: Int) {
		
		//println("UP: " + content(position).cost)
		
		var k = position
		var i: Int = (k-1)/2
		var tmp: Hall = content(k)
		
		while(k > 0 && content(i).bestPathCost > tmp.bestPathCost) {
			
			content(k) = content(i)
			content(k).indexInHeap = k
			k = i
			i = (i-2)/2
		}
		
		content(k) = tmp
		content(k).indexInHeap = k
	}
	
	private def downHeap(position: Int, n: Int) {
		
		var k = position
		var j = -1
		var tmp = content(k)
		
		var check = true
		
		while (k < n/2 && check) {
			j = 2*k + 1;
			
			if (j < n-1 && content(j).bestPathCost > content(j+1).bestPathCost) {
				j += 1
			}
			
			if (tmp.bestPathCost <= content(j).bestPathCost) {
				check = false
			}
			
			if (check) {
				content(k) = content(j)
				content(k).indexInHeap = k
				k = j
			}
		}
		
		content(k) = tmp
		content(k).indexInHeap = k		
	}
}
