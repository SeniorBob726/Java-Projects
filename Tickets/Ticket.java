public abstract class Ticket {
	private int ticketId;

	public Ticket() {
		ticketId = getNextTicketId();
	}

	public abstract double getPrice();

	private static int getNextTicketId() {

	}

	public String toString() {
		return "Number: " + ticketId + " Price: " + getPrice();
	}
}