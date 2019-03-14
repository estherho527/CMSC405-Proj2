/*
 * Name: Esther Ho
 * CMSC 405 (Computer Graphics)
 * Assignment: Project 2
 * Due: November 18, 2018
 * File: Proj2_JOGL.java
 * 
 * Description:
 * 
 * Each JOGL_Shape object, shape, has four instance variables:
 *
 *     shape.vertices -- an array of vertices where each vertex is given
 *                      as an array of three numbers.
 *
 *     shape.faces -- an array of faces, where each face is an array of integers.
 *                   The integers in the array are indices into the array of
 *                   vertices, and they give the vertices of the face in 
 *                   counterclockwise order as seen from the outside.
 *
 *     shape.rgb -- and array of colors, one for each face, where a color
 *                   is given as an array of three numbers in the range 0.0 to 1.0.
 *                   Can be null.
 *
 *	This collection of JOGL_Shapes are made to imitate various crystal structures, though
 *		not all are based on crystal structures:
 *	
 * 
 * 
 */

package cmsc405_proj2;

public class JOGL_Shapes {

	double[][] vertices; // vertex coordinates to construct an object
	int[][] faces; // faces that are made using coordinates from vertices array
	double[][] rgb; // rgb colors of the objects faces
	
	/**
	 * Default Constructor
	 *
	 * @param vertices Array of vertices that make up an Object
	 * @param faces Array of faces, using vertices that make up an Object
	 * @param rgb Array of color values for an Objects faces
	 */
	private JOGL_Shapes(double[][] vertices, int[][] faces, double[][] rgb) {
		this.vertices = vertices;
		this.faces = faces;
		this.rgb = rgb;
	}
	
	/** aquamarine-color crystal */
	static JOGL_Shapes lightBlueCrystal =
			new JOGL_Shapes(
					new double[][] {
						{0, 0, 1},
						{0.25, 0, 0},
						{0, 0.25, 0},
						{-0.25, 0, 0},
						{0, -0.25, -0},
						{0, 0, -1}
						
					},
					new int[][] {
						{0, 1, 2},
						{0, 2, 3},
						{0, 3, 4},
						{0, 4, 1},
						{5, 1, 2},
						{5, 2, 3},
						{5, 3, 4},
						{5, 4, 1}
				
					},
					new double[][] {
						{0.35, 0.91, 0.95},
						{0.3, 0.91, 0.95},
						{0.3, 0.85, 0.95},
						{0.35, 0.90, 0.90},
						{0.35, 0.85, 0.90},
						{0.3, 0.85, 0.95},
						{0.3, 0.91, 0.90},
						{0.3, 0.85, 0.90}
					});
	
	/** emerald crystal Shape */
	static JOGL_Shapes emeraldCrystal =
			new JOGL_Shapes(
					new double[][] {
						{0, 0, 1},
						{0.25, 0, 0.75},
						{0, 0.25, 0.75},
						{-0.25, 0, 0.75},
						{0, -0.25, 0.75},
						{0.25, 0, 0},
						{0, 0.25, 0},
						{-0.25, 0, 0},
						{0, -0.25, 0}
					},
					new int[][] {
						{0, 1, 2},
						{0, 2, 3},
						{0, 3, 4},
						{0, 4, 1},
						{1, 2, 6, 5},
						{2, 3, 7, 6},
						{3, 4, 8, 7},
						{4, 1, 5, 8},
						{5, 6, 7, 8}
					},
					new double[][] {
						{0.01, 0.7, 0.5},
						{0.05, 0.7, 0.5},
						{0.01, 0.75, 0.5},
						{0.01, 0.7, 0.55},
						{0.01, 0.65, 0.5},
						{0, 0.7, 0.5},
						{0.01, 0.7, 0.45},
						{0.01, 0.75, 0.5},
						{0.05, 0.7, 0.55}
					});
	
	/** topaz-colored cubed crystal Shape */
	static JOGL_Shapes whiteCubeCrystal =
			new JOGL_Shapes(
					new double[][] {
						{0, 0, 1},
						{0, 1, 1},
						{1, 1, 1},
						{1, 0, 1},
						{0, 0, 0},
						{0, 1, 0},
						{1, 1, 0},
						{1, 0, 0}
					},
					new int[][] {
						{0, 1, 2, 3},
						{0, 1, 5, 4},
						{0, 3, 7, 4},
						{3, 2, 6, 7},
						{2, 1, 5, 6},
						{4, 5, 6, 7}
					},
					new double[][] {
						{0.95, 0.6, 0.1},
						{0.90, 0.6, 0.1},
						{0.95, 0.65, 0.1},
						{0.95, 0.6, 0.15},
						{1, 0.6, 0.1},
						{0.95, 0.55, 0.1}
					
					});
	
	/** sapphire color triclinic Crystal Shape */
	static JOGL_Shapes triclinicCrystal =
			new JOGL_Shapes(
					new double[][] {
						{0.25, 0, 1.5},
						{0.25, 0.5, 1.5},
						{0, 0, 1},
						{0, 0.5, 1},
						{0.5, 0.5, 1},
						{0.5, 0, 1},
						{0, 0, 0},
						{0, 0.5, 0},
						{0.5, 0.5, 0},
						{0.5, 0, 0}
					},
					new int[][] {
						// top
						{0, 1, 4, 5},
						{0, 1, 3, 2},
						//front and back
						{0, 5, 9, 6, 2},
						{1, 4, 8, 7, 3},
						//rectangular sides
						{5, 4, 8, 9},
						{2, 3, 7, 6},
						//bottom
						{6, 7, 8, 9}
					},
					new double[][] {
						{0.1, 0.4, 1},
						{0.15, 0.4, 1},
						{0.1, 0.45, 1},
						{0.1, 0.4, 0.95},
						{0.05, 0.4, 1},
						{0.1, 0.35, 1},
						{0.1, 0.4, 0.95}
					});
	
	/** amethyst-color hexagonal crystal */
	static JOGL_Shapes hexagonalCrystal =
			new JOGL_Shapes(
					new double[][] {
						{0, 0.7, 1},
						{0.35, 0.35, 1},
						{0.35, -0.35, 1},
						{0, -0.7, 1},
						{-0.35, -0.35, 1},
						{-0.35, 0.35, 1},
						{0, 0.7, 0},
						{0.35, 0.35, 0},
						{0.35, -0.35, 0},
						{0, -0.7, 0},
						{-0.35, -0.35, 0},
						{-0.35, 0.35, 0}
					},
					new int[][] {
						// top and bottom
						{0, 1, 2, 3, 4, 5},
						{6, 7, 8, 9, 10, 11},
						//side faces
						{0, 1, 7, 6},
						{1, 2, 8, 7},
						{2, 3, 9, 8},
						{3, 4, 10, 9},
						{4, 5, 11, 10},
						{5, 0, 6, 11}
					},
					new double[][] {
						{0.3, 0, 0.55},
						{0.35, 0, 0.55},
						{0.3, 0.05, 0.55},
						{0.3, 0, 0.6},
						{0.25, 0, 0.55},
						{0.3, 0, 0.5},
						{0.35, 0, 0.5},
						{0.25, 0, 0.6},
					});
	
	/** garnet-color trigonal crystal*/
	static JOGL_Shapes trigonalCrystal =
			new JOGL_Shapes(
					new double[][] {
						{-0.25, 0.5, 1},
						{0.25, 0.5, 1},
						{0.5, 0.25, 1},
						{0.5, -0.25, 1},
						{0.25, -0.5, 1},
						{-0.25, -0.5, 1},
						{-0.5, -0.25, 1},
						{-0.5, 0.25, 1},
						{-0.25, 0.5, 0},
						{0.25, 0.5, 0},
						{0.5, 0.25, 0},
						{0.5, -0.25, 0},
						{0.25, -0.5, 0},
						{-0.25, -0.5, 0},
						{-0.5, -0.25, 0},
						{-0.5, 0.25, 0}
					},
					new int[][] {
						// top and bottom
						{0, 1, 2, 3, 4, 5, 6, 7},
						{8, 9, 10, 11, 12, 13, 14, 15},
						// side faces
						{0, 1, 9, 8},
						{1, 2, 10, 9},
						{2, 3, 11, 10},
						{3, 4, 12, 11},
						{4, 5, 13, 12},
						{5, 6, 14, 13},
						{6, 7, 15, 14},
						{7, 0, 8, 15}
					},
					new double[][] {
						{0.5, 0.08, 0.08},
						{0.55, 0.08, 0.08},
						{0.5, 0.15, 0.08},
						{0.5, 0.08, 0.15},
						{0.45, 0.08, 0.08},
						{0.5, 0, 0.08},
						{0.5, 0.08, 0},
						{0.55, 0, 0.08},
						{0.55, 0.08, 0},
						{0.45, 0.15, 0.08}
					});

}// end JOGL_Shapes