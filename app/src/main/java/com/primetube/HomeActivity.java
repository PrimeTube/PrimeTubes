package com.primetube;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.provider.MediaStore;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class HomeActivity extends AppCompatActivity {
	
	public final int REQ_CD_IMAGE_PICK = 101;
	public final int REQ_CD_CAMERA = 102;
	public final int REQ_CD_VIDEO_PICK = 103;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String profile = "";
	private String name = "";
	private String email = "";
	private double image = 0;
	private String imagePath = "";
	private String imageName = "";
	private HashMap<String, Object> mapp = new HashMap<>();
	private String url = "";
	private String your_version = "";
	private String latest_version = "";
	private String package_name = "";
	private String video = "";
	private String videoPath = "";
	private String videoName = "";
	private double videos = 0;
	private String image_Url = "";
	private String videoUrl = "";
	private String videoStart = "";
	private String videouid = "";
	private String string = "";
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
	
	private LinearLayout actionbar_linear;
	private LinearLayout main_linear;
	private LinearLayout progreesbar_linear;
	private LinearLayout bottombar_linear;
	private ImageView imageview7;
	private LinearLayout linear66;
	private ImageView imageview2;
	private LinearLayout video_linear;
	private LinearLayout upload_linear;
	private ProgressBar progressbar6;
	private ListView listview1;
	private ScrollView vscroll2;
	private TextView image_title_textview;
	private LinearLayout linear64;
	private LinearLayout upload2linear;
	private LinearLayout linear60;
	private LinearLayout linear62;
	private LinearLayout linear59;
	private LinearLayout edittext_linear;
	private Button pick_image;
	private Button pick_videos;
	private LinearLayout linear70;
	private EditText edittext_title;
	private LinearLayout linear61;
	private EditText edittext_description;
	private EditText edittext1;
	private LinearLayout linear72;
	private LinearLayout linear73;
	private LinearLayout videopic_linear;
	private LinearLayout linear69;
	private TextView video_title_textview;
	private Button upload;
	private CheckBox checkbox2;
	private LinearLayout linear1;
	private ImageView image_add_view;
	private ProgressBar progressbar3;
	private LinearLayout up_dw_porgres_linear;
	private TextView loading_number;
	private ImageView image_add_ok;
	private ImageView imageview3;
	private ImageView imageview1;
	private ImageView imageview5;
	
	private Intent intent = new Intent();
	private AlertDialog.Builder Dialog;
	private Intent intent3 = new Intent();
	private Intent intent4 = new Intent();
	private TimerTask Timer;
	private Intent intent5 = new Intent();
	private AlertDialog.Builder delete_dialog;
	private Intent intent_code = new Intent();
	private DatabaseReference FirebaseDB = _firebase.getReference("FirebaseDB");
	private ChildEventListener _FirebaseDB_child_listener;
	private StorageReference storage = _firebase_storage.getReference("storage");
	private OnCompleteListener<Uri> _storage_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _storage_download_success_listener;
	private OnSuccessListener _storage_delete_success_listener;
	private OnProgressListener _storage_upload_progress_listener;
	private OnProgressListener _storage_download_progress_listener;
	private OnFailureListener _storage_failure_listener;
	
	private StorageReference firebasevideo = _firebase_storage.getReference("firebase_Video");
	private OnCompleteListener<Uri> _firebasevideo_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _firebasevideo_download_success_listener;
	private OnSuccessListener _firebasevideo_delete_success_listener;
	private OnProgressListener _firebasevideo_upload_progress_listener;
	private OnProgressListener _firebasevideo_download_progress_listener;
	private OnFailureListener _firebasevideo_failure_listener;
	
	private Intent image_pick = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder Download_dialog;
	private TimerTask Time;
	private TimerTask time2;
	private Intent intent_like_cmt = new Intent();
	private Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_camera;
	private Intent video_pick = new Intent(Intent.ACTION_GET_CONTENT);
	private RequestNetwork internet;
	private RequestNetwork.RequestListener _internet_request_listener;
	private FirebaseAuth Auth;
	private OnCompleteListener<AuthResult> _Auth_create_user_listener;
	private OnCompleteListener<AuthResult> _Auth_sign_in_listener;
	private OnCompleteListener<Void> _Auth_reset_password_listener;
	private OnCompleteListener<Void> Auth_updateEmailListener;
	private OnCompleteListener<Void> Auth_updatePasswordListener;
	private OnCompleteListener<Void> Auth_emailVerificationSentListener;
	private OnCompleteListener<Void> Auth_deleteUserListener;
	private OnCompleteListener<Void> Auth_updateProfileListener;
	private OnCompleteListener<AuthResult> Auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> Auth_googleSignInListener;
	
	private SharedPreferences transicph;
	private TimerTask time;
	private Calendar cel = Calendar.getInstance();
	private AlertDialog.Builder dialog;
	private AlertDialog.Builder back;
	private SharedPreferences nampro;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		actionbar_linear = findViewById(R.id.actionbar_linear);
		main_linear = findViewById(R.id.main_linear);
		progreesbar_linear = findViewById(R.id.progreesbar_linear);
		bottombar_linear = findViewById(R.id.bottombar_linear);
		imageview7 = findViewById(R.id.imageview7);
		linear66 = findViewById(R.id.linear66);
		imageview2 = findViewById(R.id.imageview2);
		video_linear = findViewById(R.id.video_linear);
		upload_linear = findViewById(R.id.upload_linear);
		progressbar6 = findViewById(R.id.progressbar6);
		listview1 = findViewById(R.id.listview1);
		vscroll2 = findViewById(R.id.vscroll2);
		image_title_textview = findViewById(R.id.image_title_textview);
		linear64 = findViewById(R.id.linear64);
		upload2linear = findViewById(R.id.upload2linear);
		linear60 = findViewById(R.id.linear60);
		linear62 = findViewById(R.id.linear62);
		linear59 = findViewById(R.id.linear59);
		edittext_linear = findViewById(R.id.edittext_linear);
		pick_image = findViewById(R.id.pick_image);
		pick_videos = findViewById(R.id.pick_videos);
		linear70 = findViewById(R.id.linear70);
		edittext_title = findViewById(R.id.edittext_title);
		linear61 = findViewById(R.id.linear61);
		edittext_description = findViewById(R.id.edittext_description);
		edittext1 = findViewById(R.id.edittext1);
		linear72 = findViewById(R.id.linear72);
		linear73 = findViewById(R.id.linear73);
		videopic_linear = findViewById(R.id.videopic_linear);
		linear69 = findViewById(R.id.linear69);
		video_title_textview = findViewById(R.id.video_title_textview);
		upload = findViewById(R.id.upload);
		checkbox2 = findViewById(R.id.checkbox2);
		linear1 = findViewById(R.id.linear1);
		image_add_view = findViewById(R.id.image_add_view);
		progressbar3 = findViewById(R.id.progressbar3);
		up_dw_porgres_linear = findViewById(R.id.up_dw_porgres_linear);
		loading_number = findViewById(R.id.loading_number);
		image_add_ok = findViewById(R.id.image_add_ok);
		imageview3 = findViewById(R.id.imageview3);
		imageview1 = findViewById(R.id.imageview1);
		imageview5 = findViewById(R.id.imageview5);
		Dialog = new AlertDialog.Builder(this);
		delete_dialog = new AlertDialog.Builder(this);
		image_pick.setType("image/*");
		image_pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		Download_dialog = new AlertDialog.Builder(this);
		_file_camera = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_camera;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_camera = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_camera);
		} else {
			_uri_camera = Uri.fromFile(_file_camera);
		}
		camera.putExtra(MediaStore.EXTRA_OUTPUT, _uri_camera);
		camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		video_pick.setType("video/*");
		video_pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		internet = new RequestNetwork(this);
		Auth = FirebaseAuth.getInstance();
		transicph = getSharedPreferences("transicph", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		back = new AlertDialog.Builder(this);
		nampro = getSharedPreferences("nampro", Activity.MODE_PRIVATE);
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Dialog.setTitle("Your all activity record");
				Dialog.setMessage("\n\nEnter admin password if you are");
				final EditText input = new EditText(HomeActivity.this);
				LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				input.setLayoutParams(lpar);
				Dialog.setView(input);
				Dialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						String string = input.getText() + "";
						if (string.equals("rishuk")) {
							intent_like_cmt.setClass(getApplicationContext(), AdminActivity.class);
							intent_like_cmt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent_like_cmt);
						} else {
							
						}
					}
				});
				Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				Dialog.create().show();
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), SearchActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		pick_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(image_pick, REQ_CD_IMAGE_PICK);
			}
		});
		
		pick_videos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(video_pick, REQ_CD_VIDEO_PICK);
			}
		});
		
		upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (image == 0) {
					SketchwareUtil.showMessage(getApplicationContext(), "select your image");
				} else {
					if (videos == 0) {
						SketchwareUtil.showMessage(getApplicationContext(), "select your video");
					} else {
						if (edittext_title.getText().toString().equals("")) {
							SketchwareUtil.showMessage(getApplicationContext(), "Enter Your Video Title");
						} else {
							if (edittext1.getText().toString().equals("")) {
								SketchwareUtil.showMessage(getApplicationContext(), "Enter your name");
							} else {
								if (edittext_description.getText().toString().equals("")) {
									SketchwareUtil.showMessage(getApplicationContext(), "Enter Your Video Description");
								} else {
									storage.child(imageName).putFile(Uri.fromFile(new File(imagePath))).addOnFailureListener(_storage_failure_listener).addOnProgressListener(_storage_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
										@Override
										public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
											return storage.child(imageName).getDownloadUrl();
										}}).addOnCompleteListener(_storage_upload_success_listener);
									upload.setText("Please Wait....");
									upload.setEnabled(false);
								}
							}
						}
					}
				}
			}
		});
		
		checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					upload.setVisibility(View.VISIBLE);
				} else {
					upload.setVisibility(View.GONE);
				}
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				video_linear.setVisibility(View.VISIBLE);
				upload_linear.setVisibility(View.GONE);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Dialog.setTitle("Video Uploading way");
				Dialog.setMessage("make sure your video in Landscape form otherwise your video will remove from PLAYit");
				Dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						video_linear.setVisibility(View.GONE);
						upload_linear.setVisibility(View.VISIBLE);
					}
				});
				Dialog.create().show();
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent5.setClass(getApplicationContext(), ProfileActivity.class);
				intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent5);
			}
		});
		
		_FirebaseDB_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				progressbar6.setVisibility(View.VISIBLE);
				FirebaseDB.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.reverse(maplist);
						progressbar6.setVisibility(View.GONE);
						list.add(_childKey);
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				FirebaseDB.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.reverse(maplist);
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				FirebaseDB.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.reverse(maplist);
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		FirebaseDB.addChildEventListener(_FirebaseDB_child_listener);
		
		_storage_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progreesbar_linear.setVisibility(View.VISIBLE);
				image_add_ok.setVisibility(View.GONE);
				progressbar3.setVisibility(View.VISIBLE);
				loading_number.setText("Thumbnail_Uploading..".concat(String.valueOf((long)(_progressValue)).concat("%")));
				upload.setEnabled(false);
			}
		};
		
		_storage_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progreesbar_linear.setVisibility(View.VISIBLE);
				image_add_ok.setVisibility(View.GONE);
				progressbar3.setVisibility(View.VISIBLE);
				loading_number.setText("Thumbnail_Downloading..".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
		};
		
		_storage_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				image_add_view.setVisibility(View.VISIBLE);
				progreesbar_linear.setVisibility(View.VISIBLE);
				image_add_ok.setVisibility(View.GONE);
				progressbar3.setVisibility(View.GONE);
				image_Url = _downloadUrl;
				firebasevideo.child(videoName).putFile(Uri.fromFile(new File(videoPath))).addOnFailureListener(_firebasevideo_failure_listener).addOnProgressListener(_firebasevideo_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return firebasevideo.child(videoName).getDownloadUrl();
					}}).addOnCompleteListener(_firebasevideo_upload_success_listener);
				image = 0;
				upload.setEnabled(true);
			}
		};
		
		_storage_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), "Thumbnail Download Successful");
				image_add_ok.setVisibility(View.VISIBLE);
				progressbar3.setVisibility(View.GONE);
				Timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progreesbar_linear.setVisibility(View.GONE);
							}
						});
					}
				};
				_timer.schedule(Timer, (int)(1000));
			}
		};
		
		_storage_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				SketchwareUtil.showMessage(getApplicationContext(), "delete successful");
			}
		};
		
		_storage_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		_firebasevideo_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progreesbar_linear.setVisibility(View.VISIBLE);
				image_add_ok.setVisibility(View.GONE);
				progressbar3.setVisibility(View.VISIBLE);
				loading_number.setText("Video_Uploading..".concat(String.valueOf((long)(_progressValue)).concat("%")));
				upload.setEnabled(false);
			}
		};
		
		_firebasevideo_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progreesbar_linear.setVisibility(View.VISIBLE);
				image_add_ok.setVisibility(View.GONE);
				progressbar3.setVisibility(View.VISIBLE);
				loading_number.setText("Video Downloading..".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
		};
		
		_firebasevideo_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				videoUrl = _downloadUrl;
				videos = 0;
				image = 0;
				upload.setEnabled(true);
				cel = Calendar.getInstance();
				mapp = new HashMap<>();
				mapp.put("time", new SimpleDateFormat("hh:mm a dd:yyyy").format(cel.getTime()));
				mapp.put("title", edittext_title.getText().toString());
				mapp.put("description", edittext_description.getText().toString());
				mapp.put("name", nampro.getString("name", ""));
				mapp.put("Verification", "false");
				mapp.put("image", image_Url);
				mapp.put("video", videoUrl);
				mapp.put("views", "0");
				FirebaseDB.push().updateChildren(mapp);
				mapp.clear();
				progressbar3.setVisibility(View.GONE);
				edittext_title.setText("");
				edittext_description.setText("");
				image_add_view.setVisibility(View.GONE);
				video_title_textview.setVisibility(View.GONE);
				image_title_textview.setVisibility(View.GONE);
				image_add_ok.setVisibility(View.VISIBLE);
				upload.setText("UPLOAD");
				videopic_linear.setVisibility(View.GONE);
				Time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progreesbar_linear.setVisibility(View.GONE);
								video_linear.setVisibility(View.VISIBLE);
								upload_linear.setVisibility(View.GONE);
								SketchwareUtil.showMessage(getApplicationContext(), "upload successful");
							}
						});
					}
				};
				_timer.schedule(Time, (int)(1000));
			}
		};
		
		_firebasevideo_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), "video Download Successful");
				image_add_ok.setVisibility(View.VISIBLE);
				progressbar3.setVisibility(View.GONE);
				Timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progreesbar_linear.setVisibility(View.GONE);
							}
						});
					}
				};
				_timer.schedule(Timer, (int)(1000));
			}
		};
		
		_firebasevideo_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				SketchwareUtil.showMessage(getApplicationContext(), "Delete Successful");
			}
		};
		
		_firebasevideo_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		_internet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), "No Internet Connection...⚠️");
			}
		};
		
		Auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		Auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_Auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_Auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_Auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		linear73.setVisibility(View.GONE);
		edittext1.setVisibility(View.GONE);
		image = 0;
		videos = 0;
		_videopick();
		progreesbar_linear.setVisibility(View.GONE);
		image_add_view.setVisibility(View.GONE);
		image_title_textview.setVisibility(View.GONE);
		video_title_textview.setVisibility(View.GONE);
		videopic_linear.setVisibility(View.GONE);
		_card_style(actionbar_linear, 20, 25, "#FFFFFF");
		_card_style(bottombar_linear, 20, 25, "#FFFFFF");
		_card_style(edittext_title, 20, 25, "#E0E0E0");
		_card_style(edittext_description, 20, 25, "#E0E0E0");
		_card_style(pick_image, 20, 25, "#FA6800");
		_card_style(pick_videos, 20, 25, "#00ABA9");
		_card_style(upload, 20, 25, "#008A00");
		internet.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _internet_request_listener);
		edittext1.setText(nampro.getString("profile", ""));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMAGE_PICK:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				image = 1;
				image_add_view.setVisibility(View.VISIBLE);
				imagePath = _filePath.get((int)(0));
				imageName = Uri.parse(imagePath).getLastPathSegment();
				image_add_view.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(imagePath, 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_VIDEO_PICK:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				videos = 1;
				videoPath = _filePath.get((int)(0));
				videoName = Uri.parse(videoPath).getLastPathSegment();
				videoStart = _filePath.get((int)(0));
				vidview.setVideoURI(Uri.parse(videoStart));
				vidview.start();
				videopic_linear.setVisibility(View.VISIBLE);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		back.setIcon(R.drawable.img);
		
		back.setTitle("exit !");
		back.setMessage("do you want to close");
		back.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		back.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		back.create().show();
	}
	public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _videopick() {
		vidview = new VideoView(this);
		vidview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		linear1.addView(vidview);
		mediaControls = new MediaController(this); mediaControls.setAnchorView(vidview); vidview.setMediaController(mediaControls);
	}
	
	
	public void _mediaplayer() {
		
	} VideoView vidview;
	MediaController mediaControls; {
	}
	
	
	public void _Glide(final ImageView _img, final String _url) {
		
	}
	
	
	public void _borderline(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		ab.setColor(Color.parseColor(_color1));
		ab.setCornerRadius((int) _round);
		ab.setStroke((int) _border,
		Color.parseColor(_color2));
		_view.setBackground(ab);
		
		
		
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.ad_screen_photo, null);
			}
			
			final LinearLayout main_linear = _view.findViewById(R.id.main_linear);
			final LinearLayout linear28 = _view.findViewById(R.id.linear28);
			final LinearLayout image_linear = _view.findViewById(R.id.image_linear);
			final LinearLayout linear_delete = _view.findViewById(R.id.linear_delete);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear26 = _view.findViewById(R.id.linear26);
			final ImageView imageview_thumbnail = _view.findViewById(R.id.imageview_thumbnail);
			final TextView textview_title = _view.findViewById(R.id.textview_title);
			final TextView name = _view.findViewById(R.id.name);
			final LinearLayout linear27 = _view.findViewById(R.id.linear27);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			_borderline(main_linear, "#FFFFFF", 5, "#000000", 30);
			_borderline(linear27, "#FFFFFF", 3, "#000000", 30);
			textview_title.setText(maplist.get((int)_position).get("title").toString());
			name.setText(maplist.get((int)_position).get("name").toString());
			if (maplist.get((int)_position).get("Verification").toString().equals("true")) {
				textview2.setVisibility(View.VISIBLE);
				imageview2.setVisibility(View.VISIBLE);
			} else {
				if (maplist.get((int)_position).get("Verification").toString().equals("false")) {
					textview2.setVisibility(View.GONE);
					imageview2.setVisibility(View.GONE);
				}
			}
			if (_data.get((int)_position).containsKey("views")) {
				
			}
			if (maplist.get((int)_position).containsKey("image")) {
				imageview_thumbnail.setVisibility(View.VISIBLE);
				Glide.with(getApplicationContext()).load(Uri.parse(maplist.get((int)_position).get("image").toString())).into(imageview_thumbnail);
			} else {
				imageview_thumbnail.setVisibility(View.GONE);
			}
			imageview_thumbnail.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent_code.setClass(getApplicationContext(), VideoShortActivity.class);
					intent_code.putExtra("video", maplist.get((int)_position).get("video").toString());
					intent_code.putExtra("title", maplist.get((int)_position).get("title").toString());
					intent_code.putExtra("description", maplist.get((int)_position).get("description").toString());
					intent_code.putExtra("name", maplist.get((int)_position).get("name").toString());
					intent_code.putExtra("time", maplist.get((int)_position).get("time").toString());
					intent_code.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent_code);
				}
			});
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}