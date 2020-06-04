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
    String suitSymbol = "Err";
    switch (this.myNumber) {
      case 2:
        numStr = "2";
        break;
      case 3:
        numStr = "3";
        break;
      case 4:
        numStr = "4";
        break;
      case 5:
        numStr = "5";
        break;
      case 6:
        numStr = "6";
        break;
      case 7:
        numStr = "7";
        break;
      case 8:
        numStr = "8";
        break;
      case 9:
        numStr = "9";
        break;
      case 10:
        numStr = "10";
        break;
      case 11:
        numStr = "J";
        break;
      case 12:
        numStr = "Q";
        break;
      case 13:
        numStr = "K";
        break;
      case 1:
        numStr = "A";
        break;
    }
    switch (mySuit.toString()) {
      case "Hearts":
        suitSymbol = "\u2665";
        break;
      case "Spades":
        suitSymbol = "\u2660";
        break;
      case "Clubs":
        suitSymbol = "\u2663";
        break;
      case "Diamonds":
        suitSymbol = "\u2666";
        break;
    }
    return numStr + suitSymbol;
  }
}