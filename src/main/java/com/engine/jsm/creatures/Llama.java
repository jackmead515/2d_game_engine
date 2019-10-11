package com.engine.jsm.creatures;

import com.engine.jsm.ai.AIController;
import com.engine.jsm.ai.AIState;
import com.engine.jsm.ai.WanderAI;
import com.engine.jsm.images.ImageConstants;
import com.engine.jsm.util.ValueRange;

import java.util.concurrent.TimeUnit;

public class Llama extends AnimatedCreature {
    public Llama(int id) {
        super(id, ImageConstants.LLAMA_SHEET);
        this.setUpdateTime(TimeUnit.MILLISECONDS.toNanos(80));
        this.getStats().setMovementSpeed(1);

        AIController ai = new AIController();
        ai.setState(AIState.CYCLE);
        ai.setWanderEnabled(true);
        ai.setWanderAI(WanderAI.from(
            ValueRange.from(
                TimeUnit.SECONDS.toNanos(5),
                TimeUnit.SECONDS.toNanos(10)
            ),
            ValueRange.from(
                    TimeUnit.MILLISECONDS.toNanos(500),
                    TimeUnit.SECONDS.toNanos(2)
            )
        ));

        this.setAI(ai);
    }
}
