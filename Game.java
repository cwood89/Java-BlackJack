import java.util.Scanner;

public class Game {

  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RESET = "\u001B[0m";

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    Deck myDeck = new Deck(1, true);
    Player player = new Player("Player");
    Player dealer = new Player("Dealer");
    boolean splitPlay = false;
    String playAgain = "y";

    while (playAgain.toLowerCase().equals("y") && player.getWallet() >= 5) {
      int betAmount = 0;

      System.out.println("\nYou have " + ANSI_GREEN + "$" + player.getWallet() + ANSI_RESET + " in your wallet.");
      System.out.print("How many bets would you like to make? (each bet is " + ANSI_GREEN + "$5" + ANSI_RESET + "): ");

      while (betAmount == 0) {
        try {
          // checking valid integer using parseInt() method
          betAmount = Integer.parseInt(in.next());
        } catch (NumberFormatException e) {
          System.out.println("Please enter a valid number:");
        }

        if (betAmount * 5 > player.getWallet()) {
          System.out.print("You don't have enough money, try a smaller bet: ");
          betAmount = 0;
        }
      }

      player.bet(betAmount);

      System.out.println("\nYou bet a total of " + ANSI_GREEN + "$" + player.getBet() + ANSI_RESET + "\n");

      // play first deal
      player.addCard(myDeck.dealCard());
      dealer.addCard(myDeck.dealCard());

      player.addCard(myDeck.dealCard());
      dealer.addCard(myDeck.dealCard());

      if (player.checkForSplit()) {
        player.printHand(true);
        System.out.print("\nWould you like to split your hand? Y/N: ");

        String playSplit = in.next();
        if (playSplit.equalsIgnoreCase("y")) {
          splitPlay = true;

          player.split();
          player.bet2(betAmount);
          player.addCard(myDeck.dealCard());
          player.addCardToSecondHand(myDeck.dealCard());
        } else {
          splitPlay = false;
        }
      }
      // print hands
      System.out.println("Cards are dealt");
      player.printHand(true);

      if (splitPlay)
        player.printHand2(true);

      dealer.printHand(false);

      // flags for when each player is finished hitting

      boolean playerDone = false;
      boolean dealerDone = false;
      boolean player2ndHandDone = false;
      String ans;

      while (!playerDone && !player2ndHandDone || !dealerDone) {

        if (player.getHandSum() > 21 || player.getHandSum() == 21) {
          playerDone = true;
          if (!splitPlay)
            break;
        }

        if (!playerDone) {

          System.out.print("\n\nHit or Stay (Enter H to hit): ");
          ans = in.next();

          if (ans.compareToIgnoreCase("H") == 0) {

            playerDone = !player.addCard(myDeck.dealCard());

            System.out.println("\nYou hit.");

            if (player.getHandSum() > 21 || player.getHandSum() == 21) {
              playerDone = true;
              if (!splitPlay)
                break;
            }
          } else {

            System.out.println("\nYou stayed.");
            playerDone = true;

          }

        }

        if (splitPlay) {

          if (!player2ndHandDone) {

            if (player.getHandTwoSum() > 21 || player.getHandTwoSum() == 21) {
              player2ndHandDone = true;
            }

            System.out.print("\nHit or stay on second hand (Enter H to hit): ");
            ans = in.next();

            if (ans.compareToIgnoreCase("H") == 0) {
              player2ndHandDone = !player.addCardToSecondHand(myDeck.dealCard());

              System.out.println("\nYou hit on second hand.");

              if (player.getHandTwoSum() > 21 || player.getHandTwoSum() == 21) {
                player2ndHandDone = true;
              }
            } else {

              System.out.println("\nYou stayed on second hand.");
              player2ndHandDone = true;
            }
          } else
            player2ndHandDone = true;
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
            System.out.println("\nThe dealer stays.");
            dealerDone = true;

            if (dealer.getHandSum() > 21 || dealer.getHandSum() == 21) {
              dealerDone = true;
              break;
            }

          }

        }

        player.printHand(true);

        if (splitPlay)
          player.printHand2(true);

        dealer.printHand(false);
      }

      System.out.println();
      // print final hands
      player.printHand(true);
      if (splitPlay)
        player.printHand2(true);
      dealer.printHand(true);

      // Check for winner ========================================
      if (player.getHandSum() == 21 || player.getHandSum() > dealer.getHandSum() && player.getHandSum() <= 21
          || dealer.getHandSum() > 21 && player.getHandSum() <= 21) {

        System.out.println("\nYou win!");
        player.addToWallet(player.getBet() * 2);

      } else if (dealer.getHandSum() == player.getHandSum()) {

        player.addToWallet(player.getBet());
        System.out.println("\nPush");

      } else if (dealer.getHandSum() == 21 || dealer.getHandSum() > player.getHandSum() && dealer.getHandSum() <= 21
          || player.getHandSum() > 21) {

        System.out.println("\nDealer wins!");
      }
      // second hand winner =========================
      if (splitPlay) {
        if (player.getHandTwoSum() == 21 || player.getHandTwoSum() > dealer.getHandSum() && player.getHandTwoSum() <= 21
            || dealer.getHandSum() > 21 && player.getHandTwoSum() <= 21) {

          System.out.println("\nYour second hand wins!");
          player.addToWallet(player.getBet2() * 2);

        } else if (dealer.getHandSum() == player.getHandTwoSum()) {

          player.addToWallet(player.getBet2());
          System.out.println("\nPush");

        } else if (dealer.getHandSum() == 21
            || dealer.getHandTwoSum() > player.getHandTwoSum() && dealer.getHandSum() <= 21
            || player.getHandTwoSum() > 21) {

          System.out.println("\nDealer wins!");
        }
      }
      betAmount = 0;
      player.clearBet();
      dealer.emptyHand();
      player.emptyHand();

      if (player.getWallet() < 5) {
        System.out.println("\nYou're out of money, better luck next time.");
      } else {
        System.out.println("\nYou have " + ANSI_GREEN + "$" + player.getWallet() + ANSI_RESET + " in your wallet.");
        System.out.println("Play again? (Y/N): ");
        playAgain = in.next();
      }
    }
    in.close();
  }
}