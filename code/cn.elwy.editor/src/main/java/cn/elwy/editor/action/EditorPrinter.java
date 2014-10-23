package cn.elwy.editor.action;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

public class EditorPrinter {
    private Printer printer; // 打印对象

    private String fileName; // 文件名（带路径）

    private String contents; // 文件名对应的文件内容

    private GC gc; // 一个GC对象

    private int xPos, yPos; // 打印对象printer用的坐标

    private Rectangle bounds; // 打印空间

    private StringBuffer buf; // Holds a word at a time

    private int lineHeight; // The height of a line of text

    /**
     * 构造函数
     */
    public EditorPrinter(Printer printer, String fileName, String contents) {
        this.printer = printer;
        this.fileName = fileName;
        this.contents = contents;
    }

    /**
     * 打印文件的方法
     */
    public void print() {
        if (printer.startJob(fileName)) {// 开始打印任务
            // 设定打印空间
            bounds = computePrintArea(printer);
            xPos = bounds.x;
            yPos = bounds.y;
            // 创建GC对象
            gc = new GC(printer);
            // 设定线的高度
            lineHeight = gc.getFontMetrics().getHeight();
            // 设定tab键的空格数
            int tabWidth = gc.stringExtent(" ").x;
            // 开始打印
            printer.startPage();
            buf = new StringBuffer();
            char c;
            for (int i = 0, n = contents.length(); i < n; i++) {
                // 得到文件内容的字符
                c = contents.charAt(i);
                // 如果读到\n，调用printBuffer方法将buf中的字符打印，并换行
                if (c == '\n') {
                    printBuffer();
                    printNewline();
                }
                // 如果读到\t，表示要跳过一定的空格
                else if (c == '\t') {
                    xPos += tabWidth;
                } else {
                    buf.append(c);// 将字符添加进buf变量
                    // 检查是否有空间，如果则打印
                    if (Character.isWhitespace(c))
                        printBuffer();

                }
            }
            printer.endPage();
            printer.endJob();
            gc.dispose();
        }
    }

    /**
     * 打印缓存buf变量中的字符
     */
    private void printBuffer() {
        // 取得缓存中的字符宽度
        int width = gc.stringExtent(buf.toString()).x;
        // 如果宽度不够，则换行
        if (xPos + width > bounds.x + bounds.width)
            printNewline();
        // 打印缓存buf变量中的字符
        gc.drawString(buf.toString(), xPos, yPos, false);
        xPos += width;
        buf.setLength(0);
    }

    /**
     * 打印换行的方法
     */
    private void printNewline() {
        // 重设新行的坐标
        xPos = bounds.x;
        yPos += lineHeight;
        // 如果超过页长度，则换一页打印
        if (yPos > bounds.y + bounds.height) {
            yPos = bounds.y;
            printer.endPage();
            printer.startPage();
        }
    }

    /**
     * 计算打印空间的方法
     */
    private Rectangle computePrintArea(Printer printer) {
        // 取得打印空间
        Rectangle rect = printer.getClientArea();
        Rectangle trim = printer.computeTrim(0, 0, 0, 0);
        // 取得打印机的DPI（DPI：表示每英寸的点数,即通常说的打印机的分辨率）
        Point dpi = printer.getDPI();
        // 计算可打印空间
        int left = trim.x + dpi.x;
        if (left < rect.x)
            left = rect.x;
        int right = (rect.width + trim.x + trim.width) - dpi.x;
        if (right > rect.width)
            right = rect.width;
        int top = trim.y + dpi.y;
        if (top < rect.y)
            top = rect.y;
        int bottom = (rect.height + trim.y + trim.height) - dpi.y;
        if (bottom > rect.height)
            bottom = rect.height;
        return new Rectangle(left, top, right - left, bottom - top);
    }
}
