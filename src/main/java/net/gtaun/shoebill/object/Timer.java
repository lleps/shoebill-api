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


/**
 * 
 * 
 * @author MK124
 */
public interface Timer extends Destroyable
{
	public abstract class TimerCallback
	{
		public void onStart()						{ }
		public void onStop()						{ }
		
		public abstract void onTick(int factualInterval);
	}
	
	
	public static final int COUNT_INFINITE = 0;
	
	
	int getInterval();
	int getCount();

	boolean isRunning();
	
	void setInterval(int ms);
	void setCount(int count);
	void start();
	void stop();

	void setCallback(TimerCallback callback);
}
