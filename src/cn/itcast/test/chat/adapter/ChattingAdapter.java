package cn.itcast.test.chat.adapter;

import java.util.List;

import com.example.share.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChattingAdapter extends BaseAdapter {
	protected static final String TAG = "ChattingAdapter";
	private Context context;

	private List<ChatMessage> chatMessages;	//��������
											//��������
	public ChattingAdapter(Context context, List<ChatMessage> messages) {
		super();
		this.context = context;
		this.chatMessages = messages;

	}

	@Override
	public int getCount() {					//��������Դ���ܵļ�¼��Ŀ
		return chatMessages.size();
	}

	@Override
	public Object getItem(int position) {	//���ѡ�������Դ��ĳ����Ŀ������
		return chatMessages.get(position);	
	}

	@Override
	public long getItemId(int position) {	//��ȡ����Դ�е�����ֵ
		return position;
	}

	@Override								//��ȡҪչʾ����ĿView����
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		ChatMessage message = chatMessages.get(position);
		if (convertView == null || (holder = (ViewHolder) convertView.getTag()).flag != message.getDirection()) {

			holder = new ViewHolder();
			if (message.getDirection() == ChatMessage.MESSAGE_FROM) {
				holder.flag = ChatMessage.MESSAGE_FROM;

				convertView = LayoutInflater.from(context).inflate(R.layout.chatting_item_from, null);
			} else {
				holder.flag = ChatMessage.MESSAGE_TO;
				convertView = LayoutInflater.from(context).inflate(R.layout.chatting_item_to, null);
			}

			holder.text = (TextView) convertView.findViewById(R.id.chatting_content_itv);
			convertView.setTag(holder);
		}
		holder.text.setText(message.getContent());

		return convertView;
	}
//�Ż�listview��Adapter
	static class ViewHolder {
		TextView text;
		int flag;
	}

}