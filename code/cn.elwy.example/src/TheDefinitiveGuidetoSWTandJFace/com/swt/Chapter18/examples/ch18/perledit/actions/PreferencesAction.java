package com.swt.Chapter18.examples.ch18.perledit.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;

import com.swt.Chapter18.examples.ch18.perledit.PerlEditor;
import com.swt.Chapter18.examples.ch18.perledit.preferences.TextPreferencePage;

/**
 * This action displays the preferences dialog
 */
public class PreferencesAction extends Action {
    /**
     * PreferencesAction constructor
     */
    public PreferencesAction() {
        super("P&references...@Ctrl+R", ImageDescriptor.createFromFile(PreferencesAction.class, "/images/prefs.gif"));
        setToolTipText("Preferences");
    }

    /**
     * Runs the action
     */
    public void run() {
        PreferenceManager mgr = new PreferenceManager();
        mgr.addToRoot(new PreferenceNode("text", "Text", null, TextPreferencePage.class.getName()));

        PreferenceDialog dlg = new PreferenceDialog(PerlEditor.getApp().getMainWindow().getShell(), mgr);
        dlg.setPreferenceStore(PerlEditor.getApp().getPreferences());
        dlg.open();
    }
}
