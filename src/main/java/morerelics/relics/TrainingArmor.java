package morerelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TrainingArmor extends CustomRelic {
    public static final String ID = "TrainingArmor";
    private static final String IMG = "img/relics/TrainingArmor.png";
    
    private boolean used = false;
    
    public TrainingArmor() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.UNCOMMON, LandingSound.SOLID);
    }
      
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
   
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!used) {
            flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            used = true;
            return 0;
        }
        
        return damageAmount;
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