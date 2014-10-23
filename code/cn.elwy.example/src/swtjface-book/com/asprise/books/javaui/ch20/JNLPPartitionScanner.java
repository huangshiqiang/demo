package com.asprise.books.javaui.ch20;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class JNLPPartitionScanner extends RuleBasedPartitionScanner {
	public final static String JNLP_DEFAULT = "JNLP_DEFAULT";
	public final static String JNLP_COMMENT = "JNLP_COMMENT";
	public final static String JNLP_TAG = "JNLP_TAG";

	public JNLPPartitionScanner() {

		IToken comment = new Token(JNLP_COMMENT);
		IToken tag = new Token(JNLP_TAG);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("<!--", "-->", comment);
		rules[1] = new TagRule(tag);

		setPredicateRules(rules);
	}
}

class TagRule extends MultiLineRule {

	public TagRule(IToken token) {
		super("<", ">", token);
	}
	
	protected boolean sequenceDetected(
		ICharacterScanner scanner,
		char[] sequence,
		boolean eofAllowed) {
		int c = scanner.read();
		if (sequence[0] == '<') {
			if (c == '?') {
				// aborts in case of a processing instruction
				scanner.unread();
				return false;
			}
			if (c == '!') {
				scanner.unread();
				// aborts in case of a comment
				return false;
			}
		} else if (sequence[0] == '>') {
			scanner.unread();
		}
		return super.sequenceDetected(scanner, sequence, eofAllowed);
	}
}
