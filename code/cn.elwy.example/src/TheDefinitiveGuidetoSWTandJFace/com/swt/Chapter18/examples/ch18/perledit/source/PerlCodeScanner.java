package com.swt.Chapter18.examples.ch18.perledit.source;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import com.swt.Chapter18.examples.ch18.perledit.PerlEditor;

/**
 * This class scans through a code partition and colors it.
 */
public class PerlCodeScanner extends RuleBasedScanner {
    /**
     * PerlCodeScanner constructor
     */
    public PerlCodeScanner() {
        // Get the color manager
        ColorManager cm = PerlEditor.getApp().getColorManager();

        // Create the tokens for keywords, strings, and other (everything else)
        IToken keyword = new Token(new TextAttribute(cm.getColor(ColorManager.KEYWORD),
                cm.getColor(ColorManager.BACKGROUND), SWT.BOLD));
        IToken other = new Token(new TextAttribute(cm.getColor(ColorManager.DEFAULT)));
        IToken string = new Token(new TextAttribute(cm.getColor(ColorManager.STRING)));

        // Use "other" for default
        setDefaultReturnToken(other);

        // Create the rules
        List rules = new ArrayList();

        // Add rules for strings
        rules.add(new SingleLineRule("\"", "\"", string, '\\'));
        rules.add(new SingleLineRule("'", "'", string, '\\'));

        // Add rule for whitespace
        rules.add(new WhitespaceRule(new IWhitespaceDetector() {
            public boolean isWhitespace(char c) {
                return Character.isWhitespace(c);
            }
        }));

        // Add rule for keywords, and add the words to the rule
        WordRule wordRule = new WordRule(new PerlWordDetector(), other);
        for (int i = 0, n = PerlSyntax.KEYWORDS.length; i < n; i++)
            wordRule.addWord(PerlSyntax.KEYWORDS[i], keyword);
        rules.add(wordRule);

        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);
    }
}
