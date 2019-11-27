package com.example.tutorpet.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tutorpet.R
import com.example.tutorpet.ui.conversas.ConversasFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_disciplina_tutoria.*
import kotlinx.android.synthetic.main.activity_add_disciplina_tutoria.fabAdd
import kotlinx.android.synthetic.main.fragment_tutorias.*
import org.json.JSONObject
import java.util.*

class AddDisciplinaTutoria : AppCompatActivity() {

    var listTutorias = arrayListOf<String>()

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_disciplina_tutoria)

        this.lerDados()

        supportActionBar?.title = "Minhas Disciplinas de Tutoria"



        var pref = getSharedPreferences("configuracoes",0)

        var ehpetiano = pref?.getBoolean("ehpetiano", false)


        if( ehpetiano == false ){
            fabAdd.visibility = View.INVISIBLE
            supportActionBar?.title = "Disciplinas disponiveis"

            listView.setOnItemClickListener{_,_,_,_ ->
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }else{

            fabAdd.setOnClickListener {
                startActivity(Intent(this, NovaDisciplina::class.java ))
            }

        }
    }

    fun lerDados(){

        listTutorias.clear()

        /*
        *    var pref = activity?.getSharedPreferences("configuracoes",0)

        var ehpetiano = pref?.getBoolean("ehpetiano", false)


        if( ehpetiano == false ){
            fabAdd.visibility = View.INVISIBLE
        }

        *
        * */

        db.collection("monitoriasAgendadas")
            .get()
            .addOnSuccessListener {result ->
                for(doc in result){
                    var jo = JSONObject(doc.data)
                    var monitoria = jo.getString("nome")
                    listTutorias.add(monitoria)
                }


                listView.adapter =
                    ArrayAdapter(this,android.R.layout.simple_list_item_1,
                        listTutorias)

            }
            .addOnFailureListener{e->
                Toast.makeText(this, "Erro: $e", Toast.LENGTH_LONG).show()
            }
    }


    /*
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
    * */

    fun onTimeSet( calendar : Calendar){

    }

    fun startAlarm( calendar : Calendar){
        /**
        var alarmManage = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        intent = Intent(this, AlertReceiver)
        var pendingIntent = PendingIntent.getBroadcast((this, 1 , intent, 0);

        if ( calendar.before(Calendar.getInstance())){

        }*/
    }



}
