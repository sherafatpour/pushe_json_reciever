package sherafatpour.entekhab.notify.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import sherafatpour.entekhab.notify.R

class AddEditeNoteActivity : AppCompatActivity() {
    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var numberPickerPriority: NumberPicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.edt_text_title)
        editTextDescription = findViewById(R.id.edt_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)

        numberPickerPriority!!.minValue = 1
        numberPickerPriority!!.maxValue = 10

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        val intent = intent

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Notification"
            editTextTitle!!.setText(intent.getStringExtra(EXTRA_TITLE))
            editTextDescription!!.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            numberPickerPriority!!.value = intent.getIntExtra(EXTRA_PRIORITY, 1)

        } else {
            title = "Add Notification"

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {

        val title = editTextTitle!!.text.toString()
        val description = editTextDescription!!.text.toString()
        val priority = numberPickerPriority!!.value

        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
        }
 //send params to previous activity by intent
        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)

        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    companion object {
        val EXTRA_TITLE = "sherafatpour.entekhab.notify.EXREA_TITLE"
        val EXTRA_DESCRIPTION = "sherafatpour.entekhab.notify.EXTRA_DESCRIPTION"
        val EXTRA_PRIORITY = "sherafatpour.entekhab.notify.EXTRA_PRIORITY"
        val EXTRA_ID = "sherafatpour.entekhab.notify.EXTRA_ID"
    }
}
