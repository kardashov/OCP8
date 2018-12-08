package stas.concurrency;

import java.util.concurrent.Exchanger;

//The DukeThread class runs as an independent thread. It talks to the CoffeeShopThread that
//also runs independently. The chat is achieved by exchanging messages through a common
//Exchanger<String> object that synchronizes the chat between them.
//Note that the message printed are the "responses" received from CoffeeShopThread
class DukeThread extends Thread {
	private Exchanger<String> sillyTalk;

	public DukeThread(Exchanger<String> args) {
		sillyTalk = args;
	}

	public void run() {
		String reply = null;
		try {
			// start the conversation with CoffeeShopThread
			reply = sillyTalk.exchange("Knock knock!");
			// Now, print the response received from CoffeeShopThread
			System.out.println("CoffeeShop: " + reply);

//			 exchange another set of messages
			reply = sillyTalk.exchange("Duke");
//			 Now, print the response received from CoffeeShopThread
			System.out.println("CoffeeShop: " + reply);

//			 an exchange could happen only when both send and receive happens
//			 since this is the last sentence to speak, we close the chat by
//			 ignoring the "dummy" reply
			reply = sillyTalk.exchange("The one who was born in this coffee shop!");
			// talk over, so ignore the reply!
		} catch (InterruptedException ie) {
			System.err.println("Got interrupted during my silly talk");
		}
	}
}

class CoffeeShopThread extends Thread {
	private Exchanger<String> sillyTalk;

	public CoffeeShopThread(Exchanger<String> args) {
		sillyTalk = args;
	}

	public void run() {
		String reply = null;
		try {
			// exchange the first messages
			reply = sillyTalk.exchange("Who's there?");
			// print what Duke said
			System.out.println("Duke: " + reply);

			// exchange second message
			reply = sillyTalk.exchange("Duke who?");
			// print what Duke said
			System.out.println("Duke: " + reply);

			// there is no message to send, but to get a message from Duke
			// thread,
			// both ends should send a message; so send a "dummy" string
			reply = sillyTalk.exchange("");
			System.out.println("Duke: " + reply);
		} catch (InterruptedException ie) {
			System.err.println("Got interrupted during my silly talk");
		}
	}
}

// Coordinate the silly talk between Duke and CoffeeShop by instantitaing the
// Exchanger object
// and the CoffeeShop and Duke threads
class Exchanger_DEMO {
	public static void main(String[] args) {
		Exchanger<String> sillyTalk = new Exchanger<String>();
		new CoffeeShopThread(sillyTalk).start();
		new DukeThread(sillyTalk).start();
	}
}