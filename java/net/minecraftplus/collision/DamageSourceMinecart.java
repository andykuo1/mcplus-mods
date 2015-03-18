package net.minecraftplus.collision;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class DamageSourceMinecart extends DamageSource
{
	public DamageSourceMinecart()
	{
		super("minecart");
	}

	@Override
	public IChatComponent func_151519_b(EntityLivingBase par1EntityLivingBase)
	{
		EntityLivingBase entitylivingbase1 = par1EntityLivingBase.func_94060_bK();
		String s = "death.attack." + this.damageType;
		String s1 = s + ".player";
		return entitylivingbase1 != null && StatCollector.canTranslate(s1) ? new ChatComponentTranslation(s1, new Object[] {par1EntityLivingBase.func_145748_c_(), entitylivingbase1.func_145748_c_()}): new ChatComponentTranslation(s, new Object[] {par1EntityLivingBase.func_145748_c_()});
	}
}
