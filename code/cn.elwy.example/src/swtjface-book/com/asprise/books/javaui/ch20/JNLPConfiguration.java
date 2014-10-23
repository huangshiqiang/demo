package com.asprise.books.javaui.ch20;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.ContentFormatter;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class JNLPConfiguration extends SourceViewerConfiguration {
	private ColorManager colorManager = new ColorManager();

	public JNLPConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			JNLPPartitionScanner.JNLP_COMMENT,
			JNLPPartitionScanner.JNLP_TAG };
	}

	PresentationReconciler reconciler;

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		if (reconciler != null)
			return reconciler;

		reconciler = new PresentationReconciler();

		// 1) Demager and repairer for JNLP tags.
		RuleBasedScanner scanner = new RuleBasedScanner();

		IToken stringColor =
			new Token(
				new TextAttribute(
					colorManager.getColor(IJNLPColorConstants.STRING)));

		IRule[] rules = new IRule[2];

		// the rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", stringColor, '\\');
		// the rule whitespace rule.
		IWhitespaceDetector whitespaceDetector = new IWhitespaceDetector() {
			public boolean isWhitespace(char c) {
				return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
			}
		};

		rules[1] = new WhitespaceRule(whitespaceDetector);
		scanner.setRules(rules);
		scanner.setDefaultReturnToken(
			new Token(
				new TextAttribute(
					colorManager.getColor(IJNLPColorConstants.TAG))));

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);

		reconciler.setDamager(dr, JNLPPartitionScanner.JNLP_TAG);
		reconciler.setRepairer(dr, JNLPPartitionScanner.JNLP_TAG);

		// 2) Demager and repairer for JNLP default content type.
		IToken procInstrColor =
			new Token(
				new TextAttribute(
					colorManager.getColor(IJNLPColorConstants.PROC_INSTR)));

		rules = new IRule[2];
		// the rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstrColor);
		// the rule for generic whitespac.
		rules[1] = new WhitespaceRule(whitespaceDetector);

		scanner = new RuleBasedScanner();
		scanner.setRules(rules);
		scanner.setDefaultReturnToken(
			new Token(
				new TextAttribute(
					colorManager.getColor(IJNLPColorConstants.DEFAULT))));

		dr = new DefaultDamagerRepairer(scanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		//		NonRuleBasedDamagerRepairer ndr =
		//			new NonRuleBasedDamagerRepairer(
		//				new TextAttribute(
		//					colorManager.getColor(IJNLPColorConstants.XML_COMMENT)));
		//		reconciler.setDamager(ndr, JNLPPartitionScanner.JNLP_COMMENT);
		//		reconciler.setRepairer(ndr, JNLPPartitionScanner.JNLP_COMMENT);

		return reconciler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new ContentAssistant();

		IContentAssistProcessor processor = new JNLPCAProcessor();

		assistant.setContentAssistProcessor(
			processor,
			JNLPPartitionScanner.JNLP_TAG);
		assistant.setContentAssistProcessor(
			processor,
			JNLPPartitionScanner.JNLP_DEFAULT);

		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500); // 0.5 s.

		return assistant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentFormatter(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		ContentFormatter formatter = new ContentFormatter();
		IFormattingStrategy strategy = new JNLPFormatStrategy();
		//formatter.setFormattingStrategy(strategy,
		// JNLPPartitionScanner.JNLP_TAG);
		formatter.setFormattingStrategy(strategy);
		return formatter;
	}

}