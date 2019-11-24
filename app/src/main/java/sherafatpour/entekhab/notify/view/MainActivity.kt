package sherafatpour.entekhab.notify.view

import androidx.appcompat.app.AppCompatActivity

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.TextView

import co.ronash.pushe.Pushe
import kotlinx.android.synthetic.main.activity_main.*
import sherafatpour.entekhab.notify.R
import sherafatpour.entekhab.notify.adapter.PageAdapter
import sherafatpour.entekhab.notify.util.GlobalClass

class MainActivity : AppCompatActivity() {
    private lateinit var globalClass: GlobalClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        globalClass = GlobalClass()
        Pushe.initialize(this, true)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 2
        val adapter = PageAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        globalClass.statusBarColor(window)
       // changeTabsFont()
        /* FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditeNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final NotificationAdapter noteAdapter = new NotificationAdapter();
        recyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        noteViewModel.getAllNote().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notes) {
                //update RecyclerView
                Toast.makeText(MainActivity.this, "onChange", Toast.LENGTH_SHORT).show();
                noteAdapter.submitList(notes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Notification Delete", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
        noteAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Notification note) {
                Intent intent = new Intent(MainActivity.this, AddEditeNoteActivity.class);
                intent.putExtra(AddEditeNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddEditeNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddEditeNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(AddEditeNoteActivity.EXTRA_PRIORITY, note.getPriority());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });*/
        changeTabsFont()
    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val title = data!!.getStringExtra(AddEditeNoteActivity.EXTRA_TITLE)
            val description = data.getStringExtra(AddEditeNoteActivity.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddEditeNoteActivity.EXTRA_PRIORITY, 1)

            val note = Notification(title, description, priority)
            noteViewModel!!.insert(note)
            Toast.makeText(this, "Notification saved" + description!!, Toast.LENGTH_SHORT).show()

        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {

            val id = data!!.getIntExtra(AddEditeNoteActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Notification can't be update", Toast.LENGTH_SHORT).show()
                return
            }

            val title = data.getStringExtra(AddEditeNoteActivity.EXTRA_TITLE)
            val description = data.getStringExtra(AddEditeNoteActivity.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddEditeNoteActivity.EXTRA_PRIORITY, 1)
            val note = Notification(title, description, priority)
            note.id = id
            noteViewModel!!.update(note)
            Toast.makeText(this, "Notification Updated" + description!!, Toast.LENGTH_SHORT).show()


        } else {

            Toast.makeText(this, "Notification not saved", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.delete_all_note -> {
                noteViewModel!!.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        val ADD_NOTE_REQUEST = 1
        val EDIT_NOTE_REQUEST = 2
    }*/


    private fun changeTabsFont() {
        val vg = tabLayout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount
        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup
            val tabChildCount = vgTab.childCount
            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    tabViewChild.setTypeface(globalClass.getTypeFace(), Typeface.NORMAL)
                }
            }
        }
    }
}
