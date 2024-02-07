package net.warcar.terrariareference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.InterModComms;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.warcar.terrariareference.TerrariaReferenceMod;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import net.minecraftforge.client.event.TextureStitchEvent;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SlotsAdd {
	@SubscribeEvent
	public static void enqueueIMC(final InterModEnqueueEvent event) {
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("accesory").size(5).cosmetic().priority(1).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("mount").size(1).priority(0).build());
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("hook").size(1).icon(new ResourceLocation(TerrariaReferenceMod.MODID, "screens/hook_empty"))
																								.priority(0).build());
	}
	@SubscribeEvent
	public static void icons(TextureStitchEvent.Pre evt){
		evt.addSprite(new ResourceLocation(TerrariaReferenceMod.MODID, "screens/hook_empty"));
	}
}
