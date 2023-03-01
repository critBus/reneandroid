package com.rene.android.reneandroid.Clases;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Rene on 28/6/2020.
 */

public class Contacto {
    private String name,telefono,email,compañia,trabajo,nota;

    public Contacto(String name, String telefono, String email, String compañia, String trabajo, String nota) {
        this.name = name;
        this.telefono = telefono;
        this.email = email;
        this.compañia = compañia;
        this.trabajo = trabajo;
        this.nota = nota;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public static  Contacto[] getContactos(Activity a){
        Cursor cu = a.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                new String[] {ContactsContract.Data._ID, ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.LABEL,
                        ContactsContract.Contacts.DISPLAY_NAME},
                //ContactsContract.Data.CONTACT_ID + "=?" + " AND " +
                ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
                null// new String[] {String.valueOf(contactId)}
                , null);
       // LinkedList<String> lc=new LinkedList<>();
        LinkedList<Contacto> lc=new LinkedList<>();
        if (cu.getCount()>0){
            while(cu.moveToNext()){
                String id=cu.getString(cu.getColumnIndex(ContactsContract.Data._ID));
                String name=cu.getString(cu.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phone=cu.getString(cu.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String type=cu.getString(cu.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                String label=cu.getString(cu.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
               // lc.add(id+" "+name+"\n"+phone+"\n"+type+" "+label);
                lc.add(new Contacto(name,phone,"","","",label));
            }
        }

      /*  if (cu.getCount()>0){
            while(cu.moveToNext()){
                lc.add(new Contacto(
                        cu.getString(cu.getColumnIndex(Contacts.People._ID));
                ));
            }} */
       // VisualAndroid.toast(a,c.getCount());
return lc.toArray(new Contacto[0]);
    }
    /**
     * No es mio pero funciona
     * @param a
     * @param c
     */
    public static void addContactoVentana(Activity a, Contacto c){
        String name = c.getName();
        String phone = c.getTelefono();
        String email = c.getEmail();

        String company =c.getCompañia();
        String jobtitle = c.getTrabajo();

// Creates a new intent for sending to the device's contacts application
        Intent insertIntent = new Intent(ContactsContract.Intents.Insert.ACTION);

// Sets the MIME type to the one expected by the insertion activity
        insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

// Sets the new contact name
        insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, name);

// Sets the new company and job title
        insertIntent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
        insertIntent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobtitle);

/*
 * Demonstrates adding data rows as an array list associated with the DATA key
 */

// Defines an array list to contain the ContentValues objects for each row
        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();


/*
 * Defines the raw contact row
 */

// Sets up the row as a ContentValues object
        ContentValues rawContactRow = new ContentValues();

// Adds the account type and name to the row
        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_TYPE, Contacts.KIND_POSTAL);// mSelectedAccount.getType()
        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_NAME, name);

// Adds the row to the array
        contactData.add(rawContactRow);

/*
 * Sets up the phone number data row
 */

// Sets up the row as a ContentValues object
        ContentValues phoneRow = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        phoneRow.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        phoneRow.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);

// Adds the row to the array
        contactData.add(phoneRow);

/*
 * Sets up the email data row
 */

// Sets up the row as a ContentValues object
        ContentValues emailRow = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        emailRow.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
        );

// Adds the email address and its type to the row
        emailRow.put(ContactsContract.CommonDataKinds.Email.ADDRESS, email);

// Adds the row to the array
        contactData.add(emailRow);

/*
 * Adds the array to the intent's extras. It must be a parcelable object in order to
 * travel between processes. The device's contacts app expects its key to be
 * Intents.Insert.DATA
 */
        insertIntent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);

// Send out the intent to start the device's contacts app in its add contact activity.
        a.startActivity(insertIntent);
    }
}
/*
public static void addContactoVentana(Activity a, Contacto c) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> ops =
                new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValue(ContactsContract.Data.RAW_CONTACT_ID, 124)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, c.getTelefono())
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM)
                .withValue(ContactsContract.CommonDataKinds.Phone.LABEL, c.getNota())
                .withValue(ContactsContract.Data.RAW_CONTACT_ID, 124)
                .build());
        a.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);

    }
int rawContactId=876;
            ArrayList<ContentProviderOperation> ops =
                    new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Bob Parr")
                    .build());

            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Nickname.NAME, "Mr. Incredible")
                    .withValue(ContactsContract.CommonDataKinds.Nickname.TYPE, ContactsContract.CommonDataKinds.Nickname.TYPE_CUSTOM)
                    .withValue(ContactsContract.CommonDataKinds.Nickname.LABEL, "Superhero")
                    .build());

            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops); */
