package sut.game01.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.Mouse.*;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.game.*;
import tripleplay.ui.Root;
import tripleplay.ui.*;
import playn.core.Font;
import tripleplay.ui.layout.*;
import react.UnitSlot;
import sut.game01.core.character.*;

import playn.core.util.Callback;
import sut.game01.core.sprite.Sprite;
import sut.game01.core.sprite.SpriteLoader;

public class GameScreen extends Screen {
  private final ScreenStack ss;
  private Samurai samurai;

  public GameScreen(final ScreenStack ss,ImageLayer bb,ImageLayer bg){
      this.ss = ss;
      //this.layer.add(bg);
      //this.layer.add(bb);
  }
  @Override
  public void wasShown (){
    super.wasShown();
    samurai = new Samurai(350f,200f);
    this.layer.add(samurai.layer());
  }
    public void update(int delta) {
        this.samurai.update(delta);

    }

}