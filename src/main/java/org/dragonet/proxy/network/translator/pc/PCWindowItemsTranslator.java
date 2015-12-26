/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.network.translator.pc;

import org.dragonet.net.packet.minecraft.PEPacket;
import org.dragonet.proxy.network.InventoryTranslatorRegister;
import org.dragonet.proxy.network.UpstreamSession;
import org.dragonet.proxy.network.cache.CachedWindow;
import org.dragonet.proxy.network.translator.PCPacketTranslator;
import org.spacehq.mc.protocol.packet.ingame.server.window.ServerWindowItemsPacket;

public class PCWindowItemsTranslator implements PCPacketTranslator<ServerWindowItemsPacket> {

    @Override
    public PEPacket[] translate(UpstreamSession session, ServerWindowItemsPacket packet) {
        if (!session.getWindowCache().hasWindow(packet.getWindowId())) {
            //Almost impossible to get here. 
            return null;
        }
        CachedWindow win = session.getWindowCache().get(packet.getWindowId());
        if (win.pcType == -1 && packet.getWindowId() == 0) {
            if (packet.getItems().length < 45) {
                //Almost impossible to happen either. 
                return null;
            }
            //Update items in window cache
            win.slots = packet.getItems();
            return InventoryTranslatorRegister.sendPlayerInventory(session);
        }
        //Others do it later. 
        return null;
    }

}
