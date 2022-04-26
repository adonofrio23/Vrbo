package server;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.DocumentSnapshot;

public class UserLogin {
	public static void login(String username, String password) {
		ApiFuture<DocumentSnapshot> doc = Firebase.db.collection("users").document(username).get();
		ApiFutures.addCallback(doc, new ApiFutureCallback<DocumentSnapshot>() {
			@Override
		    public void onSuccess(DocumentSnapshot result) {
		    	if (result.exists())
		    		loginResponse(username, password, result, true);
		    	else
		    		loginResponse(username, password, result, false);
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	loginResponse(username, password, null, false);
		    }
		}, Runnable::run);
	}
	
	private static void loginResponse(String username, String password, DocumentSnapshot snap, boolean found) {
		if (found) {
			String user = "";
			String pass = "";
			try {
				user = snap.getString("username");
				pass = snap.getString("password");
			} catch (RuntimeException e) {
				System.out.println("[Firebase] - " + e);
			}
			
			if (user.equals(username) && pass.equals(password)) {
				Server.sendMessage("VALID");
			} else {
				Server.sendMessage("INVALID");
			}
		} else if (!found && snap != null){
			Server.sendMessage("INVALID");
		} else if (!found && snap == null) {
			Server.sendMessage("ERROR");
		}
	}
}
