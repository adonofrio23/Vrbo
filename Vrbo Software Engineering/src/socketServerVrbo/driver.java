package socketServerVrbo;

public class driver {
	public static void main(String args[]) {
		socketUtils su = new socketUtils();
		su.socketConnect();
		su.sendMessage("Date>");
		String message = su.recvMessage();
		message = message.replaceAll(",", "\n");
		System.out.println(message);
		//su.sendMessage("QUIT");
		su.closeSocket();
	}
}
