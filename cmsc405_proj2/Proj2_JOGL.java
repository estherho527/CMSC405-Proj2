/*
 * Name: Esther Ho
 * CMSC 405 (Computer Graphics)
 * Assignment: Project 2
 * Due: November 18, 2018
 * File: Proj2_JOGL.java
 * 
 * Description:
 *   Proj2_JOGL uses JOGL to display a 3D scene with 6 different objects.
 *   
 *   -the user can use various keys to perform transformations:
 *   	-Translation: W, A, S, D keys
 *   	-Rotation: up, down, left, right arrow keys
 *   	-Scaling: +, -
 *   	-to reset: HOME or SPACE button 
 *   
 *   -any other buttons will result in an pop-up error message (seems to be delayed)
 *
 *  -Objects are JOGL_Shapes objects, that are made to imitate crystal structures
 *  
 *
 */

package cmsc405_proj2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Proj2_JOGL extends GLJPanel implements GLEventListener, KeyListener{
	
	// Default Transform
	private double rX = 60; // Rotation along the X axis
	private double rY = 60; // Rotation along the Y axis
	private double rZ = 0; // Rotation along the Z axis
	private double tX = 0; // Translation along the X axis
	private double tY = 0; // Translation along the Y axis
	private double tZ = 0; // Translation along the Z axis
	private double sc = 1.5; // Scale, or size, of the Object

	public Proj2_JOGL(){
		super(new GLCapabilities(null));
		setPreferredSize(new Dimension(640, 480));
		addGLEventListener(this);
		addKeyListener(this);
	}
	
	public static void main(String[] args) {
		JFrame window =
				new JFrame(
						"Proj2_JOGL | Translate:WASD | Zoom In/Out:+- | Rotate:arrows | Reset: Home OR SPACE");
		Proj2_JOGL panel = new Proj2_JOGL();
		window.setContentPane(panel);
		window.pack();
		window.setLocation(50, 50);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
		panel.requestFocusInWindow();
    }

	private void drawObj(GL2 gl2, JOGL_Shapes shape, double scale, double x, double y, double z) {
		gl2.glPushMatrix();
		gl2.glScaled(scale, scale, scale);  // scale unit to desired size
		gl2.glTranslated(x, y, z);          // translate unit to desired pos

		for (int i = 0; i < shape.faces.length; i++) {
			gl2.glPushMatrix();
			double r = shape.rgb[i][0];
			double g = shape.rgb[i][1];
			double b = shape.rgb[i][2];

			// Draws obj faces with appropriate color
			gl2.glColor3d(r, g, b);
			gl2.glBegin(GL2.GL_TRIANGLE_FAN);
			for (int j = 0; j < shape.faces[i].length; j++) {
				int v = shape.faces[i][j];
				gl2.glVertex3dv(shape.vertices[v], 0);
			}
			gl2.glEnd();

			// Draws obj outlines
			gl2.glColor3d(0, 0, 0);
			gl2.glBegin(GL2.GL_LINE_LOOP);
			for (int j = 0; j < shape.faces[i].length; j++) {
				int v = shape.faces[i][j];
				gl2.glVertex3dv(shape.vertices[v], 0);
			}
			gl2.glEnd();
			gl2.glPopMatrix();
		}
		gl2.glPopMatrix();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) 
			rY -= 20;
		
		else if (key == KeyEvent.VK_RIGHT) 
			rY += 20;
		
		else if (key == KeyEvent.VK_DOWN) 
			rX -= 20;
		
		else if (key == KeyEvent.VK_UP) 
			rX += 20;
		
		else if (key == KeyEvent.VK_PLUS || key == KeyEvent.VK_EQUALS) 
			sc += .2;
		
		else if (key == KeyEvent.VK_MINUS || key == KeyEvent.VK_UNDERSCORE) 
			sc -= .2;
		
		else if (key == KeyEvent.VK_W) 
			tY += .2;
		
		else if (key == KeyEvent.VK_A) 
			tX -= .2;
		
		else if (key == KeyEvent.VK_S) 
			tY -= .2;
		
		else if (key == KeyEvent.VK_D) 
			tX += .2;
		
		else if (key == KeyEvent.VK_HOME || key == KeyEvent.VK_SPACE) {
			rX = rY = 60;
			rZ = tX = tY = tZ = 0;
			sc = 1.5;
		}
		
		else
			JOptionPane.showMessageDialog(this, "invalid key pressed", "Error", JOptionPane.ERROR_MESSAGE);
		repaint();
	}// end keyPressed

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(GLAutoDrawable toDraw) {
		GL2 gl2 = toDraw.getGL().getGL2(); // The object that contains all the OpenGL methods.

		gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl2.glLoadIdentity();
		gl2.glRotated(rZ, 0, 0, 1);            // rotate Z of scene
		gl2.glRotated(rY, 0, 1, 0);            // rotate Y of scene
		gl2.glRotated(rX, 1, 0, 0);            // rotate X of scene
		gl2.glScaled(sc, sc, sc);                      // scales scene
		gl2.glTranslated(tX, tY, tZ);   // translates scene

		// Draw Objects
		drawObj(gl2, JOGL_Shapes.lightBlueCrystal, 0.125, 0, -0.05, 0);
		drawObj(gl2, JOGL_Shapes.emeraldCrystal, 0.125, 0, 1.5, 0);
		drawObj(gl2, JOGL_Shapes.whiteCubeCrystal, 0.125, 1.5, 1.5, 1.5);
		drawObj(gl2, JOGL_Shapes.triclinicCrystal, 0.125, -1.5, 1.5, -1.5);
		drawObj(gl2, JOGL_Shapes.hexagonalCrystal, 0.125, 1.5, 1.5, -1.5);
		drawObj(gl2, JOGL_Shapes.trigonalCrystal, 0.125, -1.5, 1.5, 1.5);
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// called when the panel is created
				GL2 gl2 = drawable.getGL().getGL2();
				gl2.glMatrixMode(GL2.GL_PROJECTION);
				gl2.glOrtho(-1, 1, -1, 1, -1, 1);
				gl2.glMatrixMode(GL2.GL_MODELVIEW);
				gl2.glClearColor(0, 0, 0, 1);
				gl2.glEnable(GL2.GL_DEPTH_TEST);
				gl2.glLineWidth(2);
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
}// end Proj2_JOGL