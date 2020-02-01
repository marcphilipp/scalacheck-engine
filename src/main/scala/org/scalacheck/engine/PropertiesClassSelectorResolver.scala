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
      .filter(classOf[Properties].isAssignableFrom(_))
      .flatMap(propertiesClass => context.addToParent(parent => {
        val uniqueId = parent.getUniqueId.append("class", propertiesClass.getName)
        val properties = ReflectionSupport.newInstance(propertiesClass).asInstanceOf[Properties]
        Optional.of(new PropertiesTestDescriptor(uniqueId, properties))
      }).asScala)
      .map(Match.exact)
      .map(Resolution.`match`)
      .getOrElse(Resolution.unresolved())
}
