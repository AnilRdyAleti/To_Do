package com.example.anilreddy.to_do_project.utility;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.anilreddy.to_do_project.main.MainFragment;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class DeleteNotificationService extends IntentService {

    private StoreRetrieveData storeRetrieveData;
    private ArrayList<ToDoItem> mToDoItems;
    private ToDoItem mItem;

    DeleteNotificationService() {
        super("DeleteNotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        storeRetrieveData = new StoreRetrieveData(this, MainFragment.FILENAME);
        UUID todoID = (UUID) intent.getSerializableExtra(TodoNotificationService.TODOUUID);

        mToDoItems = loadData();
        if (mToDoItems != null) {
            for (ToDoItem item : mToDoItems) {
                if (item.getIdentifier().equals(todoID)) {
                    mItem = item;
                    break;
                }
            }

            if (mItem != null) {
                mToDoItems.remove(mItem);
                dataChanged();
                saveData();
            }
        }
    }

    private ArrayList<ToDoItem> loadData() {
        try {
            return storeRetrieveData.loadFromFile();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void saveData() {
        try {
            storeRetrieveData.saveToFile(mToDoItems);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void dataChanged() {
        SharedPreferences sharedPreferences = getSharedPreferences(MainFragment.SHARED_PREF_DATA_SET_CHANGED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MainFragment.CHANGE_OCCURED, true);
        editor.apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveData();
    }

}
