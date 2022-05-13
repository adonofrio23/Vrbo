package server;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.QuerySnapshot;

public class GetUsers {
	public static String num = "0";
	public static String getUsers() {
		ApiFuture<QuerySnapshot> doc = Firebase.db.collection("users").get();
		ApiFutures.addCallback(doc, new ApiFutureCallback<QuerySnapshot>() {
			@Override
		    public void onSuccess(QuerySnapshot result) {
		    	if (!result.isEmpty()) {
		    		num = Integer.toString(returnUsers(result.size(), true));
		    	} else {
		    		num = Integer.toString(returnUsers(0, false));
		    	}
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	num = Integer.toString(returnUsers(0, false));
		    }
		}, Runnable::run);
		return num;
	}
	
	private static int returnUsers(int num, boolean valid) {
		return num;
	}
}

