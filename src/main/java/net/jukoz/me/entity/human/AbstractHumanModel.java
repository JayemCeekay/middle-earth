package net.jukoz.me.entity.human;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.passive.PassiveEntity;

public class AbstractHumanModel<T extends PassiveEntity> extends BipedEntityModel<T> {
    public AbstractHumanModel(ModelPart root) {
        super(root);
    }
}
