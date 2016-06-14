/**
 * Copyright (C) 2012-2014 MK124

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.gtaun.shoebill.resource

import net.gtaun.shoebill.Shoebill
import net.gtaun.shoebill.event.resource.ResourceDisableEvent
import net.gtaun.shoebill.event.resource.ResourceEnableEvent
import net.gtaun.shoebill.service.Service
import net.gtaun.shoebill.service.ServiceManager
import net.gtaun.util.event.EventManager
import net.gtaun.util.event.EventManagerNode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

/**
 * @author MK124
 * @author Marvin Haschker
 */
abstract class Resource internal constructor() {
    /**
     * Checks if the Resource is enabled.
     */
    var isEnabled: Boolean = false
        private set

    /**
     * Gets the ResourceDescription of the Resource
     */
    var description: ResourceDescription? = null
        private set

    /**
     * Gets the active Shoebill instance from the Resource.
     */
    var shoebill: Shoebill? = null
        private set

    private var rootEventManager: EventManager? = null
    private var eventManager: EventManagerNode? = null
    /**
     * Gets the Datadir.
     */
    var dataDir: File? = null
        private set
    private var onDisableCalls: Deque<Runnable>? = null

    /**
     * Sets the Context of the Resource instance.
     * @param description The ResourceDescription instance.
     * @param shoebill Active Shoebill instance.
     * @param rootEventManager The parent Eventmanager
     * @param dataDir The Data Directory
     */
    fun setContext(description: ResourceDescription, shoebill: Shoebill, rootEventManager: EventManager, dataDir:
    File) {
        this.description = description
        this.shoebill = shoebill
        this.rootEventManager = rootEventManager
        this.dataDir = dataDir
        this.onDisableCalls = LinkedList<Runnable>()
    }

    /**
     * Gets called when the Resource is getting enabled.
     * @throws Throwable
     */
    @Throws(Throwable::class)
    protected abstract fun onEnable()

    /**
     * Gets called when the Resource is getting unloaded.
     * @throws Throwable
     */
    @Throws(Throwable::class)
    protected abstract fun onDisable()

    /**
     * Gets called when the Resource is getting enabled.
     * @throws Throwable
     */
    @Throws(Throwable::class)
    open fun enable() {
        eventManager = rootEventManager!!.createChildNode()

        onEnable()
        isEnabled = true

        val event = ResourceEnableEvent(this)
        eventManager!!.dispatchEvent(event, getEventManager(), this)
    }

    /**
     * Gets called when the Resource is getting unloaded.
     * @throws Throwable
     */
    @Throws(Throwable::class)
    open fun disable() {
        val event = ResourceDisableEvent(this)
        eventManager!!.dispatchEvent(event, getEventManager(), this)

        var throwable: Throwable? = null
        try {
            if (isEnabled) onDisable()
            while (!onDisableCalls!!.isEmpty()) onDisableCalls!!.pollLast().run()
        } catch (e: Throwable) {
            throwable = e
        }

        eventManager!!.destroy()
        eventManager = null

        val serviceManager = shoebill!!.serviceStore as ServiceManager
        serviceManager.unregisterAllServices(this)

        isEnabled = false
        if (throwable != null) throw throwable
    }

    fun onDisable(runnable: Runnable) {
        onDisableCalls!!.offer(runnable)
    }

    /**
     * Gets the event Manager.
     */
    fun getEventManager(): EventManager {
        return eventManager!!
    }

    /**
     * Gets the Logger
     */
    val logger: Logger
        get() = LoggerFactory.getLogger(description!!.clazz)

    /**
     * Registers a Service
     * @param type Class instance.
     * @param service The Service
     */
    fun <T : Service> registerService(type: Class<T>, service: T) {
        val serviceManager = shoebill!!.serviceStore as ServiceManager
        serviceManager.registerService(this, type, service)
    }

    /**
     * Unregisters a Service by its class
     * @param type The Class instance
     */
    fun <T : Service> unregisterService(type: Class<T>) {
        val serviceManager = shoebill!!.serviceStore as ServiceManager
        serviceManager.unregisterService(this, type)
    }
}