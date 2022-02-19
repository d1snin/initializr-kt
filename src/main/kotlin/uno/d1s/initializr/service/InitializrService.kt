package uno.d1s.initializr.service

import uno.d1s.initializr.domain.api.ProjectConfiguration
import java.util.concurrent.atomic.AtomicReference

internal interface InitializrService {

    var defaultProjectConfiguration: AtomicReference<ProjectConfiguration?>

    suspend fun newProject(configuration: ProjectConfiguration): ByteArray
}