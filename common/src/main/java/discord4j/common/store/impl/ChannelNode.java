/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J. If not, see <http://www.gnu.org/licenses/>.
 */

package discord4j.common.store.impl;

import discord4j.discordjson.json.ChannelData;
import discord4j.discordjson.json.MessageData;
import reactor.util.annotation.Nullable;

class ChannelNode {

    /**
     * Store messages for this specific channel
     */
    private final IdentityStorage<MessageData> messageStorage;

    private volatile ChannelData data;

    ChannelNode(ChannelData channelData, StorageBackend messageBackend) {
        this.data = channelData;
        this.messageStorage = new IdentityStorage<>(messageBackend,
                data -> LocalStoreLayout.toLongId(data.id()));
    }

    @Nullable ChannelData getData() {
        return data;
    }

    ChannelNode setData(ChannelData data) {
        this.data = data;
        return this;
    }

    IdentityStorage<MessageData> getMessageStorage() {
        return messageStorage;
    }
}
