package uno.d1s.initializr.dsl

import uno.d1s.initializr.domain.api.*
import java.util.*

public class ProjectConfigurationDsl {

    public var type: ProjectType? = null

    public var language: ProjectLanguage? = null

    public var springBootVersion: String? = null

    public var group: String? = null

    public var artifact: String? = null

    public var packageName: String? = null

    public var name: String? = null

    public var description: String? = null

    public var packaging: ProjectPackaging? = null

    public var javaVersion: JavaVersion? = null

    public val dependencies: EnumSet<ProjectDependency> = EnumSet.noneOf(ProjectDependency::class.java)
}