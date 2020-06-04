public class Player {

  private String name;
  private int numOfCards;
  private int handTwoNumOfCards;
  private Card[] hand = new Card[10];
  private Card[] hand2 = new Card[10];
  private int wallet = 200;
  private int bet = 0;

  public Player(String name) {
    this.name = name;
    this.emptyHand();

  }

  public void emptyHand() {
    for (int c = 0; c < 10; c++) {
      this.hand[c] = null;
      this.hand2[c] = null;
    }
    this.numOfCards = 0;
    this.handTwoNumOfCards = 0;
  }

  public boolean addCard(Card card) {

    // check if we already played the max number of cards
    if (this.numOfCards == 10) {
      System.err.printf("%s's hand already has 10 cards; " + "cannot add another\n", this.name);
      System.exit(1);
    }
    // add card
    this.hand[this.numOfCards] = card;
    this.numOfCards++;

    return (this.getHandSum() <= 21);
  }

  public boolean addCardToSecondHand(Card card) {

    // check if we already played the max number of cards
    if (this.handTwoNumOfCards == 10) {
      System.err.printf("%s's hand already has 10 cards; " + "cannot add another\n", this.name);
      System.exit(1);
    }
    // add card
    this.hand2[this.handTwoNumOfCards] = card;
    this.handTwoNumOfCards++;

    return (this.getHandTwoSum() <= 21);
  }

  public int getHandSum() {
    int handSum = 0;
    int cardNum;
    int numAces = 0;

    for (int c = 0; c < this.numOfCards; c++) {
      cardNum = this.hand[c].getNumber();
      // handling ace value
      if (cardNum == 1) {
        numAces++;
        handSum += 11;
      } else if (cardNum > 10) { // face card
        handSum += 10;
      } else {
        handSum += cardNum;
      }
    }
    // if aces and hand > 21 set all aces to value 1
    while (handSum > 21 && numAces > 0) {
      handSum -= 10;
      numAces--;
    }
    return handSum;
  }

  public int getHandTwoSum() {
    int handSum = 0;
    int cardNum;
    int numAces = 0;

    for (int c = 0; c < this.handTwoNumOfCards; c++) {
      cardNum = this.hand2[c].getNumber();
      // handling ace value
      if (cardNum == 1) {
        numAces++;
        handSum += 11;
      } else if (cardNum > 10) { // face card
        handSum += 10;
      } else {
        handSum += cardNum;
      }
    }
    // if aces and hand > 21 set all aces to value 1
    while (handSum > 21 && numAces > 0) {
      handSum -= 10;
      numAces--;
    }
    return handSum;
  }

  public void printHand(boolean showFirstCard) {
    System.out.printf("\n %s's cards:", this.name);
    for (int c = 0; c < this.numOfCards; c++) {
      if (c == 0 && !showFirstCard) {
        System.out.print(" [hidden]");
      } else {
        System.out.printf("| %s |", this.hand[c].toString());
      }
    }
  }

  public void printHand2(boolean showFirstCard) {
    System.out.printf("\n %s's cards:", this.name);
    for (int c = 0; c < this.handTwoNumOfCards; c++) {
      if (c == 0 && !showFirstCard) {
        System.out.print(" [hidden]");
      } else {
        System.out.printf("| %s |", this.hand2[c].toString());
      }
    }
  }

  public void addToWallet(int amount) {
    this.wallet += amount;
  }

  public void takeFromWallet(int amount) {
    this.wallet -= amount;
  }

  public int getWallet() {
    return this.wallet;
  }

  public int getBet() {
    return this.bet;
  }

  public void bet() {
    this.wallet -= 5;
    this.bet += 5;
  }

  public void bet(int x) {
    this.wallet -= 5 * x;
    this.bet += 5 * x;
  }

  public void clearBet() {
    this.bet = 0;
  }

  public boolean checkForSplit() {
    if (this.hand[0] == this.hand[1]) {
      return true;
    } else {
      return false;
    }
  }

  public void split() {
    this.hand2[0] = this.hand[1];
    this.hand[0] = this.hand[1];
    this.hand[1] = null;
  }
}