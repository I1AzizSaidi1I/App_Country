package isetb.tp6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class CountryDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "country_db";
    public static final String TCountry = "country";
    public static final String CID = "id";
    public static final String CNAME = "name";
    public static final String CPOP = "population";

    public static final String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TCountry + "("
            + CID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CNAME + " VARCHAR(28) NOT NULL, "
            + CPOP + " REAL)";

    public CountryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_COUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TCountry);
        onCreate(sqLiteDatabase);
    }

    public Boolean addCountry(Country c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CNAME, c.getName());
        values.put(CPOP, c.getPopulation());
        long result = db.insert(TCountry, null, values);
        db.close();
        return result != -1;
    }

    public Country getCountryById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TCountry, new String[]{CID, CNAME, CPOP}, CID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Country country = new Country(cursor.getInt(cursor.getColumnIndexOrThrow(CID)), cursor.getString(cursor.getColumnIndexOrThrow(CNAME)), cursor.getDouble(cursor.getColumnIndexOrThrow(CPOP)));
            cursor.close();
            db.close();
            return country;
        } else {
            db.close();
            return null;
        }
    }

    public ArrayList<Country> getAllCountry() {
        ArrayList<Country> countryList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TCountry;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CID)));
                country.setName(cursor.getString(cursor.getColumnIndexOrThrow(CNAME)));
                country.setPopulation(cursor.getDouble(cursor.getColumnIndexOrThrow(CPOP)));
                countryList.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return countryList;
    }
    public void deleteCountry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TCountry, CID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateCountry(Country country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CNAME, country.getName());
        values.put(CPOP, country.getPopulation());
        db.update(TCountry, values, CID + " = ?", new String[]{String.valueOf(country.getId())});
        db.close();
    }
}