package com.jtransc.text;

import com.jtransc.annotation.JTranscMethodBody;
import com.jtransc.annotation.haxe.HaxeMethodBody;
import com.jtransc.text.internal.RealToString;

@SuppressWarnings("IndexOfReplaceableByContains")
public class JTranscStringTools {
	//@JTranscMethodBody(target = "js", value = "return N.str('' + p0);")
	public static String toString(float v) {
		//return RealToString.getInstance().floatToString(v);
		String out = toString((double) v);
		int index = out.indexOf('.');
		if (index >= 0) {
			return out.substring(0, Math.min(out.length(), index + 6));
		} else {
			return out;
		}
		//return _toString(v);
	}

	//@JTranscMethodBody(target = "js", value = "return N.str('' + p0);")
	//public static String toString(float v) {
	//	//return JTranscStringTools.toString(d);
	//	String out = _toString(v);
	//	boolean hasSymbols = false;
	//	for (int n = 0; n < out.length(); n++) {
	//		char c = out.charAt(n);
	//		if (!Character.isDigit(c) && c != '-') {
	//			hasSymbols = true;
	//			break;
	//		}
	//	}
	//	if (out.indexOf("e+") >= 0) out = out.replace("e+", "E");
	//	if (out.indexOf("e-") >= 0) out = out.replace("e-", "E-");
	//	return hasSymbols ? out : (out + ".0");
	//}

	//@JTranscMethodBody(target = "js", value = "return N.str('' + p0);")
	public static String toString(double v) {
		if (Double.isNaN(v)) return "NaN";
		if (Double.isInfinite(v)) return (v < 0) ? "-Infinity" : "Infinity";
		//return JTranscStringTools.toString(d);
		String out = _toString(v);
		boolean hasSymbols = false;
		for (int n = 0; n < out.length(); n++) {
			char c = out.charAt(n);
			if (!Character.isDigit(c) && c != '-') {
				hasSymbols = true;
				break;
			}
		}
		if (out.indexOf("e+") >= 0) out = out.replace("e+", "E");
		if (out.indexOf("e-") >= 0) out = out.replace("e-", "E-");
		return hasSymbols ? out : (out + ".0");
	}

	//@JTranscMethodBody(target = "js", value = "return N.str(String(N.isNegativeZero(+p0) ? '-0' : +Math.fround(p0)));")
	//@JTranscMethodBody(target = "cpp", value = "wchar_t temp[128] = {0}; swprintf(temp, sizeof(temp), L\"%f\", (float)p0); return N::str(std::wstring(temp));")
	////@JTranscMethodBody(target = "js", value = "return N.str(String(Number(p0)));")
	////@JTranscMethodBody(target = "js", value = "return N.str(Number(p0).toPrecision(2));")
	//native static public String _toString(float v);

	@HaxeMethodBody("return N.str(N.isNegativeZero(p0) ? '-0' : '$p0');")
	@JTranscMethodBody(target = "js", value = "return N.str(String(N.isNegativeZero(+p0) ? '-0' : +p0));")
	@JTranscMethodBody(target = "cpp", value = "wchar_t temp[128] = {0}; swprintf(temp, sizeof(temp), L\"%g\", p0); return N::str(std::wstring(temp));")
	//@JTranscMethodBody(target = "js", value = "return N.str(String(Number(p0)));")
	//@JTranscMethodBody(target = "js", value = "return N.str(Number(p0).toPrecision(2));")
	//@JTranscMethodBody(target = "d", value = "return N.str(to!string(p0));")
	@JTranscMethodBody(target = "d", value = "return N.str(format(\"%.16g\", p0));")
	native static public String _toString(double v);
}
