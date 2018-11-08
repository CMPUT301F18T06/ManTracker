package project.ece301.mantracker.dataManagers;
//see https://developer.android.com/training/data-storage/room/

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import project.ece301.mantracker.data.Account;
import project.ece301.mantracker.data.Comment;
import project.ece301.mantracker.data.MedicalProblem;
import project.ece301.mantracker.data.Record;
import project.ece301.mantracker.data.RecordDAO;

@Database(entities = {Account.class, Record.class, MedicalProblem.class,
        Comment.class}, version = 1)
public abstract class LocalAppDatabase extends RoomDatabase {

    private static volatile LocalAppDatabase INSTANCE;

    public abstract RecordDAO recordDAO();

    static LocalAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalAppDatabase.class, "mantracker_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RecordDAO recordDAO;

        PopulateDbAsync(LocalAppDatabase db) {
            recordDAO = db.recordDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}