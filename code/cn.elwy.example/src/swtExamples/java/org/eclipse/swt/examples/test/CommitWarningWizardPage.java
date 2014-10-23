package org.eclipse.swt.examples.test;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class CommitWarningWizardPage extends WizardPage {

    /**
     * Create the wizard.
     */
    public CommitWarningWizardPage() {
        super("wizardPage");
        setMessage("A\r\nB");
        setTitle("Wizard Page title");
        setDescription("Wizard Page description");
    }

    /**
     * Create contents of the wizard.
     * @param parent
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new GridLayout(1, false));

        SashForm sashForm = new SashForm(container, SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        List list = new List(sashForm, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        list.setItems(new String[] { "File 1.java", "File 1.java", "File 1.java" });
        list.setBounds(0, 0, 71, 68);
        sashForm.setWeights(new int[] { 1 });
    }
}
