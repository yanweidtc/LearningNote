package com.yanwei.learningnote.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private CopyOnWriteArrayList<Book> mBooks;

    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public CopyOnWriteArrayList<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        mBooks = new CopyOnWriteArrayList<>();
        Log.d(TAG, "MyAidlService onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new Book(1, "Android"));
        mBooks.add(new Book(2, "IOS"));
    }
}
