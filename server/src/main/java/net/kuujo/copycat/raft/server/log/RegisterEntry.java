/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.raft.server.log;

import net.kuujo.alleycat.Alleycat;
import net.kuujo.alleycat.SerializeWith;
import net.kuujo.alleycat.io.BufferInput;
import net.kuujo.alleycat.io.BufferOutput;
import net.kuujo.alleycat.util.ReferenceManager;
import net.kuujo.copycat.log.Entry;
import net.kuujo.copycat.raft.Member;

import java.util.UUID;

/**
 * Register client entry.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
@SerializeWith(id=303)
public class RegisterEntry extends TimestampedEntry<RegisterEntry> {
  private Member member;
  private UUID connection;

  public RegisterEntry() {
  }

  public RegisterEntry(ReferenceManager<Entry<?>> referenceManager) {
    super(referenceManager);
  }

  /**
   * Returns the entry client.
   *
   * @return The entry client.
   */
  public Member getMember() {
    return member;
  }

  /**
   * Sets the entry client.
   *
   * @param member The entry client.
   * @return The register entry.
   */
  public RegisterEntry setMember(Member member) {
    this.member = member;
    return this;
  }

  /**
   * Returns the entry connection ID.
   *
   * @return The entry connection ID.
   */
  public UUID getConnection() {
    return connection;
  }

  /**
   * Sets the entry connection ID.
   *
   * @param connection The entry connection ID.
   * @return The register entry.
   */
  public RegisterEntry setConnection(UUID connection) {
    this.connection = connection;
    return this;
  }

  @Override
  public int size() {
    return super.size() + Integer.BYTES;
  }

  @Override
  public void writeObject(BufferOutput buffer, Alleycat alleycat) {
    super.writeObject(buffer, alleycat);
    alleycat.writeObject(member, buffer);
    alleycat.writeObject(connection, buffer);
  }

  @Override
  public void readObject(BufferInput buffer, Alleycat alleycat) {
    super.readObject(buffer, alleycat);
    member = alleycat.readObject(buffer);
    connection = alleycat.readObject(buffer);
  }

  @Override
  public String toString() {
    return String.format("%s[index=%d, term=%d, member=%s, connection=%s]", getClass().getSimpleName(), getIndex(), getTerm(), getMember(), getConnection());
  }

}