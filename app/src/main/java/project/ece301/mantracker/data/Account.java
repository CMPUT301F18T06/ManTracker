package project.ece301.mantracker.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Account {
    //includes username, email and phone #
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone")
    private String phone;

    public Account(@NonNull String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}

