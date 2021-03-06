package com.integral.forgottenrelics.handlers;

import com.integral.forgottenrelics.Main;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.common.Thaumcraft;
import vazkii.botania.common.core.helper.Vector3;

public class LunarBurstMessage implements IMessage {
    
    private double x;
    private double y;
    private double z;
    
    private float size;

    public LunarBurstMessage() { }

    public LunarBurstMessage(double x, double y, double z, float size) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.size = size;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        
        this.size = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        
        buf.writeFloat(this.size);
    }

    public static class Handler implements IMessageHandler<LunarBurstMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(LunarBurstMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            Main.proxy.lunarBurst(player.worldObj, message.x, message.y, message.z, message.size);
            
            return null;
        }
    }
    
}
