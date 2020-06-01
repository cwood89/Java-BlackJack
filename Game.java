import java.util.Scanner;

public class Game {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Deck myDeck = new Deck(1, true);
    Player player = new Player("Chris");
    Player dealer = new Player("Dealer");
    int playerScore = 0;
    int dealerScore = 0;

    String playAgain = "y";
    while (playAgain.toLowerCase().equals("y")) {

      // play first deal
      player.addCard(myDeck.dealCard());
      dealer.addCard(myDeck.dealCard());

      player.addCard(myDeck.dealCard());
      dealer.addCard(myDeck.dealCard());

      // print hands
      System.out.println("Cards are dealt\n");
      player.printHand(true);
      dealer.printHand(false);
      System.out.println("\n");

      // flags for when each player is finished hitting
      boolean playerDone = false;
      boolean dealerDone = false;
      String ans;

      while (!playerDone || !dealerDone) {
        if (!playerDone) {
          System.out.println("Hit or Stay (Enter H or S): ");
          ans = in.nextLine();
          System.out.println();

          if (ans.compareToIgnoreCase("H") == 0) {
            playerDone = !player.addCard(myDeck.dealCard());
            player.printHand(true);
            System.out.println("\n");
          } else {
            playerDone = true;
          }
        }
        if (!dealerDone) {
          if (dealer.getHandSum() < 17) {
            System.out.println("The dealer hits\n");
            dealerDone = !dealer.addCard(myDeck.dealCard());
            dealer.printHand(false);
          } else {
            System.out.println("The dealer stays\n");
            dealerDone = true;
          }
        }
        System.out.println();
      }

      // print final hands
      player.printHand(true);
      dealer.printHand(true);

      int playerSum = player.getHandSum();
      int dealerSum = dealer.getHandSum();

      if (playerSum > dealerSum && playerSum <= 21 || dealerSum > 21) {
        System.out.println("You win!");
        playerScore++;
        player.emptyHand();
        dealer.emptyHand();
      } else {
        System.out.println("Dealer wins!");
        dealerScore++;
        dealer.emptyHand();
        player.emptyHand();
      }
      System.out.println("Player score: " + playerScore);
      System.out.println("Dealer score: " + dealerScore);
      System.out.println("Play again? (Y/N) ");
      playAgain = in.nextLine();
    }
    in.close();
  }
}