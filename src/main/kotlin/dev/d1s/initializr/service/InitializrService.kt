package dev.d1s.initializr.service

import dev.d1s.initializr.api.Project
import dev.d1s.initializr.api.ProjectConfiguration

internal interface InitializrService {

    suspend fun newProject(configuration: ProjectConfiguration): Project
}