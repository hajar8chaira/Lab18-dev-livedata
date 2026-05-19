package com.example.viewmodellivedatademoenrichi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    public CounterViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        Integer savedCount = savedStateHandle.get("count_key");
        countLiveData.setValue(savedCount != null ? savedCount : 0);
    }

    public void increment() {
        Integer current = countLiveData.getValue();
        if (current != null) {
            int newVal = current + 1;
            countLiveData.setValue(newVal);
            savedStateHandle.set("count_key", newVal);
        }
    }

    public void decrement() {
        Integer current = countLiveData.getValue();
        if (current != null) {
            int newVal = current - 1;
            countLiveData.setValue(newVal);
            savedStateHandle.set("count_key", newVal);
        }
    }

    public void reset() {
        countLiveData.setValue(0);
        savedStateHandle.set("count_key", 0);
    }

    public void incrementFromBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer current = countLiveData.getValue();
                if (current != null) {
                    int newVal = current + 1;
                    countLiveData.postValue(newVal);
                    savedStateHandle.set("count_key", newVal);
                }
            }
        }).start();
    }

    public LiveData<Integer> getCount() {
        return countLiveData;
    }
}
