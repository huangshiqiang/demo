/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-7-14 7:19:55 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch24;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 *  
 */
public class FTPWindow extends ApplicationWindow {
	TableViewer localDirBrowser;
	TableViewer remoteDirBrowser;
	Label labelPathLocal;
	Label labelPathRemote;
	StyledText textLog;

	ConnectionDialog connectionDialog;

	Action actionUpLocalDir;
	Action actionUpRemoteDir;
	Action actionBrowseLocalDir;
	Action actionConnect;
	Action actionDisconnect;

	Action actionDisplayAbout;
	Action actionExit;

	FTPClient ftp;
	ConnectionInfo connectionInfo;

	/**
	 * @param parentShell
	 */
	public FTPWindow(Shell parentShell) {
		super(parentShell);

		createActions();

		addStatusLine();
		//addCoolBar(SWT.FLAT | SWT.RIGHT);
		addToolBar(SWT.FLAT);
		addMenuBar();

		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new ProtocolCommandListener() {
			public void protocolCommandSent(ProtocolCommandEvent e) {
				logMessage("> " + e.getCommand(), false);
			}

			public void protocolReplyReceived(ProtocolCommandEvent e) {
				logMessage("< " + e.getMessage(), false);
			}
		});

	}

	private void createActions() {
		// Up one level - local dir
		actionUpLocalDir = new Action() {
			public void run() {
				if (localDirBrowser.getInput() == null)
					return;
				File dir = ((File) localDirBrowser.getInput()).getParentFile();
				if (dir != null) {
					localDirBrowser.setInput(dir);
					labelPathLocal.setText("Path: " + dir);
				}
			}
		};
		actionUpLocalDir.setText("Up");
		actionUpLocalDir.setToolTipText("Up one level - local dir");
		actionUpLocalDir.setImageDescriptor(
			ImageDescriptor.createFromFile(null, "icons/ftp/up.gif"));

		// browse for local dir
		actionBrowseLocalDir = new Action() {
			public void run() {
				DirectoryDialog dialog = new DirectoryDialog(getShell());
				String path = dialog.open();
				if (path == null)
					return;
				File file = new File(path);
				localDirBrowser.setInput(file);
				labelPathLocal.setText("Path: " + file);
			}
		};
		actionBrowseLocalDir.setText("Browse...");
		actionBrowseLocalDir.setToolTipText("Browse local directory");
		actionBrowseLocalDir.setImageDescriptor(
			ImageDescriptor.createFromFile(null, "icons/ftp/browse.gif"));

		// connect
		actionConnect = new Action() {
			public void run() {
				if (connectionDialog == null)
					connectionDialog = new ConnectionDialog(FTPWindow.this);
				if (connectionDialog.open() == Dialog.OK) {
					connectionInfo = connectionDialog.getConnectionInfo();
					if (connectionInfo == null) {
						logError("Failed to get connection information.");
					} else {
						// connects to remote host.
						logMessage("Connecting to " + connectionInfo.host, true);
						try {
							ftp.connect(
								connectionInfo.host,
								connectionInfo.port);
							if (!FTPReply
								.isPositiveCompletion(ftp.getReplyCode()))
								throw new RuntimeException("FTP server refused connection.");
							logMessage("Connected to " + connectionInfo.host, true);
						} catch (Exception e) {
							logError(e.toString());
							return;
						}
						try {
							// logins in.
							if (ftp
								.login(
									connectionInfo.username,
									connectionInfo.password)) {
								logMessage(
									"Logged in as user: "
										+ connectionInfo.username, true);
							}
							// gets current working directory.
							labelPathRemote.setText(
								"Path: " + ftp.printWorkingDirectory());

							// Lists files.
							FTPFile[] files = ftp.listFiles();
							remoteDirBrowser.setInput(files);

						} catch (IOException e1) {
							logError(e1.getMessage());
							try {
								ftp.disconnect();
							} catch (IOException e2) {
								// 
							}
						}
					}
				}
			}
		};
		actionConnect.setText("Connect");
		actionConnect.setToolTipText("Connect to remote host");
		actionConnect.setImageDescriptor(
			ImageDescriptor.createFromFile(null, "icons/ftp/connect.gif"));

		// disconnect
		actionDisconnect = new Action() {
			public void run() {
				try {
					ftp.logout();
					ftp.disconnect();
				}catch(Exception e) {
					logError(e.toString());
				}
			}
		};
		actionDisconnect.setText("Disconnect");
		actionDisconnect.setToolTipText("Disconnect from remote host");
		actionDisconnect.setImageDescriptor(
			ImageDescriptor.createFromFile(null, "icons/ftp/stop.gif"));


		// up one level - remote dir.
		actionUpRemoteDir = new Action() {
			public void run() {
				try {
					if (ftp.changeToParentDirectory()) {
						// gets current working directory.
						labelPathRemote.setText(
							"Path: " + ftp.printWorkingDirectory());

						// Lists files.
						FTPFile[] files = ftp.listFiles();
						remoteDirBrowser.setInput(files);
					}
				} catch (Exception e) {
					logError(e.toString());
				}
			}
		};
		actionUpRemoteDir.setText("Up");
		actionUpRemoteDir.setToolTipText("Up one level - remote dir");
		actionUpRemoteDir.setImageDescriptor(
			ImageDescriptor.createFromFile(null, "icons/ftp/up.gif"));
		
		actionDisplayAbout = new Action() {
			public void run() {
				MessageDialog.openInformation(getShell(), "About", "FTP Client v1.0\nAll right reserved by Jack Li Guojie.");
			}
		};
		actionDisplayAbout.setText("About");
		actionDisplayAbout.setImageDescriptor(ImageDescriptor.createFromFile(null, "icons/ftp/about.gif"));
		
		actionExit = new Action() {
			public void run() {
				if(! MessageDialog.openConfirm(getShell(), "Confirm", "Are you sure you want to exit?")) 
					return;
				try {
					ftp.disconnect();
				}catch(Exception e) {
					// ignore.
				}
				close();
			}
		};
		actionExit.setText("Exit");
		actionExit.setImageDescriptor(ImageDescriptor.createFromFile(null, "icons/ftp/close.gif"));
		
	}

	private void dragNDropSupport() {
		// --- Drag source ---

		//  Allows text to be moved only.
		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		final DragSource dragSource =
			new DragSource(remoteDirBrowser.getControl(), operations);

		// Data should be transfered in plain text format.
		Transfer[] formats = new Transfer[] { TextTransfer.getInstance()};
		dragSource.setTransfer(formats);

		dragSource.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				System.out.println("DND starts");
				// disallows DND if no remote file is selected.
				IStructuredSelection selection =
					(IStructuredSelection) remoteDirBrowser.getSelection();
				FTPFile file = (FTPFile) selection.getFirstElement();
				if (file == null || file.isDirectory()) {
					event.doit = false;
				}
			}

			public void dragSetData(DragSourceEvent event) {
				// Provides the text data.
				if (TextTransfer
					.getInstance()
					.isSupportedType(event.dataType)) {
					IStructuredSelection selection =
						(IStructuredSelection) remoteDirBrowser.getSelection();
					FTPFile file = (FTPFile) selection.getFirstElement();
					if (file == null || file.isDirectory()) {
						event.doit = false;
					} else {
						event.data = file.getName();
					}
				}
			}

			public void dragFinished(DragSourceEvent event) {
			}
		});

		remoteDirBrowser
			.getControl()
			.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				dragSource.dispose();
			}
		});

		// --- Drop target ---
		final DropTarget dropTarget =
			new DropTarget(localDirBrowser.getControl(), operations);

		dropTarget.setTransfer(formats);

		dropTarget.addDropListener(new DropTargetListener() {
			public void dragEnter(DropTargetEvent event) {
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dragOperationChanged(DropTargetEvent event) {
			}

			public void dragOver(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (TextTransfer
					.getInstance()
					.isSupportedType(event.currentDataType)) {
					String text = (String) event.data;
					File target =
						new File((File) localDirBrowser.getInput(), text);
					if (target.exists()) {
						if (!MessageDialog
							.openConfirm(
								getShell(),
								"Overwriting confirmation",
								"Overwrite file " + target + "?")) {
							return;
						}
					}
					
					try {
						FileOutputStream stream = new FileOutputStream(target);

						if(ftp.retrieveFile(text, stream)) {
							logMessage("File retrieved successfully.", true);
							// refreshes the file list. 
							localDirBrowser.refresh();
						}else{
							logError("Failed to retrieve file: " + text);
						}

						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			public void dropAccept(DropTargetEvent event) {
			}
		});

		localDirBrowser.getControl().addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				dropTarget.dispose();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
	 */
	protected MenuManager createMenuManager() {
		MenuManager bar = new MenuManager();

		MenuManager menuFile = new MenuManager("&File");
		menuFile.add(actionConnect);
		menuFile.add(actionDisconnect);
		menuFile.add(new Separator());
		menuFile.add(actionExit);
		
		MenuManager menuLocal = new MenuManager("&Local");
		menuLocal.add(actionBrowseLocalDir);
		menuLocal.add(actionUpLocalDir);
		
		MenuManager menuRemote = new MenuManager("&Remote");
		menuRemote.add(actionUpRemoteDir);
		
		MenuManager menuHelp = new MenuManager("&Help");
		menuHelp.add(actionDisplayAbout);

		bar.add(menuFile);
		bar.add(menuLocal);
		bar.add(menuRemote);
		bar.add(menuHelp);
		bar.updateAll(true);

		return bar;
	}

	public static void addAction(
		ToolBarManager manager,
		Action action,
		boolean displayText) {
		if (!displayText) {
			manager.add(action);
			return;
		} else {
			ActionContributionItem item = new ActionContributionItem(action);
			item.setMode(ActionContributionItem.MODE_FORCE_TEXT);
			manager.add(item);
		}
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
	 */
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager manager = super.createToolBarManager(style);

		addAction(manager, actionConnect, true);
		addAction(manager, actionDisconnect, true);

		manager.add(new Separator());

		addAction(manager, actionBrowseLocalDir, true);
		addAction(manager, actionUpLocalDir, true);
		
		manager.add(new Separator());
		
		addAction(manager, actionUpRemoteDir, true);
		
		manager.add(new Separator());
		
		addAction(manager, actionDisplayAbout, true);		
		addAction(manager, actionExit, true);
		

		manager.update(true);		
		
		return manager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new FillLayout());

		// the vertical sashform.
		SashForm verticalForm = new SashForm(composite, SWT.VERTICAL);

		// the horizontal sashform.
		SashForm horizontalForm = new SashForm(verticalForm, SWT.HORIZONTAL);

		// Local dir browser.
		Composite compositeLocalDir = new Composite(horizontalForm, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 1;
		gridLayout.verticalSpacing = 1;
		compositeLocalDir.setLayout(gridLayout);

		Group compositeLocalDirTop = new Group(compositeLocalDir, SWT.NULL);
		compositeLocalDirTop.setText("Local");
		GridLayout gridLayout2 = new GridLayout(3, false);
		gridLayout2.marginHeight = 0;
		compositeLocalDirTop.setLayout(gridLayout2);
		compositeLocalDirTop.setLayoutData(
			new GridData(GridData.FILL_HORIZONTAL));

		labelPathLocal = new Label(compositeLocalDirTop, SWT.NULL);
		labelPathLocal.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		labelPathLocal.setText("Path: ");

		Button buttonUpLocalDir = new Button(compositeLocalDirTop, SWT.PUSH);
		buttonUpLocalDir.setText(actionUpLocalDir.getText());
		buttonUpLocalDir.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				actionUpLocalDir.run();
			}
		});

		Button buttonBrowseLocalDir =
			new Button(compositeLocalDirTop, SWT.PUSH);
		buttonBrowseLocalDir.setText(actionBrowseLocalDir.getText());
		buttonBrowseLocalDir.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				actionBrowseLocalDir.run();
			}
		});

		Table table = new Table(compositeLocalDir, SWT.BORDER);
		TableColumn tcFile = new TableColumn(table, SWT.LEFT);
		tcFile.setText("Name");

		TableColumn tcSize = new TableColumn(table, SWT.NULL);
		tcSize.setText("Size");

		TableColumn tcDate = new TableColumn(table, SWT.NULL);
		tcDate.setText("Date");

		tcFile.setWidth(200);
		tcSize.setWidth(100);
		tcDate.setWidth(100);
		table.setHeaderVisible(true);

		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		localDirBrowser = new LocalDirectoryBrowser(table);

		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				IStructuredSelection selection =
					(IStructuredSelection) localDirBrowser.getSelection();
				File file = (File) selection.getFirstElement();
				if (file != null && file.isDirectory()) {
					localDirBrowser.setInput(file);
					labelPathLocal.setText("Path: " + file);
				}
			}
		});

		// Remote directory browser.

		Composite compositeRemoteDir = new Composite(horizontalForm, SWT.NULL);
		gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 1;
		gridLayout.verticalSpacing = 1;
		compositeRemoteDir.setLayout(gridLayout);

		Group compositeRemoteDirTop = new Group(compositeRemoteDir, SWT.NULL);
		compositeRemoteDirTop.setText("Remote");
		gridLayout2 = new GridLayout(2, false);
		gridLayout2.marginHeight = 0;
		compositeRemoteDirTop.setLayout(gridLayout2);
		compositeRemoteDirTop.setLayoutData(
			new GridData(GridData.FILL_HORIZONTAL));

		labelPathRemote = new Label(compositeRemoteDirTop, SWT.NULL);
		labelPathRemote.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		labelPathRemote.setText("Path: ");

		Button buttonUpRemoteDir = new Button(compositeRemoteDirTop, SWT.PUSH);
		buttonUpRemoteDir.setText(actionUpLocalDir.getText());
		buttonUpRemoteDir.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				actionUpRemoteDir.run();
			}
		});

		Table tableRemote = new Table(compositeRemoteDir, SWT.BORDER);
		TableColumn tcFileRemote = new TableColumn(tableRemote, SWT.LEFT);
		tcFileRemote.setText("Name");

		TableColumn tcSizeRemote = new TableColumn(tableRemote, SWT.NULL);
		tcSizeRemote.setText("Size");

		TableColumn tcDateRemote = new TableColumn(tableRemote, SWT.NULL);
		tcDateRemote.setText("Date");

		tcFileRemote.setWidth(200);
		tcSizeRemote.setWidth(100);
		tcDateRemote.setWidth(100);
		tableRemote.setHeaderVisible(true);

		tableRemote.setLayoutData(new GridData(GridData.FILL_BOTH));
		remoteDirBrowser = new RemoteDirectoryBrowser(tableRemote);

		tableRemote.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				IStructuredSelection selection =
					(IStructuredSelection) remoteDirBrowser.getSelection();
				FTPFile file = (FTPFile) selection.getFirstElement();
				if (file != null && file.isDirectory()) {
					try {
						ftp.changeWorkingDirectory(file.getName());
						labelPathRemote.setText(
							"Path: " + ftp.printWorkingDirectory());
						remoteDirBrowser.setInput(ftp.listFiles());
					} catch (IOException e) {
						logError(e.toString());
					}
				}
			}
		});

		// the log box.
		textLog =
			new StyledText(
				verticalForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

		localDirBrowser.setInput(File.listRoots()[0]);
		labelPathLocal.setText("Path: " + File.listRoots()[0]);
		
		// resize sashform children.
		verticalForm.setWeights(new int[]{4, 1});

		// adding drag and drop support.
		dragNDropSupport();
		
		getToolBarControl().setBackground(
			new Color(getShell().getDisplay(), 230, 230, 230));

		getShell().setImage(new Image(getShell().getDisplay(), "icons/ftp/ftp.gif"));
		getShell().setText("FTP Client v1.0");
		
		return composite;
	}

	private void logMessage(String message, boolean showInStatusBar) {
		StyleRange styleRange1 = new StyleRange();
		styleRange1.start = textLog.getCharCount();
		styleRange1.length = message.length();
		styleRange1.foreground = getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN);
		styleRange1.fontStyle = SWT.NORMAL;
		
		textLog.append(message + "\r\n");
		textLog.setStyleRange(styleRange1);
		textLog.setSelection(textLog.getCharCount());
		
		if(showInStatusBar) {
			setStatus(message);
		}
	}

	private void logError(String message) {
		StyleRange styleRange1 = new StyleRange();
		styleRange1.start = textLog.getCharCount();
		styleRange1.length = message.length();
		styleRange1.foreground = getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_RED);
		styleRange1.fontStyle = SWT.NORMAL;		
		
		textLog.append(message + "\r\n");
		textLog.setStyleRange(styleRange1);
		textLog.setSelection(textLog.getCharCount());
	}

	public static void main(String[] args) {
		ApplicationWindow window = new FTPWindow(null);
		window.setBlockOnOpen(true);

		window.open();
		Display.getCurrent().dispose();
	}
}
