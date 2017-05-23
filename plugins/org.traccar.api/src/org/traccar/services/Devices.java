/*
 * Copyright 2015 - 2017 Anton Tananaev (anton@traccar.org)
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
package org.traccar.services;

import java.util.Collection;

import org.traccar.model.Device;
import org.traccar.model.DeviceTotalDistance;

public interface Devices {
    Collection<Device> get(boolean all, long userId);

    Collection<Device> getDevices();

    Device add(Device entity);

    Device update(Device entity);

    boolean remove(long id);

    boolean updateTotalDistance(DeviceTotalDistance entity);

}
