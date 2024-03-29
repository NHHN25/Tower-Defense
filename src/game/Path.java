/** 
 * A path represents a series of line segments along which an
 * enemy will travel.  A path is a collection of line segments,
 * specified by their endpoints.  (The end of one segment is the
 * start of the next.)
 * 
 * This Path class is a convenient way to hide the details of the
 * path from our other code.  We can simply ask a path object for 
 * some coordinates along the path.
 * 
 * @author Peter Jensen
 * @version Spring 2022
 */

package game;

import java.awt.*;
import java.util.*;

// Notice in the code below the way I line up commas, equal signs, etc. in
//   lines grouped by idea.  I also take great care to line up the left
//   edges properly.   Each scope should indent another 4 spaces.
//   It helps me read my code and avoid typos.

public class Path
{
	// Fields (variables that will exist in every Path object)
    
    // (I've decided to adapt this class to use lists instead of arrays.
	// This was NOT required, but I still want to show it to you.)
    //
    // Note:  Sometimes 'List' is ambiguous.  There are multiple
    //   built-in List classes.  When needed, use java.util.List below 
    //   to tell Eclipse which List class to use.
	
    private java.util.List<Point> path;    
    private double totalPathLength;
    
    // Methods
    
    /**
     * This constructor builds a path by reading text from a Scanner.
     * The first Scanned item is the length of the path (in points),
     * and the remaining pairs of scanned text items are coordinate values
     * (as x, y pairs).
     * 
     * @param in a Scanner ready to scan the integers that define this path
     */
    public Path (Scanner in)
    {
        // The first entry in the file should be the number of points.
        //   Read it, save it in a local variable.  (We don't need it for more
        //   than a moment.)  It is much more useful when storing the path
    	//   in an array.  In my case it's not as useful.
        
    	int pointCount = in.nextInt();
    	
    	// Create the List object (instead of using an array).  Caution:  List
    	//   is an abstract supertype.  You must create a specific kind of list.
    	//   ArrayList objects will do nicely in this circumstance.  We could
    	//   also use LinkedList objects, but we might suffer a performance hit.
    	
    	path = new ArrayList<Point>();  // ArrayList objects are List objects.
    	
    	// Read in the data points into our list.
    	//   In my code below, i represents 'items read'.  It starts
    	//   at 0 (nothing read yet), but stops when i == size (everything read).
    	
    	for (int i = 0; i < pointCount; i++)    	    
    	{
    		int x = in.nextInt();
    		int y = in.nextInt();
    		Point p = new Point(x, y);
    		
    		// Array lists grow as needed.  Adding something to the list
    		//   puts it at the end of the list.
    		
    		path.add(p);
    	}
    	
    	// Compute the path length.
        
        totalPathLength = 0;

    	// If we count segments starting at 1, 
    	//   segment 1 is path[0]   ... path[1], and   
    	//   segment n is path[n-1] ... path[n].
    	//
    	// In an array or list, the last entry is indexed one less
    	//   than the length of the list, so as long as the index is
    	//   less than the length (or size) of the list, it is valid.
    	//
    	// Remember, for arrays use path.length.  For List objects, use path.size()
    	
        for (int i = 1; i < path.size(); i++)
    	{
    		Point start = path.get(i-1);  // Extract segment start/end
    		Point end   = path.get(i);
    		
    		// A neat shortcut below, it is the same as
            // totalLength = totalLength + start.distance(end);
    		
    		totalPathLength += start.distance(end);  
    	}
    }
            
    /**
     * Returns a Point, or x, y coordinates, of some position along this
     * path.  The position is given as a percentage.  0.0 means the 
     * first position on the path, and 1.0 means the last position on the
     * path.
     * 
     * @param percentage   a distance along the path, as a percentage
     * @return             the x, y coordinate (as a Point object) of this position on the path
     */
    public Point locate (double percentage)
    {
        // Caution: Do not return any Point object from the path.  A Point
        //   object can have its contents changed (such as setting x or y to 0).
        //   If the caller got back a reference to one of our Point
        //   objects, they could change x or y and screw up the path.
        //   Instead, in the code below I either compute new Point objects,
        //   or I return new points that are copies of points in our path.
               
        // Math is annoying when using doubles.  We need to take
        //   extra care.  First, make sure that the percentage
        //   is in the range (0.0...1.0), exclusive.  If not,
        //   return reasonable values.    	
        //
        // If the percentage is at or before the start of the path,
        //   return the first path coordinate.  If the percentage is past
        //   the end, return the last path coordinate.
    	
    	// Note:  Most students won't have this in their solution.  I've
    	// added it to deal with unusual situations.
        
    	if (percentage <= 0.0) 
    		return new Point(path.get(0));  // Make a new Point object that copies our first point
    	
    	if (percentage >= 1.0)
    		return new Point(path.get(path.size()-1));  // Make a new Point object that copies our last point

    	// Convert the percentage to a distance.  This is how far along
    	//   the path we are.  We need to find the coordinate that is at this
    	//   distance from the start of the path.
    	
    	double distanceToTravel = totalPathLength * percentage;
    	
    	// Walk through the segments and keep track of the distance traveled as
    	//   we go.  If the distance traveled is greater than or equal to
    	//   the amount we're supposed to travel, we've found the segment that 
    	//   we're in.
    	
    	// In the code below, I compute values in the loop I need after the loop.
    	//   I declare my local variables before the loop (so they'll still be there
    	//   after the loop), but they need initial values.  I give them
    	//   somewhat arbitrary values, the code in the loop will change them.
    	
        Point start = path.get(0);  // Segment points for the current segment.
        Point end   = path.get(1);
    	double totalDistance = 0;   // Accumulated distance
    	double segmentLength = 1;   // Length of the current segment.  (Wrong)    	
		   	
        for (int i = 1; i < path.size(); i++)
        {
           // Extract segment start/end points
            
            start = path.get(i-1);  
            end   = path.get(i);

            // Compute the length of this segment, combine it with the total.
            
            segmentLength = start.distance(end);    		
            totalDistance = totalDistance + segmentLength;  // Better:  totalDistance += segmentLength;
    		
    		// If we've gone far enough (or too far), exit the loop immediately.
    		
    		if (totalDistance >= distanceToTravel)
    			break;        		
    	}
   		
   		// Consider the current segment, not the entire path.  The distance we are
        //   seeking is in this segment somewhere.  Calculate how much too far
        //   the end of the segment is.  Then, see what percentage of this segment
        //   the excess distance is.  
        //
        // Since the distanceToTravel is no greater than totalDistance, the
        //   result will be non-negative.
   		
   		double excessDistance    = totalDistance - distanceToTravel;
    	double segmentPercentage = excessDistance / segmentLength;   // Will be between [0..1]

    	// We know that the target coordinate is some segmentPercentage from the segment end,
    	//   and (1 - segmentPercentage) from the segment start.  Use these to compute a
    	//   weighted average of the start and end points.  I've broken this
    	//   code into separate lines for clarity.
    	//
    	// Remember, if we're 0% from the end, we're at the end, so include 100% of the end.
    	//   (Vice versa for the start.)
    	
    	double targetX = (segmentPercentage)*start.x + (1-segmentPercentage)*end.x;
    	double targetY = (segmentPercentage)*start.y + (1-segmentPercentage)*end.y;
    	
    	Point result = new Point ((int) targetX, (int) targetY);
    	
    	// Done, return the coordinates (as a Point object).
    	
    	return result;
    }
    
    /* Below this point:  Debugging and development functions only. */
    /* These are not intended for use during the game, but they do help
     * during development. */

    /**
     * Draws the path to the screen in a highly visible
     * color.  Draw it a bit thick to make it easier to see.  
     * 
     * I've adjusted the parameter of this function to require
     * a Graphics2D object.  This allows us to set line thickness.
     * 
     * This function is for debugging only - it is not intended for
     * general use.  
     * 
     * @param k any Graphics2D object suitable for drawing
     */
    public void draw (Graphics g)
    {
    	// Cast the graphics object back to its actual class.
    	
    	Graphics2D k = (Graphics2D) g;
    	
        // Set the color and line thickness.
        
        k.setColor (Color.RED);
        k.setStroke(new BasicStroke(3));
    	
        // Draw the segments.  I really didn't like the way I did it in class,
        //   so I've updated it to take advantage of the fact that we can
        //   control line widths with Graphics2D objects.
        
    	for (int i = 1; i < path.size(); i++)
    	{
            Point start = path.get(i-1);  // Extract segment start/end
            Point end   = path.get(i);
    		
    		k.drawLine(start.x, start.y, end.x, end.y);
       	}
    }   
}
