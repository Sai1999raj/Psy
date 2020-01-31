package com.example.sai.aibill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cal.db";
    public static final String TABLE_NAME = "OPEN_STALK";
    public static final String COL_1 = "ITEM";
    public static final String COL_2 = "QTY";
    public static final String COL_3 = "UNIT";
    public static final String COL_5 = "DIS";
    public static final String COL_4 = "AMT";
    public static final String COL_59 = "DATE";
    public static final String COL_60 = "MONTH";
    public static final String COL_61 = "YEAR";
    public static final String COL_67 = "OLD";
    public static final String COL_68 = "PROFIT";
    public static final String COL_7 = "ITEM1";
    public static final String COL_8 = "QTY1";
    public static final String COL_9 = "UNIT1";
    public static final String COL_10 = "AMT1";
    public static final String COL_11 = "DATE1";
    public static final String COL_12 = "MONTH1";
    public static final String COL_13 = "YEAR1";
    public static final String COL_14 = "HOUR1";
    public static final String COL_15 = "MINUTE1";
    public static final String COL_16 = "BILLNO2";
    public static final String COL_17 = "NAME2";
    public static final String COL_18 = "ITEM2";
    public static final String COL_19 = "QTY2";
    public static final String COL_20 = "UNIT2";
    public static final String COL_21 = "DIS2";
    public static final String COL_22 = "AMT2";
    public static final String COL_23 = "DATE2";
    public static final String COL_24 = "MONTH2";
    public static final String COL_25 = "YEAR2";
    public static final String COL_26 = "HOUR2";
    public static final String COL_27 = "MINUTE2";
    public static final String COL_45 = "DET2";
    public static final String COL_46 = "TAMT2";
    public static final String COL_28 = "COND";
    public static final String COL_70 = "VER1";
    public static final String TABLE_NAME2 = "DETAILS";
    public static final String COL_29 = "DET";
    public static final String COL_71 = "VER2";
    public static final String TABLE_NAME3 = "BUFFER";
    public static final String COL_30 = "ITEM3";
    public static final String COL_31 = "QTY3";
    public static final String COL_32 = "UNIT3";
    public static final String COL_33 = "DIS3";
    public static final String COL_34 = "AMT3";
    public static final String COL_72 = "VER3";
    public static final String TABLE_NAME4 = "BUFFER2";
    public static final String COL_35 = "ITEM4";
    public static final String COL_36 = "QTY4";
    public static final String COL_37 = "UNIT4";
    public static final String COL_38 = "DIS4";
    public static final String COL_39 = "AMT4";
    public static final String COL_40 = "DATE4";
    public static final String COL_41 = "MONTH4";
    public static final String COL_42 = "YEAR4";
    public static final String COL_43 = "HOUR4";
    public static final String COL_44 = "MINUTE4";
    public static final String COL_73 = "VER4";
    public static final String TABLE_NAME5 = "UNPAID";
    public static final String COL_47 = "NAME5";
    public static final String COL_48 = "ITEM5";
    public static final String COL_49 = "QTY5";
    public static final String COL_50 = "UNIT5";
    public static final String COL_51 = "AMT5";
    public static final String COL_52 = "DATE5";
    public static final String COL_53 = "MONTH5";
    public static final String COL_54 = "YEAR5";
    public static final String COL_55 = "HOUR5";
    public static final String COL_56 = "MINUTE5";
    public static final String COL_57 = "CHQTY";
    public static final String COL_58 = "FLAG";
    public static final String COL_69 = "BUY5";
    public static final String COL_74 = "VER5";
    public static final String TABLE_NAME6 = "BUFFER3";
    public static final String COL_62 = "ITEM6";
    public static final String COL_63 = "QTY6";
    public static final String COL_64 = "UNIT6";
    public static final String COL_65 = "DIS6";
    public static final String COL_66 = "AMT6";
    public static final String COL_75 = "VER6";
    public DatabaseHelper(Context context, String s, Object o, int i) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ITEM TEXT,QTY FLOAT,UNIT FLOAT,DIS FLOAT,AMT FLOAT,OLD FLOAT,PROFIT FLOAT,DATE TEXT,MONTH TEXT,YEAR TEXT,VER1 FLOAT)");
        db.execSQL("create table " + TABLE_NAME2 + " (DET TEXT)");
        db.execSQL("create table " + TABLE_NAME3 + "(ITEM3 TEXT,QTY3 FLOAT,UNIT3 FLOAT,AMT3 FLOAT,VER3 FLOAT)");
        db.execSQL("create table " + TABLE_NAME4 + "(ITEM4 TEXT,QTY4 FLOAT,UNIT4 FLOAT,DIS4 FLOAT,AMT4 FLOAT,DATE4 TEXT,MONTH4 TEXT,YEAR4 TEXT,HOUR4 TEXT,MINUTE4 TEXT,VER4 FLOAT)");
        db.execSQL("create table " + TABLE_NAME5 + "(NAME5 TEXT,ITEM5 TEXT,QTY5 FLOAT,UNIT5 FLOAT,AMT5 FLOAT,DATE5 TEXT,MONTH5 TEXT,YEAR5 TEXT,HOUR5 TEXT,MINUTE5 TEXT,CHQTY FLOAT,FLAG FLOAT,BUY5 FLOAT,VER5 FLOAT)");
        db.execSQL("create table " + TABLE_NAME6 + "(ITEM6 TEXT,QTY6 FLOAT,UNIT6 FLOAT,DIS6 FLOAT,AMT6 FLOAT,VER6 FLOAT)");
    }


    public void newpur(String name) {
        SQLiteDatabase db = getWritableDatabase();
        if(name.equals("")) {
            }
        else {
            db.execSQL("create table if not exists " + name + "_purchase (ITEM1 TEXT,QTY1 FLOAT,UNIT1 FLOAT,AMT1 FLOAT,DATE1 TEXT,MONTH1 TEXT,YEAR1 TEXT,HOUR1 TEXT,MINUTE1 TEXT)");

        }
    }

    public void newsell(String name) {
        SQLiteDatabase db = getWritableDatabase();
        if (name.equals("")) {
        } else {
            db.execSQL("create table if not exists " + name + "_sell (BILLNO2 INTEGER,DET2 TEXT,TAMT2 FLOAT,DATE2 TEXT,MONTH2 TEXT,YEAR2 TEXT,HOUR2 TEXT,MINUTE2 TEXT,COND INTEGER)");
        }
    }

    public void newsell2(String name) {
        SQLiteDatabase db = getWritableDatabase();
        if (name.equals("")) {
        } else {
            db.execSQL("create table if not exists " + name + "(BILLNO2 INTEGER,DET2 TEXT,TAMT2 FLOAT,DATE2 TEXT,MONTH2 TEXT,YEAR2 TEXT,HOUR2 TEXT,MINUTE2 TEXT,COND INTEGER)");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME6);
        onCreate(db);

    }

    public boolean insertData(String item, String qty, String unit,String dis, String amt,String old,String pro,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,item);
        contentValues.put(COL_2,qty);
        contentValues.put(COL_3,unit);
        contentValues.put(COL_5,dis);
        contentValues.put(COL_4,amt);
        contentValues.put(COL_59,date);
        contentValues.put(COL_60,month);
        contentValues.put(COL_61,year);
        contentValues.put(COL_67,old);
        contentValues.put(COL_68,pro);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }
        else
            return true;
    }
    public Cursor search()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME2 , null);
        return c;
    }

    public Cursor search3(String name,String item)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME2 , null);
        return c;
    }

    public Cursor search4()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select " +COL_47+ " from " +TABLE_NAME5,null);
        return c;
    }
    public Cursor search5(String item,String date,String month,String year,String hour,String min)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+TABLE_NAME4+" where " + COL_35 + " = '" + item + "' and " +COL_40+ " = " + date + " and " +COL_41+ " = " + month + " and " +COL_42+ " = " + year + " and " +COL_43+ " = " + hour + " and " +COL_44+ " = " + min,null);
        return c;
    }

    public Cursor search6()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME2 + "where " , null);
        return c;
    }


    public boolean insertdata7(String item, String qty, String unit, String dis,String amt, String da, String mo, String ye, String ho, String mi){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_35,item);
        contentValues.put(COL_36,qty);
        contentValues.put(COL_37,unit);
        contentValues.put(COL_38,dis);
        contentValues.put(COL_39,amt);
        contentValues.put(COL_40,da);
        contentValues.put(COL_41,mo);
        contentValues.put(COL_42,ye);
        contentValues.put(COL_43,ho);
        contentValues.put(COL_44,mi
        );
        long result = db.insert(TABLE_NAME4,null,contentValues);
        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public boolean insertitem(String det){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_29,det);
        long result = db.insert(TABLE_NAME2,null,contentValues);
        if (result==-1){
            return false;
        }
        else
            return true;
    }
    public Cursor tab(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select name from sqlite_master where type = 'table'",null);
        return c;
    }
    public boolean insertData1(String item,String qty,String unit,String amt) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_62, item);
        contentValues.put(COL_63, qty);
        contentValues.put(COL_64, unit);
        contentValues.put(COL_66, amt);
            long result = db.insert(TABLE_NAME6, null, contentValues);
            return true;
        }

    public boolean insertData3(String name, String item,String qty,String unit,String amt, String date, String month, String year, String hour, String minute) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, item);
        contentValues.put(COL_8, qty);
        contentValues.put(COL_9, unit);
        contentValues.put(COL_10, amt);
        contentValues.put(COL_11, date);
        contentValues.put(COL_12, month);
        contentValues.put(COL_13, year);
        contentValues.put(COL_14, hour);
        contentValues.put(COL_15, minute);
        long result = db.insert(name, null, contentValues);
        if (name != null && result == -1) {
            return false;
        } else
            return true;
    }


    public boolean up(String item,String qty,String amt){
        SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, qty);
            contentValues.put(COL_4, amt);
            db.update(TABLE_NAME, contentValues, "ITEM = ?", new String[]{item});
            return true;

        }

    public boolean up2(String item,String qty,String unit,String dis,String amt,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, qty);
        contentValues.put(COL_3,unit);
        contentValues.put(COL_5,dis);
        contentValues.put(COL_4, amt);
        db.update(TABLE_NAME, contentValues, "ITEM = ? and DATE = ? and MONTH = ? and YEAR = ?", new String[]{item,date,month,year});
        return true;

    }

    public boolean up4(String name,String item,String date,String month,String year,String hour,String min,String qty,String flag){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_57, qty);
        contentValues.put(COL_58,flag);
        db.update(TABLE_NAME5, contentValues,"NAME5 = ? and ITEM5 = '" + item +"' and DATE5 = ? and MONTH5 = ? and YEAR5 = ? and HOUR5 = ? and MINUTE5 = ?", new String[]{name,date,month,year,hour,min});
        return true;

    }

    public boolean up5(String name,String item,String qty,String amt,String chqty,String flag){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_49, qty);
        contentValues.put(COL_57, chqty);
        contentValues.put(COL_58,flag);
        contentValues.put(COL_51,amt);
        db.update(TABLE_NAME5, contentValues, "NAME5 = ? and ITEM5 = ? and FLAG = ? ", new String[]{name,item,String.valueOf(0)});
        return true;

    }

    public boolean up6(String chqty,String flag){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_57, chqty);
        contentValues.put(COL_58,flag);
        db.update(TABLE_NAME5, contentValues,null,null);
        return true;

    }

    public Cursor we(String Item,String unit,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " +COL_3+ " = " + unit + " and " +COL_59+ " = " + date + " and " +COL_60+ " = " + month + " and "+COL_61+ " = " + year,new String[]{Item} );
        return c;
        }

        public boolean insertdata2(String item,String qty,String unit,String amt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,item);
            contentValues.put(COL_2,qty);
            contentValues.put(COL_3,unit);
            contentValues.put(COL_4,amt);
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            } else
                return true;
        }
        public Cursor view1(String name){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + name,null);
        return c;
        }

    public Cursor vi(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+ TABLE_NAME5 +" where NAME5 = '" + name +"'",null);
        return c;
    }

    public Cursor vi2(String name,String item)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+ TABLE_NAME5 +" where NAME5 = ? and ITEM5 LIKE ?",new String[] {name,item});
        return c;
    }


    public Cursor getdata(String name,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + name +" where " + COL_11 + " = " + date + " and " + COL_12 + " = " + month + " and " + COL_13 + " = " + year,null);
        return c;
        }

    public boolean insertdata4(String item,String qty,String unit,String dis,String amt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_62,item);
        contentValues.put(COL_63,qty);
        contentValues.put(COL_64,unit);
        contentValues.put(COL_65,dis);
        contentValues.put(COL_66,amt);
        long result = db.insert(TABLE_NAME6, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean up1(String item,String qty,String unit,String dis,String amt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_62,item);
        contentValues.put(COL_63,qty);
        contentValues.put(COL_64,unit);
        contentValues.put(COL_65,dis);
        contentValues.put(COL_66,amt);
        db.update(TABLE_NAME6, contentValues, "ITEM6 = ?", new String[]{item});
        return true;

    }

    public boolean up12(String item,String qty,String unit,String amt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_62,item);
        contentValues.put(COL_63,qty);
        contentValues.put(COL_64,unit);
        contentValues.put(COL_66,amt);
        db.update(TABLE_NAME6, contentValues, "ITEM6 = ?", new String[]{item});
        return true;

    }

    public boolean up7(String item,String qty,String unit,String amt,String old,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,qty);
        contentValues.put(COL_4,amt);
        contentValues.put(COL_67,old);
        db.update(TABLE_NAME, contentValues,"ITEM = ? and UNIT = ? and DATE = ? and MONTH = ? and YEAR = ?",new String[]{item,unit,date,month,year});
            return true;

    }

    public boolean up8(String item,String qty,String unit,String dis,String amt,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,qty);
        contentValues.put(COL_5,dis);
        contentValues.put(COL_4,amt);
        db.update(TABLE_NAME, contentValues,"ITEM = ? and DATE = ? and MONTH = ? and YEAR = ?",new String[]{item,date,month,year});
        return true;

    }

    public boolean up9(String item,String qty,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,qty);
        db.update(TABLE_NAME, contentValues,"ITEM = ?  and DATE = ? and MONTH = ? and YEAR = ?",new String[]{item,date,month,year});
        return true;

    }

    public boolean up10(String item,String unit,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3,unit);
        db.update(TABLE_NAME, contentValues,"ITEM = ? and DATE = ? and MONTH = ? and YEAR = ?",new String[]{item,date,month,year});
        return true;

    }

    public boolean up11(String item,String qty,String dis,String amt,String pro,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,qty);
        contentValues.put(COL_5,dis);
        contentValues.put(COL_4,amt);
        contentValues.put(COL_68,pro);
        db.update(TABLE_NAME, contentValues,"ITEM = ? and DATE = ? and MONTH = ? and YEAR = ?",new String[]{item,date,month,year});
        return true;

    }
    public boolean up13(String name,String item,String qty,String amt){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_49, qty);
        contentValues.put(COL_51,amt);
        db.update(TABLE_NAME5, contentValues, "NAME5 = ? and ITEM5 = ?", new String[]{name,item});
        return true;

    }

    public boolean up14(String item,String qty,String unit,String dis,String amt,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_73, String.valueOf(1));
        db.update(TABLE_NAME4, contentValues, "ITEM4 = ? and QTY4 = ? and UNIT4 = ? and DIS4 = ? and AMT4 = ? and DATE4 = ? and MONTH4 = ? and YEAR4 = ?", new String[]{item,qty,unit,dis,amt,date,month,year});
        return true;

    }

    public boolean up15(String item,String qty,String unit,String dis,String amt,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_73, String.valueOf(0));
        db.update(TABLE_NAME4, contentValues, "ITEM4 = ? and QTY4 = ? and UNIT4 = ? and DIS4 = ? and AMT4 = ? and DATE4 = ? and MONTH4 = ? and YEAR4 = ?", new String[]{item,qty,unit,dis,amt,date,month,year});
        return true;

    }




    public Cursor view2(String item){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME6 + " where " + COL_62 + " = ?" ,new String[] {item});
        return c;
    }

    public Cursor view3(String item){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME4 + " where " + COL_35 + " = ?" ,new String[] {item});
        return c;
    }

    public Cursor view4(String flag){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME5 + " where " + COL_58 + " = ? " ,new String[] {flag});
        return c;
    }

    public Cursor view5(String item,String unit,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " + COL_59 + " = ? and " + COL_3 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?",new String[] {item,date,unit,month,year});
        return c;
    }

    public Cursor view6(String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ? and " + COL_2 + " != 0",new String[] {date,month,year});
        return c;
    }

    public Cursor view7(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME ,null);
        return c;
    }

    public Cursor view8(String item){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME6 + " where " + COL_62 + " = ? "  ,new String[] {item});
        return c;
    }

    public Cursor view9(String item,String unit,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?" ,new String[] {item,date,month,year});
        return c;
    }

    public Cursor view10(String item,String old,String unit,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " + COL_67 + " = ? and " + COL_3 + " = ? and " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?" ,new String[] {item,old,unit,date,month,year});
        return c;
    }

    public Cursor view11(String item,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " + COL_2 + " != ? and " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?" ,new String[] {item,String.valueOf(0),date,month,year});
        return c;
    }
    public Cursor view12(String item,String old,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " + COL_67 + " = ? and " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?" ,new String[] {item,old,date,month,year});
        return c;
    }

    public Cursor view13(String item,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_1 + " = ? and " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?" ,new String[] {item,date,month,year});
        return c;
    }

    public Cursor view14(String name,String item,String date,String month,String year,String hour,String min){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME5 + " where " + COL_47 + " = ? and " + COL_48 + " = ? and " + COL_52 + " = ? and " + COL_53 + " = ? and " + COL_54 + " = ? and " + COL_55 + " = ? and " + COL_56 + " = ?" ,new String[] {name,item,date,month,year,hour,min});
        return c;
    }

    public Cursor view15(String text){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where ITEM LIKE ? and QTY != 0" ,new String[] {text});
        return c;
    }

    public Cursor view16(String text,String unit){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where ITEM LIKE ? and QTY != 0 and UNIT = ?" ,new String[] {text,unit});
        return c;
    }

    public Cursor view17(String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where DATE = ? and MONTH = ? and YEAR = ? and QTY != ?" ,new String[] {date,month,year,String.valueOf(0)});
        return c;
    }

    public Cursor view18(String text){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME6 + " where ITEM6 LIKE ? and QTY6 != 0" ,new String[] {text});
        return c;
    }

    public Cursor view19(String text,String date,String month,String year,String hour,String min){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME4 + " where ITEM4 LIKE ? and DATE4 = ? and MONTH4 = ? and YEAR4 = ? and HOUR4 = ? and MINUTE4 = ?" ,new String[] {text,date,month,year,hour,min});
        return c;
    }

    public Cursor view20(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME2  ,new String[] {});
        return c;
    }

    public Cursor view21(String name,String item,String date,String month,String year,String hour,String min){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME5 + " where " + COL_47 + " = ? and " + COL_48 + " LIKE ? and " + COL_52 + " = ? and " + COL_53 + " = ? and " + COL_54 + " = ? and " + COL_55 + " = ? and " + COL_56 + " = ?" ,new String[] {name,item,date,month,year,hour,min});
        return c;
    }
    public Cursor view22(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME4 + " where QTY4 != 0",null);
        return c;
    }

    public Cursor view23(String item,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where ITEM LIKE ? and DATE = ? and MONTH = ? and YEAR = ? and QTY != 0" ,new String[] {item,date,month,year});
        return c;
    }

    public Cursor view24(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME6 + " where QTY6 != 0",null);
        return c;
    }

    public Cursor view25(String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_59 + " = ? and " + COL_60 + " = ? and " + COL_61 + " = ?",new String[] {date,month,year});
        return c;
    }





    public boolean insertdata5(String name,String det,String tamt,String date,String month,String year,String hour,String minute,String con){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_45,det);
        contentValues.put(COL_46,tamt);
        contentValues.put(COL_23,date);
        contentValues.put(COL_24,month);
        contentValues.put(COL_25,year);
        contentValues.put(COL_26,hour);
        contentValues.put(COL_27,minute);
        contentValues.put(COL_28,con);
        long result = db.insert(name, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata6(String name,String det,String tamt,String date,String month,String year,String hour,String minute,String con){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_45,det);
        contentValues.put(COL_46,tamt);
        contentValues.put(COL_23,date);
        contentValues.put(COL_24,month);
        contentValues.put(COL_25,year);
        contentValues.put(COL_26,hour);
        contentValues.put(COL_27,minute);
        contentValues.put(COL_28,con);
        long result = db.insert(name+"_sell", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata9(String name,String det,String amt,String date,String month,String year,String hour,String minute,String con){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_45,det);
        contentValues.put(COL_22,amt);
        contentValues.put(COL_23,date);
        contentValues.put(COL_24,month);
        contentValues.put(COL_25,year);
        contentValues.put(COL_26,hour);
        contentValues.put(COL_27,minute);
        contentValues.put(COL_28,con);
        long result = db.insert(name+"_sell", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata10(String name,String det,String amt,String date,String month,String year,String hour,String minute,String con){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_45,det);
        contentValues.put(COL_22,amt);
        contentValues.put(COL_23,date);
        contentValues.put(COL_24,month);
        contentValues.put(COL_25,year);
        contentValues.put(COL_26,hour);
        contentValues.put(COL_27,minute);
        contentValues.put(COL_28,con);
        long result = db.insert(name, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata11(String name,String ite,String qty,String unit,String amt,String date,String month,String year,String hour,String minute,String chqty,String flag,String buy){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_47,name);
        contentValues.put(COL_48,ite);
        contentValues.put(COL_49,qty);
        contentValues.put(COL_50,unit);
        contentValues.put(COL_51,amt);
        contentValues.put(COL_52,date);
        contentValues.put(COL_53,month);
        contentValues.put(COL_54,year);
        contentValues.put(COL_55,hour);
        contentValues.put(COL_56,minute);
        contentValues.put(COL_57,chqty);
        contentValues.put(COL_58,flag);
        contentValues.put(COL_69,buy);
        long result = db.insert(TABLE_NAME5, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata12(String name,String ite,String qty,String unit,String amt,String date,String month,String year,String hour,String minute,String chqty,String flag,String buy){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_47,name+"_sell");
        contentValues.put(COL_48,ite);
        contentValues.put(COL_49,qty);
        contentValues.put(COL_50,unit);
        contentValues.put(COL_51,amt);
        contentValues.put(COL_52,date);
        contentValues.put(COL_53,month);
        contentValues.put(COL_54,year);
        contentValues.put(COL_55,hour);
        contentValues.put(COL_56,minute);
        contentValues.put(COL_57,chqty);
        contentValues.put(COL_58,flag);
        contentValues.put(COL_69,buy);
        long result = db.insert(TABLE_NAME5, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata13(String name,String ite,String qty,String unit,String amt,String date,String month,String year,String hour,String minute){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_47,name+"_sell");
        contentValues.put(COL_48,ite);
        contentValues.put(COL_49,qty);
        contentValues.put(COL_50,unit);
        contentValues.put(COL_51,amt);
        contentValues.put(COL_52,date);
        contentValues.put(COL_53,month);
        contentValues.put(COL_54,year);
        contentValues.put(COL_55,hour);
        contentValues.put(COL_56,minute);
        long result = db.insert(TABLE_NAME5, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata14(String name,String ite,String qty,String unit,String amt,String date,String month,String year,String hour,String minute){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7,ite);
        contentValues.put(COL_8,qty);
        contentValues.put(COL_9,unit);
        contentValues.put(COL_10,amt);
        contentValues.put(COL_11,date);
        contentValues.put(COL_12,month);
        contentValues.put(COL_13,year);
        contentValues.put(COL_14,hour);
        contentValues.put(COL_15,minute);
        long result = db.insert(name, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata16(String ite,String qty,String unit,String dis,String amt,String old,String pro,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ite);
        contentValues.put(COL_2,qty);
        contentValues.put(COL_3,unit);
        contentValues.put(COL_5,dis);
        contentValues.put(COL_4,amt);
        contentValues.put(COL_59,date);
        contentValues.put(COL_60,month);
        contentValues.put(COL_61,year);
        contentValues.put(COL_67,old);
        contentValues.put(COL_68,pro);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata15(String name,String ite,String qty,String unit,String amt,String date,String month,String year,String hour,String minute){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7,ite);
        contentValues.put(COL_8,qty);
        contentValues.put(COL_9,unit);
        contentValues.put(COL_10,amt);
        contentValues.put(COL_11,date);
        contentValues.put(COL_12,month);
        contentValues.put(COL_13,year);
        contentValues.put(COL_14,hour);
        contentValues.put(COL_15,minute);
        long result = db.insert(name +"_purchase", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertdata17(String ite,String qty,String unit,String dis,String amt,String pro,String date,String month,String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ite);
        contentValues.put(COL_2,qty);
        contentValues.put(COL_3,unit);
        contentValues.put(COL_5,dis);
        contentValues.put(COL_4,amt);
        contentValues.put(COL_59,date);
        contentValues.put(COL_60,month);
        contentValues.put(COL_61,year);
        contentValues.put(COL_68,pro);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }






    public int dele(String item,String qty,String unit,String dis,String amt)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME6,"ITEM6 = ? and QTY6 = ? and UNIT6 = ? and DIS6 = ? and AMT6 = ?",new String[] {item,qty,unit,dis,amt});
    }

    public int dele5(String item,String qty,String unit,String amt)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME6,"ITEM6 = ? and QTY6 = ? and UNIT6 = ? and AMT6 = ?",new String[] {item,qty,unit,amt});
    }

    public int dele2(String item,String qty,String unit,String amt)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME4,"ITEM4 = ? and QTY4 = ? and UNIT4 = ? and AMT4 = ?",new String[] {item,qty,unit,amt});
    }

    public int dele1()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME6,null,null);
    }

    public int dele3()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME4,null,null);
    }

    public int dele4(String name,String item,String date,String month,String year,String hour,String min,String chqty)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME5,"NAME5 = ? and ITEM5 = ? and DATE5 = ? and MONTH5 = ? and YEAR5 = ? and HOUR5 = ? and MINUTE5 = ? and CHQTY = ?",new String[] {name,item,date,month,year,hour,min,chqty});
    }

    public int dele6(String name,String item,String date,String month,String year,String hour,String min)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME5,"NAME5 = ? and ITEM5 = ? and DATE5 = ? and MONTH5 = ? and YEAR5 = ? and HOUR5 = ? and MINUTE5 = ?",new String[] {name,item,date,month,year,hour,min});

    }











}
