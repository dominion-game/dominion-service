package edu.cnm.deepdive.dominionservice;

public enum ActionsTypes {
  nothing {
    @Override
    public void doAction() {
    }
  },
  drawCard {
    @Override
    public void doAction() {



    }
  };

  public abstract void doAction();
}

//    },   //0
//    drawCard{  //1
//      @Override
//      public void doAction(){
//
//      }
//    },   //0
//  addAction, //2
//    addBuy, //3
//    discardCard, //4
//    addGold,
//    trashCard,
//    upgradeTreasure,
//    add4CostCard
//  };


  //this does an action
  //likely will take a
//  public void doAction(){
//
//  }
