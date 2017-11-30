package adapter;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.robot.R;

import bean.ChatMessage;
import bean.ChatMessage.Type;

import activity.webActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ChatMessageAdapter extends BaseAdapter {
	private List<ChatMessage> mDatas;
	private LayoutInflater mInflater;
	private Context context;
	boolean flag = false;
	WebView webview;
	public ChatMessageAdapter(Context context,List<ChatMessage> mDatas,WebView webview) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
		this.context = context;
//		this.webview = webview;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		ChatMessage chatMessage = mDatas.get(position);
		if (chatMessage.getType() == Type.INCOMING) {
			return 0;
		}else {
			return 1;
		}
		
	}
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position,View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		final ChatMessage chatMessage = mDatas.get(position);
		 ViewHolder viewHolder = null;
		
		if (convertView==null) {
			viewHolder = new ViewHolder();
		
			//ͨ��
			if (getItemViewType(position)==0){
				
				convertView = mInflater.inflate(R.layout.left, parent,false);
				
				viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_time1);
				viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_left_msg);
				viewHolder.mUrl = (TextView) convertView.findViewById(R.id.id_left_url);
				
					
			}else {			
				convertView = mInflater.inflate(R.layout.right, parent,false);
				viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_time2);
				viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_right_msg);
				
			}
			
			
			convertView.setTag(viewHolder);
		}
			else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		viewHolder.mDate.setText(df.format(chatMessage.getDate()));
		viewHolder.mMsg.setText(chatMessage.getMsg());

		if (getItemViewType(position)==0&&mDatas.get(position).getUrl()!=null){
			viewHolder.mUrl.setText(Html.fromHtml("<u>点击查看</u>"));
			viewHolder.mUrl.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	                intent.setData(Uri.parse(chatMessage.getUrl()));
	                context.startActivity(intent);
	 
//	                Intent intent = new Intent(context,webActivity.class);
//					 intent.putExtra("url",chatMessage.getUrl());
//					 context.startActivity(intent);

	            	
	            }
	        });
		}
		if (getItemViewType(position)==0&&mDatas.get(position).getUrl()==null) {
			viewHolder.mUrl.setText("");
			
		}
		
		
		

	
	
		
	
		
		return convertView;
	}
	class ViewHolder{
		TextView mDate;
		TextView mMsg;
		TextView mUrl;
		WebView webView;
	
		
	}
	class ViewHolder2{

		WebView webView;
	
		
	}

}
