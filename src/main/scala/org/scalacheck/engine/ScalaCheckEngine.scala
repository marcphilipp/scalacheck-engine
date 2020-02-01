package org.scalacheck.engine

import org.junit.platform.engine.support.descriptor.EngineDescriptor
import org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolver
import org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine
import org.junit.platform.engine.{EngineDiscoveryRequest, ExecutionRequest, TestDescriptor, UniqueId}
import org.scalacheck.Properties

class ScalaCheckEngine extends HierarchicalTestEngine[ScalaCheckExecutionContext] {

  private val resolver = EngineDiscoveryRequestResolver.builder[EngineDescriptor]()
    .addClassContainerSelectorResolver(_.isAssignableFrom(classOf[Properties]))
    .addSelectorResolver(new PropertiesClassSelectorResolver) // TODO pass in class filter
    .build()

  override def getId: String = "scalacheck"

  override def discover(request: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor = {
    val descriptor = new EngineDescriptor(uniqueId, "ScalaCheck")
    resolver.resolve(request, descriptor)
    descriptor
  }

  override def createExecutionContext(request: ExecutionRequest) = new ScalaCheckExecutionContext
}
