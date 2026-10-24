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
package org.tinyuml.ui.diagram;

import java.awt.event.MouseEvent;
import org.tinyuml.draw.Scaling;

/**
 * This class represents an editor specific mouse event. The properties of the
 * original MouseEvent are preserved by wrapping and delegating most
 * functionality to it.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class EditorMouseEvent {
  private MouseEvent event;
  private double scaleFactor;

  /**
   * Sets the mouse event and current editor scaling.
   * @param anEvent the MouseEvent
   * @param aScaleFactor the scale factor
   */
  public void setMouseEvent(MouseEvent anEvent, double aScaleFactor) {
    this.event = anEvent;
    this.scaleFactor = aScaleFactor;
  }

  /**
   * Sets the mouse event using one of the predefined scaling values.
   * @param anEvent the MouseEvent
   * @param aScaling the predefined scaling
   */
  public void setMouseEvent(MouseEvent anEvent, Scaling aScaling) {
    setMouseEvent(anEvent, aScaling.getScaleFactor());
  }

  /**
   * Returns the original mouse event.
   * @return the original mouse event
   */
  public MouseEvent getMouseEvent() { return event; }

  /**
   * Returns the x coordinate in the editor coordinate system.
   * @return the x coordinate
   */
  public double getX() {
    return event.getX() / scaleFactor;
  }

  /**
   * Returns the y coordinate in the editor coordinate system.
   * @return the y coordinate
   */
  public double getY() {
    return event.getY() / scaleFactor;
  }

  /**
   * Returns the click count.
   * @return the click count
   */
  public int getClickCount() { return event.getClickCount(); }

  /**
   * Determines if the mouse button pressed was the popup trigger.
   * Note that this function only returns the correct value on a mouseReleased
   * event (at least on Windows)
   * @return true if popup trigger, false otherwise
   */
  public boolean isPopupTrigger() { return event.isPopupTrigger(); }

  /**
   * Returns true if the event was triggered by the main button. This is to
   * compensate that we can not rely on isPopupTrigger().
   * @return true if main button, false otherwise
   */
  public boolean isMainButton() {
    return event.getButton() == MouseEvent.BUTTON1;
  }
}
