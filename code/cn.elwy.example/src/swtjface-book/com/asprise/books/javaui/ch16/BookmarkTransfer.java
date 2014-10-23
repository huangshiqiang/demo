/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on 2004-4-27 19:58:51 by JACK $Id$
 *  
 ******************************************************************************/

package com.asprise.books.javaui.ch16;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

/**
 *  
 */
public class BookmarkTransfer extends ByteArrayTransfer {
	private static final String BOOKMARK_TRANSFER_NAME = "BOOKMARK";
	private static final int BOOKMARK_TRANSFER_ID =
		registerType(BOOKMARK_TRANSFER_NAME);
	private static final BookmarkTransfer instance = new BookmarkTransfer();

	public static BookmarkTransfer getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
	 */
	protected int[] getTypeIds() {
		return new int[] { BOOKMARK_TRANSFER_ID };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
	 */
	protected String[] getTypeNames() {
		return new String[] { BOOKMARK_TRANSFER_NAME };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.Transfer#javaToNative(java.lang.Object,
	 *      org.eclipse.swt.dnd.TransferData)
	 */
	protected void javaToNative(Object object, TransferData transferData) {
		if (object == null || !(object instanceof Bookmark))
			return;

		Bookmark bookmark = (Bookmark) object;

		if (isSupportedType(transferData)) {
			try {
				// Writes data to a byte array.
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(stream);
				out.writeUTF(bookmark.name);
				out.writeUTF(bookmark.href);
				out.writeUTF(bookmark.addDate);
				out.writeUTF(bookmark.lastVisited);
				out.writeUTF(bookmark.lastModified);
				out.close();

				super.javaToNative(stream.toByteArray(), transferData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.Transfer#nativeToJava(org.eclipse.swt.dnd.TransferData)
	 */
	protected Object nativeToJava(TransferData transferData) {
		if (isSupportedType(transferData)) {
			byte[] raw = (byte[]) super.nativeToJava(transferData);
			if (raw == null)
				return null;
			Bookmark bookmark = new Bookmark();

			try {
				ByteArrayInputStream stream = new ByteArrayInputStream(raw);
				DataInputStream in = new DataInputStream(stream);
				bookmark.name = in.readUTF();
				bookmark.href = in.readUTF();
				bookmark.addDate = in.readUTF();
				bookmark.lastVisited = in.readUTF();
				bookmark.lastModified = in.readUTF();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return bookmark;
		} else {
			return null;
		}
	}

}
