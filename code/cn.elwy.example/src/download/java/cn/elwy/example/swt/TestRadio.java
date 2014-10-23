package cn.elwy.example.swt;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class TestRadio {

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestRadio window = new TestRadio();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		// 初始化窗体资源，并设置窗体名称
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(500, 284);
		shell.setText("单选按钮测试");
		//

		shell.open();

		// 声明一个分组类
		final Group group = new Group(shell, SWT.NONE);
		// 设置分组类名称
		group.setText("性别");
		// 设置分组类的位置和大小
		group.setBounds(22, 10, 188, 89);

		// 声明一个单选按钮,注意，这里是增加到开始创建的第一个分组框种
		final Button button_1 = new Button(group, SWT.RADIO);
		// 设置单选按钮的名称
		button_1.setText("男");
		// 设置单选按钮的位置和大小
		button_1.setBounds(10, 26, 93, 16);

		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.setSelection(true);
		button_2.setText("女");
		button_2.setBounds(10, 63, 93, 16);

		final Group group_1 = new Group(shell, SWT.NONE);
		group_1.setText("爱好");
		group_1.setBounds(22, 122, 188, 118);

		final Button button_3 = new Button(group_1, SWT.RADIO);
		button_3.setSelection(true);
		button_3.setText("游泳");
		button_3.setBounds(10, 23, 93, 16);

		final Button button_4 = new Button(group_1, SWT.RADIO);
		button_4.setText("看书");
		button_4.setBounds(10, 57, 93, 16);

		final Button button_5 = new Button(group_1, SWT.RADIO);
		button_5.setText("睡觉");
		button_5.setBounds(10, 92, 93, 16);

		final Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			// 按钮单击事件，显示两个组的选择情况
			public void widgetSelected(final SelectionEvent e) {
				// Button类是Control的子类，可以直接强转
				Control[] c = group.getChildren();
				Button tmpb = null;
				StringBuffer result = new StringBuffer();
				result.append("性别分组选择的结果是:");
				for (Control control : c) {
					tmpb = (Button) control;
					if (tmpb.getSelection()) {
						result.append(tmpb.getText());
					}
				}
				result.append("\n爱好分组选择的结果是:");
				c = group_1.getChildren();
				for (Control control : c) {
					tmpb = (Button) control;
					if (tmpb.getSelection()) {
						result.append(tmpb.getText());
					}
				}

				MessageBox msg = new MessageBox(shell, SWT.ICON_INFORMATION
						| SWT.OK);
				msg.setText("选择结果");
				msg.setMessage(result.toString());
				msg.open();
			}
		});
		button.setText("显示结果");
		button.setBounds(295, 161, 101, 42);
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}