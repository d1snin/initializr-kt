package dev.d1s.initializr.service

import dev.d1s.initializr.domain.api.Project
import dev.d1s.initializr.domain.api.ProjectConfiguration

internal interface InitializrService {

    suspend fun newProject(configuration: ProjectConfiguration): Project
}