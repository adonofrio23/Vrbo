package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class GetListings {
	public static void getListing(String location, String beds) {
		ApiFuture<QuerySnapshot> doc = Firebase.db.collection("rentals").document(location).collection(beds).get();
		ApiFutures.addCallback(doc, new ApiFutureCallback<QuerySnapshot>() {
			@Override
		    public void onSuccess(QuerySnapshot result) {
		    	if (!result.isEmpty()) {
		    		List<QueryDocumentSnapshot> docs = new ArrayList<>();
		    		for (QueryDocumentSnapshot data : result) {
		    			docs.add(data);
		    		}
		    		returnListings(docs, true);
		    	} else {
		    		returnListings(null, false);
		    	}
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	returnListings(null, false);
		    }
		}, Runnable::run);
	}
	
	private static void returnListings(List<QueryDocumentSnapshot> list, boolean valid) {
		if (valid) {
			String message = "";
			Collections.shuffle(list);
			for (QueryDocumentSnapshot data : list) {
				message += data.getData();
				message += "+";
			}
			Server.sendMessage(message);
		} else {
			Server.sendMessage("INVALID");
		}
	}
}
