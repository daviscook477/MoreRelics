package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.StartGameSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DoorKnob extends CustomRelic implements StartGameSubscriber {
    public static final String ID = "DoorKnob";
    private static final String IMG = "img/relics/DoorKnob.png";
    private static final String OUTLINE = "img/relics/outline/DoorKnob.png";

    public DoorKnob() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.COMMON, LandingSound.CLINK);
        BaseMod.subscribeToStartGame(this);
    }

    @Override
    public void receiveStartGame() {
        if (AbstractDungeon.player.relics.indexOf(this) != -1) {
            BaseMod.mapPathDensityMultiplier += 0.75f;
        }
    }

    @Override
    public void onEquip() {
        BaseMod.mapPathDensityMultiplier += 0.75f;
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