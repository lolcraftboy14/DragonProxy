package net.glowstone.net.message.play.inv;

import com.flowpowered.networking.Message;
import lombok.Data;

@Data
public final class HeldItemMessage implements Message {

    public final int slot;

}

