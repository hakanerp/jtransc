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

package com.jtransc.ast.dce

import com.jtransc.ast.AstClass
import com.jtransc.ast.AstField
import com.jtransc.ast.AstMethod
import com.jtransc.ast.AstProgram

// Dead Code Elimination
fun SimpleDCE(program: AstProgram): AstProgram {
	val mainClass = program.get(program.entrypoint)
	val mainMethod = mainClass.getMethodSure("main", "([Ljava/lang/String;)V")

	var classesToAnalyze = arrayListOf<AstClass>()
	var methodsToAnalyze = arrayListOf<AstMethod>()
	var fieldsToAnalyze = arrayListOf<AstField>()

	classesToAnalyze.add(mainClass)
	methodsToAnalyze.add(mainMethod)
	//fieldsToAnalyze

	println(mainMethod.dependencies)

	return program
}