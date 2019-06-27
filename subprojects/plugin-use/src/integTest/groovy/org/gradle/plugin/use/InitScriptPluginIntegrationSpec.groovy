/*
 * Copyright 2019 the original author or authors.
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

package org.gradle.plugin.use

import org.gradle.test.fixtures.file.LeaksFileHandles
import org.gradle.test.fixtures.server.http.MavenHttpModule
import org.intellij.lang.annotations.Language

@LeaksFileHandles
class InitScriptPluginIntegrationSpec extends AbstractPluginSpec {

    def setup() {
        executer.requireGradleDistribution() // need accurate classloading
    }

    def "init script with plugin block"() {
        given:
        MavenHttpModule module =
            publishInitPlugin("System.out.println(\"Executing a 'Gradle' plugin\")")

        File initFile = file("init.gradle")
        initFile << USE

        when:
        executer.usingInitScript(initFile)

        then:
        succeeds 'help'
    }
}
