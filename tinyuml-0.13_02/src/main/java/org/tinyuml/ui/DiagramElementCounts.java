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
package org.tinyuml.ui;

import org.tinyuml.draw.CompositeNode;
import org.tinyuml.draw.DiagramElement;
import org.tinyuml.umldraw.shared.NoteElement;
import org.tinyuml.umldraw.structure.ClassElement;
import org.tinyuml.umldraw.structure.ComponentElement;
import org.tinyuml.umldraw.structure.PackageElement;
import org.tinyuml.umldraw.structure.StructureDiagram;

/**
 * Counts the node categories shown in the diagram status bar.
 */
public final class DiagramElementCounts {

  private int packages;
  private int classes;
  private int components;
  private int notes;

  private DiagramElementCounts() { }

  /**
   * Counts categorized diagram nodes, including nodes nested in composites.
   * Connections are omitted because they are not one of the node categories.
   * @param diagram the diagram to count
   * @return the counts for the diagram
   */
  public static DiagramElementCounts count(StructureDiagram diagram) {
    DiagramElementCounts result = new DiagramElementCounts();
    for (DiagramElement element : diagram.getChildren()) {
      result.countElement(element);
    }
    return result;
  }

  private void countElement(DiagramElement element) {
    if (element instanceof PackageElement) {
      packages++;
    } else if (element instanceof ClassElement) {
      classes++;
    } else if (element instanceof ComponentElement) {
      components++;
    } else if (element instanceof NoteElement) {
      notes++;
    }

    if (element instanceof CompositeNode) {
      for (DiagramElement child :
        ((CompositeNode) element).getChildren()) {
        countElement(child);
      }
    }
  }

  public int getTotal() { return packages + classes + components + notes; }

  public int getPackages() { return packages; }

  public int getClasses() { return classes; }

  public int getComponents() { return components; }

  public int getNotes() { return notes; }
}
