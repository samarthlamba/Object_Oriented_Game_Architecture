# OOGA Lab Discussion
## Names and NetIDs

Matt Bowman mab181;
Sam Lamba sl562;
Joe Heflin jnh24;
Roshni Penmatcha rkp13;

## Fluxx

### High Level Design Ideas

Abstract Card class with implementations Goal, Keeper, Action, RuleCard

Abstract Rule with new rules as implementations

Hand that keeps track of cards for each player


### CRC Card Classes

This class's purpose is to keep track of each card that a player owns
```java
 public class Hand {
     private Collection<Card> cardsInHand;
     private int score;
     private Card cardForTurn;

     public Hand(Collection<Card> initialHand, int initialScore) {
         this.cardsInHand = initialHand;
         score = initialScore;
     }

     public void addCard(Card cardToAdd) {
         cardsInHand.add(cardToAdd);
     }
     public Card getCardForTurn() {
         cardsInHand.remove(cardForturn);
         return cardForTurn;
     }

     public void setCardForTurn(Card card) {
         this.cardForTurn = card;
     }
 }
```

This class's purpose is to represent a playable card object
```java
 public abstract class Card {
     private String cardName;
     private boolean playable;
     public abstract void play(EventHandler playFunction);
 }
```

### Use Cases

### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
private List<Card> dealCards(int numberOfCardsToDeal) {
    //TODO
}
int cardsDealt = dealCards(5);
 Hand newHand = new Hand(cardsDealt, 0) //Do five times
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 activePlayer.setCardForTurn();

 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```