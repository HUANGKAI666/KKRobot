package activity;

import com.example.robot.R;

import android.app.Activity;
import android.app.ProgressDialog;

import android.os.Bundle;

import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class webActivity extends Activity {
	private WebView webview;
	private ProgressDialog dialog;
	private float startX;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 setContentView(R.layout.webview);
		init();
	}
	private void init() {
		
		webview =(WebView) findViewById(R.id.WebView1);
		final String url = getIntent().getStringExtra("url");   
		
		webview.loadUrl(url);
		
	
		
		                        
		webview.setWebViewClient(new WebViewClient());
//		{
//			@Override
//			public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
//				// TODO Auto-generated method stub
//				view.loadUrl(url);
//				return(true);
//			}
//		});
		
		WebSettings setting =webview.getSettings();
		setting.setJavaScriptEnabled(true);
		webview.setWebChromeClient(new WebChromeClient()
		{
			public void onProgressChanged(WebView view, int newProgress) {
				
				if (newProgress==100) {
					closeDialog();
					
				}
				else {

					openDialog(newProgress);
					
				}
				
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if (dialog==null) {
					dialog=new ProgressDialog(webActivity.this);
					dialog.setTitle("加载中……");
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.show();
					
				}
				else {
					dialog.setProgress(newProgress);}}
			private void closeDialog() {
				// TODO Auto-generated method stub
				if (dialog!=null&&dialog.isShowing()) {
					dialog.dismiss();
					dialog=null;
					}}});}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (webview.canGoBack()) {
			webview.goBack();
		} else {
			System.exit(0);

		}
	}
	
	
	
}

	
