/*
 * Created on May 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.pc.bsp.common.util;

/**
 * @author simple
 */
public class Base64 {

	/**
	 *  
	 */
	public Base64() {
		super();
	}

	public String encode(String encodeStr) {
		int len;
		if (encodeStr.length() % 3 != 0)
			len = (encodeStr.length() + 3) - encodeStr.length() % 3;
		else
			len = encodeStr.length();
		for (; len - encodeStr.length() > 0; encodeStr = encodeStr + "\0")
			;
		int pad = 0;
		char tmpChar[] = new char[3];
		char bufChar[] = new char[4];
		String retStr = new String();
		for (int i = 0; i < len;) {
			for (int j = 0; j < 3; j++)
				if (encodeStr.charAt(i) != 0 && pad == 0) {
					tmpChar[j] = encodeStr.charAt(i++);
				} else {
					pad++;
					tmpChar[j] = '\0';
					i++;
				}

			bufChar[0] = (char) ((byte) tmpChar[0] >> 2);
			bufChar[1] = (char) (((byte) tmpChar[0] & 0x3) << 4 | (byte) tmpChar[1] >> 4);
			bufChar[2] = (char) (((byte) tmpChar[1] & 0xf) << 2 | (byte) tmpChar[2] >> 6);
			bufChar[3] = (char) ((byte) tmpChar[2] & 0x3f);
			for (int j = 0; j < 4; j++)
				if (bufChar[j] >= 0 && bufChar[j] <= '\031')
					bufChar[j] = (char) ((byte) bufChar[j] + 65);
				else if (bufChar[j] >= '\032' && bufChar[j] <= '3')
					bufChar[j] = (char) (((byte) bufChar[j] - 26) + 97);
				else if (bufChar[j] >= '4' && bufChar[j] <= '=')
					bufChar[j] = (char) (((byte) bufChar[j] - 52) + 48);
				else if (bufChar[j] == '>')
					bufChar[j] = '+';
				else if (bufChar[j] == '?')
					bufChar[j] = '/';

			if (pad != 0) {
				for (int j = 0; j < 4 - pad; j++)
					retStr = retStr + new String(bufChar, j, 1);

			} else {
				for (int j = 0; j < 4; j++)
					retStr = retStr + new String(bufChar, j, 1);

			}
		}

		while (pad != 0) {
			pad--;
			retStr = retStr + "=";
		}
		return retStr;
	}

	public String decode(String decodeStr) {
		if (decodeStr.length() % 4 != 0)
			return null;
		char tmpChar[] = new char[4];
		char bufChar[] = new char[3];
		String retStr = new String();
		int pad = 0;
		for (int i = 0; i < decodeStr.length();) {
			for (int j = 0; j < 4; j++)
				tmpChar[j] = decodeStr.charAt(i++);

			for (int j = 0; j < 4; j++)
				if (tmpChar[j] >= 'A' && tmpChar[j] <= 'Z')
					tmpChar[j] = (char) ((byte) tmpChar[j] - 65);
				else if (tmpChar[j] >= 'a' && tmpChar[j] <= 'z')
					tmpChar[j] = (char) ((byte) ((byte) tmpChar[j] - 97) + 26);
				else if (tmpChar[j] >= '0' && tmpChar[j] <= '9')
					tmpChar[j] = (char) ((byte) ((byte) tmpChar[j] - 48) + 52);
				else if (tmpChar[j] == '+')
					tmpChar[j] = '>';
				else if (tmpChar[j] == '/')
					tmpChar[j] = '?';
				else if (tmpChar[j] == '=') {
					pad++;
					tmpChar[j] = '\0';
				}

			bufChar[0] = (char) ((byte) tmpChar[0] << 2 | (byte) tmpChar[1] >> 4);
			bufChar[1] = (char) ((byte) tmpChar[1] << 4 | (byte) tmpChar[2] >> 2);
			bufChar[2] = (char) ((byte) tmpChar[2] << 6 | (byte) tmpChar[3]);
			for (int j = 0; j < 3 - pad; j++)
				retStr = retStr + String.valueOf((char) (byte) bufChar[j]);

		}

		return retStr;
	}

}
