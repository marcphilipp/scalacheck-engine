package org.scalacheck.engine

import org.junit.platform.engine.TestDescriptor.Type
import org.junit.platform.engine.{TestDescriptor, UniqueId}
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor
import org.junit.platform.engine.support.hierarchical.Node
import org.opentest4j.AssertionFailedError
import org.scalacheck.Test.TestCallback
import org.scalacheck.util.Pretty
import org.scalacheck.{Prop, Test}

case class PropertyTestDescriptor(uniqueId: UniqueId, displayName: String, prop: Prop)
  extends AbstractTestDescriptor(uniqueId, displayName)
    with Node[ScalaCheckExecutionContext] {
  override def getType: Type = Type.TEST

  override def execute(context: ScalaCheckExecutionContext, dynamicTestExecutor: Node.DynamicTestExecutor): ScalaCheckExecutionContext = {
    var testResult: Option[Test.Result] = None
    Test.check(prop) {
      _.withTestCallback(new TestCallback {
        override def onTestResult(name: String, result: Test.Result): Unit = {
          testResult = Some(result)
        }
      })
    }
    maybeThrowException(testResult.get)
    context
  }

  private def maybeThrowException(result: Test.Result): Unit = {
    if (!result.passed) {
      import Pretty.prettyTestRes
      throw new AssertionFailedError(Pretty.pretty(result))
    }
  }
}
