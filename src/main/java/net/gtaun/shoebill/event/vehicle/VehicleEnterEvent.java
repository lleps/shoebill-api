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

package net.gtaun.shoebill.event.vehicle;

import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.Interruptable;

/**
 * 
 * 
 * @author MK124
 */
public class VehicleEnterEvent extends VehicleEvent implements Interruptable
{
	private Player player;
	private boolean isPassenger;
	
	
	public VehicleEnterEvent(Vehicle vehicle, Player player, boolean isPassenger)
	{
		super(vehicle);
		this.player = player;
		this.isPassenger = isPassenger;
	}
	
	@Override
	public void interrupt()
	{
		super.interrupt();
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public boolean isPassenger()
	{
		return isPassenger;
	}
}
