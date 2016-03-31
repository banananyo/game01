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

public class HomeScreen extends UIScreen {
  public static final Font TITLE_FONT = graphics().createFont("Helvetica", Font.Style.PLAIN, 24);

  private final ScreenStack ss;

  private Root root;
  private final Button start = new Button("Play");
  private final Button setting = new Button("setting");
  private final Button loadsave = new Button("load/save");
  private final Button shop = new Button("shop");
  /*private final Image bgImage;
  private final ImageLayer bg;*/
  private final Image cImage;
  private final ImageLayer c;

  private final Image bgImage;
  private final Image bbImage;
  private final ImageLayer bg;
  private final ImageLayer bb;

  public HomeScreen(final ScreenStack ss){
      this.ss = ss;
      root = iface.createRoot(AxisLayout.vertical().gap(15), SimpleStyles.newSheet(), layer);

      root.addStyles(Style.BACKGROUND.is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5).inset(5, 10)));
      
      root.setSize(width(), height());

      
      cImage = assets().getImage("images/c.png");
      this.c = graphics().createImageLayer(cImage);
      c.setTranslation(50,150);
      this.layer.add(c);

      root.add(new Label("RUNNING SAMURAI!!!").addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));



      //----------------------------------------------------------------------
       bgImage = assets().getImage("images/gameScreen.png");
      this.bg = graphics().createImageLayer(bgImage);
      

      bbImage = assets().getImage("images/backbutt.png");
      this.bb = graphics().createImageLayer(bbImage);
      bb.setTranslation(10, 400);

      bb.addListener(new Mouse.LayerAdapter() {
        @Override
        public void onMouseUp(Mouse.ButtonEvent event) {
          ss.remove(ss.top()); //pop
        }
      });
      //----------------------------------------------------------------------


      root.add(start.onClick(new UnitSlot() {
        public void onEmit() {
          ss.push(new GameScreen(ss,bb,bg));
        }
      }));
      root.add(setting.onClick(new UnitSlot() {
        public void onEmit() {
          ss.push(new SettingScreen(ss));
        }
      }));
      root.add(loadsave.onClick(new UnitSlot() {
        public void onEmit() {
          ss.push(new LoadSaveScreen(ss));
        }
      }));
      root.add(shop.onClick(new UnitSlot() {
        public void onEmit() {
          ss.push(new ShopScreen(ss));
        }
      }));
  }
  public void wasShown (){
    super.wasShown();
  }
}