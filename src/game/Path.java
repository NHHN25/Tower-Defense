/** Header comment needed! */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class represents the path of the background and includes a method to
 * draw the path and locate the position of the object on the path
 * 
 * @author Nam Nguyen and Minh Pham April 4th, 2022
 */
public class Path {
	/* Contracts needed */

	// Fields to store the path and its length
	private Point[] points;
	private int amountOfCoords, totalPath;
	private double[] segmentLengths;

	/**
	 * This constructor does the following: - It reads a number of coordinates, n,
	 * from the scanner. - It creates new array(s) (or an ArrayList) to hold the
	 * path coordinates, and stores it in the field in 'this' object. - It loops n
	 * times, each time scanning a coordinate x,y pair, creating an object to
	 * represent the coordinate (if needed), and stores the coordinate in the
	 * array(s) or ArrayList.
	 * 
	 * @param s a Scanner set up by the caller to provide a list of coordinates
	 */
	public Path(Scanner sc) {
		// Read length of coordinates
		this.amountOfCoords = sc.nextInt();

		// Create new arrays with length
		this.points = new Point[amountOfCoords];
		this.segmentLengths = new double[points.length - 1];

		// Read and store all points in a Point array.
		for (int i = 0; i < points.length; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point point = new Point(x, y);
			points[i] = point;
		}

		// Calculate total Path Length
		double segmentLength = 0;
		for (int i = 0; i < points.length - 1; i++) {
			segmentLength = Math.sqrt((Math.pow((points[i + 1].getX() - points[i].getX()), 2))
					+ (Math.pow(points[i + 1].getY() - points[i].getY(), 2)));
			segmentLengths[i] = segmentLength;
			totalPath += (int) segmentLength;

		}

	}

	/**
	 * Draws the current path to the screen (to wherever the graphics object points)
	 * using a highly-visible color.
	 * 
	 * @param g a graphics object
	 */
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);

		for (int i = 1; i < points.length; i++) {
			int x = (int) points[i].getX();
			int y = (int) points[i].getY();
			g.drawLine((int)(points[i-1].getX()), (int)(points[i-1].getY()), (int)(points[i].getX()), (int)(points[i].getY()));
		}
	}

	/**
	 * Given a percentage between 0% and 100%, this method calculates the location
	 * along the path that is exactly this percentage along the path. The location
	 * is returned in a Point object (integer x and y), and the location is a screen
	 * coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is returned. If the
	 * percentage is greater than 100%, the final position is returned.
	 * 
	 * If students don't want to use Point objects, they may write or use another
	 * object to represent coordinates.
	 *
	 * Caution: Students should never directly return a Point object from a path
	 * list. It could be changed by the outside caller. Instead, always create and
	 * return a new point objects as the result from this method.
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point locatePosition(double percentTraveled) {
		// Calculate the travel distance
		double traveledDistance = totalPath * percentTraveled;

		// Initialize variables
		int segmentCounter = 0;
		double segmentRatio;
		int pointX = 0, pointY = 0;
		Point positionPoint;

		// Subtract travel distance with every segment length until it cannot be deduct
		// anymore
		for (int i = 0; i < segmentLengths.length; i++) {
			traveledDistance -= segmentLengths[i];
			segmentCounter++;
			if (traveledDistance < segmentLengths[i])
				break;
		}

		// Calculate the percentage of the current segment that the object is in
		segmentRatio = traveledDistance / segmentLengths[segmentCounter];

		// Calculate the values of X and Y of the current position of the object
		pointX = (int) (((1 - segmentRatio) * points[segmentCounter].getX())
				+ (segmentRatio * points[segmentCounter + 1].getX()));
		pointY = (int) (((1 - segmentRatio) * points[segmentCounter].getY())
				+ (segmentRatio * points[segmentCounter + 1].getY()));

		// Check if input percentage is lower than 0% or larger than 100%
		if (percentTraveled < 0.0)
			return positionPoint = new Point((int) (points[0].getX()), (int) (points[0].getY()));

		else if (percentTraveled > 1.0)
			return positionPoint = new Point((int) (points[points.length - 1].getX()),
					(int) (points[points.length - 1].getY()));

		else
			return positionPoint = new Point(pointX, pointY);
	}

}
