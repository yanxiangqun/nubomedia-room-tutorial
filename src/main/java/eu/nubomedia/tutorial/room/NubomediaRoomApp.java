/*
 * (C) Copyright 2016 NUBOMEDIA (http://www.nubomedia.eu)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
package eu.nubomedia.tutorial.room;

import org.kurento.room.KurentoRoomServerApp;
import org.kurento.room.kms.KmsManager;
import org.kurento.room.rpc.JsonRpcUserControl;
import org.springframework.boot.SpringApplication;

/**
 * Demo application for NUBOMEDIA Room (based on kurento-room-demo).
 *
 * @author Boni Garcia (boni.garcia@urjc.es)
 * @since 6.4.1
 */
public class NubomediaRoomApp extends KurentoRoomServerApp {

  @Override
  public KmsManager kmsManager() {
    return new SingleKmsManager();
  }

  @Override
  public JsonRpcUserControl userControl() {
    return new NubomediaRoomUserControl(roomManager());
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(NubomediaRoomApp.class, args);
  }
}
