package com.sg.evaluation;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity implements OnClickListener
{
    EditText RRN,Name,CGPA;
    Button Insert,Delete,Update,View,ViewAll;
    SQLiteDatabase db;
    /** Called when the activity is first created. */
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RRN=(EditText)findViewById(R.id.RRN);
        Name=(EditText)findViewById(R.id.Name);
        CGPA=(EditText)findViewById(R.id.CGPA);
        Insert=(Button)findViewById(R.id.Insert);
        Delete=(Button)findViewById(R.id.Delete);
        Update=(Button)findViewById(R.id.Update);
        View=(Button)findViewById(R.id.View);
        ViewAll=(Button)findViewById(R.id.ViewAll);
        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);
        // Creating database and table
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rrnVARCHAR,nameVARCHAR,cgpa VARCHAR);");
    }
    public void onClick(View view)
    {
        // Inserting a record to the Student table
        if(view==Insert)
        {
            // Checking for empty fields
            if(RRN.getText().toString().trim().length()==0||
                    Name.getText().toString().trim().length()==0||
            CGPA.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES('"+RRN.getText()+"','"+Name.getText()+
                    "','"+CGPA.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        // Deleting a record from the Student table
        if(view==Delete)
        {
            // Checking for empty roll number
            if(RRN.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter RRN");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rrn='"+RRN.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student WHERE rrn='"+RRN.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid RRN");
            }
            clearText();
        }
        // Updating a record in the Student table
        if(view==Update)
        {
            // Checking for empty roll number
            if(RRN.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter RRN");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rrn='"+RRN.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='" + Name.getText() + "',marks='" + CGPA.getText() +
                        "' WHERE rollno='"+RRN.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid RRN");
            }
            clearText();
        }
        // Display a record from the Student table
        if(view==View)
        {
            // Checking for empty roll number
            if(RRN.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter RRN");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rrn='"+RRN.getText()+"'", null);
            if(c.moveToFirst())
            {
                Name.setText(c.getString(1));
                CGPA.setText(c.getString(2));
            }
            else
            {
                showMessage("Error", "Invalid RRN");
                clearText();
            }
        }
        // Displaying all the records
        if(view==ViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM student", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("RRN: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("CGPA: "+c.getString(2)+"\n\n");
            }
            showMessage("Student Details", buffer.toString());
        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        RRN.setText("");
        Name.setText("");
        CGPA.setText("");
        RRN.requestFocus();
    }
}