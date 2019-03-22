package com.javacore.wisdom.util;

import static com.google.common.base.Strings.isNullOrEmpty;

public final class StringUtil {
	


	public static boolean isNullOrWhitespace(final String str) {
		if (isNullOrEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}
	

}
