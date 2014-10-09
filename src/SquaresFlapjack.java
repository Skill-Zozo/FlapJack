/*  
	 paramdefun Square {n count x y r}{
	 
		count 1.0 + quote count set \\ count = count + 1
		n count <
		ifthen { } 
		else {
			n count r neg x neg + y r + r 2.2 /
			Square {n count x y r}
			n count x r + y r + r 2.2 /
			Square {n count x y r}
			n count r neg x neg + y neg r neg + r 2.2  /
			Square {n count x y r}
			n count x r + y neg r neg + r 2.2 /
			Square {n count x y r}
			randomcolor
			r y x
			drawsquare
			defualtpen
			
			
			r y x
			drawemptysquare
		}
	}
	5.0 \\how many times it iterates 
	2.0 -2.0 setcanvas \\setcanvas
	0.0 \\ count variable
	0.0 0.0 1.0 \\(x,y,r) 
	Square {n count x y r}





paramdefun king {x y z}{ x y * z +}	
	*
	*/		
	
