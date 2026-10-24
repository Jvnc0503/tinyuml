/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package test.tinyuml.ui;

import junit.framework.TestCase;
import org.tinyuml.model.ElementType;
import org.tinyuml.model.RelationType;
import org.tinyuml.model.UmlModelImpl;
import org.tinyuml.ui.DiagramElementCounts;
import org.tinyuml.umldraw.shared.UmlNode;
import org.tinyuml.umldraw.structure.PackageElement;
import org.tinyuml.umldraw.structure.StructureDiagram;

/**
 * Tests category counts for diagram elements.
 */
public class DiagramElementCountsTest extends TestCase {

  public void testCountsNestedElementsAndExcludesRelations() {
    StructureDiagram diagram = new StructureDiagram(new UmlModelImpl());
    PackageElement pkg = (PackageElement)
      diagram.getElementFactory().createNode(ElementType.PACKAGE);
    UmlNode nestedClass =
      diagram.getElementFactory().createNode(ElementType.CLASS);
    pkg.addChild(nestedClass);
    diagram.addChild(pkg);

    UmlNode classElement =
      diagram.getElementFactory().createNode(ElementType.CLASS);
    UmlNode component =
      diagram.getElementFactory().createNode(ElementType.COMPONENT);
    UmlNode note = diagram.getElementFactory().createNode(ElementType.NOTE);
    diagram.addChild(classElement);
    diagram.addChild(component);
    diagram.addChild(note);
    diagram.addChild(diagram.getElementFactory().createConnection(
      RelationType.DEPENDENCY, classElement, nestedClass));

    DiagramElementCounts counts = DiagramElementCounts.count(diagram);

    assertEquals(5, counts.getTotal());
    assertEquals(1, counts.getPackages());
    assertEquals(2, counts.getClasses());
    assertEquals(1, counts.getComponents());
    assertEquals(1, counts.getNotes());
  }

  public void testEmptyDiagramHasZeroCounts() {
    DiagramElementCounts counts =
      DiagramElementCounts.count(new StructureDiagram(new UmlModelImpl()));

    assertEquals(0, counts.getTotal());
    assertEquals(0, counts.getPackages());
    assertEquals(0, counts.getClasses());
    assertEquals(0, counts.getComponents());
    assertEquals(0, counts.getNotes());
  }
}
