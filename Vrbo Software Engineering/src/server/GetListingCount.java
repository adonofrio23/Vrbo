package server;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.DocumentSnapshot;

public class GetListingCount {
	public static int num = 0;
	public static int getListing() {
		ApiFuture<DocumentSnapshot> doc = Firebase.db.collection("rentals").document("Count").get();
		ApiFutures.addCallback(doc, new ApiFutureCallback<DocumentSnapshot>() {
			@Override
		    public void onSuccess(DocumentSnapshot result) {
		    	if (result.exists()) {
		    		num = returnListings(result, true);
		    	} else {
		    		num = returnListings(null, false);
		    	}
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	num = returnListings(null, false);
		    }
		}, Runnable::run);
		return num;
	}
	
	private static int returnListings(DocumentSnapshot doc, boolean valid) {
		num = 0;
		String field = "";
		if (valid) {
			try {
				field = doc.getString("num");
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			num = Integer.parseInt(field);
		}
		return num;
	}
	
	public static int getNum() {
		return num;
	}
}
