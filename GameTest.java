import java.util.Scanner;

public class GameTest {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Deck myDeck = new Deck(1, true);
    Player player = new Player("Chris");
    Player dealer = new Player("Dealer");

    String playAgain = "y";

    while (playAgain.toLowerCase().equals("y") && player.getWallet() >= 5) {

      System.out.println("\nYou have $" + player.getWallet() + " in your wallet.");
      System.out.print("How many bets would you like to make? (each bet is $5): ");

      int betAmount = 0;
      while (betAmount == 0) {
        try {
          // checking valid integer using parseInt() method
          betAmount = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Please enter a valid number:");
        }
      }

      player.bet(betAmount);
      System.out.println("\nYou bet a total of $" + player.getBet() + "\n");

      // play first deal
      player.addCard(myDeck.dealCard());
      dealer.addCard(myDeck.dealCard());

      player.addCard(myDeck.dealCard());
      dealer.addCard(myDeck.dealCard());

      // print hands
      System.out.println("Cards are dealt");
      player.printHand(true);
      dealer.printHand(false);

      // flags for when each player is finished hitting

      boolean playerDone = false;
      boolean dealerDone = false;
      String ans;

      while (!playerDone || !dealerDone) {

        if (!playerDone) {

          System.out.print("\n\nHit or Stay (Enter H to hit): ");
          ans = in.next();

          if (ans.compareToIgnoreCase("H") == 0) {
            playerDone = !player.addCard(myDeck.dealCard());

            System.out.println("\nYou Hit.");

            if (player.getHandSum() > 21 || player.getHandSum() == 21) {
              playerDone = true;
              break;
            }

          } else {

            System.out.println("\nYou Stayed.");
            playerDone = true;

            if (player.getHandSum() > 21 || player.getHandSum() == 21) {
              playerDone = true;
              break;
            }
          }
        }

        if (!dealerDone) {
          if (dealer.getHandSum() < 17) {

            System.out.println("\nThe dealer hits.");
            dealerDone = !dealer.addCard(myDeck.dealCard());

            if (dealer.getHandSum() > 21 || dealer.getHandSum() == 21) {
              dealerDone = true;
              break;
            }

          } else {
            System.out.println("The dealer stays.\n");
            dealerDone = true;

            if (dealer.getHandSum() > 21 || dealer.getHandSum() == 21) {
              dealerDone = true;
              break;
            }

          }

        }

        player.printHand(true);
        dealer.printHand(false);
      }

      System.out.println();
      // print final hands
      player.printHand(true);
      dealer.printHand(true);

      // Check for winner ========================================
      if (player.getHandSum() == 21 || player.getHandSum() > dealer.getHandSum() && player.getHandSum() <= 21
          || dealer.getHandSum() > 21) {

        System.out.println("\nYou win!");
        player.addToWallet(player.getBet() * 2);

      } else if (dealer.getHandSum() == player.getHandSum()) {

        player.addToWallet(player.getBet());
        System.out.println("Push");

      } else if (dealer.getHandSum() == 21 || dealer.getHandSum() > player.getHandSum() && dealer.getHandSum() <= 21
          || player.getHandSum() > 21) {

        System.out.println("\nDealer wins!");
      }

      player.clearBet();
      dealer.emptyHand();
      player.emptyHand();

      if (player.getWallet() < 5) {
        System.out.println("\nYou're out of money, better luck next time.");
      } else {
        System.out.println("\nYou have $" + player.getWallet() + " in your wallet.");
        System.out.println("Play again? (Y/N): ");
        playAgain = in.next();
      }
    }
    in.close();
  }
}