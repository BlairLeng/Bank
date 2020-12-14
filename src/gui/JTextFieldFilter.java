package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class JTextFieldFilter extends PlainDocument {
	public static final String DOUBLE = "0123456789.";
	protected String acceptedChars = null;
	protected boolean negativeAccepted = false;
	public JTextFieldFilter() {
		this(DOUBLE);
	}

	public JTextFieldFilter(String acceptedChars) {
		this.acceptedChars = acceptedChars;
	}

	// negativeAccepted: accept negative or not
	public void setNegativeAccepted(boolean negativeAccepted) {
		if (acceptedChars.equals(DOUBLE)) {
			this.negativeAccepted = negativeAccepted;
			acceptedChars += "-";
		}
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		for (int i = 0; i < str.length(); i++) {
			if (!acceptedChars.contains(str)) {
				return;
			}
		}
		// �жϵ�ǰ������Ƿ�Ϊ".", ����������ж�ǰ����û�������
		if (acceptedChars.equals(DOUBLE) || (acceptedChars.equals(DOUBLE + "-") && negativeAccepted)) {
			if (str.contains(".")) {
				if (getText(0, getLength()).contains(".")) {
					return;
				}
			}
		}
		// �ж����֧�ָ���, ��ô����(-)�����ڵ�һλ
		if (negativeAccepted) {
			if (str.indexOf("-") != 0 || offset != 0) {
			return;
			}
		}
		super.insertString(offset, str, attr);
	}
}
