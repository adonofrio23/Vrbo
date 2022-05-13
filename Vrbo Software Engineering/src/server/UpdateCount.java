package server;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

public class UpdateCount {
	public static void update() {
		Map<String, String> m = new HashMap<>();
		m.put("num", Integer.toString(GetListingCount.getNum() + 1));
		GetListingCount.num = GetListingCount.num + 1;
		ApiFuture<WriteResult> resp = Firebase.db.collection("rentals")
				.document("Count")
				.set(m, SetOptions.merge());
		ApiFutures.addCallback(resp, new ApiFutureCallback<WriteResult>() {
			@Override
		    public void onSuccess(WriteResult result) {
		    	createResult(result, true);
		    }

		    @Override
		    public void onFailure(Throwable t) {
		    	System.out.println("[Firebase] - " + t);
		    	createResult(null, false);
		    }
		}, Runnable::run);
	}
	
	private static void createResult(WriteResult result, boolean success) {
		if (success) {} else {}
	}
}
