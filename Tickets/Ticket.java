public abstract class Ticket {
	private static int nextTicketId = 1;
	private int ticketId;

	public Ticket() {
		ticketId = getNextTicketId();
	}

	public abstract double getPrice();

	private static int getNextTicketId() {
		return nextTicketId++;
	}

	public String toString() {
		return "Number: " + ticketId + " Price: " + getPrice();
	}

	public static void main(String[] args) {
		Ticket w = new WalkIn();
		Ticket r = new Reserved(8);
		Ticket sr = new StudentReserved(12);
		System.out.println(w);
		System.out.println(r);
		System.out.println(sr);
	}
}