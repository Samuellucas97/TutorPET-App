package com.example.tutorpet.ui.forum

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorpet.App
import com.example.tutorpet.adapter.MessageAdapter
import com.example.tutorpet.model.Message
import com.example.tutorpet.service.ChatService
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.fragment_forum.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ForumFragment : Fragment() {


    private val TAG = "ChatActivity"
    private var adapter: MessageAdapter = MessageAdapter(context)

    private val pusherAppKey = "149cc406e138ed3086f2"
    private val pusherAppCluster = "us2"
//
//    private lateinit var contexto: Context
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        this.contexto = context
//    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(com.example.tutorpet.R.layout.fragment_forum, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        messageList.layoutManager = LinearLayoutManager(context)
        messageList.adapter = adapter


        btnSend.setOnClickListener {
            if(txtMessage.text.isNotEmpty()) {
                val message = Message(
                    App.user,
                    txtMessage.text.toString(),
                    Calendar.getInstance().timeInMillis
                )

                val call = ChatService.create().postMessage(message)

                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (!response.isSuccessful) {
                            Log.e(TAG, response.code().toString());
                            Toast.makeText(activity,"Response was not successful", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {

                        Log.e(TAG, t.toString());
                        Toast.makeText(activity,"Error when calling the service", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(activity,"Message should not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        setupPusher()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true


    }


    private fun setupPusher() {
        val options = PusherOptions()
        options.setCluster(pusherAppCluster)

        val pusher = Pusher(pusherAppKey, options)
        val channel = pusher.subscribe("chat")

        channel.bind("new_message") { channelName, eventName, data ->
            val jsonObject = JSONObject(data)

            val message = Message(
                jsonObject["user"].toString(),
                jsonObject["message"].toString(),
                jsonObject["time"].toString().toLong()
            )

            adapter.addMessage(message)
            // scroll the RecyclerView to the last added element
            messageList.scrollToPosition(adapter.itemCount - 1);

        }

        pusher.connect()
    }




}
