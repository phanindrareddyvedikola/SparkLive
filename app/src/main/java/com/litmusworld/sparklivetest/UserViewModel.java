package com.litmusworld.sparklivetest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {

    private MutableLiveData<List<User>>  userData;
    private GetUserDataRepository userDataRepo;

    public void init(){

        if(userData != null) {
            return;
        }
        userDataRepo = GetUserDataRepository.getInstance();
        userData = userDataRepo.getUserData();
    }

    public LiveData<List<User>> getUserData(){
        return userData;
    }

}
