package server;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Firebase {
	public static Firestore db = null;
	public static boolean init() {
		FirebaseOptions options = null;
		
		try {
			FileInputStream serviceAccount = new FileInputStream("./src/server/serviceAccount.json");
			
			options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://vrbo-server.firebaseio.com/")
					.setStorageBucket("vrbo-server.appspot.com")
					.build();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[Firebase] - Failed To Initialize");
			return false;
		}
	
		FirebaseApp.initializeApp(options);
		System.out.println("[Firebase] - Initialized Successfully");
		db = FirestoreClient.getFirestore();
		return true;
	}
	
}
