public class Card {

  private Suit mySuit;
  // ace = 1, jack -king = 11 - 13
  private int myNumber;

  public Card(Suit suit, int number) {

    this.mySuit = suit;
    if (number >= 1 && number <= 13) {
      this.myNumber = number;
    } else {
      System.err.println(number + " is not a valid number");
      System.exit(1);
    }
  }

  public int getNumber() {
    return this.myNumber;
  }

  @Override
  public String toString() {
    String numStr = "Err";

    switch (this.myNumber) {
      case 2:
        numStr = "Two";
        break;
      case 3:
        numStr = "Three";
        break;
      case 4:
        numStr = "Four";
        break;
      case 5:
        numStr = "Five";
        break;
      case 6:
        numStr = "Six";
        break;
      case 7:
        numStr = "Seven";
        break;
      case 8:
        numStr = "Eight";
        break;
      case 9:
        numStr = "Nine";
        break;
      case 10:
        numStr = "Ten";
        break;
      case 11:
        numStr = "Jack";
        break;
      case 12:
        numStr = "Queen";
        break;
      case 13:
        numStr = "King";
        break;
      case 1:
        numStr = "Ace";
        break;
    }

    return numStr + " of " + mySuit.toString();
  }

}