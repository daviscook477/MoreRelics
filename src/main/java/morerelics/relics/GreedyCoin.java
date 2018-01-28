package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class GreedyCoin extends CustomRelic {
    public static final String ID = "GreedyCoin";
    private static final String IMG = "img/relics/GreedyCoin.png";
    private static final String OUTLINE = "img/relics/outline/GreedyCoin.png";

    public GreedyCoin() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public void update() {
        super.update();

        String oldDesc = description;
        description = getUpdatedDescription();
        if (!oldDesc.equals(description)) {
            tips.clear();
            tips.add(new PowerTip(name, description));
            initializeTips();
        }
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        float hpPct = (float) AbstractDungeon.player.currentHealth / (float) AbstractDungeon.player.maxHealth;

        if (room instanceof MonsterRoomElite && hpPct <= 0.3f) {
            flash();
            AbstractDungeon.player.gainGold(250);
        }
    }

    @Override
    public String getUpdatedDescription() {
        AbstractPlayer p = AbstractDungeon.player;
        float hpPct = (p != null) ? ((float) p.currentHealth / (float) p.maxHealth) : 1.0f;
        return DESCRIPTIONS[0] + Math.round(hpPct * 100.0f) + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GreedyCoin();
    }
}