package server;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.DocumentSnapshot;

public class AdminLogin {
	static boolean res = false;
	public static boolean login(String username, String password) {
		ApiFuture<DocumentSnapshot> doc = Firebase.db.collection("users").document("admin").get();
		ApiFutures.addCallback(doc, new ApiFutureCallback<DocumentSnapshot>() {
			@Override
		    public void onSuccess(DocumentSnapshot result) {
		    	if (result.exists())
		    		if (loginResponse(username, password, result))
		    			res = true;
		    		else
		    			res = false;
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	res = false;
		    }
		}, Runnable::run);
		return res;
	}
	
	private static boolean loginResponse(String username, String password, DocumentSnapshot snap) {
		boolean valid = false;
		
		String user = "";
		String pass = "";
		try {
			user = snap.getString("username");
			pass = snap.getString("password");
		} catch (RuntimeException e) {
			System.out.println("[Firebase] - " + e);
		}
		
		if (user.equals(username) && pass.equals(password)) {
			valid = true;
		} else {
			valid = false;
		}
		
		return valid;
	}
}
