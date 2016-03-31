package sut.game01.core.character;

import playn.core.*;
import playn.core.util.Callback;
import sut.game01.core.sprite.Sprite;
import sut.game01.core.sprite.SpriteLoader;

/**
 * Created by Administrator on 30/3/2559.
 */
public class Samurai {
    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    public enum State{
        IDLE, ATTK, RUN
    };

    private State state = State.IDLE;

    private int e = 0;
    private int offset = 0;

    public Samurai(final float x, final float y){
        /*sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event){
                state = State.ATTK;
                spriteIndex = -1;
                e=0;
            }
        });*/
        PlayN.keyboard().setListener(new Keyboard.Adapter(){
            /*@Override
            public void onKeyDown(Keyboard.Event event){

            }*/

            @Override
            public void onKeyUp(Keyboard.Event event){
                if(event.key() == Key.SPACE){
                    switch(state){
                        case IDLE: state = State.RUN; break;
                        case RUN: state = State.ATTK; break;
                        case ATTK: state = State.IDLE; break;
                    }
                }
            }
        });

        sprite = SpriteLoader.getSprite("images/Samurai.json");
        sprite.addCallback(new Callback<Sprite>(){
            @Override
            public void onSuccess(Sprite result){
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f, sprite.height()/2f);
                sprite.layer().setTranslation(x, y);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause){
                PlayN.log().error("Error Loading image!", cause);
            }
        });
    }
    public Layer layer(){
        return sprite.layer();
    }
    public void update(int delta) {
        if(hasLoaded == false) return;

        e = e + delta;
        if(e > 150){
            switch (state){
                case IDLE: offset = 0; break;
                case RUN: offset = 4; break;
                case ATTK: offset = 8;
                    /*if(spriteIndex == 10){
                        state = State.IDLE;
                    }*/break;
            }
            spriteIndex = offset + ((spriteIndex + 1) % 4);
            sprite.setSprite(spriteIndex);
            e=0;
        }
    }
}
