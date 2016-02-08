/*
 * Copyright 2016 Carlos Ballesteros Velasco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jtransc;

import jtransc.annotation.JTranscInvisible;

import java.lang.reflect.Method;

@JTranscInvisible
public class JTranscAnnotationBase {
	@Override
	public String toString() {
		String out = "";
		out += "@";
		out += this.getClass().getName();
		out += "(";
		String args = "";
		for (Method method : this.getClass().getDeclaredMethods()) {
			if (args.length() != 0) args += ", ";
			try {
				out += method.getName() + "=" + method.invoke(this);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		out += ")";
		return out;
	}
}
