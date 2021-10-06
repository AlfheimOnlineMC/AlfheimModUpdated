package fr.augma.alfheimfly.client.skin;

import java.awt.Color;
import java.util.Collections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GeoSkinItemRenderer<T extends Item & IAnimatable & IHasSkin, V extends AnimatedGeoModel<T> & ISkinnable<T>> extends GeoItemRenderer<T> {

	public GeoSkinItemRenderer(AnimatedGeoModel<T> modelProvider) {
		super(modelProvider);
	}
	
	@Override
	public void render(T animatable, ItemStack itemStack) {
		this.currentItemStack = itemStack;
		GeoModel model = modelProvider.getModel(this.getModelLocation(animatable));
		@SuppressWarnings({ "rawtypes", "unchecked" })
		AnimationEvent itemEvent = new AnimationEvent(animatable, 0, 0, Minecraft.getMinecraft().getRenderPartialTicks(), false, Collections.singletonList(itemStack));
		modelProvider.setLivingAnimations(animatable, this.getUniqueID(animatable), itemEvent);
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, 0.01f, 0);
		GlStateManager.translate(0.5, 0.5, 0.5);

		Minecraft.getMinecraft().renderEngine.bindTexture(this.getTextureLocation(animatable));
		Color renderColor = getRenderColor(animatable, 0f);
		render(model, animatable, 0f, (float) renderColor.getRed() / 255f, (float) renderColor.getGreen() / 255f, (float) renderColor.getBlue() / 255f, (float) renderColor.getAlpha() / 255);
		GlStateManager.popMatrix();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResourceLocation getTextureLocation(T instance) {
		return ((V) this.modelProvider).getSkinTexture(instance, getSkinTexture(instance));
	}
	
	@SuppressWarnings("unchecked")
	public ResourceLocation getModelLocation(T instance) {
		return ((V) this.modelProvider).getSkinModel(instance, getSkinModel(instance));
	}
	
	public String getSkinModel(T item) {
		return this.currentItemStack.hasTagCompound() && this.currentItemStack.getTagCompound().getBoolean("hasskin") ? this.currentItemStack.getTagCompound().getString("skin") : item.getDefaultModel();
	}
	
	public String getSkinTexture(T item) {
		return this.currentItemStack.hasTagCompound() && this.currentItemStack.getTagCompound().getBoolean("hasskin") ? this.currentItemStack.getTagCompound().getString("skin") : item.getDefaultTexture();
	}
}
