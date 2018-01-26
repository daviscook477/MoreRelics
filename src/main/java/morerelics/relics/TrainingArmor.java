package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;

public class TrainingArmor extends CustomRelic implements PreMonsterTurnSubscriber {
    public static final String ID = "TrainingArmor";
    private static final String IMG = "img/relics/TrainingArmor.png";
    
    private boolean used = false;
    private ArrayList validIntents;
    
    public TrainingArmor() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.UNCOMMON, LandingSound.SOLID);
        
        validIntents = new ArrayList<Intent>();
        validIntents.add(Intent.ATTACK);
        validIntents.add(Intent.ATTACK_BUFF);
        validIntents.add(Intent.ATTACK_DEBUFF);
        validIntents.add(Intent.ATTACK_DEFEND);
        
        BaseMod.subscribeToPreMonsterTurn(this);
    }
    
    public boolean receivePreMonsterTurn(AbstractMonster m) {
        if (!used && AbstractDungeon.player.hasRelic("TrainingArmor") && validIntents.contains(m.intent)) {
            used = true;
            flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            CardCrawlGame.sound.play("BLOCK_ATTACK");
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