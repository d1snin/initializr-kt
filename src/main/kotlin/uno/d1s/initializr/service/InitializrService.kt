package uno.d1s.initializr.service

import uno.d1s.initializr.domain.api.Project
import uno.d1s.initializr.domain.api.ProjectConfiguration

internal interface InitializrService {

    suspend fun newProject(configuration: ProjectConfiguration): Project
}