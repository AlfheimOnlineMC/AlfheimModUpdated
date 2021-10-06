package fr.augma.alfheimfly.client.player.layer;

import java.util.ArrayList;
import java.util.List;

import fr.augma.alfheimfly.client.player.model.ModelCaitSithTail;
import fr.augma.alfheimfly.utils.AlfheimRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class LayerCaitSithTail implements LayerRenderer<EntityLivingBase> {
	
	private final ModelCaitSithTail tail;
	private static final ResourceLocation TEXTURE = new ResourceLocation(AlfheimRef.MODID, "textures/entity/tail.png");
	private static List<String> caitPlayer = new ArrayList<>();
	
	public LayerCaitSithTail() {
		this.tail = new ModelCaitSithTail();
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(!(entitylivingbaseIn instanceof EntityPlayer)) return;
		if(caitPlayer.contains(entitylivingbaseIn.getName())) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			tail.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
	
	public static void refreshPlayer(List<String> player) {
		caitPlayer = player;
	}
}
