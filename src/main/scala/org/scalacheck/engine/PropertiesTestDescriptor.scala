package org.scalacheck.engine

import org.junit.platform.engine.TestDescriptor.Type
import org.junit.platform.engine.UniqueId
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.hierarchical.Node
import org.scalacheck.Properties

class PropertiesTestDescriptor(uniqueId: UniqueId, properties: Properties)
  extends AbstractTestDescriptor(uniqueId, properties.name)
    with Node[ScalaCheckExecutionContext] {
  override def getType: Type = Type.CONTAINER

  override def execute(context: ScalaCheckExecutionContext, dynamicTestExecutor: Node.DynamicTestExecutor): ScalaCheckExecutionContext = {
    context
  }
}
