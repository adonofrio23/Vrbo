package server;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

public class CreateUser {
	public static void create(String username, String password, String firstName, String lastName, String email, String phone, String cc) {
		Map<String, Object> data = new HashMap<>();
		data.put("username", username);
		data.put("password", password);
		data.put("firstName", firstName);
		data.put("lastName", lastName);
		data.put("email", email);
		data.put("phone", phone);
		data.put("cc", cc);
		
		ApiFuture<WriteResult> resp = Firebase.db.collection("users").document(username).set(data, SetOptions.merge());
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
		if (success) {
			Server.sendMessage("SUCCESS");
		} else {
			Server.sendMessage("ERROR");
		}
	}
}
