package dev.d1s.initializr.dsl.converter

import java.util.concurrent.atomic.AtomicReference

internal interface DslConverter<D, A> {

    var defaults: AtomicReference<D>

    fun toApi(dsl: D): A
}