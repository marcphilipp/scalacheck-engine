package org.scalacheck.engine

import org.junit.platform.engine.TestDescriptor.Type
import org.junit.platform.engine.UniqueId
import org.junit.platform.engine.support.descriptor.{AbstractTestDescriptor, ClassSource}
import org.junit.platform.engine.support.hierarchical.Node
import org.junit.platform.engine.support.hierarchical.Node.DynamicTestExecutor
import org.scalacheck.Properties

class PropertiesTestDescriptor(uniqueId: UniqueId, properties: Properties)
  extends AbstractTestDescriptor(uniqueId, properties.name, ClassSource.from(properties.getClass))
    with Node[ScalaCheckExecutionContext] {
  override def getType: Type = Type.CONTAINER

  override def mayRegisterTests(): Boolean = true

  override def execute(context: ScalaCheckExecutionContext, dynamicTestExecutor: DynamicTestExecutor): ScalaCheckExecutionContext = {
    properties.properties.foreach { case (name, prop) =>
      val descriptor = PropertyTestDescriptor(uniqueId.append("prop", name), name, prop)
      addChild(descriptor)
      dynamicTestExecutor.execute(descriptor)
    }
    context
  }
}
