/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-4 19:32:27 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch23;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class ClassFigure extends Figure {
	ImageRegistry registry = new ImageRegistry();
	// image keys.
	String KEY_CLASS = "class";
	String KEY_METHOD_PUBLIC = "method_public";
	String KEY_METHOD_DEFAULT = "method_default";
	String KEY_METHOD_PROTECTED = "method_protected";
	String KEY_METHOD_PRIVATE = "method_private";
	String KEY_FIELD_PUBLIC = "field_public";
	String KEY_FIELD_DEFAULT = "field_default";
	String KEY_FIELD_PROTECTED = "field_protected";
	String KEY_FIELD_PRIVATE = "field_private";

	String[] keys =
		{
			KEY_CLASS,
			KEY_METHOD_PUBLIC,
			KEY_METHOD_DEFAULT,
			KEY_METHOD_PROTECTED,
			KEY_METHOD_PRIVATE,
			KEY_FIELD_PUBLIC,
			KEY_FIELD_DEFAULT,
			KEY_FIELD_PROTECTED,
			KEY_FIELD_PRIVATE };

	public Box fieldBox = new Box();
	public Box methodBox = new Box();

	public ClassFigure(Class cls) {
		setLayoutManager(new ToolbarLayout());
		setBorder(new LineBorder(ColorConstants.black));
		setBackgroundColor(ColorConstants.yellow);
		setOpaque(true);

		for (int i = 0; i < keys.length; i++)
			registry.put(
				keys[i],
				ImageDescriptor.createFromFile(
					null,
					"icons/java/" + keys[i] + ".gif"));

		Label title = new Label(cls.getName(), registry.get(KEY_CLASS));
		add(title);
		add(fieldBox);
		add(methodBox);

		// fields.
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Image image = null;
			if (Modifier.isPublic(field.getModifiers())) {
				image = registry.get(KEY_FIELD_PUBLIC);
			} else if (Modifier.isProtected(field.getModifiers())) {
				image = registry.get(KEY_FIELD_PROTECTED);
			} else if (Modifier.isPrivate(field.getModifiers())) {
				image = registry.get(KEY_FIELD_PRIVATE);
			} else {
				image = registry.get(KEY_FIELD_DEFAULT);
			}
			fieldBox.add(new Label(fields[i].getName(), image));
		}

		// fields.
		Method[] methods = cls.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			Image image = null;
			if (Modifier.isPublic(method.getModifiers())) {
				image = registry.get(KEY_METHOD_PUBLIC);
			} else if (Modifier.isProtected(method.getModifiers())) {
				image = registry.get(KEY_METHOD_PROTECTED);
			} else if (Modifier.isPrivate(method.getModifiers())) {
				image = registry.get(KEY_METHOD_PRIVATE);
			} else {
				image = registry.get(KEY_METHOD_DEFAULT);
			}
			methodBox.add(new Label(methods[i].getName(), image));
		}

	}
}

class Box extends Figure {
	public Box() {
		setBorder(new BoxBorder());

		ToolbarLayout toolbarLayout = new ToolbarLayout();
		toolbarLayout.setStretchMinorAxis(false);

		setLayoutManager(toolbarLayout);
	}

	private class BoxBorder extends AbstractBorder {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
		 */
		public Insets getInsets(IFigure figure) {
			return new Insets(1, 0, 0, 0);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure,
		 *      org.eclipse.draw2d.Graphics,
		 *      org.eclipse.draw2d.geometry.Insets)
		 */
		public void paint(IFigure figure, Graphics graphics, Insets insets) {
			Rectangle rect = getPaintRectangle(figure, insets);
			graphics.drawLine(rect.getTopLeft(), rect.getTopRight());
		}

	}
}

/**
 *  
 */
public class ClassAnalyzer extends ApplicationWindow {
	Text className;

	Canvas canvas;
	Figure contents;
	XYLayout xyLayout;

	/**
	 * @param parentShell
	 */
	public ClassAnalyzer(Shell parentShell) {
		super(parentShell);
		addToolBar(SWT.NULL);
	}

	private void showClass(Class cls) {
		if (cls == null)
			return;

		// removes all existing items.
		contents.removeAll();

		// draws super class.
		Label sup = null;
		if (cls.getSuperclass() != null) {
			final Class superClass = cls.getSuperclass();
			sup = new Label(superClass.getName());
			sup.setBorder(new LineBorder());
			sup.setBackgroundColor(ColorConstants.lightGreen);
			sup.setOpaque(true);
			sup.addMouseListener(new MouseListener() {
				public void mousePressed(org.eclipse.draw2d.MouseEvent me) {
				}

				public void mouseReleased(org.eclipse.draw2d.MouseEvent me) {
				}

				public void mouseDoubleClicked(
					org.eclipse.draw2d.MouseEvent me) {
					showClass(superClass);
				}
			});
		}

		if (sup != null) {
			contents.add(sup);
			xyLayout.setConstraint(sup, new Rectangle(20, 20, -1, -1));
		}

		ClassFigure classFigure = new ClassFigure(cls);
		contents.add(classFigure);
		if (sup == null)
			xyLayout.setConstraint(classFigure, new Rectangle(20, 20, -1, -1));
		else
			xyLayout.setConstraint(
				classFigure,
				new Rectangle(20, sup.getBounds().height + 40, -1, -1));

		// adds connection.
		if (sup != null) {
			PolylineConnection connection = new PolylineConnection();
			ChopboxAnchor source = new ChopboxAnchor(classFigure);
			ChopboxAnchor target = new ChopboxAnchor(sup);
			connection.setSourceAnchor(source);
			connection.setTargetAnchor(target);

			PolygonDecoration decoration = new PolygonDecoration();
			PointList list = new PointList();
			list.addPoint(-2, -2);
			list.addPoint(0, 0);
			list.addPoint(-2, 2);
			decoration.setTemplate(list);

			connection.setTargetDecoration(decoration);

			contents.add(connection);
		}

		// resizes the shell.
		getShell().setSize(
			contents.getPreferredSize().width + 30,
			contents.getPreferredSize().height + 80);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new FillLayout());

		canvas = new Canvas(composite, SWT.NULL);
		canvas.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));

		LightweightSystem lws = new LightweightSystem(canvas);
		contents = new Figure();
		xyLayout = new XYLayout();
		contents.setLayoutManager(xyLayout);

		lws.setContents(contents);

		showClass(this.getClass());

		// Creates tool bar items.
		getToolBarManager().add(new Action("Set class ...") {
			public void run() {
				InputDialog dialog =
					new InputDialog(
						getShell(),
						"",
						"Please enter the class name",
						"",
						null);
				if (dialog.open() != Dialog.OK)
					return;

				contents.removeAll();
				Class cls = null;
				try {
					cls = Class.forName(dialog.getValue());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (cls != null) {
					showClass(cls);
				}
			}
		});
		getToolBarManager().update(true);

		return composite;
	}

	public static void main(String[] args) {
		ClassAnalyzer window = new ClassAnalyzer(null);

		window.setBlockOnOpen(true);
		window.open();

		Display.getCurrent().dispose();
	}
}
