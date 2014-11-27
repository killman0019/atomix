/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.cluster;

import net.kuujo.copycat.protocol.ProtocolClient;

/**
 * Internal remote node.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public interface RemoteNode extends Node {

  /**
   * Returns the remote node client.
   *
   * @return The remote node client.
   */
  ProtocolClient client();

}
