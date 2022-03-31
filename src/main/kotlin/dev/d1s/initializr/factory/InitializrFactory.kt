package dev.d1s.initializr.factory

import dev.d1s.initializr.api.Initializr
import dev.d1s.initializr.impl.InitializrImpl

public fun initializr(): Initializr = InitializrImpl()