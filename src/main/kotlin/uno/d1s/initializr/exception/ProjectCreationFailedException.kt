package uno.d1s.initializr.exception

public class ProjectCreationFailedException(errorMessage: String) :
    RuntimeException("Project creation failed: $errorMessage")