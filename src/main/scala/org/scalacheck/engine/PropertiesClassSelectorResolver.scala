package org.scalacheck.engine

import java.util.Optional

import org.junit.platform.commons.support.ReflectionSupport
import org.junit.platform.engine.discovery.ClassSelector
import org.junit.platform.engine.support.discovery.SelectorResolver
import org.junit.platform.engine.support.discovery.SelectorResolver.{Context, Match, Resolution}
import org.scalacheck.Properties

import scala.compat.java8.OptionConverters._

case class PropertiesClassSelectorResolver() extends SelectorResolver {
  override def resolve(selector: ClassSelector, context: Context): Resolution =
    Option(selector.getJavaClass)
      .filter(c => classOf[Properties].isAssignableFrom(c))
      .flatMap(clazz => context.addToParent(parent => {
        val uniqueId = parent.getUniqueId.append("class", clazz.getName)
        val properties = ReflectionSupport.newInstance(clazz).asInstanceOf[Properties]
        Optional.of(new PropertiesTestDescriptor(uniqueId, properties))
      }).asScala)
      .map(t => Match.exact(t))
      .map(m => Resolution.`match`(m))
      .getOrElse(Resolution.unresolved())
}
