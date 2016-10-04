/*
 * (C) Copyright 2016 NUBOMEDIA (http://www.nubomedia.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package eu.nubomedia.tutorial.room;

import org.kurento.room.NotificationRoomManager;
import org.kurento.room.exception.RoomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the room demo app.
 *
 * @author Boni Garcia (boni.garcia@urjc.es)
 * @since 6.4.1
 */
@RestController
public class DemoController {

  private static final Logger log = LoggerFactory.getLogger(DemoController.class);

  @Autowired
  private NotificationRoomManager roomManager;

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
      super(msg);
    }
  }

  @RequestMapping("/close")
  public void closeRoom(@RequestParam("room") String room) {
    log.debug("Trying to close the room '{}'", room);
    if (!roomManager.getRooms().contains(room)) {
      log.debug("Unable to close room '{}', not found.", room);
      throw new ResourceNotFoundException("Room '" + room + "' not found");
    }
    try {
      roomManager.closeRoom(room);
    } catch (RoomException e) {
      log.error("Error closing room {}", room, e);
      throw new ResourceNotFoundException(e.getMessage());
    }
  }

}
