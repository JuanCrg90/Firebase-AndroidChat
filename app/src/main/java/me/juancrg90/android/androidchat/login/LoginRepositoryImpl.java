package me.juancrg90.android.androidchat.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import me.juancrg90.android.androidchat.domain.FirebaseHelper;
import me.juancrg90.android.androidchat.entities.User;
import me.juancrg90.android.androidchat.lib.EventBus;
import me.juancrg90.android.androidchat.lib.GreenRobotEventBus;
import me.juancrg90.android.androidchat.login.events.LoginEvent;

/**
 * Created by JuanCrg90
 */
public class LoginRepositoryImpl implements  LoginRepository {

    private FirebaseHelper helper;
    FirebaseAuth authReference;
    DatabaseReference myUserReference;

    public LoginRepositoryImpl() {

        this.helper = FirebaseHelper.getInstance();
        this.authReference = helper.getAuthReference();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {
        authReference.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    postEvent(LoginEvent.onSignUpError, task.getException().toString());
                }
                else {
                    postEvent(LoginEvent.onSignUpSuccess);
                    signIn(email, password);
                }

            }
        });

    }

    @Override
    public void signIn(String email, String password) {

        authReference.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("Error", "signInWithEmail", task.getException());
                    postEvent(LoginEvent.onSignInError, task.getException().toString());
                }
                else {
                    Log.d("success", "signInWithEmail:onComplete:" + task.isSuccessful());
                    myUserReference = helper.getMyUserReference();
                    myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User currentUser = dataSnapshot.getValue(User.class);


                            if(currentUser == null) {
                                String email = helper.getAuthUserEmail();

                                if(email != null) {
                                    currentUser = new User();
                                    myUserReference.setValue(currentUser);
                                }
                            }

                            helper.changeUserConnectionStatus(User.ONLINE);
                            postEvent(LoginEvent.onSignInSuccess);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }
        });
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);

        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
