package com.example.share;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.test.chat.adapter.ChatMessage;
import cn.itcast.test.chat.adapter.ChattingAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DialogActivity extends Activity {
	protected static final String TAG = "MainActivity";
	private ChattingAdapter chatHistoryAdapter;
	private List<ChatMessage> messages = new ArrayList<ChatMessage>();

	private ListView chatHistoryLv;
	private Button sendBtn;
	private EditText textEditor;
	private ImageView sendImageIv;
	private ImageView captureImageIv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//����title
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // ע��˳��
		
		setContentView(R.layout.activity_dialog);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // ע��˳��
                R.layout.title_dialog);
		
		//��dialog����ʾ������Ϣ����ʷ��dialog history��
		chatHistoryLv = (ListView) findViewById(R.id.dialog_history);
		setAdapterForThis();
		
		sendBtn = (Button) findViewById(R.id.btn_send);
		textEditor = (EditText) findViewById(R.id.text_editor);
		sendBtn.setOnClickListener(l);
		
		//���file_imgviewʵ�ֽ����ص�layout��ʾ����
		ImageView imageV = (ImageView) this.findViewById(R.id.file_imgview);
		imageV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout file_invisible_btn = (LinearLayout) findViewById(R.id.file_invisible_btn);
				file_invisible_btn.setVisibility(file_invisible_btn.VISIBLE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dialog, menu);
		return true;
	}


	// ����adapter
	private void setAdapterForThis() {
		initMessages();
		chatHistoryAdapter = new ChattingAdapter(this, messages);
		chatHistoryLv.setAdapter(chatHistoryAdapter);
	}

	// ΪlistView�������
	private void initMessages() {
		messages.add(new ChatMessage(ChatMessage.MESSAGE_FROM, "hello"));
		messages.add(new ChatMessage(ChatMessage.MESSAGE_TO, "hello"));
		messages.add(new ChatMessage(ChatMessage.MESSAGE_FROM, "welcome me blog:http://blog.csdn.net/feiyangxiaomi"));
	}

	/**
	 * ����ʱ�����
	 */
	private View.OnClickListener l = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			if (v.getId() == sendBtn.getId()) {
				String str = textEditor.getText().toString();//��ȡ��ǰ��������
				String sendStr;
				if (str != null
						&& (sendStr = str.trim().replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "")
								.replaceAll("\f", "")) != "") {
					sendMessage(sendStr);
					
				}
				textEditor.setText("");

			} else if (v.getId() == sendImageIv.getId()) {
				Intent i = new Intent();
				i.setType("image/*");
				i.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(i, Activity.DEFAULT_KEYS_SHORTCUT);
				//close a pic
			} else if (v.getId() == captureImageIv.getId()) {
				Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
			}
		}

		// ģ�ⷢ����Ϣ
		private void sendMessage(String sendStr) {
			messages.add(new ChatMessage(ChatMessage.MESSAGE_TO, sendStr));
			chatHistoryAdapter.notifyDataSetChanged();
		}
	};
}
