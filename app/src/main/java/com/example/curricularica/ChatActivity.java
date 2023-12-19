package com.example.curricularica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.VolleyError;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private Button buttonSend;
    private ListView chatListView;
    private ArrayAdapter<String> chatAdapter;
    private ArrayList<String> chatMessages;
    private ChatBotApiClient chatBotApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        chatListView = findViewById(R.id.chatListView);

        chatMessages = new ArrayList<>();
        chatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatMessages);
        chatListView.setAdapter(chatAdapter);

        // Initialize ChatGPTApiClient
        chatBotApiClient = new ChatBotApiClient(this);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    editTextMessage.setText("");
                }
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.copilot);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.timetable) {
                Intent timetableIntent = new Intent(this, MainActivity.class);
                startActivity(timetableIntent);
                return true;
            } else if (item.getItemId() == R.id.deadlines) {
                Intent deadlinesIntent = new Intent(this, DeadlinesActivity.class);
//                deadlinesIntent.putExtra("TIMETABLE_LIST", (Serializable) courseModels);
//                deadlinesIntent.putExtra("TIMETABLE_LIST_2", (Serializable) courseModels);

                startActivity(deadlinesIntent);
                return true;
            } else if (item.getItemId() == R.id.copilot) {
                Intent copilotIntent = new Intent(this, ChatActivity.class);
                startActivity(copilotIntent);
                return true;
            }
            return true;
        });
    }

    private void sendMessage(String message) {
        // Add user message to chat
        chatMessages.add("You: " + message);
        chatAdapter.notifyDataSetChanged();

        // Use ChatGPTApiClient to send the message
        chatBotApiClient.sendMessage(message, new ChatBotApiClient.Callback() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Extracting the response text
                    String responseText = response.getJSONArray("choices")
                            .getJSONObject(0)
                            .getString("text").trim();

                    // Display the response in the chat
                    receiveMessage("Chatbot: " + responseText);
                } catch (JSONException e) {
                    e.printStackTrace();
                    receiveMessage("Chatbot: Error parsing response.");
                }
            }

            @Override
            public void onError(VolleyError error) {
                // Extracting error details
                String statusCode = String.valueOf(error.networkResponse != null ? error.networkResponse.statusCode : "No Status Code");
                String message = "Curricularica Copilot: Error - " + statusCode;

                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data, "utf-8");
                        JSONObject data = new JSONObject(responseBody);
                        String errorDetail = data.optString("error", "No error message");
                        message += ", Details: " + errorDetail;
                    } catch (Exception e) {
                        e.printStackTrace();
                        message += ", Error parsing error response.";
                    }
                }

                receiveMessage(message);
            }
        });
    }

    private void receiveMessage(String message) {
        runOnUiThread(() -> {
            chatMessages.add(message);
            chatAdapter.notifyDataSetChanged();
        });
    }
}
