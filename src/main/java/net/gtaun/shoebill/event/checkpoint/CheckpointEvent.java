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

package net.gtaun.shoebill.event.checkpoint;

import net.gtaun.shoebill.object.primitive.CheckpointPrim;
import net.gtaun.shoebill.object.primitive.PlayerPrim;
import net.gtaun.shoebill.util.event.Event;

/**
 * @author MK124
 *
 */

public abstract class CheckpointEvent extends Event
{
	private PlayerPrim player;
	private CheckpointPrim checkpoint;
	
	
	protected CheckpointEvent( PlayerPrim player, CheckpointPrim checkpoint )
	{
		this.player = player;
		this.checkpoint = checkpoint;
	}
	
	public PlayerPrim getPlayer()
	{
		return player;
	}
	
	public CheckpointPrim getCheckpoint()
	{
		return checkpoint;
	}
}
