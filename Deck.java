import java.util.Random;

public class Deck {

  private Card[] myCards;
  private int numCards; // play more than one deck

  public Deck() {
    this(1, false);
  }

  public Deck(int numDecks, boolean shuffle) {

    this.numCards = numDecks * 52;
    this.myCards = new Card[this.numCards];

    // init card index
    int c = 0;

    // for each deck
    for (int d = 0; d < numDecks; d++) {

      // for each suit
      for (int s = 0; s < 4; s++) {

        // for each number
        for (int n = 1; n <= 13; n++) {
          // add a new card to deck
          this.myCards[c] = new Card(Suit.values()[s], n);
          c++;
        }
      }
    }
    if (shuffle) {
      this.shuffle();
    }
  }

  public void shuffle() {

    Random rand = new Random();

    Card temp;

    int j;
    for (int i = 0; i < this.numCards; i++) {
      // swapping cards
      j = rand.nextInt(this.numCards);
      temp = this.myCards[i];
      this.myCards[i] = this.myCards[j];
      this.myCards[j] = temp;
    }
  }

  public Card dealCard() {

    Card top = this.myCards[0];
    // shift all cards to the left by 1
    for (int c = 1; c < this.numCards; c++) {
      this.myCards[c - 1] = this.myCards[c];
    }
    // remove last card
    this.myCards[this.numCards - 1] = null;
    // decrement num of cards in deck
    this.numCards--;

    return top;
  }

  public void printDeck(int numToPrint) {
    for (int c = 0; c < numToPrint; c++) {
      System.out.printf("% 3d/%d %s\n", c + 1, this.numCards, this.myCards[c].toString());
    }
    System.out.printf("\t\t[%d others]\n", this.numCards - numToPrint);
  }

  public int getNumOfCards() {
    return this.numCards;
  }

}