package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DoorKnob extends CustomRelic {
    public static final String ID = "DoorKnob";
    private static final String IMG = "img/relics/DoorKnob.png";
    
    public DoorKnob() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.COMMON, LandingSound.CLINK);
    }
    
    @Override
    public void onEquip() {
        BaseMod.mapPathDensityMultiplier = 1.5f;
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new DoorKnob();
    }
}