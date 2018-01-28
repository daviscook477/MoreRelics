package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;

public class TrainingArmor extends CustomRelic implements PreMonsterTurnSubscriber {
    public static final String ID = "TrainingArmor";
    private static final String IMG = "img/relics/TrainingArmor.png";
    private static final String OUTLINE = "img/relics/outline/TrainingArmor.png";

    private boolean used = false;
    private ArrayList<Intent> validIntents = new ArrayList<>();
    
    public TrainingArmor() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.UNCOMMON, LandingSound.SOLID);

        validIntents.add(Intent.ATTACK);
        validIntents.add(Intent.ATTACK_BUFF);
        validIntents.add(Intent.ATTACK_DEBUFF);
        validIntents.add(Intent.ATTACK_DEFEND);
        
        BaseMod.subscribeToPreMonsterTurn(this);
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster m) {
        if (!used && AbstractDungeon.player.relics.indexOf(this) != -1 && validIntents.contains(m.intent)) {
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToTop(new SFXAction("BLOCK_ATTACK"));
            AbstractDungeon.actionManager.addToTop(new AnimateSlowAttackAction(m));

            flash();
            used = true;
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        used = false;
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new TrainingArmor();
    }
}