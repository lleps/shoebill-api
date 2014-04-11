/**
 * Copyright (C) 2011 MK124
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.gtaun.shoebill.object;

import net.gtaun.shoebill.constant.DialogStyle;

/**
 * 
 * 
 * @author MK124
 */
public interface DialogId extends Destroyable, Proxyable<DialogId>
{
    /**
     * Gets the id of the Dialog
     * @return The DialogId
     */
	int getId();

    /**
     * Show the Dialog to a Player with Params.
     * @param player The Player which will see the Dialog
     * @param style The DialogStyle
     * @param caption The Caption of the Dialog
     * @param text The Text in the Dialog
     * @param button1 The Text of Button 1
     * @param button2 The Text of Button 2
     */
	void show(Player player, DialogStyle style, String caption, String text, String button1, String button2);

    /**
     * Closes the Dialog of the Player
     * @param player The Player
     */
	void cancel(Player player);
}